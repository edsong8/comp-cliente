package com.webservices.resouces;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.persistence.model.SysPayload;
import com.twa.cliente.dto.ContatoRequestDTO;
import com.twa.cliente.dto.ContatoResponseDTO;
import com.twa.cliente.dto.ContatoUpdateDTO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ContatoResource {

    @ApiOperation(value = "Listar contatos por empresa")
    @ApiResponses({@ApiResponse(message = "Encontrar contatoes por empresa", code = 200),
            @ApiResponse(message = "Parameter invalid", code = 400)})
    @GetMapping(value = "/{idEmpresa}/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)})
    public ResponseEntity<SysPayload<ContatoResponseDTO>> list(
            @PathVariable(value = "idEmpresa", required = true) Long idEmpresa,
            @PathVariable(value = "page", required = true) Integer page,
            @PathVariable(value = "size", required = true) Integer size);

    @ApiOperation(value = "Pegar contato")
    @ApiResponses({@ApiResponse(message = "Pegar contato", code = 200, response = ContatoResponseDTO.class),
            @ApiResponse(message = "Parameter {id} invalid", code = 400)})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)
    })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SysPayload<ContatoResponseDTO>> get(
            @PathVariable(value = "id", required = true) Long id);

    @ApiOperation(value = "Salvar contato")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)
    })
    @ApiResponses({@ApiResponse(message = "Salvar contato", code = 200, response = ContatoResponseDTO.class),
            @ApiResponse(message = "Parameter invalid", code = 400)})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SysPayload<ContatoResponseDTO>> save(
            @RequestBody(required = true) ContatoRequestDTO dto, HttpServletResponse response);

    @ApiOperation(value = "Atualizar contato")
    @ApiResponses({@ApiResponse(message = "Atualizar contato", code = 200, response = ContatoResponseDTO.class),
            @ApiResponse(message = "Parameter {id} invalid", code = 400)})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SysPayload<ContatoResponseDTO>> edit(
            @PathVariable(value = "id", required = true) Long id,
            @RequestBody(required = true) ContatoUpdateDTO dto);

    @ApiOperation(value = "Deletar contato")
    @ApiResponses({@ApiResponse(message = "Deletar contato", code = 200, response = ContatoResponseDTO.class),
            @ApiResponse(message = "Parameter {id} invalid", code = 400)})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SysPayload<ContatoResponseDTO>> delete(
            @PathVariable(value = "id", required = true) Long id);
}
