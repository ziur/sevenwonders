'use strict';

angular.
    module('sevenWonders.core.lobby').
    factory('Lobby', ['$cookies', '$websocket', 'Restangular', 'Auth', '$q', 'APP_CONFIG',
        function ($cookies, $websocket, Restangular, Auth, $q, APP_CONFIG) {
            var lobbySource;

            return {
                connectWs: function(lobby) {
                    lobbySource = lobby;
                    var dataStream = $websocket(APP_CONFIG.wsPath + '/lobby');
                    dataStream.onMessage(function (message) {
                        lobby.validateGame(JSON.parse(message.data));
                    });
                }
            };
        }
    ]);