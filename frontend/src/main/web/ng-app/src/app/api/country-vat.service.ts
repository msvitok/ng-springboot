import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {CountryVatModel} from "../models/country-vat.model";

@Injectable({
  providedIn: 'root'
})
export class CountryVatService {

  constructor(private http: HttpClient) {}

  countryVats(asc: boolean, limit: number|string) : Observable<CountryVatModel[]> {
    return this.http.get<CountryVatModel[]>('http://localhost:8080/countryVats/list', {
        params: new HttpParams()
        .append('asc', String(asc))
        .append('limit', String(limit))
    });
  }
}
