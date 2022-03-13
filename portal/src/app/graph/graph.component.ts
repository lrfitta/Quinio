import { Component, Input, OnInit } from '@angular/core';
import { Graph } from '../model/graph';

import { ChartConfiguration, ChartData, ChartDataset, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-graph',
  templateUrl: './graph.component.html',
  styleUrls: ['./graph.component.css']
})
export class GraphComponent implements OnInit {

  @Input() 
  public set weekly(datos : Graph[]) {
    this.barChartDataWeekly = [];
    this.labelWeekly = [];
    let data : number[] = []
    for (const index in datos) {
      this.barChartDataWeekly.push({ data: [datos[index].value], label: datos[index].label });
    }
    this.labelWeekly.push("Bonificacion");

  }
  

  @Input() set daily(datos : Graph[]) {
    this.barChartDataDaily = [];
    this.labelDaily = [];
    let data : number[] = []
    for (const index in datos) {
      data.push(datos[index].value)
      this.labelDaily.push(datos[index].label);
    }
    this.barChartDataDaily.push({ data: data, label: "Transacciones de Bonificacion", pointBackgroundColor: 'rgba(148,159,177,1)', borderColor:"green", backgroundColor : 'rgba(0, 255, 0, 0.3)'});
  }

  barChartDataWeekly: ChartDataset[] = [];
  labelWeekly : string[] = [];

  barChartDataDaily: ChartDataset[] = [];
  labelDaily : string[] = [];

  constructor() { }

  ngOnInit(): void {
  }

  barChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    scales: {
      x: {},
      y: {
        min: 0
      }
    },
    plugins: {
      legend: {
        display: true,
      }
    }
  };
}
