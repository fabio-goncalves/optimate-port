package br.com.optimate.jwt.resource;

import br.com.optimate.jwt.service.AbstractService;
import jakarta.inject.Inject;

public class AbstractResource<T extends AbstractService> {
    @Inject
    protected T service;

}
