angular
    .module('delivery')
    .controller('driverController', ['$scope', '$location', '$orderProperty', '$http', 'Notification',
        function ($scope, $location, $orderProperty, $http, Notification){
            $scope.redirect = function(){
                $location.url('/find-order');
            }


        }]);