
import { AbstractControl } from '@angular/forms'

export class LoginValidators {
    static validateEmailId(control: AbstractControl): any {
        let emailIdPattern: RegExp = /[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]+/;
        if (!emailIdPattern.test(control.value)) {
            return { "emailIdPatternError": true }
        }
        return null;
    }

    static validateName(control: AbstractControl): any {
        let namePattern1: RegExp = /^[a-zA-Z ]+/;
        let namePattern2: RegExp = /^[^ ].*/;
        let namePattern3: RegExp = /.*[^ ]$/;
        let value = control.value;
        let matches: boolean = namePattern1.test(value) && namePattern2.test(value) && namePattern3.test(value);

        if (!matches) {
            return { "namePatternError": true }
        }
        return null;
    }
    static validatePhoneNumber(control: AbstractControl): any {
        let pattern1: RegExp = /^\d{10}$/;

        let value = control.value;
        let matches: boolean = pattern1.test(value);

        if (!matches) {
            return { "phoneNumberError": true }
        }
        return null;
    }
    static validatePassword(control: AbstractControl): any {
        let pattern1: RegExp = /^.*[A-Z]+.*/;
        let pattern2: RegExp = /^.*[a-z]+.*/;
        let pattern3: RegExp = /.*[\d]+.*/;
        let pattern4: RegExp = /.*[@#$%&*^]+.*/;
        let value = control.value;
        let matches: boolean = pattern1.test(value) && pattern2.test(value) && pattern3.test(value)
            && pattern4.test(value);

        if (!matches) {
            return { "passwordPatternError": true }
        }
        return null;
    }

    static confirmPassword(passwordControl: AbstractControl): any {
        return (confirmPasswordControl: AbstractControl)=>{
            if(passwordControl.value != confirmPasswordControl.value)
                return {'confirmPassword': true}
            return null;
        };
    }
}