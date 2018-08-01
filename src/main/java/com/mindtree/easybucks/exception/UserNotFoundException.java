package com.mindtree.easybucks.exception;

public class UserNotFoundException  extends RuntimeException {
    private String user;
    private String fieldName;
    private Object fieldValue;

    public UserNotFoundException( String user, String userName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", user, userName, fieldValue));
        this.user = user;
        this.fieldName = userName;
        this.fieldValue = fieldValue;
    }

    public String getUserName() {
        return user;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

}
