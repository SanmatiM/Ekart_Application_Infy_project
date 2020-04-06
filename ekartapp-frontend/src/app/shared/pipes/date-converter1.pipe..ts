import { Pipe, PipeTransform } from "@angular/core";



@Pipe({ name: "dateConverterX" })
export class DatePipeX implements PipeTransform {
    transform(value: any,args?:any): any {

        let data: string = value + "";
        let dates: string[] = data.split(",");
        let date = new Date(parseInt(dates[0]),
            parseInt(dates[1])-1,
            parseInt(dates[2]),
            parseInt(dates[3]),
            parseInt(dates[4]));
            

        return date;
    }

}