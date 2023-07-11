package com.twa.cliente.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sys.persistence.model.TecObjectGenericTwa;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contato")
@Setter
@Getter
@SequenceGenerator(name = "SEQ_ID", sequenceName = "contato_id_seq", allocationSize = 1)
public class Contato extends TecObjectGenericTwa<Contato> {

    private static final long serialVersionUID = 1L;

    @Id
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contato")
    @SequenceGenerator(name = "contato", sequenceName = "contato_id_seq", allocationSize = 1)
    private Long id;
    
    @Column(name = "empresa_id", nullable = false)
    @JsonProperty(value = "empresaId")
    private Long empresaId;
    
    private String nome;

    @Column(name = "telefone_residencial", length = 20)
    private String telefoneResidencial;

    @Column(name = "telefone_comercial", length = 20)
    private String telefoneComercial;

    @Column(name = "telefone_celular", length = 20)
    private String telefoneCelular;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(name = "cliente_id")
    private Long clienteId;

    public Contato() {
	super(Contato.class);

    }

    public Contato(final Long id) {
	super(Contato.class);
	this.setId(id);

    }

}
