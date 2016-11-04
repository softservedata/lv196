angular
    .module('delivery')
    .controller('driverController', ['$scope', '$locations', '$orderProperty', '$http', 'Notification', '$uibModal',
        function ($scope, $locations, $orderProperty, $http, Notification, $uibModal) {
            $scope.orders = {
                open: []
            };

            $scope.retrieveOpenOrders = () => {
                $http.get('/driver/open').then(response => {
                    $scope.orders.open = response.data;
                })
            };
            $scope.retrieveOpenOrders();

            $scope.findLocations = val => {
                return $locations.find(val).then(response => response.data);
            };

            $scope.filter = () => {
                console.log('In method filter');
                var cityFromId = '';
                if ($scope.filterByCityFrom && $scope.filterByCityFrom.cityId) {
                    cityFromId = $scope.filterByCityFrom.cityId;
                } else if ($scope.filterByCityFrom) {
                    Notification.error('Sorry, you write incorrect city from name. You can use the hint');
                }

                var cityToId = '';
                if ($scope.filterByCityTo && $scope.filterByCityTo.cityId) {
                    cityToId = $scope.filterByCityTo.cityId;
                } else if ($scope.filterByCityTo) {
                    Notification.error('Sorry, you write incorrect city to name. You can use the hint');
                }

                var filterByWeight = '';
                if ($scope.filterByWeight > 0) {
                    filterByWeight = $scope.filterByWeight;
                } else if ($scope.filterByWeight) {
                    Notification.error('Sorry, you write incorrect weight. Please, write a positive number');
                }

                var filterByArrivalDate = '';
                if (Date.parse($scope.filterByArrivalDate) > Date.now()) {
                    filterByArrivalDate = Date.parse($scope.filterByArrivalDate);
                } else if ($scope.filterByArrivalDate) {
                    Notification.error('Sorry, you write incorrect date. You can use the calendar');
                }

                $http({
                    url: '/driver/filtered-orders/',
                    method: 'GET',
                    params: {
                        cityFromId: cityFromId,
                        cityToId: cityToId,
                        weight: filterByWeight,
                        date: filterByArrivalDate
                    }
                }).then(response => {
                    console.log(response.data),
                        $scope.orders.open = response.data
                });
            };

            $scope.addOffer = (id) => {
                $orderProperty.setId(id);
                $http.post('/driver/offer/' + $orderProperty.getId()).then(response => {
                    console.log("addOffer");
                    Notification.success('Thank you, your offer added successfully');
                }).catch(function () {
                    Notification.error('Sorry, you can not create more than one offer for this order');
                })
            }

            $scope.redirect = function () {
                $location.url('/find-order');
            };

            $scope.orders = {
                myOpen: []
            };

            $scope.retrieveOpenOrdersWithMyOffers = () => {
                $http.get('/driver/my-offers').then(response => {
                    $scope.orders.myOpen = response.data;
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

            $scope.orders = {
                inProgress: []
            };

            $scope.retrieveMyOrdersInProgress = () => {
                $http.get('/driver/my-orders-in-progress').then(response => {
                    $scope.orders.inProgress = response.data;
                })
            };
            $scope.retrieveMyOrdersInProgress();

            $scope.orders = {
                closed: []
            };

            $scope.retrieveMyOrdersClosed = () => {
                $http.get('/driver/my-orders-closed').then(response => {
                    $scope.orders.closed = response.data;
                })
            };
            $scope.retrieveMyOrdersClosed();

            $scope.addFeedback = function (orderForFeedback) {
                const modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/app/feedbacks/views/add.feedback.html',
                    controller: 'addFeedbackController',
                    resolve:{
                        order: ()=> orderForFeedback
                    }
                });

            };
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
                })
            };
        }])