package com.webservices.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.ContextWebApplicationJUnit;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twa.cliente.dto.ClienteRequestDTO;
import com.twa.cliente.dto.ClienteUpdateDTO;
import com.twa.cliente.entity.Cliente;
import com.twa.cliente.repository.ClienteRepositorio;
import com.twa.cliente.repository.impl.ClienteRepositorioImpl;
import com.twa.cliente.service.ClienteService;
import com.webservices.resouces.ClienteResource;

@SpringBootTest
public class ClienteControlerTest extends ContextWebApplicationJUnit {

    @Autowired
    @Qualifier("clienteControler")
    private ClienteResource controler;

    @Mock
    private HttpServletResponse httpResponse;

    @Autowired
    @Qualifier("clienteServiceImpl")
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper modelMapper;

    private ClienteRepositorio clienteRepository;

    @BeforeEach
    public void initMocks() {
	clienteRepository = Mockito.mock(ClienteRepositorioImpl.class);
	ReflectionTestUtils.setField(controler, "mapper", modelMapper);
	ReflectionTestUtils.setField(controler, "service", clienteService);
	ReflectionTestUtils.setField(clienteService, "dao", clienteRepository);
    }

    @Test
    @DisplayName(value = "Listar Clientes")
    void listTest() {

	var clientes = criarCliente();

	when(clienteRepository.findAll(Mockito.eq(Cliente.class), Mockito.anyMap(), Mockito.anyInt(), Mockito.anyInt()))
		.thenReturn(clientes);
	var response = controler.list(1L, 0, 10);
	assertNotNull(response);
	assertEquals(2, response.getBody().getCollection().size());
	assertEquals(1L, response.getBody().getCollection().get(0).getId());
    }

    @Test
    @DisplayName(value = "Listar clientes vazias")
    void listVaziasTest() {
	
	when(clienteRepository.findAll(Mockito.eq(Cliente.class), Mockito.anyMap(), Mockito.anyInt(), Mockito.anyInt()))
	.thenReturn(Arrays.asList());
	var response = controler.list(1l, 0, 10);
	assertNotNull(response);
	assertEquals(0, response.getBody().getCollection().size());
	
    }

    @Test
    @DisplayName(value = "get {id} cliente")
    void getTest() {
	var cliente = criarEmpresa();
	when(clienteRepository.findOneById(Mockito.eq(Cliente.class), Mockito.anyLong(), Mockito.anyMap()))
		.thenReturn(cliente);
	
	var response = controler.get(1L);
	
	assertNotNull(response);
	assertEquals(1L, response.getBody().getData().getId());
    }

    @Test
    @DisplayName(value = "save cliente")
    void saveTest() {
	var empresaRequest = criarEmpresaRequest();
	var cliente = criarEmpresa();
	when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
	var response = controler.save(empresaRequest, httpResponse);
	assertNotNull(response);
	assertEquals(1L, response.getBody().getData().getId());
    }

    @Test
    @DisplayName(value = "edit cliente, encontrou")
    void editTest() {
	var empresaUpdate = criarEmpresaUpdate();
	var cliente = criarEmpresa();
	cliente.setNome("Teste Atualizado");
	when(clienteRepository.update(Mockito.any(Cliente.class))).thenReturn(cliente);
	when(clienteRepository.count(Mockito.eq(Cliente.class), Mockito.anyMap())).thenReturn(1);
	var response = controler.edit(1L, empresaUpdate);
	assertNotNull(response);
	assertEquals(1L, response.getBody().getData().getId());
    }

    @Test
    @DisplayName(value = "edit cliente, não encontrou")
    void editNaoEncontrouTest() {
	var empresaUpdate = criarEmpresaUpdate();

	when(clienteRepository.count(Mockito.eq(Cliente.class), Mockito.anyMap())).thenReturn(0);
	var throwable = Assertions.assertThrows(RuntimeException.class, () -> {
	    controler.edit(1L, empresaUpdate);
	});
	assertEquals("OBJECT_NOT_FOUND", throwable.getMessage());
    }
    
    @Test
    @DisplayName(value = "delete cliente, encontrou")
    void deleteTest() {
	var cliente = criarEmpresa();
	when(clienteRepository.deleteById(Mockito.eq(Cliente.class), Mockito.anyLong(),Mockito.anyMap())).thenReturn(cliente);
	when(clienteRepository.findOneById(Mockito.eq(Cliente.class), Mockito.anyLong(), Mockito.anyMap())).thenReturn(cliente);
	var response = controler.delete(1L);
	assertNotNull(response);
	assertEquals(1L, response.getBody().getData().getId());
    }

    @Test
    @DisplayName(value = "delete cliente, não encontrou")
    void deleteNaoEncontrouTest() {
	when(clienteRepository.deleteById(Mockito.eq(Cliente.class), Mockito.anyLong(), Mockito.eq(null))).thenReturn(null);
	var response = controler.delete(1L);
	assertNotNull(response);
	assertEquals(null, response.getBody().getData());
    }

    private ClienteUpdateDTO criarEmpresaUpdate() {
	var cliente = new ClienteUpdateDTO();
	return cliente;
    }

    private ClienteRequestDTO criarEmpresaRequest() {
	var cliente = new ClienteRequestDTO();
	cliente.setNome("Teste 01");
	return cliente;
    }

    private Cliente criarEmpresa() {
	var cliente01 = new Cliente(1L);
	cliente01.setNome("Teste 01");
	return cliente01;
    }

    private List<Cliente> criarCliente() {
	var cliente01 = new Cliente(1L);
	cliente01.setNome("Teste 01");
	var empresa02 = new Cliente(2L);
	empresa02.setNome("Teste 02");
	return Arrays.asList(cliente01, empresa02);
    }
}