package com.freemarcket.catalogo.services;

import com.freemarcket.catalogo.DTO.CategoryDTO;
import com.freemarcket.catalogo.DTO.ProductDTO;
import com.freemarcket.catalogo.entities.Category;
import com.freemarcket.catalogo.entities.Product;
import com.freemarcket.catalogo.repositories.CategoryRepository;
import com.freemarcket.catalogo.repositories.ProductRepository;
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
public class ProductService {
    @Autowired
    private ProductRepository respository;

    @Autowired
    private CategoryRepository categoryRespository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> list = respository.findAll(pageRequest);
        return list.map(x -> new ProductDTO(x));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = respository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entidade não encontrada"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO productDTO) {
        Product entity = new Product();
        copyDtoToEntity(productDTO, entity);
        respository.save(entity);
        return new ProductDTO(entity);
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO productDTO) {

        try {
            Product entity = respository.getReferenceById(id);
            copyDtoToEntity(productDTO, entity);
            entity = respository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id '" + id + "' não encontrado");

        }

    }

    public void delete(Long id) {
        try {
            respository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id '" + id + "' não encontrado");
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Você não pode exluir uma categoria com Produtos cadastrados");
        }


    }
    private void copyDtoToEntity(ProductDTO productDTO, Product entity) {
        entity.setName(productDTO.getName());
        entity.setDescription(productDTO.getDescription());
        entity.setPrice(productDTO.getPrice());

        entity.getCategories().clear();
        for (CategoryDTO categoryDTO : productDTO.getCategories()){
            Category category = categoryRespository.getReferenceById(categoryDTO.getId());
            entity.getCategories().add(category);
        }
    }
}
