angular
    .module('delivery', ['ui.router', 'ui.bootstrap'])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/orders');

        $stateProvider
            .state('profile', {
                url: '/profile',
                templateUrl: '/resources/app/views/profile.html'
            })
            .state('orders', {
                url: '/orders',
                templateUrl: '/resources/app/views/orders.html',
                controller: 'ordersController'
            })
            .state('feedback', {
                url: '/feedback',
                templateUrl: '/resources/app/views/feedback.html'
            });
    }]);