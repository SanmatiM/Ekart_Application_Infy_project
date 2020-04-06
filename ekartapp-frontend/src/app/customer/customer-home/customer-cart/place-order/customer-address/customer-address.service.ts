import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Address } from 'src/app/shared/models/address';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';

@Injectable()
export class CustomerAddressService {
    private headers = new HttpHeaders({ 'Content-Type': 'text/plain' });
    constructor(private http: HttpClient) { }


    deleteAddress(addressToDelete: Address, customerEmailId: string): Observable<string> {
        let url: string = environment.customerAPIUrl + "/deleteShippingAddress/" + addressToDelete.addressId + "/" + customerEmailId;
        return this.http.post<string>(url, null, { headers: this.headers, responseType: 'text' as 'json' })
            .pipe(
                catchError(this.handleError)
            );

    }

    addNewAddress(addressToadd: Address, customerEmailId: string): Observable<string> {
        let url: string = environment.customerAPIUrl + "/addNewShippingAddress/" + customerEmailId;
        return this.http.post<string>(url, addressToadd, { responseType: 'text' as 'json' })
            .pipe(
                catchError(this.handleError)
            );
    }

    updateNewAddress(address: Address): Observable<string> {
        let url: string = environment.customerAPIUrl + "/updateShippingAddress";
        return this.http.post<string>(url, address, { responseType: 'text' as 'json' })
            .pipe(
                catchError(this.handleError)
            );
    }

    private handleError(err: HttpErrorResponse) {
        console.log(err)
        let errMsg: string = '';

        if (err.status == 400) {
            errMsg = "The request can not be processed at the moment. Please try again later or connect with admin!!";
        } else if (err.status == 404) {
            errMsg = "The resources you are looking for is not available. Please try again later or connect with admin!!";
        } else {
            if (err.error instanceof Error) {

                errMsg = err.error.message;

                console.log(errMsg)
            }
            else if (typeof err.error === 'string') {
                alert("I am in error")
                errMsg = JSON.parse(err.error).message
            }
            else {
                if (err.status == 0) {
                    errMsg = "A connection to back end can not be established.";
                } else {
                    errMsg = err.error.message;
                }
            }
        }
        return throwError(errMsg);
    }



}