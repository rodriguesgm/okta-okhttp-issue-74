package com.okta.okhttp.issue.okhttpissue74.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "dependencies.okta-api")
public class OktaProperties {
    private String token;
    private String baseUrl;
    private int connectTimeoutSecs;
}
