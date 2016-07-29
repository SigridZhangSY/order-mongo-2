package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport{

    @Test
    public void should_return_201_and_uri_when_post_user(){

        Response post = post("users", TestHelper.userMap("john"));

        assertThat(post.getStatus(), is(201));
        assertThat(Pattern.matches(".*/users/.*", post.getLocation().toString()), is(true));
    }

    @Test
    public void should_return_400_when_post_user_with_name_is_empty(){

        Response post = post("users", new HashMap<String, Object>());
        assertThat(post.getStatus(), is(400));
    }
}
