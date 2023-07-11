package com.twa.cliente.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContatoUpdateDTO implements Serializable {

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
