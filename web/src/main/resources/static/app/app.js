angular
    .module('delivery', ['ui.router', 'ui.bootstrap', 'ngAnimate','ui-notification', 'pascalprecht.translate', 'ngCookies'])
    .config(['$stateProvider', '$urlRouterProvider', '$translateProvider', function ($stateProvider, $urlRouterProvider, $translateProvider) {
        $urlRouterProvider.otherwise('/orders/in-progress');
        $urlRouterProvider.when('/orders', '/orders/in-progress');

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
                templateUrl: '/app/orders/views/orders.closed.html',
                controller: 'ordersClosedController'
            })
            .state('driver', {
                url: '/driver',
                templateUrl: '/app/views/driver/driver.html',
                controller: 'driverController'
            })
            .state('findOrder', {
                url: '/find-order',
                templateUrl: '/app/views/driver/findOrder.html',
                controller: 'findOrdersController'
            })
            .state('feedbacks', {
                url: '/feedbacks',
                templateUrl: '/app/feedbacks/views/feedbacks.html',
                controller: 'feedbacksController'
            })
            .state('users', {
                url: '/users',
                templateUrl: '/app/users/views/users.html',
                controller: 'usersController'
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
            })
	        .state('orders-tracking', {
	            url: '/orders-tracking',
	            templateUrl: '/app/tracking/view/orders-tracking.html',
	            controller: 'ordersTrackingController'
	        })
            .state('notification', {
                url: '/notification',
                templateUrl: '/app/notification/views/show.notification.html',
                controller: 'notificationController'
            });
        
  	  	$translateProvider
	  		.useStaticFilesLoader({
	  			prefix: '/i18n/',
	  			suffix: '.json'
	  		})
	  		.preferredLanguage('en')
	  		.useLocalStorage()
	  		.useMissingTranslationHandlerLog();
    }]);