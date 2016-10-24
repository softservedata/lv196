angular
    .module('delivery')
    .controller('notificationController',['$scope', '$http', 'Notification','$notificationProperty',
        function ($scope, $http, Notification, $notificationProperty) {
            $scope.notifications = {
                notifications: []
            };

            $scope.retrieveNotifications = () => {
                $http.get('/notification').then(response => {
                    $scope.notifications = response.data;
                    if ($notificationProperty.getAmount() > 0) {
                       Notification('Now all your notifications are readed')
                    }
                })
            };
            $scope.retrieveNotifications();

            $scope.delete = id => {
                $http.delete('/notification/'+id).then(response =>{
                    Notification.success('Delete notification successfull');
                    $scope.retrieveNotifications();
                })
            };
        }])
    .controller('notificationAmountController',['$scope', '$http', '$notificationProperty','$timeout','Notification',
        function ($scope, $http, $notificationProperty, $timeout, Notification) {
            $scope.countNewNotification = () => {
                $http.get('/notification/count').then(response => {
                    $scope.amountNewNotification = response.data;
                    if ($scope.amountNewNotification > $notificationProperty.getAmount()) {
                        Notification('You have new Notification');
                        $notificationProperty.setAmount($scope.amountNewNotification);
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


