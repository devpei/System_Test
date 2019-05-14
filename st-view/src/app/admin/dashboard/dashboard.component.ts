import 'zone.js';
import 'reflect-metadata';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { EChartOption } from 'echarts';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  /**今日总测试量 */
  todayTestNumber: number = 0;

  /**今日失败量 */
  todayFailNumber: number = 0;

  /**成功率 */
  successRate: number = 0;

  /**近7天产量数据 */
  weekYieldOption: EChartOption = null;

  /**周产量分布 */
  weekProfileOption: EChartOption = null;

  constructor(private http: HttpClient) { }

  /**初始方法 */
  ngOnInit() {
    this.getBaseStatistics();
    this.getYieldChartData('7');
    this.getProfileOption('7');
  }

  /**获取基础统计数据 */
  getBaseStatistics() {
    this.http.get('/api/dashboard/baseStatistics').subscribe((data: any) => {
      if (data.result === 1) {
        this.todayTestNumber = data.data.todayTestNumber;
        this.todayFailNumber = data.data.todayFailNumber;
        this.successRate = data.data.successRate;
      }
    });
  }

  /**获取产量统计数据 */
  getYieldChartData(cycle: string): void {
    this.http.get('/api/dashboard/chartData').subscribe((data: any) => {
      if (data.result === 1) {
        let xs = new Array();
        let ys = new Array();
        data.data.forEach((element: any) => {
          xs.push(element.key);
          ys.push(element.value);
        });
        this.weekYieldOption = {
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}台)"
          },
          xAxis: {
            type: 'category',
            data: xs
          },
          yAxis: {
            type: 'value'
          },
          series: [{
            name: '产量',
            data: ys,
            type: 'bar'
          }]
        };
      }
    });
  }

  /**获取产量分布 */
  getProfileOption(cycle: string): void {
    this.http.get('/api/dashboard/profileOption').subscribe((data: any) => {
      if (data.result === 1) {
        this.weekProfileOption = {
          tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
          },
          series: [{
            name: '占比',
            type: 'pie',
            data: data.data.sort(function (a, b) { return a.value - b.value; }),
          }]
        }
      }
    });
  }

}
