import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CustomerRoutingModule } from './customer-routing.module';
import { AddressPipe } from '../shared/pipes/address.pipe';
import { ProductDescriptionPipe } from '../shared/pipes/product-description.pipe';
import { CustomerDetailsComponent } from './customer-home/customer-details/customer-details.component';
import { ViewAllProductsComponent } from './customer-home/view-all-products/view-all-products.component';
import { CustomerProductDetails } from './customer-home/view-all-products/customer-product-details/customer-product-details.component';
import { CustomerCartComponent } from './customer-home/customer-cart/customer-cart.component';
import { ViewCartProductDetail } from './customer-home/customer-cart/view-cart-product/view-cart-product-detail.component';
import { LoginComponent } from './customer-landing-page/login/login.component';
import { RegistrationComponent } from './customer-landing-page/registration/registration.component';
import { ViewAllProductsService } from './customer-home/view-all-products/view-all-products.service';
import { CustomerSharedService } from './customer-home/customer-shared-service';
import { CustomerHomeService } from './customer-home/customer-home.service';
import { CustomerCartService } from './customer-home/customer-cart/customer-cart.service';
import { LoginService } from './customer-landing-page/login/login.service';
import { RegistrationService } from './customer-landing-page/registration/registration.service';
import { CustomerHomeComponent } from './customer-home/customer-home.component';
import { CustomerLandingPageComponent } from './customer-landing-page/customer-landing-page.component';
import { CustomerProfileComponent } from './customer-home/customer-details/customer-profile-details/customer-profile.component';
import { CustomerProfileService } from './customer-home/customer-details/customer-profile-details/customer-profile.service';
//  import { CarddetailsComponent } from './customer-home/customer-details/customer-carddetails/customer-carddetails.component';
import { DatePipeX } from '../shared/pipes/date-converter1.pipe.';
import {NgxPaginationModule} from 'ngx-pagination';
import { PlaceOrderComponent } from './customer-home/customer-cart/place-order/place-order.component';
import { CustomerAddressComponent } from './customer-home/customer-cart/place-order/customer-address/customer-address.component';
import { CustomerAddressService } from './customer-home/customer-cart/place-order/customer-address/customer-address.service';
@NgModule({
    declarations: [
        CustomerHomeComponent,
        CustomerDetailsComponent,
        ViewAllProductsComponent,
        CustomerProfileComponent,
        CustomerAddressComponent,
        AddressPipe,
        ProductDescriptionPipe,
        CustomerProductDetails,
        CustomerCartComponent,
        ViewCartProductDetail,
        CustomerLandingPageComponent,
        LoginComponent,
        RegistrationComponent,
        DatePipeX,
        PlaceOrderComponent
    ],
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        CustomerRoutingModule,
        NgxPaginationModule
        
    ],
    providers: [
        ViewAllProductsService,
        CustomerProfileService,
        CustomerSharedService,
        CustomerHomeService,
        CustomerCartService,
        CustomerAddressService,
        LoginService,
        RegistrationService
    ],
    exports: []

})
export class CustomerModule {

}