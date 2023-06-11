package com.freemarcket.catalogo.resources.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

}
