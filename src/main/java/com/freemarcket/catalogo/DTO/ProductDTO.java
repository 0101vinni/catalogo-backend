package com.freemarcket.catalogo.DTO;

import com.freemarcket.catalogo.entities.Category;
import com.freemarcket.catalogo.entities.Product;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private Double price;

    @Getter @Setter private List<CategoryDTO> categories = new ArrayList<>();

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
    }

    public ProductDTO(Product entity, Set<Category> categories) {
        this(entity);
        categories.forEach(category -> this.categories.add(new CategoryDTO(category)));
    }
}
