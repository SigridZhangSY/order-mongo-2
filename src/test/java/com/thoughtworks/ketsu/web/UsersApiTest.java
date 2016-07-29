package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport{

    @Inject
    UserRepository userRepository;

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

    @Test
    public void should_return_detail_when_find_user_by_id(){
        User user = userRepository.createUser(TestHelper.userMap("xxx")).get();
        Response get = get("users/" + user.getId().toString());

        assertThat(get.getStatus(), is(200));

        final Map<String, Object> map = get.readEntity(Map.class);
        assertThat(map.get("uri"), is("/users/" + user.getId().toString()));
    }

    @Test
    public void should_return_404_when_user_not_exist(){

        Response get = get("users/1" );
        assertThat(get.getStatus(), is(404));

        Response get1 = get("users/" + new ObjectId());
        assertThat(get1.getStatus(), is(404));
    }
}
