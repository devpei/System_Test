import { Component, OnInit } from '@angular/core';

import { Title } from '@angular/platform-browser'

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(private title: Title) { }

  ngOnInit() {
    this.title.setTitle('瑞讯科技测试系统')
  }

}
