package com.tutorial.dynamocrud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDto {

    private String id;
    private String nombre;
    private DireccionDto direccionDto;
}
