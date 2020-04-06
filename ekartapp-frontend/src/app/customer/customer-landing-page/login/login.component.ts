import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer } from '../../../shared/models/customer';
import { LoginValidators } from '../../../shared/validators/login.validator';
import { LoginService } from './login.service';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    customer: Customer;
    loginForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    tryToLogin: boolean = false;
    constructor(private fb: FormBuilder, private loginService: LoginService,
        private router: Router) {

    }

    ngOnInit() {
        this.customer = new Customer();
        this.createForm();

    }
    createForm() {

        this.loginForm = this.fb.group({
            emailId: [this.customer.emailId, [Validators.required, LoginValidators.validateEmailId], null],
            password: [this.customer.password, [Validators.required], null]
        });
    }



    login() {
        this.tryToLogin = true;
        this.errorMessage = null;
        this.successMessage = null;
        this.customer = this.loginForm.value as Customer;
        this.loginService.login(this.customer).subscribe(
            (response) => {
                this.customer = response
                sessionStorage.setItem("customer", JSON.stringify(this.customer));
                sessionStorage.setItem("userType", JSON.stringify("Customer"));
                sessionStorage.setItem("cart",JSON.stringify(this.customer.customerCarts))
                this.tryToLogin = false;
                this.router.navigate(['/home']);
            },
            (error) => {
                this.tryToLogin = false;
                this.errorMessage = <any>error;
            }
        )
    }
}