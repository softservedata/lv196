angular
    .module('delivery')
    .controller('driverController', ['$scope', '$location', '$orderProperty', '$http', 'Notification',
        function ($scope, $location, $orderProperty, $http, Notification){
            $scope.orders = {
                open: []
            };

            $scope.orders = {
                inProgress: []
            };

            $scope.orders = {
                closed: []
            };

            $scope.redirect = function(){
                $location.url('/find-order');
            };

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
                    $scope.retrieveOpenOrdersWithMyOffers();
                })
            };

            $scope.retrieveMyOrdersInProgress = () => {
                $http.get('/driver/my-orders-in-progress').then(response => {
                    $scope.orders.inProgress = response.data;
                })
            };
            $scope.retrieveMyOrdersInProgress();

            $scope.retrieveMyOrdersClosed = () => {
                $http.get('/driver/my-orders-closed').then(response => {
                    $scope.orders.closed = response.data;
                })
            };
            $scope.retrieveMyOrdersClosed();
        }])

    .controller('customerFeedbackController', ['$scope', '$orderProperty', '$http',
        function ($scope, $orderProperty, $http) {

            $scope.feedbacks = {
                feedback: []
            };

            $scope.customerFeedback = (id) => {
                console.log("customerFeedback");
                console.log(id);
                console.log($orderProperty);
                console.log($orderProperty.getId());
                $orderProperty.setId(id);
                $http.get('/driver/customer-feedback/' + $orderProperty.getId()).then(response => {
                    console.log(response.data)
                    $scope.feedbacks.feedback = response.data;
                })};
}])