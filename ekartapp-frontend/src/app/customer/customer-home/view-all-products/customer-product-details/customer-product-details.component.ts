import { Component, OnInit, Input } from "@angular/core";
import { Product } from "../../../../shared/models/product";
import { CustomerSharedService } from "../../customer-shared-service";
import { Cart } from 'src/app/shared/models/cart';
import { Customer } from 'src/app/shared/models/customer';
import { CustomerCartService } from '../../customer-cart/customer-cart.service';



@Component({
    selector: "customer-product-details",
    templateUrl: "./customer-product-details.component.html"
})
export class CustomerProductDetails implements OnInit {
    @Input()
    selectedProduct: Product;
    errorMessage: string;
    successMessage: string;

    constructor(private customerCommonService: CustomerSharedService, private customerCartService: CustomerCartService){}

    ngOnInit(): void {
    }

    addToCart(){
        this.successMessage = null
        this.errorMessage = null
        let cart:Cart[] = JSON.parse(sessionStorage.getItem("cart"));
        if(cart==null){
            cart = [];
        }
        let customer: Customer = JSON.parse(sessionStorage.getItem("customer"))
        let cartToAdd: Cart = new Cart();
        cartToAdd.customerEmailId = customer.emailId
        cartToAdd.quantity = 1;
        cartToAdd.product = this.selectedProduct;

        let alreadyAddedToCart:boolean = (cart.filter(c=>c.product.productId==this.selectedProduct.productId)).length != 0;
        
        if(alreadyAddedToCart){
            this.errorMessage = "Already added to Cart. Go to cart for modifying your selection."
        } else{
            cart.push(cartToAdd);
            this.customerCommonService.updateCartList(cart)
            sessionStorage.setItem("cart", JSON.stringify(cart));
            this.customerCommonService.addProductToCart(cartToAdd).subscribe((response)=>{
                this.successMessage=response;
            }, (error)=>{
                this.errorMessage = error;
            })
        }

   }


}