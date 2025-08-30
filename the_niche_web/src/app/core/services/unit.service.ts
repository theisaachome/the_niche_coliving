import {BaseService} from './base.service';
import {Unit} from '../models/models';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({providedIn:"root"})
export class UnitService extends  BaseService<Unit>{
  constructor( http:HttpClient){
    super(http,"http://localhost:8080/api/v1");
  }
}
