package com.twa.cliente.service.impl;

import com.persistence.commun.component.SysCriterion;
import com.persistence.commun.service.component.SysGenericService;
import com.twa.cliente.repository.ClienteRepositorio;
import com.twa.cliente.service.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class ClienteServiceImpl extends SysGenericService implements ClienteService {

    private static final long serialVersionUID = 1L;

    public ClienteServiceImpl(@Qualifier("clienteRepositorioImpl") final ClienteRepositorio dao) {
        super(dao);
    }

    @PostConstruct
    public void init() {
        log.info("created ClienteRepositorioImpl ...");
    }

    @Override
    public ClienteRepositorio getDao() {
        return (ClienteRepositorio) super.getDao();
    }

    @Override
    public <E extends Serializable> E deleteById(final Class<E> clazz, final Long entityId, final Map<String, SysCriterion> conditions) throws RuntimeException {
        return this.getDao().deleteById(clazz, entityId, conditions);
    }
}
