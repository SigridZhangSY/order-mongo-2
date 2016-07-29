package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(DatabaseTestRunner.class)
public class ProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_save_and_find_product(){
        Optional fetch = productRepository.createProduct(TestHelper.productMap("ddddddd"));
        assertThat(fetch.isPresent(), is(true));
    }

    @Test
    public void should_list_products(){
        Product product = productRepository.createProduct(TestHelper.productMap("apple")).get();
        List<Product> productList = productRepository.listProducts();
        assertThat(productList.size(), is(1));
    }

    @Test
    public void should_find_product_by_id(){
        Product product = productRepository.createProduct(TestHelper.productMap("asdf")).get();
        Optional<Product> fetch = productRepository.findById(product.getId().toString());

        assertThat(fetch.isPresent(), is(true));
    }
}
