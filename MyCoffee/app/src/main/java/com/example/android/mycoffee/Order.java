package com.example.android.mycoffee;

//MODEL class

public class Order {

    private int quantity = 1;
    private int price = 0;

    public int getQuantity(){
        return this.quantity;
    }

    public int increment() {
        return ++this.quantity;
    }

    public int decrement() {
        return --this.quantity;
    }

    public int getPrice(boolean cream, boolean chocolate) {
        this.price = this.quantity * 5;

        if (cream && chocolate) {
            return this.price += this.quantity * 3;
        }

        if (cream) {
            return this.price += this.quantity; //* 1
        }

        if (chocolate) {
            return this.price += this.quantity * 2;
        }

        return price;
    }

}