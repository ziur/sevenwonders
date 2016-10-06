'use strict';

deferredBootstrapper.bootstrap({
  element: document.body,
  module: 'sevenWonder',
  resolve: {
    APP_CONFIG: ['$http', function ($http) {
      return $http.get('/api/config',
      {
        transformResponse: function(data, headers){
            var conf = data.split(':');
            data = {};
            data.wsPath = 'ws://' + conf[0] + ':' + conf[1];
            data.host = conf[0];
            data.port = conf[1];
            return data;
       }});
    }]
  }
});

angular.module('sevenWonder', [
    'ngRoute',
    'restangular',
    'ngCookies',
    'ngWebSocket',
    'sevenWonder.login',
    'sevenWonder.lobby',
    'sevenWonder.gameroom',
    'sevenWonder.choosewonder',
    'sevenWonder.gameboard'
]);