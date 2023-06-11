package com.freemarcket.catalogo.repositories;

import com.freemarcket.catalogo.entities.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

}
