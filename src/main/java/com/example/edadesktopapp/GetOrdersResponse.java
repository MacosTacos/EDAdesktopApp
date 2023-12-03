package com.example.edadesktopapp;

import java.util.List;

public class GetOrdersResponse {
    private int id;
    private int status;
    List<OrderItemEntityList> orderItemEntityList;

    public GetOrdersResponse(int id, int status, List<OrderItemEntityList> orderItemEntityList) {
        this.id = id;
        this.status = status;
        this.orderItemEntityList = orderItemEntityList;
    }

    public GetOrdersResponse(int id, int status) {
        this.id = id;
        this.status = status;
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
        return orderItemEntityList;
    }

    public void setOrderItemEntityList(List<OrderItemEntityList> orderItemEntityList) {
        this.orderItemEntityList = orderItemEntityList;
    }

    @Override
    public String toString() {
        return "GetOrdersResponse{" +
                "id=" + id +
                ", status=" + status +
                ", orderItemEntityList=" + orderItemEntityList +
                '}';
    }

    public class OrderItemEntityList{
        private int id;
        private int quantity;
        //private String name;

        public OrderItemEntityList(int id, int quantity) {
            this.id = id;
            this.quantity = quantity;
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
            return "OrderItemEntityList{" +
                    "id=" + id +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}