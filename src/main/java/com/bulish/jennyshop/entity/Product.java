package com.bulish.jennyshop.entity;



import com.bulish.jennyshop.service.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotBlank(message = "title can't be empty")
    @Column(name = "title")
    private String title;


    @Min(value = 100, message = "the cost can't be less then 100")
    @Max(value = 10000, message = "the cost can't be higher then 10 000")
    @Column(name = "cost")
    private int cost;


    @Column(name = "image")
    private String imageLink;


    public Product(ProductDto productDto) {
        this.id = productDto.getId();
        this.title = productDto.getTitle();
        this.cost = productDto.getCost();
        this.imageLink = productDto.getImageLink();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product that = (Product) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
