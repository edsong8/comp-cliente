package com.twa.cliente.entity;

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

    
    @Column(name = "contato_id")
    private Long contatoId;

    @Column(name = "tipo_pessoa",length = 50, nullable = false)
    private String tipoPessoa;

    @Column(name = "nao_contribuinte", nullable = false)
    private boolean naoContribuinte;

    @Column(name = "cpf_cnpj", length = 14,  nullable = false)
    @JsonProperty(value = "cpfCnpj")    
    private String cpfCnpj;

    
    @Column(length = 200, nullable = false)
    private String nome;

    @Column(name="rg_ie",length = 30)
    private String rgIe;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;

    @Column(length = 10)
    private String cep;
    
    @Column(length = 200)
    private String rua;

    @Column(length = 10)
    private String nr;

    @Column(length = 100)
    private String complemento;

    @Column(length = 75)
    private String bairro;

    @Column(length = 100)
    private String cidade;

    @Column(length = 2)
    private String estado;

    @Column(name="c_pais", length = 4)
    private Long cPais;
    
    @Column(name="x_pais", length = 50)
    private String pais;

    @Column(name = "cod_postal", length = 30 )
    private String codPostal;
    
    @Column(name="cep_entrega",length = 10)
    private String cepEntrega;
    
    @Column(name="rua_entrega",length = 200)
    private String ruaEntrega;

    @Column(name="nr_entrega",length = 10)
    private String nrEntrega;

    @Column(name="complemento_entrega",length = 100)
    private String complementoEntrega;

    @Column(name="bairro_entrega",length = 75)
    private String bairroEntrega;

    @Column(name="cidade_entrega",length = 100)
    private String cidadeEntrega;

    @Column(name="estado_entrega",length = 2)
    private String estadoEntrega;

    @Column(name="endereco_entrega_igual",  nullable = false)
    private boolean enderecoEntregaIgual;
    
    @Column(name="data_cadastro",  nullable = false)
    private Date dataCadastro;

    @Column(name="venda_simplificada",  nullable = false)
    private Boolean vendaSimplificada;

    public Cliente() {
	super(Cliente.class);

    }

    public Cliente(final Long id) {
	super(Cliente.class);
	this.setId(id);

    }

}
