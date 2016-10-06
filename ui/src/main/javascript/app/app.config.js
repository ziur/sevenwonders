'use strict';

angular.module('sevenWonder')
    .config(['$locationProvider', '$routeProvider',
        function config($locationProvider, $routeProvider) {
            $routeProvider
                .when('/login', {
                    template: '<login></login>'
                })
                .when('/lobby', {
                    template: '<lobby></lobby>'
                })
                .when('/gameroom', {
                    template: '<gameroom></gameroom>'
                })
                .when('/choosewonder', {
                    template: '<choosewonder></choosewonder>'
                })
                .when('/gameboard', {
                    template: '<gameboard></gameboard>'
                })

                .otherwise('/login');
        }
    ])
    .config(['RestangularProvider', 'APP_CONFIG', function(RestangularProvider, APP_CONFIG) {
        //APP_CONFIG

        var host = "";
        if (APP_CONFIG.host) {
            host = "http://" + APP_CONFIG.host + ":" + APP_CONFIG.port;
        }

        RestangularProvider.setBaseUrl(host + '/api');
    }])
    ;