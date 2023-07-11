package com.webservices.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.swing.SortOrder;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persistence.commun.component.SysCriterion;
import com.persistence.model.SysPayload;
import com.twa.cliente.dto.ContatoRequestDTO;
import com.twa.cliente.dto.ContatoResponseDTO;
import com.twa.cliente.dto.ContatoUpdateDTO;
import com.twa.cliente.entity.Contato;
import com.twa.cliente.service.ContatoService;
import com.webservices.TecControllerRest;
import com.webservices.resouces.ContatoResource;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/contato")
@Api(tags = "Contato", value = "contato")
@Slf4j
public class ContatoControler extends TecControllerRest implements ContatoResource {

    private final ContatoService service;

    private final ObjectMapper mapper;

    public ContatoControler(@Qualifier("contatoServiceImpl") ContatoService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @PostConstruct
    public void init() {
        log.info("created ContatoControler..");
    }

    public ResponseEntity<SysPayload<ContatoResponseDTO>> list(Long idEmpresa, Integer page, Integer size) {
        final Map<String, SysCriterion> fields = new HashMap<>();
        fields.put("id", new SysCriterion("id", SortOrder.DESCENDING));
        fields.put("empresaId", new SysCriterion("empresaId", Restrictions.eq("empresaId", idEmpresa)));

        var contatos = this.service.findAll(Contato.class, fields, page, size);
        var rows = this.service.count(Contato.class, fields);
        var payload = converterContatoes(contatos, rows);

        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SysPayload<ContatoResponseDTO>> save(ContatoRequestDTO dto, HttpServletResponse response) {
        var contato = this.service.save(getObjectMapper().convertValue(dto, Contato.class));

        return new ResponseEntity<>(converterContato(contato), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SysPayload<ContatoResponseDTO>> edit(Long id, ContatoUpdateDTO dto) {
        var contato = this.service.update(id, getObjectMapper().convertValue(dto, Contato.class));

        return new ResponseEntity<>(converterContato(contato), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SysPayload<ContatoResponseDTO>> delete(Long id) {
        final Map<String, SysCriterion> fields = new HashMap<>();
        fields.put("cargo", new SysCriterion("cargo", FetchMode.JOIN));

        var contato = this.service.deleteById(Contato.class, id, fields);

        return new ResponseEntity<>(converterContato(contato), HttpStatus.OK);
    }

    private SysPayload<ContatoResponseDTO> converterContatoes(List<Contato> contatos, Integer rows) {
        var payload = newPayload();
        payload.setSize(rows);

        var collection = contatos.stream().map(contato -> getObjectMapper().convertValue(contato, ContatoResponseDTO.class)).collect(Collectors.toList());
        payload.setCollection(collection);

        return payload;
    }

    private SysPayload<ContatoResponseDTO> converterContato(Contato contato) {
        var payload = newPayload();

        var dto = getObjectMapper().convertValue(contato, ContatoResponseDTO.class);
        payload.setData(dto);

        return payload;
    }

    @Override
    public ResponseEntity<SysPayload<ContatoResponseDTO>> get(Long id) {
        final Map<String, SysCriterion> fields = new HashMap<>();

        var usuario = this.service.findOneById(Contato.class, id, fields);

        var payload = new SysPayload<ContatoResponseDTO>();
        var dto = getObjectMapper().convertValue(usuario, ContatoResponseDTO.class);
        payload.setData(dto);

        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
    
    
    @Override
    public ObjectMapper getObjectMapper() {
        return this.mapper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SysPayload<ContatoResponseDTO> newPayload() {
        return new SysPayload<ContatoResponseDTO>();
    }
}
