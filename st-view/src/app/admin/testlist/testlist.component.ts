import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { formatDate } from '@angular/common';
import { FormControl, FormGroup } from '@angular/forms';
import { DomSanitizer } from '@angular/platform-browser';

import { NzMessageService, NzModalService } from 'ng-zorro-antd';

import { saveAs } from 'file-saver/dist/FileSaver';

// const FileSaver = require('angular-file-saver/dist/angular-file-saver.js');

// const fileSaver = new FileSaver();

@Component({
  selector: 'app-testlist',
  templateUrl: './testlist.component.html',
  styleUrls: ['./testlist.component.css']
})
export class TestlistComponent implements OnInit {

  /**表单是否加状态 */
  tableIsLoading: boolean = false;

  /**搜索表单模型 */
  searchForm: FormGroup = new FormGroup({
    serialNumber: new FormControl(),
    devicesModel: new FormControl(),
    userCode: new FormControl(),
    dateRange: new FormControl()
  });

  /**查询表单是否展开 */
  isCollapse = false;

  /**当前列是否展开 */
  mapOfExpandData: { [key: string]: boolean } = {};

  /**当前页 */
  current: number = 1;

  /**每页显示数量 */
  size: number = 10;

  /**总数 */
  total: number = 0;

  /**数据列表 */
  listData: object = [];

  /**时间范围 */
  dateRange = [];

  constructor(private http: HttpClient, private message: NzMessageService, private modalService: NzModalService, private sanitizer: DomSanitizer) { }

  /**初始化 */
  ngOnInit(): void {
    this.page(null);
  }

  /**分页获取数据 */
  page(params: any): void {
    this.tableIsLoading = true;
    this.http.get('/api/devices/page', {
      params: params
    }).subscribe((data: any) => {
      this.tableIsLoading = false;
      if (data.result === 1) {
        this.listData = data.data.records;
        this.current = data.data.current;
        this.size = data.data.size;
        this.total = data.data.total;
      } else {
        this.message.create('error', data.message);
      }
    });
  }

  /**换页 */
  pageIndexChange(e: number): void {
    let params = this.createParams();
    params.current = e;
    this.page(params);
  }

  /**下载Excel */
  generateExcel(): void {
    this.modalService.confirm({
      nzTitle: '提示',
      nzContent: '生成Excel必须在搜索框指定数据的时间范围，可以选择设备型号或者工号。',
      nzOnOk: () => {
        let params = this.createParams();
        if (params.startDate == null || params.endDate == null) {
          this.message.create('error', '必需在搜索框选择一个时间范围');
          return;
        }
        this.http.get('/api/devices/generateExcel1', {
          params: params,
          responseType: "blob"
        }).subscribe((data: Blob) => {
          saveAs(data, 'baobiao.xlsx');
        });
      }
    });
  }

  /**提交查询表单 */
  submitSearch(): void {
    this.page(this.createParams());
  }

  /**重置搜索表单 */
  resetForm(): void {
    this.searchForm.reset();
  }

  /**控制搜索框闭合 */
  toggleCollapse(): void {
    this.isCollapse = !this.isCollapse;
  }

  /**创建查询参数 */
  createParams(): any {
    let params: any = {};
    if (this.searchForm.value.serialNumber != null && this.searchForm.value.serialNumber != '') {
      params.serialNumber = this.searchForm.value.serialNumber
    }
    if (this.searchForm.value.devicesModel != null && this.searchForm.value.devicesModel != '') {
      params.devicesModel = this.searchForm.value.devicesModel
    }
    if (this.searchForm.value.userCode != null && this.searchForm.value.userCode != '') {
      params.userCode = this.searchForm.value.userCode
    }
    if (this.searchForm.value.dateRange != null) {
      console.log(this.searchForm.value.dateRange);
      if (this.searchForm.value.dateRange.length > 0) {
        params.startDate = formatDate(this.searchForm.value.dateRange[0], 'yyyy-MM-dd HH:mm:ss', 'zh-Hans');
        params.endDate = formatDate(this.searchForm.value.dateRange[1], 'yyyy-MM-dd HH:mm:ss', 'zh-Hans');
      }
    }
    return params;
  }
}
