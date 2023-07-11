package com.twa.cliente.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContatoResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long empresaId;
    
    private String nome;

    private String telefoneResidencial;

    private String telefoneComercial;

    private String telefoneCelular;

    private String email;

    private Long clienteId;
}
