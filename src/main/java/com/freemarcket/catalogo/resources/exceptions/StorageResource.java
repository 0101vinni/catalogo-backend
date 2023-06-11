package com.freemarcket.catalogo.resources.exceptions;

import com.freemarcket.catalogo.DTO.StorageDTO;
import com.freemarcket.catalogo.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/storages")
public class StorageResource {
    @Autowired
    private StorageService service;

    @GetMapping
    public ResponseEntity<Page<StorageDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPage", defaultValue = "12") Integer linesPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        PageRequest pageRequest = PageRequest.of(page, linesPage, Sort.Direction.valueOf(direction),orderBy );
        Page<StorageDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StorageDTO> findById(@PathVariable Long id) {
        StorageDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);

    }

    @PostMapping
    public ResponseEntity<StorageDTO> insert(@RequestBody StorageDTO storageDTO){
        service.insert(storageDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(storageDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(storageDTO);

    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<StorageDTO> update(@PathVariable Long id, @RequestBody StorageDTO storageDTO){
        storageDTO = service.update(id, storageDTO);

        return ResponseEntity.ok().body(storageDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }


}
