import { Pipe, PipeTransform } from "@angular/core";


@Pipe({ name: "productDescription" })
export class ProductDescriptionPipe implements PipeTransform {
    transform(value: any) {
        if (!value) {
            return ["Description not available."]
        } else {
            let patter: string = "/\,/";
            let result = value.split(",").join("\n");
            result = result.split("\n ").join("\n").split("\n");
            return result;
        }
    }

}