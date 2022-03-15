package com.okta.okhttp.issue.okhttpissue74.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;

@Service
public record OktaUserService(Client client) {
    public Stream<User> getUsers() {
        return client.listUsers().stream();
    }

    public User getUser(String userId) {
        return client.getUser(userId);
    }
}
