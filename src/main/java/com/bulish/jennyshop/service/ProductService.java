package com.bulish.jennyshop.service;

import com.bulish.jennyshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;


/**
 * Interface is service layer between repository and entity, provides methods for manipulating with entity
 * @author Bulish Evgenia
 * @version 1.0
 */

public interface ProductService {


    List<ProductDto> findAll();

    Page<ProductDto> findWithFilter(Optional<String> nameFilter,
                                    Optional<Integer> min,
                                    Optional<Integer> max,
                                    Optional<Integer> page,
                                    Optional<Integer> size);



    void saveProductWithImage(MultipartFile file, Product product);

    Optional<ProductDto> findById(Long id);


    void save(ProductDto product);

    void deleteById(Long id);


}