/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.sevenwonders.core;

import org.fundacionjala.sevenwonders.beans.GameRoomService;
import org.fundacionjala.sevenwonders.core.rest.GameRoomModel;
import org.fundacionjala.sevenwonders.core.rest.PlayerModel;
import org.junit.Assert;
import org.junit.Test;

/**
 * Used to test all the functionality of the {@link org.fundacionjala.sevenwonders.beans.GameRoomService}.
 *
 * @author Juan Barahona
 */
public class GameRoomServiceTest {

    @Test
    public void postAndGetGameRoomTest(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();

        player.setUserName("Juan");
        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        Assert.assertNotNull(gameRoomService.getGameRoom(1).getOwner());
    }

    @Test
    public void addPlayerToGameRoomTest(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();
        player.setUserName("Juan");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomModel = gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel playerOne = new PlayerModel();
        playerOne.setUserName("Dwits");

        gameRoomService.addPlayer(gameRoomModel.getId(),playerOne);

        Assert.assertEquals(2, gameRoomService.getGameRoom(1).getPlayers().size());
    }

    @Test
    public void automaticCreateGameWhenFullRoom(){
        GameRoomService gameRoomService = new GameRoomService();
        GameRoomModel gameRoomModel = new GameRoomModel();
        PlayerModel player = new PlayerModel();
        player.setUserName("Juan");

        gameRoomModel.setMaxPlayers(3);
        gameRoomModel.setOwner(player);

        gameRoomService.createGameRoom(gameRoomModel);

        PlayerModel playerOne = new PlayerModel();
        playerOne.setUserName("Dwits");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerOne);

        Assert.assertEquals(0, gameRoomService.getGameService().getGames().size());

        PlayerModel playerTwo = new PlayerModel();
        playerTwo.setUserName("Lucero");

        gameRoomService.addPlayer(gameRoomModel.getId(), playerTwo);

        Assert.assertEquals(1, gameRoomService.getGameService().getGames().size());

    }
}
