package com.github.emails.payload;

public enum ConfirmReport {

    success, error;

    public String confirmAccount() {
        if (this == ConfirmReport.success) {
            return "registration-success";
        }
        return "registration-error";
    }

    public String resetPass() {
        if (this == ConfirmReport.success) {
            return "password-reset-success";
        }
        return "password-reset-error";
    }

}
