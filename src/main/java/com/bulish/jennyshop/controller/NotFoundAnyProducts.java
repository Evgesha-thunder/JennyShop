package com.bulish.jennyshop.controller;


/**
 * Class is responsible for errors handling
 * @author Bulish Evgenia
 * @version 1.0
 */
public class NotFoundAnyProducts extends RuntimeException{
    public NotFoundAnyProducts(String message) {
        super(message);
    }
}
