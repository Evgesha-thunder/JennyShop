package com.bulish.jennyshop.controller;

import com.bulish.jennyshop.entity.ShoppingCart;
import com.bulish.jennyshop.service.ProductDto;
import com.bulish.jennyshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.Optional;


/**
 * Class CartController for working with entity Cart
 * @author Bulish Evgenia
 * @version 1.0
 */

@Controller
@RequestMapping("/cart")
@SessionAttributes("shoppingCart")
@AllArgsConstructor
public class CartController {

    /**
     * Field is responsible for providing access to methods Product entity
     */
    private final ProductService productService;

    /**
     * Field Logger is intended to logging
     * @see Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    /**
     * Method is responsible for displaying cart list
     * @return String
     */
    @GetMapping("/all")
    public String showAllcart(){
        logger.info("Получаем список всех продуктов в корзине");
        return "cart_list";
    }

    /**
     * Method is responsible for adding product to cart
     * @return String
     */
    @GetMapping("/add")
    public RedirectView addToCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart){
        Optional<ProductDto> product = productService.findById(id);
        if (!product.isPresent()){
            throw new NotFoundAnyProducts("this product doesn't exist");
        }
        cart.addProduct(product.get());
        logger.info("Добавляем продукт в корзину");

        return new RedirectView("/product");
    }

    /**
     * Method is responsible for deleting product from cart
     * @return String
     */
    @GetMapping("/remove")
    public RedirectView removeFromCart(@RequestParam Long id, @ModelAttribute ShoppingCart cart){
        Optional<ProductDto> product = productService.findById(id);
        if (!product.isPresent()){
            throw new NotFoundAnyProducts("this product doesn't exist");
        }
        cart.deleteProduct(product.get());
        logger.info("удалаем продукт из корзины");
        return new RedirectView("/cart/all");
    }

}
