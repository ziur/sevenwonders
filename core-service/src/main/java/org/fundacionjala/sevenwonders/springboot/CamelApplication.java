/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.springboot;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 *  This contains how to start a camel rest service.
 *
 * @author Juan Barahona
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.fundacionjala.sevenwonders")
@EnableWebSocket
public class CamelApplication extends SpringBootServletInitializer implements WebSocketConfigurer {
    private static final String CAMEL_URL_MAPPING = "/api/*";
    private static final String CAMEL_SERVLET_NAME = "CamelServlet";

    @Autowired
    GameWebsocketHandler gameWebsocketHandler;

    @Autowired
    LobbyWebsocketHandler lobbyWebsocketHandler;

    public static void main(String[] args) {
        SpringApplication.run(CamelApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CamelApplication.class);
    }



    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(lobbyWebsocketHandler, "/lobby").setAllowedOrigins("*");
        registry.addHandler(gameWebsocketHandler, "/game").setAllowedOrigins("*");
    }

    @Bean
    public ServletRegistrationBean camelServletRegistration() {

        // Create servlet for Camel Rest Endpoints
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }

    @Bean
    public LobbyWebsocketHandler lobbyWebsocketHandler() {
        return new LobbyWebsocketHandler();
    }

    @Bean
    public GameWebsocketHandler gameWebsocketHandler() {
        return new GameWebsocketHandler();
    }
}
