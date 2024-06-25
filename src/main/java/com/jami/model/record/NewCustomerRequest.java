package com.jami.model.record;

public record NewCustomerRequest(
        String name,
        String email,
        Integer age
) {
}
