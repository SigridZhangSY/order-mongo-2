package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserRepository;
import com.thoughtworks.ketsu.infrastructure.ParameterCheck;
import com.thoughtworks.ketsu.web.exception.InvalidParameterException;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("users")
public class UsersApi {

    @Context
    UserRepository userRepository;

    @Context
    Routes routes;

    @Context
    ParameterCheck parameterCheck;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Map<String, Object> info){

        List<String> fields = new ArrayList<>();
        fields.add("name");
        List<String> empty = parameterCheck.emptyCheck(fields, info);
        if(empty.size() > 0)
            throw new InvalidParameterException(empty);

        User user = userRepository.createUser(info).get();
        return Response.created(routes.userUri(user)).build();
    }
}
