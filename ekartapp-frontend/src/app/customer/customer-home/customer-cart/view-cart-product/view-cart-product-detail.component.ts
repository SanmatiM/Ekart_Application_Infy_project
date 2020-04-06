import { Component, OnInit, Input, OnDestroy } from "@angular/core";
import { Cart } from "../../../../shared/models/cart";
import { CustomerCartService } from "../customer-cart.service";
import { CustomerSharedService } from "../../customer-shared-service";

@Component({
    selector: 'view-cart-product-detail',
    templateUrl: './view-cart-product-detail.component.html'
})
export class ViewCartProductDetail implements OnInit, OnDestroy {

    @Input()
    selectedCartProduct: Cart;

    successMessage: string;
    errorMessage: string;
    copyOfProduct: Cart;
    ngOnInit(): void {
        this.copyOfProduct = Object.assign({}, this.selectedCartProduct);
    }

    constructor(private customerService: CustomerCartService,
        private sharedService: CustomerSharedService) { }
    alter(operation: string) {
        if (operation == '-') this.selectedCartProduct.quantity--;
        else this.selectedCartProduct.quantity++;
    }

    updateCart() {
        this.successMessage=null;
        this.errorMessage=null;
        if (this.selectedCartProduct.quantity <= 0) {
            this.errorMessage = "Quantity cannot be negative or zero";
            Object.assign(this.selectedCartProduct,this.copyOfProduct);
        } else {
            this.customerService.updateCart(this.selectedCartProduct).subscribe(
                message => {
                    this.successMessage = message;
                    let cartList: Cart[] = JSON.parse(sessionStorage.getItem("cart"));
                    let index = cartList.findIndex(item => item.cartId == this.selectedCartProduct.cartId);
                    cartList[index] = this.selectedCartProduct;
                    Object.assign(this.copyOfProduct,this.selectedCartProduct);
                    this.sharedService.updateCartList(cartList);
                    sessionStorage.setItem("cart", JSON.stringify(cartList));
                }, error => {
                    this.errorMessage = <any>error;
                    Object.assign(this.selectedCartProduct,this.copyOfProduct);
                }
            )
        }
    }

    ngOnDestroy(){
        Object.assign(this.selectedCartProduct,this.copyOfProduct);
    }


}