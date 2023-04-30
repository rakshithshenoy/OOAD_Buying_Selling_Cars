package com.mvcjava.ecomm.product;

import com.mvcjava.ecomm.service.ProductService;
import com.mvcjava.ecomm.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class ProductController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private RetailerService retailerService;
    @GetMapping("/retailer")
    public String getretailerpage()
    {
        return "retailer_dashboard";
    }

    @GetMapping("/addproducts")
    public String getproductform(@RequestParam("r_name")String name ,Model model)
    {
        // System.out.println("********here");
        model.addAttribute("r_name",name);
        model.addAttribute("productRequest",new Product());
        return "add_prod";
    }

    @PostMapping("/addproducts")
    public String postaddproduct(@RequestParam("file")MultipartFile file,@RequestParam("name") String name,@RequestParam("category") String category,@RequestParam("description") String description,@RequestParam("price") Integer price,@RequestParam("r_name") String rname,Model model)
    {
        System.out.println("ret name " + rname);
        Integer retId = retailerService.getIdByName(rname);
        System.out.println("ret id " + retId);
        productService.storeproduct(file,name,category,description,price,retId);
        System.out.println();
        model.addAttribute("r_name",rname);
        return "retailer_dashboard";
    }

    @GetMapping("/retailerview")
    public String ret_view(@RequestParam("r_name")String rname, Model model)
    {
        Integer rID = retailerService.getIdByName(rname);
        System.out.println("ret id " + rID);
        model.addAttribute("r_name",rname);
        model.addAttribute("resultproducts",productService.getProdbyretId(rID));
        return "retailer_view";
    }

    @GetMapping("/displayproducts")
    public String showproducts(Model model)
    {
        model.addAttribute("listproducts",productService.getAllproducts());
        return "products";
    }

    @GetMapping("/viewstationary")
    public String prod_stationary(@RequestParam("name") String name, Model model)
    {
        model.addAttribute("username",name);
        model.addAttribute("resultproducts",productService.getstationary());
        return "show_results";
    }

    @GetMapping("/viewsports")
    public String prod_sports(@RequestParam("name") String name, Model model)
    {
        model.addAttribute("username",name);
        model.addAttribute("resultproducts",productService.getsports());
        return "show_results";
    }
    @GetMapping("/viewappliances")
    public String prod_appliances(@RequestParam("name") String name, Model model)
    {
        model.addAttribute("username",name);
        model.addAttribute("resultproducts",productService.getapplicance());
        return "show_results";
    }

    @GetMapping("/viewgadgets")
    public String prod_gadgets(@RequestParam("name") String name,Model model)
    {
        model.addAttribute("r_name",name);
        model.addAttribute("resultproducts",productService.getgadgets());
        return "show_results";
    }
    @GetMapping("/products")
    public String getProducts(@RequestParam("p_name") String productName,@RequestParam("u_name") String u_name,Model model){
        model.addAttribute("username",u_name);
        model.addAttribute("resultproducts",productService.getProducts(productName.toLowerCase()));
        return "show_search_results";

    }

}
