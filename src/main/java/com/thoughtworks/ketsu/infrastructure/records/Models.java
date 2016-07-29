package com.thoughtworks.ketsu.infrastructure.records;

import com.google.inject.AbstractModule;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.ParameterCheck;
import com.thoughtworks.ketsu.infrastructure.repository.ProductRepositoryImpl;
import com.thoughtworks.ketsu.infrastructure.repository.UserRepositoryImpl;

public class Models extends AbstractModule {

    @Override
    protected void configure() {
        ParameterCheck parameterCheck = new ParameterCheck();
        bind(ParameterCheck.class).toInstance(parameterCheck);

        bind(ProductRepository.class).to(ProductRepositoryImpl.class);
        bind(UserRepository.class).to(UserRepositoryImpl.class);
    }

}
