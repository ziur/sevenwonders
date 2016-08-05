package org.fundacionjala.sevenwonders.beans;

import org.fundacionjala.sevenwonders.core.Game;
import org.fundacionjala.sevenwonders.core.GameRoom;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

/**
 * Has the basic functionality that permit to rest service access and generate
 * data without obtain unusable information, this have the principal function
 * of sevenwonders.
 *
 * @author Juan Barahona
 */


@Component
public class GameService {
    private final Map<String, Game> games = new TreeMap<>();
    private String autoIncrementId;

    public GameService(){
        autoIncrementId = 1 + "";
    }

    /**
     * Post: Create a game with an identifier
     *
     * @param game
     */
    public void createGame(Game game) {
        games.put(autoIncrementId, game);
        autoIncrementId = (Integer.parseInt(autoIncrementId) + 1) + "";
    }
}
