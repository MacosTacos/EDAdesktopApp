package com.example.edadesktopapp;

import java.util.List;

public class GetOrdersResponse {
    private int id;
    private int status;
    private int price;
    List<OrderItemEntityList> orderItems;

    public GetOrdersResponse(int id, int status, int price , List<OrderItemEntityList> orderItems) {
        this.id = id;
        this.status = status;
        this.orderItems = orderItems;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<OrderItemEntityList> getOrderItemEntityList() {
        return orderItems;
    }

    public void setOrderItemEntityList(List<OrderItemEntityList> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {


        return "Заказ номер " + id + "\n" + orderItems;
    }

    public class OrderItemEntityList{
        private int id;
        private int quantity;
        private String name;

        public OrderItemEntityList(int id, int quantity, String name) {
            this.id = id;
            this.quantity = quantity;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {

            return name +
                    " : " + quantity + "шт.";
        }
    }
}