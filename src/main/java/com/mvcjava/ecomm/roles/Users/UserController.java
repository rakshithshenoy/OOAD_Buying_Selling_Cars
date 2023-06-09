package com.mvcjava.ecomm.roles.Users;

import com.mvcjava.ecomm.repository.UsersRepository;
import com.mvcjava.ecomm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController
{
    @Autowired
    private UsersService usersService;
    private UsersRepository usersRepository;

    @GetMapping("/index")
    public String getindex()
    {
        return "index";
    }

    //for debugging
    @GetMapping("/hello")
    public String gethello()
    {
        return "hello";
    }

    @GetMapping("/login")
    public String getLoginpage(Model model)
    {
        model.addAttribute("loginRequest",new UserModel());
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterpage(Model model)
    {
        model.addAttribute("registerRequest",new UserModel());
        return "register";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel)
    {
        System.out.println("register request: "+userModel);
        UserModel registeruser = usersService.registerUser(userModel.getLogin(),userModel.getPassword(),userModel.getMailid(),userModel.getRole());
        return registeruser == null?"error":"redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel,Model model)
    {
        System.out.println("login request: "+userModel);
        UserModel authenticated = usersService.authentication(userModel.getLogin(),userModel.getPassword());
        if(authenticated != null)
        {
            model.addAttribute("username",authenticated.getLogin());
            String name = authenticated.getRole();
            if(name.equals("user"))
            {
                return "user_dashboard2";
            }
            if(name.equals("admin"))
            {
                return "admin_dashboard";
            }
            if(name.equals("retailer"))
            {
                model.addAttribute("r_name",userModel.login);
                return "retailer_dashboard";
            }
            else
            {
                return "error";
            }
        }
        else
        {
            return "error";
        }
    }

    @GetMapping("/homefromuser")
    public String homepage(@RequestParam("customer") String username, Model model)
    {
        model.addAttribute("username",username);
        return "user_dashboard2";
    }

    @GetMapping("/return")
    public String returnorder(@RequestParam("customer") String username,Model model)
    {
        model.addAttribute("customer",username);
        return "Return";
    }
}
