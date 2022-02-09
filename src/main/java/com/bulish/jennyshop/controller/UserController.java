package com.bulish.jennyshop.controller;


import com.bulish.jennyshop.service.UserDto;
import com.bulish.jennyshop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;



/**
 * Class Controller for working with User entity
 * @author Bulish Evgenia
 * @version 1.0
 */

@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * Field Logger is intended to logging
     */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * Field userService is injected in this class for giving access to methods
     */
     private final UserService userService;




  @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }


    /**
     * Method is responsible for displaying registration page
     * @param model is responsible for interacting between Controller and View
     * @return String
     */
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("user", new UserDto());
        return "registration";
    }


    /**
     * Method is responsible for saving new User entity in database and redirecting to current User list
     * @param userDto is parameter that is a new Entity with data that coming from view and is saved in database
     * @param result is an parameter that responsible for handling errors that occur while filling the form with data
     * @return String
     */
    @PostMapping("/registration")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result){
        if (result.hasErrors()){
            return "registration";
        }
        userDto.setRoles(new HashSet<>());
        logger.info("Сохраняем нового пользователя в базе данных");
        userService.save(userDto);
        return "redirect:/login";

    }


}
