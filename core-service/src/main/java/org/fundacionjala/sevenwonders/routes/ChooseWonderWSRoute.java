/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.fundacionjala.sevenwonders.core.rest.GameModel;
import org.fundacionjala.sevenwonders.processors.RoomProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Vania Catorceno
 */
@Component
public class ChooseWonderWSRoute extends RouteBuilder {
    private RoomProcessor roomProcessor = new RoomProcessor();
    @Override
    public void configure() throws Exception {
       from("atmosphere-websocket://choosewonder")
                .log("room ready websocket")
                .unmarshal().json(JsonLibrary.Jackson, GameModel.class)
                .process(roomProcessor)
                .log("${body}")
                .to("direct:sendMessageRoom");

        from("direct:sendMessageRoom")
                .to("bean:gameRoomService?method=isCompletedPlayers(${header.id})")
                .to("bean:gameRoomService?method=startGame(${header.id})")
                .to("atmosphere-websocket://choosewonder?sendToAll=true");


    }
}
