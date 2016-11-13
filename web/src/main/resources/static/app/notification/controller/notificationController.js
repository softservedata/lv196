angular
    .module('delivery')
    .controller('notificationController',['$scope', '$http', 'Notification', '$filter',
        function ($scope, $http, Notification, $filter) {
            $scope.templateForNotification = {
                templateUrl: '/app/notification/views/show.notification.html'
            };

            $scope.countNewNotification = () => {
                $scope.amountNewNotification = sessionStorage.getItem('last');

                $scope.circleStyle = function (amount){
                    if (amount > 0) {
                        return "showCircle";
                    } else {
                        return "circle";
                    }
                };
                //begin count method, for now Amount = sessionStorage.getItem('last'))

                $http.get('/notification/count').then(response => {
                    $scope.amountNewNotification = response.data;
                    if ($scope.amountNewNotification > sessionStorage.getItem('last')) {
                        Notification($filter('translate')('new_notification'));
                        sessionStorage.setItem('last', $scope.amountNewNotification);
                        //if amount > 0 sessionStorage = amount
                    }
                    else if ($scope.amountNewNotification == 0) {
                        sessionStorage.setItem('last', 0);
                        //if amount = 0 sessionStorage = 0
                    }
                    $scope.countNewNotification();
                })
            };
            $scope.countNewNotification();

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

                    angular.forEach($scope.notifications.notifications, function (value) {
                        if (value.notificationStatus === 'Info'){
                            $scope.notifications.info.push(value);
                        }
                        else if (value.notificationStatus === 'Success'){
                            $scope.notifications.success.push(value);
                        }
                        else if (value.notificationStatus === 'Warning'){
                            $scope.notifications.warning.push(value);
                        }
                    });

                    if (sessionStorage.getItem('last') > 0) {
                        sessionStorage.setItem('last',0);
                        Notification($filter('translate')('notifications_are_readed'));
                    }
                    
                });
            };

            $scope.delete = id => {
                $http.delete('/notification/'+id).then(response =>{
                    Notification.success($filter('translate')('notification_deleted'));
                    $scope.retrieveNotifications();
                })
            };
        }]);


