package com.burak.constants;

public class PathConstants {
    private PathConstants() {}

    public static final String API = "/api";
    public static final String VERSIONS = "/v1";
    public static final String ORDERS = API+VERSIONS+"/orders";
    public static final String ORDERLINES = API+VERSIONS+"/order-lines";
    public static final String ORDER_ID = ORDERS+"/{orderId}";
    public static final String ORDERLINE_ID = ORDERLINES+"/{orderId}";
}
