package com.rest.project.enums;

public enum OrderStatus {

    BEING_CREATED, PAID_FOR, FULFILLED, CANCELED;

    public static boolean valid(OrderStatus currentStatus, OrderStatus newStatus){
//        return switch(currentStatus){
//            case BEING_CREATED -> newStatus==PAID_FOR||newStatus==CANCELED;
//            case PAID_FOR -> newStatus==FULFILLED;
//            case FULFILLED -> false;
//            case CANCELED -> false;
//            default -> throw new RuntimeException("Unrecognized situation");
//        };
        switch (currentStatus){
            case BEING_CREATED: return newStatus==PAID_FOR||newStatus==CANCELED;
            case PAID_FOR: return newStatus==FULFILLED;
            case FULFILLED: return false;
            case CANCELED: return false;
            default: throw new RuntimeException("Unrecognized situation");
        }
    }
}
