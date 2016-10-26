'use strict';

angular.
    module('sevenWonders.core.lobby').
    factory('Lobby', ['$websocket',
        function ($websocket) {
            var lobbySource;

            return {
                connectWs: function(lobby) {
                    lobbySource = lobby;
                    var dataStream = $websocket('ws://localhost:8080/lobby');
                    dataStream.onMessage(function (message) {
                        lobby.validateGame(JSON.parse(message.data));
                    });
                }
            };
        }
    ]);