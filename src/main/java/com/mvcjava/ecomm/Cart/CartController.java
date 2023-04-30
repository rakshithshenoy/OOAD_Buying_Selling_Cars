package com.mvcjava.ecomm.Cart;

import com.mvcjava.ecomm.Order.Order;
import com.mvcjava.ecomm.product.LineItem;
import com.mvcjava.ecomm.repository.UsersRepository;
import com.mvcjava.ecomm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class CartController
{
    String current_user = null;
    @Autowired
    public Cart cart;
    @Autowired
    CartService cartService;
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/addtocart")
    public String add_cust_item(@RequestParam("item")String item,@RequestParam("customer")String customer,Model model)
    {
        model.addAttribute("cartItem",new LineItem());
        if(item!=null && customer!=null)
        {
            model.addAttribute("item",item);
            model.addAttribute("customer",customer);
        }
        return "cart_form";
    }

    @PostMapping("/addtocart")
    public String add_cust_item(@RequestParam("item")String item,@RequestParam("customer")String customer,@RequestParam("qty")String quantity,Model model)
    {
        System.out.println("item "+item+" customer "+customer+" qty "+quantity);
        if(item!=null && customer!=null && quantity!=null)
        {
            cartService.add_prod_basket(customer,item,Integer.valueOf(quantity),cart);
            model.addAttribute("name",item);
            model.addAttribute("customer",customer);
        }
        model.addAttribute("username",customer);
        return "user_dashboard2";
    }

    @GetMapping("/viewcart")
    public String mycart(@RequestParam("customer")String user, Model model)
    {
        model.addAttribute("customer",user);
        model.addAttribute("cartItems",cart.getBasket());
        return "cart_items";
    }

    @GetMapping("/removeitem")
    public String removeitem(@RequestParam("item")String item, Model model)
    {
        cartService.remove_prod_basket(item,cart.getBasket());
        model.addAttribute("cartItems",cart.getBasket());
        return "cart_items";
    }

    @GetMapping("/getbill")
    public String printbill(@RequestParam("customer")String customer, Model model)
    {
        model.addAttribute("customer",customer);
        model.addAttribute("total",cartService.gettotal(cart));
        model.addAttribute("cartItems",cart.getBasket());
        return "bill";
    }

    @PostMapping("/getbill")
    public String bill_form(@RequestParam("customer")String customer,@RequestParam("total")Integer total,Model model)
    {
        model.addAttribute("customer",customer);
        model.addAttribute("total",total);
        return "payment";
    }

    @GetMapping("/payment")
    public String payment_form(@RequestParam("total")String total,@RequestParam("customer")String customer,Model model)
    {
        System.out.println("in payment");
        model.addAttribute("customer",customer);
        model.addAttribute("total",total);
        return "payment";
    }
    @PostMapping("/payment")
    public String payment(@RequestParam("total") String total,@RequestParam("amount") String amount,@RequestParam("address") String address,@RequestParam("method") String method,@RequestParam("customer") String customer,Model model)
    {
        System.out.println(total);
        System.out.println(amount);
        System.out.println(customer);
        System.out.println();
        System.out.println("total "+Integer.parseInt(total)+" amount "+Integer.parseInt(amount));

        if(Integer.valueOf(total).equals(Integer.valueOf(amount)))
        {
            System.out.println("paid equal");
            Date date = new Date();
            long dt = date.getTime();
            Timestamp ts = new Timestamp(dt);
            String date_time = new String(ts.toString());
            System.out.println("data time " + date_time);
            Order order = cartService.saveorder(customer,date_time,Integer.valueOf(total),address);
            if(order != null)
            {
                cartService.savebill(cart,order.getOrderID());
            }
            model.addAttribute("customer",customer);
            return "order_placed";
        }
        else
        {
            //go back to payment form
            System.out.println("paid unequal");
            return "payment";
        }
    }


    @GetMapping("/vieworders")
    public String showorders(@RequestParam("customer")String customer, Model model)
    {
        model.addAttribute("username",customer);
        model.addAttribute("orders",cartService.getorders(customer));
        return "orders";
    }

    @GetMapping("/home")
    public String home()
    {
        return "home";
    }


    @GetMapping("/logout")
    public String logout()
    {
        cart.getBasket().clear();
        return "index";
    }

    @GetMapping("/viewbill")
    public String viewbill(@RequestParam("cartID")Integer cartID,@RequestParam("customer") String username,Model model)
    {

        model.addAttribute("customer",username);
        model.addAttribute("bills",cartService.getbills(cartID));
        return "bill_disp";
    }


}
