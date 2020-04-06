import { Address } from "./address";
import { Cart } from './cart';



export class Customer {
    
    emailId: string;
    name: string;
    password: string;
    
    phoneNumber: string;
    addresses: Address[];
    customerCarts: Cart[];
    
    
    
}