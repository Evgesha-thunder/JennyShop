package com.bulish.jennyshop.controller;


import com.bulish.jennyshop.entity.Product;
import com.bulish.jennyshop.service.ProductDto;
import com.bulish.jennyshop.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.Optional;


/**
 * Class Controller is respsonsible for working with Product Entity
 * @author Bulish Evgenia
 * @version 1.0
 */

@Controller
@RequestMapping("/product")
public class ProductController {

    /**
     * Field Logger is intended to logging
     * @see Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * Field productService is injected in this class for giving access to Product's methods
     */
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Method is responsible for displaying filtered Product list
     * @param model is responsible for interacting between Controller and View
     * @param nameFilter is parameter that coming from view and then is used for filtering Product by title
     * @param min is parameter that coming from view and then is used for filtering Product by price
     * @param max  is parameter that coming from view and then is used for filtering Product  by price
     * @param page  is parameter that coming from view and then is used for pagination view of all Products
     * @param size     is parameter that coming from view and then is used for pagination view of all Products
     * @return String
     */
    @GetMapping
    public String allProducts(Model model,
                              @RequestParam(name = "nameFilter") Optional<String> nameFilter,
                              @RequestParam(name = "min") Optional<Integer> min,
                              @RequestParam(name = "max") Optional<Integer> max,
                              @RequestParam(name = "page") Optional<Integer> page,
                              @RequestParam(name = "size") Optional<Integer> size){

         logger.info("Получаем список всех продуктов");
         model.addAttribute("products",productService.findWithFilter(nameFilter,min,max,page,size));

         return "products";

    }


    /**
     * Method is responsible for displaying page form for adding new Product entity in dataBase
     * @param model is responsible for interacting between Controller and View
     * @return view html
     */
    @GetMapping("/new")
    public String addNewProduct(Model model){
       logger.info("Добавляем новый продукт");
       model.addAttribute("product", new ProductDto());
       return "add_product";
    }

    /**
     * Method is responsible for displaying form for editing already existing entity Product
     * @param model is responsible for interacting between Controller and View and we can put into model
     * different type of data for interacting with them in view
     * @param id is a variable that we get through url and use for searching an entity
     * Product that ought to be edited
     * @return String
     */
    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model){
       model.addAttribute("product", productService.findById(id).orElseThrow(()
               ->new NotFoundAnyProducts("There is no any products with that id")));
       return "add_product";

    }

    /**
     * Method is responsible for displaying one chosen Product by id
     * @param model is responsible for interacting between Controller and View and we can put into model
     * different type of data for interacting with them in view
     * @param id is a variable that we get through url and use for searching an entity Product
     * @return String
     */
    @GetMapping("info/{id}")
    public String showOneProduct(@PathVariable("id") Long id, Model model) {
        Optional<ProductDto> product = productService.findById(id);
        if (!product.isPresent()) {
            throw  new NotFoundAnyProducts("No one product exist with this id");
        }
        model.addAttribute("product", product.get());
        return "one_product";
    }

    /**
     * Method is responsible for saving or editing already existing entity Product in database
     * @param product is an entity that are filled in the view and come through the model
     * @param image is responsible for adding image in Product form
     * @param bindingResult is an parameter that responsible for handling errors that can
     * occur while filling the form with data
     *  @return String
     */
    @PostMapping
    public String updateProduct(@Valid @ModelAttribute("product") ProductDto product, BindingResult bindingResult,
                                @RequestParam(name = "image") MultipartFile image){
       if (bindingResult.hasErrors()){
           return "add_product";
       }
       Product product1 = new Product(product);

       logger.info("Мы обнавляем продукт");
          productService.saveProductWithImage(image,product1);
        return "redirect:/product";
    }

    /**
     * Method is responsible for deleting  an  entity Product from database
     * @param id is parameter that is used for searching a particular entity in database
     * @return String
     */
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id){
        logger.info("Удаляем продукт из базы данных");
       productService.deleteById(id);
       return "redirect:/product";
    }

    /**
     * Method is responsible for handling  errors that can occur while manipulating with entity Product
     * @param ex is a parameter that comes from another class and responsible for displaying error message
     * @return modelAndView
     */
    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundAnyProducts ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }


}
