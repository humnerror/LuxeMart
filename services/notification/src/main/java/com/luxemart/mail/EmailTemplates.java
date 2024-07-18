package com.luxemart.mail;


import lombok.Getter;


public enum EmailTemplates {

    PAYMENT_TEMPLATE("payment-confirmation.html","Payment successfully processed"),

    ORDER_TEMPLATE("order-confirmation.html","Order Confirmation");


    @Getter
    private final String templates;

    @Getter
    private final String subject;

    EmailTemplates(String templates, String subject) {
        this.templates = templates;
        this.subject = subject;
    }
}
