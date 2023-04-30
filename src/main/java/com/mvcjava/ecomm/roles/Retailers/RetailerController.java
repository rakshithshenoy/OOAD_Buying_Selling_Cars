package com.mvcjava.ecomm.roles.Retailers;

import com.mvcjava.ecomm.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RetailerController {
    @Autowired
    private RetailerService retailerService;


    @GetMapping("/displayretailers")
    public String displayretailers(Model model) {
        model.addAttribute("listretailers", retailerService.getAllRetailers());
        return "retailers";
    }

    @GetMapping("/getRetailerIdByName")
    public Integer getIdByName(String name) {
        return retailerService.getIdByName(name);
    }

    @GetMapping("/retailerdashboard")
    public String retailerpage(@RequestParam("r_name") String rname, Model model) {
        model.addAttribute("r_name", rname);
        return "retailer_dashboard";
    }

    @GetMapping("/removeretailer")
    public String removeretailer(@RequestParam("rID") Integer rid, Model model)
    {
        model.addAttribute("listretailers",retailerService.removeretailer(rid));
        return "retailers";
    }

}
