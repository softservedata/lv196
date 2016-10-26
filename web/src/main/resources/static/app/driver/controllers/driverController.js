angular
    .module('delivery')
    .controller('driverController', ['$scope', '$location', '$orderProperty', '$http', 'Notification',
        function ($scope, $location, $orderProperty, $http, Notification){
            $scope.orders = {
                open: []
            };

            $scope.redirect = function(){
                $location.url('/find-order');
            }

            $scope.retrieveOpenOrdersWithMyOffers = () => {
                $http.get('/driver/my-offers').then(response => {
                    $scope.orders.open = response.data;
                })
            };
            $scope.retrieveOpenOrdersWithMyOffers();

            $scope.cancelOffer = (id) => {
                $orderProperty.setId(id);
                $http.delete('/driver/cancel-offer/' + $orderProperty.getId()).then(response => {
                    console.log("cancelOffer");
                    Notification.success('Thank you, your offer canceled successfully');
                })
            }

        }]);