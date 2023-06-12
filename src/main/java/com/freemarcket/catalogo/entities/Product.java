package com.freemarcket.catalogo.entities;

import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;
    @Getter @Setter private String name;

    @Column(columnDefinition = "TEXT")
    @Getter @Setter private String description;
    @Getter @Setter private Double price;
    
    @ManyToMany(mappedBy = "storages")
    @Getter Set<Storage> storages = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @Getter @Setter Set<Category> categories = new HashSet<>();

}
