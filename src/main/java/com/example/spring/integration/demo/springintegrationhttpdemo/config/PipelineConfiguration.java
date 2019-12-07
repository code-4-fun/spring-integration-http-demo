package com.example.spring.integration.demo.springintegrationhttpdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.http.dsl.Http;

@Configuration
public class PipelineConfiguration {

    @Bean("requestChannel")
    DirectChannel requestChannel() {
        return MessageChannels.direct().get();
    }

    @Bean("responseChannel")
    DirectChannel responseChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    DirectChannel errorChannel() {
        return new DirectChannel();
    }

    @Bean
    IntegrationFlow sayHelloChannel() {
        return IntegrationFlows
                .from(
                        Http
                                .inboundGateway("/sayHello")
                                .requestMapping(m -> m.methods(HttpMethod.GET))
                                .payloadExpression("#requestParams.name.get(0)")
                                .requestChannel(requestChannel())
                                .replyChannel(responseChannel())
                                .errorChannel(errorChannel())
                )
                .handle("sayHelloService", "greet")
                .get();
    }

    @Bean
    IntegrationFlow errorHandlerFlow(){
        return IntegrationFlows
                .from(errorChannel())
                .handle("errorHandlerService", "handleErrors")
                .get();
    }

}
