package com.thoughtworks.ketsu.repositories;


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
public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void should_save_and_find_user(){
        Optional<User> user = userRepository.createUser(TestHelper.userMap("john"));
        assertThat(user.isPresent(), is(true));
    }

    @Test
    public void should_find_user_by_id(){
        User user = userRepository.createUser(TestHelper.userMap("xxx")).get();
        Optional<User> fetch = userRepository.findUserById(user.getId().toString());
        assertThat(fetch.isPresent(), is(true));
    }
}
