
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";
import { environment } from "../../../../environments/environment";
import { Cart } from "../../../shared/models/cart";
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';


@Injectable({
    providedIn: 'root'
})
export class CustomerCartService {
    
    private headers = new HttpHeaders({ 'Content-Type': 'text/plain' });
    constructor(private http: HttpClient) { }

    deleteProductFromCart(cart: Cart, customerEmailId: string): Observable<string> {
        let url: string = environment.customerCartUrl + "/deleteProductFromCart/" + customerEmailId;
        return this.http.post<string>(url, cart.cartId, { headers: this.headers, responseType: 'text' as 'json' })
            .pipe(
                catchError(this.handleError)
            );

    }


    updateCart(cart: Cart): Observable<string> {
        let url: string = environment.customerCartUrl + "/modifyQuantityOfProductInCart";
        return this.http.post(url, cart, {responseType: 'text' })
            .pipe(catchError(this.handleError));

    }
    private handleError(err: HttpErrorResponse) {
        console.log(err)
        let errMsg: string = '';
        if (err.error instanceof Error) {
            errMsg = err.error.message;
            console.log(errMsg)
        }
        else if (typeof err.error === 'string') {
            errMsg = JSON.parse(err.error).message
        }
        else {
            if (err.status == 0) {
                errMsg = "A connection to back end can not be established.";
            } else {
                errMsg = err.error.message;
            }
        }
        return throwError(errMsg);
    }

    getCustomerCart(emailId: any) {
        let url: string = environment.customerCartUrl + "/getCustomerCart/" + emailId + '/';
        return this.http.get<Cart[]>(url)
            .pipe(catchError(this.handleError));
    }

}