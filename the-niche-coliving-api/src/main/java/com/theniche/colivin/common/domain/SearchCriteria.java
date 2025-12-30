package com.theniche.colivin.common.domain;

public class SearchCriteria {
    private String key;
    private Object value;
    private Operation operation;

    public SearchCriteria(String key, Object value, Operation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    public String getKey() { return key; }
    public Object getValue() { return value; }
    public Operation getOperation() { return operation; }

    public enum Operation {
        EQUAL, LIKE, GREATER_THAN, LESS_THAN
    }
}
