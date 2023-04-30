package com.mvcjava.ecomm.Cart;

import com.mvcjava.ecomm.product.LineItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Cart
{
//    private HashMap<LineItem,Integer> basket;
    private List<LineItem> basket;

    public Cart()
    {
        this.basket = new ArrayList<>();
    }

    public List<LineItem> getBasket()
    {
        if(basket == null)
        {
            System.out.println("basket is null");
        }
        return basket;
    }
}
