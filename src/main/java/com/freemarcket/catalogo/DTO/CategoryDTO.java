package com.freemarcket.catalogo.DTO;

import java.io.Serializable;

import com.freemarcket.catalogo.entities.Category;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor


public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private Long id;
    @Getter @Setter private String name;

    public CategoryDTO (Category entity){
        this.id=entity.getId();
        this.name=entity.getName();
    }



}
