import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";
import { environment } from "../../../../environments/environment";
import { Customer } from "../../../shared/models/customer";
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators'

@Injectable({
    providedIn: 'root'
})
export class RegistrationService {
    private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    constructor(private http: HttpClient) {

    }

    registerCustomer(customer: Customer): Observable<string> {
        const url = environment.customerAPIUrl + '/registerCustomer';
        return this.http.post<string>(url, customer, { headers: this.headers, responseType: 'text' as 'json' })
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