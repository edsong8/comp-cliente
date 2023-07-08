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
import com.twa.cliente.dto.ClienteRequestDTO;
import com.twa.cliente.dto.ClienteResponseDTO;
import com.twa.cliente.dto.ClienteUpdateDTO;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ClienteResource {

    @ApiOperation(value = "Listar clientes por empresa")
    @ApiResponses({@ApiResponse(message = "Encontrar clientees por empresa", code = 200),
            @ApiResponse(message = "Parameter invalid", code = 400)})
    @GetMapping(value = "/{idEmpresa}/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)})
    public ResponseEntity<SysPayload<ClienteResponseDTO>> list(
            @PathVariable(value = "idEmpresa", required = true) Long idEmpresa,
            @PathVariable(value = "page", required = true) Integer page,
            @PathVariable(value = "size", required = true) Integer size);

    @ApiOperation(value = "Pegar cliente")
    @ApiResponses({@ApiResponse(message = "Pegar cliente", code = 200, response = ClienteResponseDTO.class),
            @ApiResponse(message = "Parameter {id} invalid", code = 400)})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)
    })
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SysPayload<ClienteResponseDTO>> get(
            @PathVariable(value = "id", required = true) Long id);

    @ApiOperation(value = "Salvar cliente")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)
    })
    @ApiResponses({@ApiResponse(message = "Salvar cliente", code = 200, response = ClienteResponseDTO.class),
            @ApiResponse(message = "Parameter invalid", code = 400)})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SysPayload<ClienteResponseDTO>> save(
            @RequestBody(required = true) ClienteRequestDTO dto, HttpServletResponse response);

    @ApiOperation(value = "Atualizar cliente")
    @ApiResponses({@ApiResponse(message = "Atualizar cliente", code = 200, response = ClienteResponseDTO.class),
            @ApiResponse(message = "Parameter {id} invalid", code = 400)})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SysPayload<ClienteResponseDTO>> edit(
            @PathVariable(value = "id", required = true) Long id,
            @RequestBody(required = true) ClienteUpdateDTO dto);

    @ApiOperation(value = "Deletar cliente")
    @ApiResponses({@ApiResponse(message = "Deletar cliente", code = 200, response = ClienteResponseDTO.class),
            @ApiResponse(message = "Parameter {id} invalid", code = 400)})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "Header", name = "Authorization", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "Header", name = "client_token", required = true, dataTypeClass = String.class)
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SysPayload<ClienteResponseDTO>> delete(
            @PathVariable(value = "id", required = true) Long id);
}
