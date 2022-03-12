import { LiveAnnouncer } from '@angular/cdk/a11y';
import { Component, ViewChild } from '@angular/core';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
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
  afterButton : boolean = true;

  daily : Graph[] = []
  weekly : Graph[] = []
  
  constructor(private service : TransactionService, private _liveAnnouncer: LiveAnnouncer) {
    this.generateReport();
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

    this.generateReport()
  }

  onlyNumber(event : any) {
    return (event.charCode >= 48 && event.charCode <= 57)
  }

  generateReport() {
    this.service.getReport(this.page).subscribe(report => {
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
        alert(report.codeError + " - " + report.description);
      }
    });
  }
}
