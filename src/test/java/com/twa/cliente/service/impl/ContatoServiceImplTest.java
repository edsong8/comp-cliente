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
import com.twa.cliente.entity.Contato;
import com.twa.cliente.repository.impl.ContatoRepositorioImpl;
import com.twa.cliente.service.ClienteService;

@SpringBootTest
public class ContatoServiceImplTest extends ContextWebApplicationJUnit {

    @Autowired
    @Qualifier("contatoServiceImpl")
    private ClienteService contatoService;


    private ContatoRepositorioImpl contatoRepositorio;

    @BeforeEach
    public void initMocks() {
	contatoRepositorio = Mockito.mock(ContatoRepositorioImpl.class);
	ReflectionTestUtils.setField(contatoService, "dao", contatoRepositorio);
    }

    @Test
    @DisplayName(value = "save user")
    void saveTest() {
	
	var contato = construirContato();
	when(contatoRepositorio.save(contato)).thenReturn(contato);
	var userDetail = contatoService.save(contato);
	
	assertNotNull(userDetail);
	assertEquals(1L, userDetail.getId());
    }

    private Contato construirContato() {
	var contato = new Contato();
	contato.setId(1L);
	return contato;
    }
}