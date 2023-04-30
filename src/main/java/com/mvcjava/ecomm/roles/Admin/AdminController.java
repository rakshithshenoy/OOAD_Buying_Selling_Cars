package com.mvcjava.ecomm.roles.Admin;

import com.mvcjava.ecomm.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class AdminController
{
    @Autowired
    private RetailerService retailerService;

    @GetMapping("/displayretailers")
    public String displayretailers(Model model)
    {
        model.addAttribute("listretailers",retailerService.getAllRetailers());
        return "retailers";
    }
}
