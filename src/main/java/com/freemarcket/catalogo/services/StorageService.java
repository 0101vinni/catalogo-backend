package com.freemarcket.catalogo.services;

import com.freemarcket.catalogo.DTO.StorageDTO;
import com.freemarcket.catalogo.entities.Storage;
import com.freemarcket.catalogo.repositories.StorageRepository;
import com.freemarcket.catalogo.services.excptions.DatabaseException;
import com.freemarcket.catalogo.services.excptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    @Transactional(readOnly = true)
    public Page<StorageDTO> findAllPaged(PageRequest pageRequest) {
        Page<Storage> list = repository.findAll(pageRequest);
        return list.map(x -> new StorageDTO(x));
    }

    @Transactional(readOnly = true)
    public StorageDTO findById(Long id) {
        Optional<Storage> obj = repository.findById(id);
        Storage entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
        return new StorageDTO(entity);
    }

    @Transactional
    public StorageDTO insert(StorageDTO storageDTO) {
        Storage entity = new Storage();
        entity.setEndereco(storageDTO.getEndereco());
        entity = repository.save(entity);
        return new Storage(entity);
    }
    @Transactional
    public StorageDTO update(Long id, StorageDTO storageDTO) {

        try {
            Storage entity = repository.getReferenceById(id);
            entity.setEndereco(storageDTO.getEndereco());
            entity = repository.save(entity);
            return new StorageDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id '" + id + "' não encontrado");

        }

    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id '" + id + "' não encontrado");
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Você não pode exluir um estoque com Produtos cadastrados");
        }


    }

}
