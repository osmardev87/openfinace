package tech.gomesdev87.finace.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {

    @GetMapping("/")
    public String getLoginPage() {
        return "login";
    }
}
