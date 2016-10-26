/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.sevenwonders.routes;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by Vania Catorceno
 */
@Component
public class ChooseWonderWSRoute extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        from("atmosphere-websocket://choosewonder")
                .to("atmosphere-websocket://choosewonder?sendToAll=false");

        from("direct:sendMessageRoom")
                .to("bean:gameRoomService?method=startGame(${header.id})")
                .to("atmosphere-websocket://choosewonder?sendToAll=true");

    }
}
