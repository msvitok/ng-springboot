import {Component, ViewChild} from '@angular/core';
import {CountryVatService} from "./api/country-vat.service";
import {CountryVatModel} from "./models/country-vat.model";
import {tap} from "rxjs/operators";

import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from "@angular/material";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  @ViewChild(MatSort, {static: true}) matSort: MatSort;

  initSort: string = "asc";

  dataSource: MatTableDataSource<any>;
  displayedColumns: string[] = ['code', 'countryCode', 'name', 'effectiveFrom', 'standardVat'];

  constructor(private countryVatService: CountryVatService) {
    this.updateData(this.initSort, '');
  }

  onSubmit(sort, limit) {
    this.updateData(sort, limit);
  }

  private updateData(sort: string, limit: number|string): void {
    this.countryVatService.countryVats(sort == "asc", limit)
      .pipe(
        tap((countryVatModels: CountryVatModel[]) => {
          this.dataSource = new MatTableDataSource(countryVatModels);
          this.dataSource.sort = this.matSort;
        }))
      .subscribe();
  }
}
