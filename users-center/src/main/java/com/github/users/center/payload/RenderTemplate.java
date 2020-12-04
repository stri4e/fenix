package com.github.users.center.payload;

import com.github.users.center.utils.ConfirmReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RenderTemplate {

    private String template;

    private String error;

    public static RenderTemplate success() {
        return new RenderTemplate(ConfirmReport.success.name(), "");
    }

    public static RenderTemplate error(String error) {
        return new RenderTemplate(ConfirmReport.error.name(), error);
    }

}
