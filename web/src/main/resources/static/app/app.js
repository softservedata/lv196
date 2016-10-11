angular
    .module('delivery', ['ui.router', 'ui.bootstrap'])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/orders/in-progress');

        $stateProvider
            .state('profile', {
                url: '/profile',
                templateUrl: '/app/views/profile.html'
            })
            .state('orders', {
                url: '/orders',
                templateUrl: '/app/orders/views/orders.html',
                controller: 'ordersController'
            })
            .state('orders.inProgress', {
                url: '/in-progress',
                templateUrl: '/app/orders/views/orders.in-progress.html',
                controller: 'ordersInProgressController'
            })
            .state('orders.open', {
                url: '/open',
                templateUrl: '/app/orders/views/orders.open.html',
                controller: 'ordersOpenController'
            })
            .state('orders.closed', {
                url: '/closed',
                templateUrl: '/app/views/orders/orders.closed.html',
                controller: 'ordersClosedController'
            })
            .state('all-orders', {
                url: '/all-orders',
                templateUrl: '/app/views/orders/all.orders.html',
                controller: 'allOrdersController'
            })
            .state('feedbacks', {
                url: '/feedbacks',
                templateUrl: '/app/views/feedbacks/feedbacks.html',
                controller: 'feedbacksController'
            })
            .state('users', {
                url: '/users',
                templateUrl: '/app/views/users.html',
                controller: 'userController'
            })
            .state('tracking', {
            url: '/tracking',
            templateUrl: '/app/tracking/view/tracking.html',
            controller: 'pleaceController'
            })
            .state('dialog', {
                url: '/dialog',
                templateUrl: '/app/tracking/view/dialog.html',
                controller: 'pleaceController'
            });
    }]);