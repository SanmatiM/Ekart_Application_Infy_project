import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";
import { Observable, throwError } from 'rxjs';
import { Product } from '../../../shared/models/product';

import { environment } from '../../../../environments/environment';

import { catchError } from 'rxjs/internal/operators/catchError';
import { Cart } from 'src/app/shared/models/cart';

@Injectable({
  providedIn: 'root'
})
export class ViewAllProductsService {

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Product[]> {

    let url = environment.CustomerProductAPI + "/getAllProducts";
    return this.http.get<Product[]>(url)
      .pipe(catchError(this.handleError));

  }

  addToCart(cart: Cart): Observable<Cart> {
    // const url = environment.customerAPIUrl + '/customrLogin';
    return this.http.post<Cart>("http://localhost:8765/EKart/CustomerCartAPI/addProductToCart", cart)
      .pipe(catchError(this.handleError));

  }
  private handleError(err: HttpErrorResponse) {
    console.log(err)
    let errMsg:string='';
    if (err.error instanceof Error) {   
        errMsg=err.error.message;
        console.log(errMsg)
    }
     else if(typeof err.error === 'string'){
        errMsg=JSON.parse(err.error).message
    }
    else {
       if(err.status==0){ 
           errMsg="A connection to back end can not be established.";
       }else{
           errMsg=err.error.message;
       }
     }
        return throwError(errMsg);
}
}
