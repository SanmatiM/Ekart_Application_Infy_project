import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { ViewAllProductsService } from './view-all-products.service';

import { Customer } from '../../../shared/models/customer';
import { Product } from '../../../shared/models/product';
import { CustomerSharedService } from '../customer-shared-service';
import { Router, ActivatedRoute } from '@angular/router';
import { Cart } from '../../../shared/models/cart';

@Component({
  selector: 'app-view-all-products',
  templateUrl: './view-all-products.component.html',
  styleUrls: ['./view-all-products.component.css']
})
export class ViewAllProductsComponent implements OnInit {

  successMessage: string;
  errorMessage: string;
  productList: Product[];

  searchText: string;

  viewDetails: boolean = false;
  selectedProduct: Product;

  productListToDisplay: Product[] = [];
  constructor(private viewAllProductService: ViewAllProductsService,
    private sharedService: CustomerSharedService,
    private router: Router,
    private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.getAllProduct();
  }
  getAllProduct() {
    this.viewAllProductService.getAllProducts()
      .subscribe(products => {
        this.productList = products;
        this.productListToDisplay = this.productList;
      }
      );


  }
  setSelectedProduct(product: Product) {
    this.viewDetails = true;
    this.selectedProduct = product;
  }




  search(abc) {
    if (this.searchText) {
      this.productListToDisplay = this.productList.filter(product => {
        return product.brand.indexOf(this.searchText) != -1 || product.name.indexOf(this.searchText) != -1
      });
    } else {
      this.productListToDisplay = this.productList;
    }

  }

  clear() {
    this.productListToDisplay = this.productList;
    this.searchText = "";
  }

  addToCart(product: Product) {
    this.successMessage = '';
    this.errorMessage = '';
    let cart: Cart = new Cart();
    let customer: Customer = JSON.parse(sessionStorage.getItem("customer"));

    cart.customerEmailId = customer.emailId;
    cart.product = product;
    cart.quantity = 1;

    this.viewAllProductService.addToCart(cart).subscribe(
      cartFromDB => {
        this.successMessage = cartFromDB.successMessage;
      }, error => this.errorMessage = <any>error
    )
  }

}
