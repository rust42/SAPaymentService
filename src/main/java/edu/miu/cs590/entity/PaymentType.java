package edu.miu.cs590.entity;

public enum PaymentType {

    CreditCard(Value.CREDIT_CARD),
    Paypal(Value.PAYPAL);

    PaymentType(String value) {
    }

    public static class Value {
        public static final String CREDIT_CARD="credit_card";
        public static final String PAYPAL="paypal";
    }
}
