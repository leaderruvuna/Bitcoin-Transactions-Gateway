package com.btcapi.demo.services;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.concurrent.Future;

@Component
public class BlockCypherService {

    private final Environment environment;
    private static String blockCypherMain;
    private static String blockCypherTest3;
    private static String blockCypherTest;

    public BlockCypherService(Environment environment) {
        this.environment = environment;
    }

    @Bean
    void configureEnvironment() {
        blockCypherMain = environment.getProperty("blockCypher.api.main");
        blockCypherTest3 = environment.getProperty("blockCypher.api.test3");
        blockCypherTest = environment.getProperty("blockCypher.api.test");
    }

    @Async
    public Future txCreation(String inputAddress, String outputAddress, double amount) {
        return null;
    }

    @Async
    public Future txSignHx(String toSign) {
        return null;
    }
}
