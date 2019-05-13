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
    this.weekYieldOption = {
      xAxis: {
        type: 'category',
        data: ['2019-05-06', '2019-05-07', '2019-05-08', '2019-05-09', '2019-05-10', '2019-05-11', '2019-05-12']
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        type: 'bar'
      }]
    };
    this.weekProfileOption = {
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      series: [{
        name: '占比',
        type: 'pie',
        data: [
          { value: 10, name: '成功' },
          { value: 2, name: '失败' }
        ].sort(function (a, b) { return a.value - b.value; }),
      }]
    }
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

}
