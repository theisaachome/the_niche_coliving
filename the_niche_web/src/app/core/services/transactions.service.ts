import {BaseService} from './base.service';
import {Transaction} from '../models/models';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable(
  {
    providedIn:'root'
  }
)
export class TransactionsService extends BaseService<Transaction>{

  constructor(protected override http: HttpClient) {
    super(http, '/api/budget-accounts'); // API Endpoint
  }

}
