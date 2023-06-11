package com.freemarcket.catalogo.DTO;

import java.io.Serializable;

import com.freemarcket.catalogo.entities.Storage;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor


public class StorageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private Long id;
    @Getter @Setter private String endereco;

    public StorageDTO (Storage entity){
        this.id=entity.getId();
        this.endereco=entity.getEndereco();
    }



}
