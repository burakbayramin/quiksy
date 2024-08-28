package com.burak.constants;

public class PathConstants {
    private PathConstants() {}

    public static final String API = "/api";
    public static final String VERSIONS = "/v1";
    public static final String PRODUCTS = API+VERSIONS+"/products";
    public static final String PURCHASE = PRODUCTS+"/purchase";
    public static final String PRODUCT_ID = PRODUCTS+ "/{productId}";
}
