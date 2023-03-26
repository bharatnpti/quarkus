package io.quarkus.rest.client.reactive.runtime;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.restclient.config.core.IRestClientsConfig;
import io.quarkus.restclient.config.core.RestClientBuilderFactory;

public class ReactiveRestClientBuilderFactory implements RestClientBuilderFactory {

    public RestClientBuilder newBuilder(Class<?> proxyType, IRestClientsConfig restClientsConfigRoot) {
        RegisterRestClient annotation = proxyType.getAnnotation(RegisterRestClient.class);
        String configKey = null;
        String baseUri = null;
        if (annotation != null) {
            configKey = annotation.configKey();
            baseUri = annotation.baseUri();
        }

        RestClientBuilderImpl restClientBuilder = new RestClientBuilderImpl();
        RestClientCDIDelegateBuilder<?> restClientBase = new RestClientCDIDelegateBuilder<>(proxyType, baseUri, configKey,
                restClientsConfigRoot);
        restClientBase.configureBuilder(restClientBuilder);

        return restClientBuilder;
    }

}
