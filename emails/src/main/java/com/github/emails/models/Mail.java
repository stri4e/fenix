package com.github.emails.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {

    private String to;

    private String from;

    private String subject;

    private List<Object> attachments;

    private Map<String, Object> model;

    public Mail(
            String to,
            String from,
            String subject,
            Map<String, Object> model
    ) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.model = model;
    }

}
