package com.twa.cliente.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sys.persistence.model.TecObjectGenericTwa;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Setter
@Getter
@SequenceGenerator(name = "SEQ_ID", sequenceName = "cliente_id_seq", allocationSize = 1)
public class Cliente extends TecObjectGenericTwa<Cliente> {

    private static final long serialVersionUID = 1L;

    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente")
    @SequenceGenerator(name = "cliente", sequenceName = "cliente_id_seq", allocationSize = 1)
    private Long id;
    
    @Column(name = "empresa_id", nullable = false)
    @JsonProperty(value = "empresaId")
    private Long empresaId;
    
    @Column(name = "grupo_empresa_id", nullable = false)
    @JsonProperty(value = "grupoEmpresaId")
    private Long grupoEmpresaId;
    
    @Column(name = "tipo_pessoa",length = 50, nullable = false)
    private String tipoPessoa;
    
    @Column(nullable = false)
    private boolean destinatario;
    
    @Column(nullable = false)
    private boolean inativo;
    
    @Column(name = "nao_contribuinte", nullable = false)
    private boolean naoContribuinte;
    
    @Column(name = "tipo_inscricao", nullable = false)
    private String tiposInscr;
    
    @Column(name = "cpf_cnpj", length = 14,  nullable = false)
    @JsonProperty(value = "cpfCnpj")    
    private String cpfCnpj;
    
    @Column(length = 50)
    private String inscricao;

    @Column(length = 200, nullable = false)
    private String nome;
    
    @Column(length = 200)
    private String apelido;
    
    @Column(length = 10)
    private String cep;

    @Column(length = 100)
    private String ender;
    
    @Column(length = 10)
    private String nr;
    
    @Column(length = 50)
    private String compl;
    
    @Column(length = 75)
    private String bairro;
    
    @Column(length = 100)
    private String cidade;
    
    @Column(name = "estado", length = 30)
    //@Enumerated(EnumType.STRING)
    private String estado;
    
    @Column(name="c_pais", length = 4)
    private Long cPais;
    
    @Column(name="x_pais", length = 50)
    private String pais;

    @Column(name = "cod_postal", length = 30 )
    private String codPostal;
    
    @Column(length = 20 )
    private String telefone;
    
    @Column(length = 60 )
    private String email;
    
    @Column(name="forma_pgto",length = 10)
    private String formaPgto;

    
    @Column(nullable = false)
    private Boolean promob;
    
    @Column(precision = 15, scale = 2)    
    private BigDecimal markup;
    
    @Column(precision = 15, scale = 2)    
    private BigDecimal frete;

    @Column(precision = 15, scale = 2)    
    private BigDecimal montagem;
    
    @Column(name = "layout_projeto", length = 20 )
    private String layoutProjeto;
    
    @Column(name = "conta_pg_ant", length = 4 )
    private Long classificadorVista;
    
    @Column(name = "conta_pg_fat", length = 4 )
    private Long classificadorFaturado;
    
    @Column(name = "conta_pg_ped", length = 4 )
    private Long classificadorPedidoCompra;
    
    @Column(name = "access_token_gabster", length = 200 )
    private String accessTokenGabster;
    
    @Column(name = "data_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInclusao;
    
    public Cliente() {
	super(Cliente.class);

    }

    public Cliente(final Long id) {
	super(Cliente.class);
	this.setId(id);

    }

}
