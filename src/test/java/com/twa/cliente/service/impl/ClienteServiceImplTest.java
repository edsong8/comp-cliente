package com.twa.cliente.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.ContextWebApplicationJUnit;
import com.twa.cliente.entity.Cliente;
import com.twa.cliente.repository.ClienteRepositorio;
import com.twa.cliente.repository.impl.ClienteRepositorioImpl;
import com.twa.cliente.service.ClienteService;

@SpringBootTest
public class ClienteServiceImplTest extends ContextWebApplicationJUnit {

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;


    private ClienteRepositorio clienteRepositorio;

    @BeforeEach
    public void initMocks() {
	clienteRepositorio = Mockito.mock(ClienteRepositorioImpl.class);
	ReflectionTestUtils.setField(clienteService, "dao", clienteRepositorio);
    }

    @Test
    @DisplayName(value = "save user")
    void saveTest() {
	
	var cliente = construirEmpresa();
	when(clienteRepositorio.save(cliente)).thenReturn(cliente);
	var userDetail = clienteService.save(cliente);
	
	assertNotNull(userDetail);
	assertEquals(1L, userDetail.getId());
    }

    private Cliente construirEmpresa() {
	var cliente = new Cliente();
	cliente.setId(1L);
	return cliente;
    }
}