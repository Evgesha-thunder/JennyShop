package com.bulish.jennyshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Class Controller for displaying login page
 * @author Bulish Evgenia
 * @version 1.0
 */

@Controller
public class LoginController {



    /**
     * Method is responsible for showing login page
     * @return String
     */
    @GetMapping("/login")
    public String loginPage(){
        return "login_form";
    }

    /**
     * Method is responsible for displaying page in case some errors with access to any resources
     * @return String
     */
    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }


}
