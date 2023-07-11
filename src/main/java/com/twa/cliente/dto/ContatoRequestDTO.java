package com.twa.cliente.dto;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ContatoRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato")
    @SequenceGenerator(name = "contato", sequenceName = "contato_id_seq", allocationSize = 1)
    private Long id;
    
    private Long empresaId;
    
    private String nome;

    private String telefoneResidencial;

    private String telefoneComercial;

    private String telefoneCelular;

    private String email;

    private Long clinteId;
}
