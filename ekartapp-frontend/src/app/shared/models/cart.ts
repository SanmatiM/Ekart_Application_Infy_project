import { Product } from "../../shared/models/product";

export class Cart{
    cartId:number;
	customerEmailId:string;
	product:Product;
	quantity:number;
	errorMessage:string; 
	successMessage:string;
}