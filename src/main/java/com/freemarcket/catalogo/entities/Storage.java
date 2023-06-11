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
@Table(name = "tb_storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;
    @Getter @Setter private String endereco;

    @ManyToMany
    @JoinTable(
            name = "tb_storage_product",
            joinColumns = @JoinColumn(name = "storage_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @Getter Set<Storage> storages = new HashSet<>();

    public Storage(Storage entity) {
    }
}