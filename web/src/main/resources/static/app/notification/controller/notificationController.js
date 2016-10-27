angular
    .module('delivery')
    .controller('notificationController',['$scope', '$http', 'Notification','$notificationProperty', '$rootScope',
        function ($scope, $http, Notification, $notificationProperty, $rootScope) {
            $scope.notifications = {
                notifications: []
            };

            $scope.retrieveNotifications = () => {
                $http.get('/notification').then(response => {
                    $scope.notifications = response.data;
                    if ($notificationProperty.getAmount() > 0) {
                        ($rootScope.lang === 'en') ? Notification('Now all your notifications are readed'):
                            Notification('Всі ваші сповіщення зараз прочитані');
                    }
                })
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
    .controller('notificationAmountController',['$scope', '$http', '$notificationProperty', 'Notification', '$rootScope',
        function ($scope, $http, $notificationProperty, Notification, $rootScope) {
            $scope.countNewNotification = () => {
                $http.get('/notification/count').then(response => {
                    $scope.amountNewNotification = response.data;
                    if ($scope.amountNewNotification > $notificationProperty.getAmount()) {
                        ($rootScope.lang === 'en') ? Notification('You have new Notification'):
                            Notification('У вас нове сповіщення');
                        $notificationProperty.setAmount($scope.amountNewNotification);
                    }
                    else {
                        $notificationProperty.setAmount(0);
                    }
                    $scope.countNewNotification();
                })
            };
            $scope.countNewNotification();
        }])
    .service('$notificationProperty', function () {
        var _amount = null;
        return {
            setAmount: function (amount) {
                _amount = amount;
            },
            getAmount: function () {
                return _amount;
            }
        };
    });


