package com.twa.cliente.service.impl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.persistence.commun.component.SysCriterion;
import com.persistence.commun.service.component.SysGenericService;
import com.twa.cliente.repository.ContatoRepositorio;
import com.twa.cliente.service.ContatoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class ContatoServiceImpl extends SysGenericService implements ContatoService {

    private static final long serialVersionUID = 1L;

    public ContatoServiceImpl(@Qualifier("contatoRepositorioImpl") final ContatoRepositorio dao) {
        super(dao);
    }

    @PostConstruct
    public void init() {
        log.info("created ContatoRepositorioImpl ...");
    }

    @Override
    public ContatoRepositorio getDao() {
        return (ContatoRepositorio) super.getDao();
    }

    @Override
    public <E extends Serializable> E deleteById(final Class<E> clazz, final Long entityId, final Map<String, SysCriterion> conditions) throws RuntimeException {
        return this.getDao().deleteById(clazz, entityId, conditions);
    }
}
