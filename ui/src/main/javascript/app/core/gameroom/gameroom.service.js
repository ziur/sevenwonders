'use strict';

angular.
    module('sevenWonders.core.gameroom').
    factory('GameRoom', ['$cookies', '$websocket', 'Game', 'Restangular', '$q', '$location', '$timeout', 'APP_CONFIG',
        function ($cookies, $websocket, Game, Restangular, $q, $location, $timeout, APP_CONFIG) {
            return {
                getGameRoom: function () {
                    return Game.getCurrentGame();
                },
                getPlayers: function () {
                    var defer = $q.defer();
                    Restangular.one('games', Game.getCurrentGame().id).getList('players')
                        .then(function (data) {
                            defer.resolve(data);
                        }).catch(function () {
                            defer.reject();
                        });
                    return defer.promise;
                },
                connectWebsocket: function (game) {
                    var dataStream = $websocket(APP_CONFIG.wsPath + '/game');
                    dataStream.onMessage(function (message) {
                        game.addPlayer(JSON.parse(message.data));
                        console.log('joined');
                    });
                },

                connectRoomWebsocket: function(game) {
                    var dataRoom = $websocket(APP_CONFIG.wsPath + '/choosewonder');
                    dataRoom.onOpen(function () {
                        console.log('open connection at choosewonder');
                    });

                   dataRoom.onMessage(function (message) {
                       var room = JSON.parse(message.data);
                           $timeout(function() {
                                $location.path('/choosewonder');
                                console.log('room is complete');
                           }, 2000);
                   });
                }
            }
        }
    ]);