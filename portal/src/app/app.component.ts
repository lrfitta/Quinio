import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import * as moment from 'moment';
import { MessageComponent } from './message/message.component';
import { Graph } from './model/graph';
import { Report } from './model/report';
import { TransactionService } from './service/transaction.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  @ViewChild(MatSort) sort: MatSort = new MatSort();

  page : number = 0;
  numberOfPage : number = 0;
  title = 'portal';
  displayedColumns: string[] = ['startWeek', 'numberWeek', 'bonusTransaction', 'sales', 'bonusAmount', 'redemptionTransaction', 
                                'redeemedAmount', 'expireTransaction', 'expireAmount', 'availableBalance'];
  dataSource : MatTableDataSource<Report> = new MatTableDataSource();

  beforeButton : boolean = false;
  afterButton : boolean = false;

  daily : Graph[] = []
  weekly : Graph[] = []

  range = new FormGroup({
    start: new FormControl("",Validators.required),
    end: new FormControl("",Validators.required),
  });

  public showOverlay = false;
  
  constructor(private service : TransactionService, private _liveAnnouncer: LiveAnnouncer, private dialog: MatDialog) {
  }

  announceSortChange(sortState : any) {
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }
  public doFilter = (event : any) => {
    let value = event.target.value
    this.dataSource.filter = value.trim().toLocaleLowerCase();
  }

  public paginator(nextPage: boolean) {
    if(this.page == 0) {
      this.beforeButton = false;
      return;
    }
    if(this.page + 1 == this.numberOfPage) {
      this.afterButton = false;
      return;
    }

    if(nextPage) {
      this.page += 1;
    } else {
      this.page -= 1;
    }

    this.find()
  }

  onlyNumber(event : any) {
    return (event.charCode >= 48 && event.charCode <= 57)
  }

  find() {
    
    if(!this.range.invalid) {
      let start = this.range.controls["start"].value;
      let end = this.range.controls["end"].value;
      let startDate = (moment(start)).format('DD-MM-YYYY')
      let endDate = (moment(end)).format('DD-MM-YYYY')
      this.generateReport(startDate, endDate)
    } else {
      this.showError("Las fechas ingresadas son incorrectas");
    }
  }

  showError(error:  string) {
    this.dialog.open(MessageComponent, {
      data: {
        error,
      },
    });
  }

  generateReport(startDate: string, endDate: string) {
    this.showOverlay = true;
    this.service.getReport(this.page, startDate, endDate).subscribe(report => {
      if(report.result == "SUCCESS") {
        this.dataSource = new MatTableDataSource(report.data);
        this.dataSource.sort = this.sort;
        this.numberOfPage = report.numberOfPages;
        this.daily = report.daily;
        this.weekly = report.weekly;

        if(this.page + 1 == this.numberOfPage) {
          this.afterButton = false;
        }
        
      } else {
        this.showError(report.codeError + " - " + report.description);
      }
      this.showOverlay = false;
    });
  }
}
