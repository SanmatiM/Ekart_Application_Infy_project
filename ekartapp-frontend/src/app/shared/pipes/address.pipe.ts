import { Pipe, PipeTransform } from "@angular/core";
import { Address } from '../models/address';



@Pipe({ name: "address" })
export class AddressPipe implements PipeTransform {
    transform(value: Address) {
        if (!value) {
            return [];
        }
        let addressArr: string[] = [];
        addressArr.push(value.addressLine1);
        addressArr.push(value.addressLine2);
        addressArr.push(value.city);
        addressArr.push(value.state + " - " + value.pin);
        addressArr.push("Contact no: " + value.contactNumber);

        return addressArr;
    }

}