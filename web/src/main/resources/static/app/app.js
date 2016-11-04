angular
    .module('delivery', ['ui.router', 'ui.bootstrap', 'ngAnimate','ui-notification', 'pascalprecht.translate', 
                         'ngCookies', 'ngRateIt', 'ngMaterial', 'chart.js'])
    .config(['$stateProvider', '$urlRouterProvider', '$translateProvider', '$mdThemingProvider', function ($stateProvider, 
    		  $urlRouterProvider, $translateProvider, $mdThemingProvider) {
        $urlRouterProvider.otherwise('/orders/in-progress');
        $urlRouterProvider.when('/orders', '/orders/in-progress');

        $stateProvider
            .state('profile', {
                url: '/profile',
                templateUrl: '/app/users/views/profile.html',
                controller: 'userProfileController'
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
                templateUrl: '/app/driver/views/driver.html',
                controller: 'driverController'
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
            controller: 'pleaceController',
            params: {
                id: null,
             }
            })
            .state('dialog', {
                url: '/dialog',
                templateUrl: '/app/tracking/view/dialog.html',
                controller: 'pleaceController'
            })
            .state('adminMaps', {
            url: '/adminMaps',
            templateUrl: '/app/tracking/view/adminMaps.html',
            controller: 'adminMapsController'
            })
	        .state('orders-tracking', {
	            url: '/orders-tracking',
	            templateUrl: '/app/tracking/view/orders-tracking.html',
	            controller: 'ordersTrackingController'
	        })
            .state('statistics', {
                url: '/statistics',
                templateUrl: '/app/statistics/views/statistics.html',
                controller: 'statisticsController'
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

  	  $translateProvider.useSanitizeValueStrategy('escapeParameters');
    }]);