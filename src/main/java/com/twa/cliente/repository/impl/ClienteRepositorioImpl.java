package com.twa.cliente.repository.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.persistence.commun.component.SysGenericRepository;
import com.twa.cliente.repository.ClienteRepositorio;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ClienteRepositorioImpl extends SysGenericRepository implements ClienteRepositorio {

    private static final long serialVersionUID = 1L;

    public ClienteRepositorioImpl() {
    }

    @PostConstruct
    public void init() {
	log.info("Create ClienteRepositorioImpl");
    }

   
}
