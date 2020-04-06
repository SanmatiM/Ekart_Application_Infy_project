import { Component } from "@angular/core";
import { OnInit } from "@angular/core";
import { CustomerAddressService } from './customer-address.service';
import { Address } from 'src/app/shared/models/address';
import { Customer } from 'src/app/shared/models/customer';
import { CustomerSharedService } from '../../../customer-shared-service';
//Test


@Component({
    selector: "customer-address",
    templateUrl: "./customer-address.component.html",
    styleUrls: ['./customer-address.component.css']
})
export class CustomerAddressComponent implements OnInit {

    loggedInCustomer: Customer;
    addresses: Address[];
    successMessage: string = null;
    errorMessage: string = null;

    addressToUpdate: Address = null;
    copyOfAddress: Address;
    addressToAdd: Address = null;
    

    constructor(private addressService: CustomerAddressService, private customerSharedService: CustomerSharedService) {

    }

    ngOnInit() {
        this.successMessage = null;
        this.errorMessage = null;
        this.loggedInCustomer = JSON.parse(sessionStorage.getItem("customer"));
        this.addresses = this.loggedInCustomer.addresses;
        
    }

    deleteAddress(addressToDelete: Address) {
        this.successMessage = null;
        this.errorMessage = null;
        this.addressService.deleteAddress(addressToDelete, this.loggedInCustomer.emailId).subscribe(
            response => {
                this.successMessage = response;
                this.addresses = this.addresses.filter(address => address.addressId !== addressToDelete.addressId);
                this.customerSharedService.updateCustomerAddressList(this.addresses);
                this.loggedInCustomer.addresses = this.addresses;
                sessionStorage.setItem("customer", JSON.stringify(this.loggedInCustomer));
            }, error => {
                this.errorMessage = error;
            }
        );
    }
    /*
    Create new address add page and navigate to that programmatically
    */
    updateAddress(addressToUpdate: Address) {
        this.successMessage = null;
        this.errorMessage = null;
        this.addressToUpdate = addressToUpdate;
        this.copyOfAddress = Object.assign({},addressToUpdate)
        console.log(this.addressToUpdate, this.copyOfAddress)
    }

    addNewAddress(){
        this.successMessage = null;
        this.errorMessage = null;
        this.addressToAdd = new Address();
    }

    updateSelectedAddress(){
        this.successMessage = null;
        this.errorMessage = null;
        this.addressService.updateNewAddress(this.addressToUpdate).subscribe(response => {
            this.successMessage = response;
            this.addressToUpdate = null;
            this.loggedInCustomer.addresses = this.addresses;
            sessionStorage.setItem("customer", JSON.stringify(this.loggedInCustomer));
        }, error => {
            this.errorMessage = error;
            Object.assign(this.addressToUpdate, this.copyOfAddress)
        })
    }

    cancelUpdate(){
        this.successMessage = null;
        this.errorMessage = null;
        Object.assign(this.addressToUpdate, this.copyOfAddress)
        this.addressToUpdate = null;
    }

    addAddress(){
        this.successMessage = null;
        this.errorMessage = null;
        this.addressService.addNewAddress(this.addressToAdd, this.loggedInCustomer.emailId).subscribe(response => {
            this.successMessage = response;
            let id = this.successMessage.substring(this.successMessage.indexOf(":")+1).trim();
            this.addressToAdd.addressId = parseInt(id);
            this.addresses.push(this.addressToAdd);
            this.loggedInCustomer.addresses = this.addresses;
            this.addressToAdd = null;
            sessionStorage.setItem("customer", JSON.stringify(this.loggedInCustomer));
        }, error => {
            this.errorMessage = error;
        })
    }

    cancelAdd(){
        this.successMessage = null;
        this.errorMessage = null;
        this.addressToAdd = null;
    }

}