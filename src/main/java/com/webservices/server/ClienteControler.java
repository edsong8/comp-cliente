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
import com.twa.cliente.dto.ClienteRequestDTO;
import com.twa.cliente.dto.ClienteResponseDTO;
import com.twa.cliente.dto.ClienteUpdateDTO;
import com.twa.cliente.entity.Cliente;
import com.twa.cliente.service.ClienteService;
import com.webservices.TecControllerRest;
import com.webservices.resouces.ClienteResource;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cliente")
@Api(tags = "Cliente", value = "cliente")
@Slf4j
public class ClienteControler extends TecControllerRest implements ClienteResource {

    private final ClienteService service;

    private final ObjectMapper mapper;

    public ClienteControler(@Qualifier("flienteServiceImpl") ClienteService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @PostConstruct
    public void init() {
        log.info("created ClienteControler..");
    }

    public ResponseEntity<SysPayload<ClienteResponseDTO>> list(Long idEmpresa, Integer page, Integer size) {
        final Map<String, SysCriterion> fields = new HashMap<>();
        fields.put("id", new SysCriterion("id", SortOrder.DESCENDING));
        fields.put("empresaId", new SysCriterion("empresaId", Restrictions.eq("empresaId", idEmpresa)));

        var flientees = this.service.findAll(Cliente.class, fields, page, size);
        var rows = this.service.count(Cliente.class, fields);
        var payload = converterClientees(flientees, rows);

        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SysPayload<ClienteResponseDTO>> save(ClienteRequestDTO dto, HttpServletResponse response) {
        var cliente = this.service.save(getObjectMapper().convertValue(dto, Cliente.class));

        return new ResponseEntity<>(converterCliente(cliente), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SysPayload<ClienteResponseDTO>> edit(Long id, ClienteUpdateDTO dto) {
        var cliente = this.service.update(id, getObjectMapper().convertValue(dto, Cliente.class));

        return new ResponseEntity<>(converterCliente(cliente), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SysPayload<ClienteResponseDTO>> delete(Long id) {
        final Map<String, SysCriterion> fields = new HashMap<>();
        fields.put("cargo", new SysCriterion("cargo", FetchMode.JOIN));

        var cliente = this.service.deleteById(Cliente.class, id, fields);

        return new ResponseEntity<>(converterCliente(cliente), HttpStatus.OK);
    }

    private SysPayload<ClienteResponseDTO> converterClientees(List<Cliente> flientees, Integer rows) {
        var payload = newPayload();
        payload.setSize(rows);

        var collection = flientees.stream().map(cliente -> getObjectMapper().convertValue(cliente, ClienteResponseDTO.class)).collect(Collectors.toList());
        payload.setCollection(collection);

        return payload;
    }

    private SysPayload<ClienteResponseDTO> converterCliente(Cliente cliente) {
        var payload = newPayload();

        var dto = getObjectMapper().convertValue(cliente, ClienteResponseDTO.class);
        payload.setData(dto);

        return payload;
    }

    @Override
    public ResponseEntity<SysPayload<ClienteResponseDTO>> get(Long id) {
        final Map<String, SysCriterion> fields = new HashMap<>();

        var usuario = this.service.findOneById(Cliente.class, id, fields);

        var payload = new SysPayload<ClienteResponseDTO>();
        var dto = getObjectMapper().convertValue(usuario, ClienteResponseDTO.class);
        payload.setData(dto);

        return new ResponseEntity<>(payload, HttpStatus.OK);
    }
    
    
    @Override
    public ObjectMapper getObjectMapper() {
        return this.mapper;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SysPayload<ClienteResponseDTO> newPayload() {
        return new SysPayload<ClienteResponseDTO>();
    }
}
