package com.mvcjava.ecomm.Bill;



import jakarta.persistence.*;

@Table(name = "bills")
@Entity
public class Bill
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer cartID;
    String itemName;
    Integer price;
    Integer qty;
    Integer cost;

    public Integer getCartID() {
        return cartID;
    }

    public void setCartID(Integer cartID) {
        this.cartID = cartID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
