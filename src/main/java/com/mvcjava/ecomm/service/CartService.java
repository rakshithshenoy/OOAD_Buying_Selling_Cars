package com.mvcjava.ecomm.service;

import com.mvcjava.ecomm.Bill.Bill;
import com.mvcjava.ecomm.Cart.Cart;
import com.mvcjava.ecomm.Order.Order;
import com.mvcjava.ecomm.product.LineItem;
import com.mvcjava.ecomm.product.Product;
import com.mvcjava.ecomm.repository.BillRepository;
import com.mvcjava.ecomm.repository.OrderRepository;
import com.mvcjava.ecomm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.transaction.Transactional;

@Service
public class CartService
{
    @Autowired
    ProductRepository productRepository;
    @Autowired
    BillRepository billRepository;
    @Autowired
    OrderRepository orderRepository;

     public LineItem check_item(String item, List<LineItem> basket)
     {
         for (int i = 0; i < basket.size(); i++)
         {
             if(basket.get(i).getItemname().toLowerCase().equals(item))
             {
                 return basket.get(i);
             }
         }
         return null;
     }

     @Transactional
    public void add_prod_basket(String customer,String itemname,int quantity,Cart cart)
    {
        Product product = productRepository.findByName(itemname);
        LineItem lineItem = check_item(itemname,cart.getBasket());
        List<LineItem> basket = cart.getBasket();
        if(lineItem != null)
        {
            int oldqty = lineItem.getQty();
            lineItem.setQty(oldqty+quantity);
            lineItem.setCost(lineItem.getCost()+lineItem.getPrice()*quantity);
        }
        else
        {
            LineItem newitem = new LineItem();
            newitem.setItemname(itemname);
            newitem.setQty(quantity);
            newitem.setPrice(product.getPrice());
            newitem.setCost(product.getPrice()*quantity);
            newitem.setImage(product.getImage());
            basket.add(newitem);
        }

        for (int i = 0;i<basket.size();i++)
        {
            System.out.println("--> "+basket.get(i).getItemname()+" "+basket.get(i).getQty());
        }

    }

    public void remove_prod_basket(String item,List<LineItem> basket)
    {
        for (int i = 0; i < basket.size(); i++)
        {
            if(basket.get(i).getItemname().toLowerCase().equals(item))
            {
                basket.remove(basket.get(i));
            }
        }
    }

    public Integer gettotal(Cart cart)
    {
            Integer total= 0;
            List<LineItem> basket = cart.getBasket();

            for (int i = 0; i < basket.size(); i++)
            {
                total += basket.get(i).getPrice()*basket.get(i).getQty();
            }
            System.out.println("the total is = "+total);
            return total;
    }

    public Order saveorder(String username, String date_time, Integer total, String addr)
    {
        if (username == null || date_time == null || total == null)
        {
            System.out.println("something is null in Order!!!!");
            return null;
        }
        else
        {
            Order order = new Order();
            order.setDate_time(date_time);
            order.setUsername(username);
            order.setTotal(total);
            order.setAddress(addr);
            return orderRepository.save(order);
        }
    }

    public void savebill(Cart cart,Integer OrderID)
    {
        List<LineItem> basket = cart.getBasket();
        for (int i = 0; i < basket.size(); i++)
        {
            Bill bill = new Bill();
            bill.setCartID(OrderID);
            bill.setItemName(basket.get(i).getItemname());
            bill.setPrice(basket.get(i).getPrice());
            bill.setQty(basket.get(i).getQty());
            bill.setCost(bill.getPrice()*bill.getQty());
            billRepository.save(bill);
        }
    }

    public List<Order> getorders(String name)
    {
        return orderRepository.getAllByUsername(name);
    }

    public List<Bill> getbills(Integer cartID)
    {
        return billRepository.findAllByCartID(cartID);
    }
}
