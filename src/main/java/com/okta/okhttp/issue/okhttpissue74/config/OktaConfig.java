package com.okta.okhttp.issue.okhttpissue74.config;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.okta.commons.http.okhttp.OkHttpRequestExecutorFactory;
import com.okta.okhttp.issue.okhttpissue74.config.properties.OktaProperties;
import com.okta.sdk.authc.credentials.TokenClientCredentials;
import com.okta.sdk.client.Client;
import com.okta.sdk.client.ClientBuilder;
import com.okta.sdk.client.Clients;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;

@Configuration
@NoArgsConstructor
@AllArgsConstructor
@EnableConfigurationProperties(OktaProperties.class)
public class OktaConfig {

    private Supplier<ClientBuilder> clientBuilder = Clients::builder;
    private Function<OkHttpClient, OkHttpRequestExecutorFactory> okHttpRequestExecutorFactory = OkHttpRequestExecutorFactory::new;

    @Bean
    public Client client(OktaProperties oktaProperties, OkHttpClient okHttpClient) {
        return clientBuilder.get()
                .setOrgUrl(oktaProperties.getBaseUrl())
                .setConnectionTimeout(oktaProperties.getConnectTimeoutSecs())
                .setRequestExecutorFactory(clientConfiguration ->
                        okHttpRequestExecutorFactory.apply(okHttpClient).create(clientConfiguration)
                )
                .setClientCredentials(new TokenClientCredentials(oktaProperties.getToken()))
                .build();
    }

    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                // The integration I need and that It seems not possible with okta-sdk-httpclient
                // https://github.com/zalando/logbook
                // .addNetworkInterceptor(new LogbookInterceptor(logbook))
                .build();
    }
}
