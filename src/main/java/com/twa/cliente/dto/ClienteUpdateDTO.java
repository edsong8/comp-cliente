package com.twa.cliente.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ClienteUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long empresaId;
    
    private Long grupoEmpresaId;
    
    private String tipoPessoa;
    
    private boolean destinatario;
    
    private boolean inativo;
    
    private boolean naoContribuinte;
    
    @Enumerated(EnumType.STRING)
    private String tiposInscr;
    
    private String cpfCnpj;
    
    private String inscricao;
    
    private String nome;
    
    private String apelido;
    
    private String cep;
    
    private String ender;
    
    private String nr;
    
    private String compl;
    
    private String bairro;
    
    private String cidade;
    
    @Enumerated(EnumType.STRING)
    private String estado;
    
    private Long cPais;
    
    private String pais;
    
    private String codPostal;
    
    private String telefone;
    
    private String email;
    
    private String formaPgto;
    
    private Boolean promob;
    
    private BigDecimal markup;
    
    private BigDecimal frete;
    
    private BigDecimal montagem;
    
    private String layoutProjeto;
    
    private Long classificadorVista;
    
    private Long classificadorFaturado;
    
    private Long classificadorPedidoCompra;
    
    private String accessTokenGabster;
    
    private Date dataInclusao;


}
