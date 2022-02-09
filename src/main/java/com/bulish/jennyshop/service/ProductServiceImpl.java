package com.bulish.jennyshop.service;


import com.bulish.jennyshop.entity.Product;
import com.bulish.jennyshop.entity.ProductRepository;
import com.bulish.jennyshop.entity.ProductSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class is service layer between repository and entity, provides methods for manipulating with entity
 * @author Bulish Evgenia
 * @version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService{

    private final PasswordEncoder encoder;
    private final ProductRepository productRepository;

    public ProductServiceImpl(PasswordEncoder encoder, ProductRepository productRepository) {
         this.encoder = encoder;
         this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(product -> new ProductDto(product.getId(),
                product.getTitle(), product.getCost(),product.getImageLink())).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Page<ProductDto> findWithFilter(Optional<String> nameFilter,
                                           Optional<Integer> min,
                                           Optional<Integer> max,
                                           Optional<Integer> page,
                                           Optional<Integer> size) {

        Specification<Product> spec = Specification.where(null);
        if(nameFilter.isPresent() && ! nameFilter.get().isBlank()){
            spec = spec.and(ProductSpecification.productNamePrefix(nameFilter.get()));

        }

        if (min.isPresent()){
            spec = spec.and(ProductSpecification.minCost(min.get()));
        }

        if (max.isPresent()){
            spec = spec.and(ProductSpecification.maxCost(max.get()));
        }

        return productRepository.findAll(spec,PageRequest.of(page.orElse(1)-1,size.orElse(3))).map(product -> new ProductDto(product.getId(),
                product.getTitle(), product.getCost(),product.getImageLink()));

    }




    @Transactional
    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(product -> new ProductDto(product.getId(),
                product.getTitle(), product.getCost(),product.getImageLink()));
    }

    @Transactional
    @Override
    public void save(ProductDto productDto) {
        Product product = new Product(productDto.getId(),productDto.getTitle(),
                productDto.getCost(),productDto.getImageLink());

        productRepository.save(product);

    }

    @Override
    @Transactional
    public void saveProductWithImage(MultipartFile file, Product product) {
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            try {
                product.setImageLink(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        productRepository.saveAndFlush(product);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
    productRepository.deleteById(id);
    }



}


