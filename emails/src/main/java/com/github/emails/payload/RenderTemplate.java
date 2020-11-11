package com.github.emails.payload;

import com.github.emails.utils.ConfirmReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenderTemplate {

    private String template;

    private String error;

    public String renderConfirmAccount() {
        if ("success".equals(this.template)) {
            return "registration-success";
        }
        if ("errorDef".equals(this.template)) {
            return "base-error";
        }
        return "registration-error";
    }

    public String renderResetPass() {
        if ("success".equals(this.template)) {
            return "password-reset-success";
        }
        if ("errorDef".equals(this.template)) {
            return "base-error";
        }
        return "password-reset-error";
    }

    public static RenderTemplate errorDef() {
        return new RenderTemplate(ConfirmReport.error.name(), "");
    }

}
