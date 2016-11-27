angular
    .module('delivery', ['ui.router', 'ui.bootstrap', 'ngAnimate', 'ui-notification', 'pascalprecht.translate',
        'ngCookies', 'ngRateIt', 'ngMaterial', 'chart.js', 'cloudinary', 'ngFileUpload'])
    .config(['$stateProvider', '$urlRouterProvider', '$translateProvider', '$mdThemingProvider', 'cloudinaryProvider',
        '$sceDelegateProvider',
        function ($stateProvider, $urlRouterProvider, $translateProvider, $mdThemingProvider, cloudinaryProvider,
                  $sceDelegateProvider) {
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
            .state('messages', {
                url: '/messages',
                templateUrl: '/app/chat/views/conversation.html',
                controller: 'conversationsController'
            })
            .state('feedback', {
                url: '/feedback',
                templateUrl: '/app/feedback/views/feedback.html',
                controller: 'feedbackController'
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

            cloudinaryProvider
                .set("cloud_name", "lv196java")
                .set("upload_preset", "uuau4esb");

            $sceDelegateProvider.resourceUrlWhitelist([
                'self',
                'https://res.cloudinary.com/lv196java/image/**',
                'http://res.cloudinary.com/lv196java/image/**'
            ]);
        }])
    .factory('$storage', ['$http', '$q', ($http, $q) => {
        return {
            getEmail: () => sessionStorage.getItem('email'),
            config: () => {
                const deferred = $q.defer();
                $http.get('configure').then(response => {
                    sessionStorage.setItem('email', response.data.email);
                    sessionStorage.setItem('wsEndpoint', response.data.wsEndpoint);
                    sessionStorage.setItem("google-key", response.data.googleGeocodeKey);
                    deferred.resolve(response.data);
                }, () => deferred.reject("Couldn't retrieve email"));
                return deferred.promise;
            },
            findOfferInfo: offerId => {
                const deferred = $q.defer();
                const storedInfo = JSON.parse(sessionStorage.getItem('offer-info-' + offerId));

                if (storedInfo !== null) {
                    deferred.resolve(storedInfo);
                } else {
                    $http.get('order/offer-info/' + offerId).then(response => {
                        sessionStorage.setItem('offer-info-' + offerId, JSON.stringify(response.data));
                        deferred.resolve(response.data);
                    }, () => deferred.reject('Couldn\'t find offer info'));
                }

                return deferred.promise;
            }
        }
    }])
    .run(['$http', '$rootScope', '$filter', '$storage', '$chat', 'Notification',
        function ($http, $rootScope, $filter, $storage, $chat, Notification) {

            $storage.config().then(data => {

                $rootScope.stomp = Stomp.client(data.wsEndpoint + '/chat');
                $rootScope.stomp.connect({login: data.email}, () => {

                    $rootScope.stomp.subscribe('/user/queue/chat-notifications', response => {
                        const offerId = response.body;

                        $storage.findOfferInfo(offerId).then(offerInfo => {
                            const senderName = data.email === offerInfo.driverEmail ?
                                offerInfo.customerName : offerInfo.driverName;

                            if ($chat.openedChatId() != offerId) {
                                Notification.info({
                                    title: 'New message from ' + senderName,
                                    message: 'Regarding order: ' + offerInfo.cityNameFrom + ' - ' + offerInfo.cityNameTo +
                                    ', ' + $filter('date')(offerInfo.arrivalDate, 'dd/MM/yyyy'),
                                    replaceMessage: true,
                                    onClose: (el, event) => {
                                        if (event.type === 'click') {
                                            $chat.open(offerId);
                                        }
                                    }
                                });
                            }
                        });
                    });
                });
            })
        }]);
