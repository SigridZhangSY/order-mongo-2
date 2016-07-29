package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.order.Order;
import com.thoughtworks.ketsu.domain.product.Product;
import com.thoughtworks.ketsu.domain.product.ProductRepository;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ApiTestRunner.class)
public class UserApiTest extends ApiSupport{

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Test
    public void should_return_201_and_uri__when_post_order(){
        User user = userRepository.createUser(TestHelper.userMap("xxx")).get();
        Product product = productRepository.createProduct(TestHelper.productMap("forOrder")).get();

        Response post = post("users/" + user.getId() + "/orders", TestHelper.orderMap(product.getId().toString()));

        assertThat(post.getStatus(), is(201));
        assertThat(Pattern.matches(".*/orders/.*", post.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_400_when_post_order_with_name_is_empty(){
        User user = userRepository.createUser(TestHelper.userMap("xxx")).get();
        Product product = productRepository.createProduct(TestHelper.productMap("forOrder")).get();
        Map<String, Object> map = TestHelper.orderMap(product.getId().toString());
        map.remove("name");

        Response post = post("users/" + user.getId() + "/orders", map);

        assertThat(post.getStatus(), is(400));
    }

    @Test
    public void should_return_detail_when_list_orders(){
        User user = userRepository.createUser(TestHelper.userMap("xxx")).get();
        Product product = productRepository.createProduct(TestHelper.productMap("forOrder")).get();
        Order order = user.createOrder(TestHelper.orderMap(product.getId().toString())).get();

        Response get = get("users/" + user.getId() + "/orders");
        assertThat(get.getStatus(), is(200));
        final List<Map<String, Object>> list = get.readEntity(List.class);
        assertThat(list.size(), is(1));
    }

    @Test
    public void should_return_detail_when_find_order(){
        User user = userRepository.createUser(TestHelper.userMap("xxx")).get();
        Product product = productRepository.createProduct(TestHelper.productMap("forOrder")).get();
        Order order = user.createOrder(TestHelper.orderMap(product.getId().toString())).get();

        Response get = get("users/" + user.getId() + "/orders/" + order.getId());
        assertThat(get.getStatus(), is(200));
        final Map<String, Object> map = get.readEntity(Map.class);
        assertThat(map.get("uri").toString(), is("/users/" + user.getId() + "/orders/" + order.getId()));

    }

}
