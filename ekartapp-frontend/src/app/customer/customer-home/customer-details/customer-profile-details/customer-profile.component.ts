import { Component, Output, EventEmitter } from "@angular/core";
import { OnInit } from "@angular/core";
import { CustomerProfileService } from "./customer-profile.service";
import { Router, ActivatedRoute } from "@angular/router";
import { Customer } from 'src/app/shared/models/customer';
import { CustomerSharedService } from '../../customer-shared-service';






@Component(
    {
        selector: 'customer-profile',
        templateUrl: './customer-profile.component.html'
    }
)
export class CustomerProfileComponent implements OnInit {
    customer: Customer;
    updating: string = null;
    customerToUpdate: Customer;

    errorMessage: string = "";
    successMessage: string = "";

    constructor(private SellerProfileService: CustomerProfileService,
        private router: Router,
        private route: ActivatedRoute,
        private customerSharedService: CustomerSharedService
    ) {


    }
    ngOnInit() {
        this.customer = JSON.parse(sessionStorage.getItem("customer"));
        this.customerToUpdate = Object.assign({}, this.customer);
    }

    linkClick(value) {
        this.updating = value;
        this.errorMessage = "";
        this.successMessage = "";
    }

    updateDetail(action: string) {
        this.errorMessage = null;
        this.successMessage = null;
        if (action == "update") {
            this.SellerProfileService.updateCustomerDetails(this.customerToUpdate).subscribe(
                (response) => {
                    this.successMessage = response;
                    this.customer = this.customerToUpdate;
                    this.customerSharedService.updatedCustomerDetails(this.customerToUpdate);
                    sessionStorage.setItem("customer", JSON.stringify(this.customerToUpdate));
                    this.updating = "";
                    this.router.navigate(["home/details"]);
                }, error => {
                    this.errorMessage = <any>error;
                    Object.assign(this.customerToUpdate,this.customer);
                }

            )
        } else {
            this.updating = "";
            Object.assign(this.customerToUpdate,this.customer);
        }
    }


}