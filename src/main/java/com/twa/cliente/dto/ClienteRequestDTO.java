package com.twa.cliente.dto;

import java.io.Serializable;
import java.util.Date;

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
public class ClienteRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente")
    @SequenceGenerator(name = "cliente", sequenceName = "cliente_id_seq", allocationSize = 1)
    private Long id;
    
    private Long empresaId;

    private Long contatoId;

    private String tipoPessoa;

    private boolean naoContribuinte;

    private String cpfCnpj;

    private String nome;

    private String rgIe;

    private Date dataNascimento;

    private String cep;
    
    private String rua;

    private String nr;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private Long cPais;
    
    private String pais;

    private String codPostal;
    
    private String cepEntrega;
    
    private String ruaEntrega;

    private String nrEntrega;

    private String complementoEntrega;

    private String bairroEntrega;

    private String cidadeEntrega;

    private String estadoEntrega;

    private boolean enderecoEntregaIgual;
    
    private Date dataCadastro;

    private Boolean vendaSimplificada;
}
