package com.rest.project.enums;

public enum OrderStatus {

    BEING_CREATED, PAID_FOR, FULFILLED, CANCELED;

    static boolean valid(OrderStatus currentStatus, OrderStatus newStatus) {
        return switch(currentStatus){
            case BEING_CREATED -> newStatus==PAID_FOR||newStatus==CANCELED;
            case PAID_FOR -> newStatus==FULFILLED;
            case FULFILLED -> false;
            case CANCELED -> false;
            default -> throw new RuntimeException("Unrecognized situation");
        };
    }
}
