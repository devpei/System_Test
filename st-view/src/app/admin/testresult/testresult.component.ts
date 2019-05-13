import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-testresult',
  templateUrl: './testresult.component.html',
  styleUrls: ['./testresult.component.css']
})
export class TestresultComponent implements OnInit {

  /**测试结果对象 */
  testResult: any = {
    devices: {},
    testRrcords: {
      ethernet: {},
      wifi: {},
      serialPortList: [],
      usbList: []
    }
  };

  /**url参数 */
  devicesId: number = 0;

  constructor(private http: HttpClient, private route: ActivatedRoute) { }

  /**初始化方法 */
  ngOnInit() {
    this.devicesId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.getTestResult();
  }

  /**获取测试结果 */
  getTestResult(): void {
    this.http.get('/api/devices/testResult/' + this.devicesId).subscribe((data: any) => {
      if (data.result === 1) {
        this.testResult = data.data;
      }
    });
  }

}
