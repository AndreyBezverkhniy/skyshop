package org.skypro.skyshop.model.basket;

public class ShopError {
    private int code;
    private String message;

    public ShopError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
