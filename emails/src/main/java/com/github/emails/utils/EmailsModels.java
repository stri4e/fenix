package com.github.emails.utils;

import java.util.HashMap;
import java.util.Map;

import static com.github.emails.utils.EmailConstants.*;

public class EmailsModels {

    public static Map<String, Object>
    createModel(final String fName, final String lName, final String url) {
        Map<String, Object> model = new HashMap<>();
        model.put(firstName.name(), fName);
        model.put(lastName.name(), lName);
        model.put(confirmAccountUrl.name(), url);
        model.put(signature.name(), "https://shop.com");
        return model;
    }

}
