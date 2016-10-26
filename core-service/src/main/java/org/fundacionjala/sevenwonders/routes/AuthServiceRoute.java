/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Juan Manuel Barahona on 24/08/2016.
 */

@Component()
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthServiceRoute extends RouteBuilder {

    @Value("${server.port}")
    private String port;

    @Value("${server.host}")
    private String host;

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .endpointProperty("servletName", "CamelServlet")
                .dataFormatProperty("prettyPrint", "true")
                .enableCORS(true)
                .corsHeaderProperty("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization")
                .port(port);

        rest("/login").description("Login rest service")
                .consumes("application/json").produces("application/json")
                .post().description("Create a player").type(PlayerModel.class)
                .to("bean:authService?method=login");

        from("servlet:config")
                .transform(simple(host + ":" +  port));

    }
}