package com.burak.kafka.order;

public record Product(Long id, String name, int stock, double price) {
}
