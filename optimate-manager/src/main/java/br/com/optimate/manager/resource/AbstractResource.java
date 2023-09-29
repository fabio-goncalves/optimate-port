package br.com.optimate.manager.resource;

import br.com.optimate.manager.service.AbstractService;
import jakarta.inject.Inject;

public class AbstractResource<T extends AbstractService> {
    @Inject
    protected T service;

}
