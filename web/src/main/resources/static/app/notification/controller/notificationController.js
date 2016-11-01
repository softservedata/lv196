angular
    .module('delivery')
    .controller('notificationController',['$scope', '$http', 'Notification', '$rootScope',
        function ($scope, $http, Notification, $rootScope) {
            $scope.notifications = {
                notifications: [],
                info: [],
                warning: [],
                success: []
            };

            $scope.retrieveNotifications = () => {
                $http.get('/notification').then(response => {
                    $scope.notifications.notifications = response.data;
                    $scope.notifications.info.length = 0;
                    $scope.notifications.success.length = 0;
                    $scope.notifications.warning.length = 0;
                    for (var i = 0; i < $scope.notifications.notifications.length; i++){
                        ($rootScope.lang === 'en') ? $scope.notifications.notifications[i].message = $scope.notifications.notifications[i].message.substring(0, $scope.notifications.notifications[i].message.indexOf(';')):
                                                     $scope.notifications.notifications[i].message = $scope.notifications.notifications[i].message.substring($scope.notifications.notifications[i].message.indexOf(';')+5);
                    }
                    for (var i = 0; i < $scope.notifications.notifications.length; i++){
                        if ($scope.notifications.notifications[i].notificationStatus === 'Info'){
                            $scope.notifications.info.push($scope.notifications.notifications[i]);
                        }
                        else if ($scope.notifications.notifications[i].notificationStatus === 'Success'){
                            $scope.notifications.success.push($scope.notifications.notifications[i]);
                        }
                        else if ($scope.notifications.notifications[i].notificationStatus === 'Warning'){
                            $scope.notifications.warning.push($scope.notifications.notifications[i]);
                        }
                    }
                    if (sessionStorage.getItem('last') > 0) {
                        ($rootScope.lang === 'en') ? Notification('Now all your notifications are readed'):
                            Notification('Всі ваші сповіщення зараз прочитані');
                        sessionStorage.setItem('last',0);
                        console.info("all notif are readed = " + sessionStorage.getItem('last'));
                    }
                    
                });
            };
            $scope.retrieveNotifications();

            $scope.delete = id => {
                $http.delete('/notification/'+id).then(response =>{
                    ($rootScope.lang === 'en') ? Notification.success('Delete notification successfull'):
                        Notification.success('Видалення сповіщення успішне');
                    $scope.retrieveNotifications();
                })
            };
        }])
    .controller('notificationAmountController',['$scope', '$http', 'Notification', '$rootScope', '$uibModal',
        function ($scope, $http, Notification, $rootScope, $uibModal) {
            $scope.countNewNotification = () => {
                $scope.amountNewNotification = sessionStorage.getItem('last');
                console.info('begin count method, for now Amount = ' + sessionStorage.getItem('last'));

                $http.get('/notification/count').then(response => {
                    $scope.amountNewNotification = response.data;
                    console.info('in get method $scope.amountNewNotification = '+$scope.amountNewNotification);

                    if ($scope.amountNewNotification > sessionStorage.getItem('last')) {
                        ($rootScope.lang === 'en') ? Notification('You have new Notification'):
                            Notification('У вас нове сповіщення');

                        sessionStorage.setItem('last', $scope.amountNewNotification);
                        console.info("if amount > 0 sessionStorage = " + sessionStorage.getItem('last'));
                    }
                    else if ($scope.amountNewNotification == 0) {
                        sessionStorage.setItem('last', 0);
                        console.info("if amount = 0 sessionStorage = " + sessionStorage.getItem('last'));
                    }
                    $scope.countNewNotification();
                })
            };
            $scope.countNewNotification();

            $scope.open = function () {
                const modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/app/notification/views/show.notification.html',
                    controller: 'notificationController'
                });
            };
        }]);


