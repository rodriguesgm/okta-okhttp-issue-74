package com.okta.okhttp.issue.okhttpissue74.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okta.okhttp.issue.okhttpissue74.service.OktaUserService;
import com.okta.sdk.resource.user.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final OktaUserService oktaUserService;

    @GetMapping
    public List <User> getUsers(
    ) {
        return oktaUserService.getUsers().toList();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable(name = "id") String userId) {
        return oktaUserService.getUser(userId);
    }
}
