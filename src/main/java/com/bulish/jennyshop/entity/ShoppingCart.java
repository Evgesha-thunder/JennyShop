package com.bulish.jennyshop.entity;

import com.bulish.jennyshop.service.ProductDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Class is responsible for providing methods to manipulate with Cart and Product Entity
 * @author Bulish Evgenia
 * @version 1.0
 */

@Data
@Builder
@NoArgsConstructor
public class ShoppingCart {

    /**
     * Field is responsible for keeping Product entities
     */
    private final Map<ProductDto,Integer> productQuantity = new HashMap<>();

    /**
     * Method is responsible for adding product to map
     * @param productDto is Product entity
     */
    public void addProduct(ProductDto productDto) {
        productQuantity.merge(productDto, 1, (prev, cur )-> prev + 1);
    }

    /**
     * Method is responsible for deleting product from map
     * @param productDto is Product entity
     */
    public void deleteProduct(ProductDto productDto) {
        if (productQuantity.containsKey(productDto)){
            Integer quantity = productQuantity.get(productDto);
            deleteProduct(productDto, quantity);
            return;
        }
        throw  new IllegalArgumentException("product can't be found in the cart");
    }


    /**
     * Method is responsible for deleting Product entity from cart
     * @param product is Product entity
     * @param count is  is used control quantity left products in the carta
     */
    private void deleteProduct(ProductDto product, Integer count) {
        if (count > 1) {
           productQuantity.put(product, count - 1);
        } else {
            productQuantity.remove(product);
        }
    }



    public Map<ProductDto, Integer> getProductQuantity() {
        return new HashMap<>(productQuantity);
    }

    public int getCount() {
        return productQuantity.values().stream()
                .reduce(0, Integer::sum);
    }

}

