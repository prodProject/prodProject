package com.prod.prodProject.Helpers;

import android.util.Log;

import com.google.android.libraries.places.internal.em;
import com.prod.prodProject.Enums.UserGenderTypeEnum;

public class ValidationHelper {

    private boolean valid = true;

    public boolean validate(String fname, String lname, String email, String mobno, String pass, String cpass, UserGenderTypeEnum genderEnum) {
        if (fname.isEmpty()) {
            valid = false;
            return valid;
        }
        if (lname.isEmpty()) {
            valid = false;
            return valid;
        }
        if (mobno.isEmpty()) {
            valid = false;
            return valid;
        }
        if (pass.isEmpty()) {
            valid = false;
            return valid;
        }
        if (cpass.isEmpty()) {
            valid = false;
            return valid;
        }
        checkEmailisValid(email);
        checkPasswordConfirm(pass, cpass);
        checkGenderEnum(genderEnum);
        return valid;
    }

    private void checkGenderEnum(UserGenderTypeEnum genderEnum) {
        if (genderEnum == UserGenderTypeEnum.UNKNOWN) {
            valid = false;
            return;
        }
    }

    private void checkPasswordConfirm(String pass, String cpass) {
        if (!pass.equals(cpass)) {
            valid = false;
            return;
        }
    }

    private void checkEmailisValid(String email) {
        if (email.isEmpty()) {
            valid = false;
            return;
        }
        String[] arr = email.split("@");
        if (arr.length < 2) {
            valid = false;
            return;
        }
        if (!arr[1].contains(".")) {
            valid = false;
            return;
        }
    }

    private boolean checkValueIsEmptyOrValid(String value) {
        return value.isEmpty();
    }

}
