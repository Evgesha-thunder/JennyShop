package com.bulish.jennyshop.controller;

import com.bulish.jennyshop.entity.ShoppingCart;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * Class ControllerAdvice is responsible for providing access to this global variable shoppingCart from others classes
 * @author Bulish Evgenia
 * @version 1.0
 */
@ControllerAdvice
public class GlobalCartController {

    @ModelAttribute("shoppingCart")
    public ShoppingCart shoppingCart() {
        return new ShoppingCart();
    }
}
