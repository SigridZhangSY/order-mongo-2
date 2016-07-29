package com.thoughtworks.ketsu.repositories;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(DatabaseTestRunner.class)
public class UserTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;


    @Test
    public void should_save_and_find_order(){
        User user = userRepository.createUser(TestHelper.userMap("vvv")).get();
        Product product = productRepository.createProduct(TestHelper.productMap("apple")).get();
        Optional<Order> order = user.createOrder(TestHelper.orderMap(product.getId().toString()));

        assertThat(order.isPresent(), is(true));
    }

    @Test
    public void should_list_orders(){
        User user = userRepository.createUser(TestHelper.userMap("vvv")).get();
        Product product = productRepository.createProduct(TestHelper.productMap("apple")).get();
        Order order = user.createOrder(TestHelper.orderMap(product.getId().toString())).get();

        assertThat(user.listOrders().size(), is(1));
    }

    @Test
    public void should_find_order_by_id(){
        User user = userRepository.createUser(TestHelper.userMap("vvv")).get();
        Product product = productRepository.createProduct(TestHelper.productMap("apple")).get();
        Order order = user.createOrder(TestHelper.orderMap(product.getId().toString())).get();

        Optional<Order> fetch = user.findOrder(order.getId().toString());
        assertThat(fetch.isPresent(), is(true));
    }
}
