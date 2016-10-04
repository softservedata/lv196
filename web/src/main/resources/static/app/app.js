angular
    .module('delivery', ['ui.router', 'ui.bootstrap'])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/orders/active');

        $stateProvider
            .state('profile', {
                url: '/profile',
                templateUrl: '/app/views/profile.html'
            })
            .state('orders', {
                url: '/orders',
                templateUrl: '/app/views/orders.html',
                controller: 'ordersController'
            })
            .state('orders.active', {
                url: '/active',
                templateUrl: '/app/views/orders.active.html',
                controller: 'ordersActiveController'
            })
            .state('orders.closed', {
                url: '/closed',
                templateUrl: '/app/views/orders.closed.html',
                controller: 'ordersClosedController'
            })
            .state('feedback', {
                url: '/feedback',
                templateUrl: '/app/views/feedback.html'
            })
            .state('users', {
                url: '/users',
                templateUrl: '/app/views/users.html',
                controller: 'userController'
            });
    }]);