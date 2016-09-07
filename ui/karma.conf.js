module.exports = function(config) {
    config.set({
        // base path, that will be used to resolve files and exclude
        basePath: 'src/main/javascript/app',
        frameworks: ['jasmine'],
        files: [
            'lib/angular/angular.js',
            'lib/angular-route/angular-mocks.js',
            'lib/angular-route/angular-route.js',
            'lib/angular-cookies/angular-cookies.js',
            'lib/angular-websocket/dist/angular-websocket.js',
            'lib/lodash/lodash.js',
            'lib/restangular/dist/restangular.js',

            'gameroom/gameroom.module.js',
            'core/directives/gameroom/wonder/wonder.html.js',
            'core/directives/gameroom/user/user.html.js',
            'core/directives/gameroom/players/players.html.js',
            'core/user/user.module.js',
            'core/user/user.service.js',
            'core/lobby/lobby.module.js',
            'core/lobby/lobby.service.js',
            'core/gameroom/gameroom.module.js',
            'core/gameroom/gameroom.service.js',
            'core/game/game.service.spec.js',
            'core/game/game.module.js',
            'core/game/game.service.js',
            'core/auth/auth.module.js',
            'core/auth/auth.service.js',
            'lobby/lobby.module.js',
            'lobby/lobby.component.js',
            'login/login.module.js',
            'login/login.component.js',
            'gameroom/gameroom.component.js',
            'app.module.js',
            'app.config.js',
            '**/*.spec.js'

        ],
        // list of files / patterns to exclude
        exclude: [],
        plugins: [
            'karma-jasmine',
            'karma-coverage',
            'karma-chrome-launcher',
            'karma-phantomjs-launcher'
        ],
        // test results reporter to use
        // possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
        reporters: ['progress', 'coverage'],

        // level of logging
        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        // enable / disable watching file and executing tests whenever any file changes
        autoWatch: false,

        // Start these browsers, currently available:
        // - Chrome
        // - ChromeCanary
        // - Firefox
        // - Opera
        // - Safari (only Mac)
        // - PhantomJS
        // - IE (only Windows)
        browsers: ['PhantomJS'],

        // Continuous Integration mode
        // if true, it capture browsers, run tests and exit
        singleRun: false,

        // Coverage reporter generates the coverage
        coverageReporter: {
            reporters: [
                { type: 'lcov', dir: 'build/coverage/' },
                { type: 'text-summary', dir: 'build/coverage/' }
            ]
        }

    });
};