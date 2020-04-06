import { Component, OnInit } from "@angular/core";
import { Customer } from "../../shared/models/customer";
import { Router, ActivatedRoute } from "@angular/router";
import { CustomerHomeService } from "./customer-home.service";
import { Cart } from "../../shared/models/cart";
import { CustomerSharedService } from "./customer-shared-service";


@Component({
    selector: 'customer-home',
    templateUrl: './../customer-home/customer-home.component.html',
    styleUrls: ['./customer-home.component.css']

})
export class CustomerHomeComponent {
    isViewProductSelected: boolean = false;
    isRouting: boolean = false;
    optionSelected: string;
    loggedInCustomer: Customer;
    searchText: string;
    cart: Cart[] = [];


    constructor(private router: Router, private route: ActivatedRoute,
        private customerHomeService: CustomerHomeService, private customerSharedService: CustomerSharedService) {

    }

    ngOnInit() {
        this.getLoggedInCustomer();
        this.getCustomerCart();
    }
    getLoggedInCustomer() {
        this.customerSharedService.updatedCustomer.subscribe(customer => this.loggedInCustomer = customer);
        this.loggedInCustomer = JSON.parse(sessionStorage.getItem("customer"));
    }

    getCustomerCart() {
        this.customerSharedService.updatedCartList.subscribe(cartList => this.cart = cartList)
        // this.customerHomeService.getCustomerCart(this.loggedInCustomer.emailId).subscribe(
        //     cart => {
        //         this.cart = cart;
        //         sessionStorage.setItem("cart", JSON.stringify(this.cart));
        //         this.customerSharedService.updatedCartList.subscribe(cartList => this.cart = cartList)
        //         this.customerSharedService.updateCartList(this.cart)
        //     }, err => {
        //         this.customerSharedService.updatedCartList.subscribe(cartList => this.cart = cartList)
        //     }
        // )
    }

    logout() {
        sessionStorage.clear();
        this.router.navigate([""])
    }
}