package io.quarkus.restclient.runtime;

import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.restclient.config.core.IRestClientsConfig;
import io.quarkus.restclient.config.core.RestClientBuilderFactory;

public class ClassicRestClientBuilderFactory implements RestClientBuilderFactory {

    public RestClientBuilder newBuilder(Class<?> proxyType, IRestClientsConfig restClientsConfigRoot) {
        RegisterRestClient annotation = proxyType.getAnnotation(RegisterRestClient.class);
        String configKey = null;
        String baseUri = null;
        if (annotation != null) {
            configKey = annotation.configKey();
            baseUri = annotation.baseUri();
        }

        RestClientBuilder restClientBuilder = RestClientBuilder.newBuilder();
        RestClientBase restClientBase = new RestClientBase(proxyType, baseUri, configKey, new Class[0], restClientsConfigRoot);
        restClientBase.configureBuilder(restClientBuilder);

        return restClientBuilder;
    }

    //    @Override
    //    public RestClientBuilder newBuilder(Class<?> proxyType, io.quarkus.restclient.config.core.IRestClientsConfig IRestClientsConfigRoot) {
    //        return null;
    //    }
}
