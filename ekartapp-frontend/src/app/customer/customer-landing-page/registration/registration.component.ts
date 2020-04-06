import { Component, OnInit } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { LoginValidators } from "../../../shared/validators/login.validator";
import { RegistrationService } from "./registration.service";
import { Customer } from "../../../shared/models/customer";


@Component(
    {
        selector: "registration",
        templateUrl: "./registration.component.html",
        styleUrls: ['./registration.component.css']
    }
)
export class RegistrationComponent implements OnInit {

    customer: Customer;
    registerUserForm: FormGroup;
    errorMessage: string;
    successMessage: string;
    constructor(private fb: FormBuilder, private registerService: RegistrationService) {

    }

    ngOnInit() {
        this.customer = new Customer();
        this.createForm();

    }
    createForm() {

        this.registerUserForm = this.fb.group({
            emailId: [this.customer.emailId, [Validators.required, LoginValidators.validateEmailId], null],
            name: [this.customer.name, [Validators.required, LoginValidators.validateName], null],
            phoneNumber: [this.customer.phoneNumber, [Validators.required, LoginValidators.validatePhoneNumber], null],
            password: [this.customer.password, [Validators.required, LoginValidators.validatePassword], null],
            confirmPassword: ["", [Validators.required], null]

        });
        this.registerUserForm.get('confirmPassword').setValidators([Validators.required,LoginValidators.confirmPassword(this.registerUserForm.get('password'))]);
    }

    registerUser() {
        this.errorMessage = null;
        this.successMessage = null;
        this.customer = this.registerUserForm.value as Customer;

        this.registerService.registerCustomer(this.customer)
            .subscribe(
                message => {
                    this.successMessage = message;
                    this.registerUserForm.reset();
                }
                , error => this.errorMessage = <any>error
            )

    }

}