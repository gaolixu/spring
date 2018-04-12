package org.mybatis.bean;


public class Noodle extends Food{
    
    private int price;

    @Override
    public String toString() {
        return "Noodle{" +
                "price=" + price +
                ",name="+name+"}";
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
