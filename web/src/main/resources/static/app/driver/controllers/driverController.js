angular
    .module('delivery')
    .controller('driverController', ['$scope', '$chat', '$locations', '$orderProperty', '$http', 'Notification', '$uibModal', '$location', '$anchorScroll',
        function ($scope, $chat, $locations, $orderProperty, $http, Notification, $uibModal, $location, $anchorScroll) {

            $scope.filterObject = {
                filterByCityFrom: '',
                filterByCityTo: '',
                filterByWeight: '',
                filterByArrivalDate: ''
            };

            $scope.paginationObject = {
                itemsPerPage: 10,
                currentPage: 1
            };

            $scope.orders = {
                open: []
            };

            $scope.setItemsPerPage = function () {
                $scope.paginationObject.currentPage = 1;
                $scope.retrieveOpenOrders();
            };

            $scope.scroll = function (dir) {
                $location.hash(dir);
                $anchorScroll();
            };

            $scope.pageChanged = function () {
                $scope.scroll('top');
                $scope.retrieveOpenOrders();
            };

            $scope.retrieveOpenOrders = function () {
                $http({
                    url: '/driver/open/',
                    method: 'GET',
                    params: {
                        itemsPerPage: $scope.paginationObject.itemsPerPage,
                        currentPage: $scope.paginationObject.currentPage
                    }
                }).then(function (response0) {
                    $http.get("/driver/count-items/")
                        .then(function (response1) {
                            $scope.totalItems = response1.data;
                        });
                    $scope.orders.open = response0.data;
                })
            };
            $scope.retrieveOpenOrders();

            $scope.findLocations = val => {
                return $locations.find(val).then(response => response.data);
            };

            $scope.filter = () => {
                console.log('In method filter');
                var cityFromId = '';
                if ($scope.filterObject.filterByCityFrom && $scope.filterObject.filterByCityFrom.cityId) {
                    cityFromId = $scope.filterObject.filterByCityFrom.cityId;
                } else if ($scope.filterObject.filterByCityFrom) {
                    Notification.error('Sorry, you write incorrect city from name. You can use the hint');
                }

                var cityToId = '';
                if ($scope.filterObject.filterByCityTo && $scope.filterObject.filterByCityTo.cityId) {
                    cityToId = $scope.filterObject.filterByCityTo.cityId;
                } else if ($scope.filterObject.filterByCityTo) {
                    Notification.error('Sorry, you write incorrect city to name. You can use the hint');
                }

                var filterByWeight = '';
                if ($scope.filterObject.filterByWeight > 0) {
                    filterByWeight = $scope.filterObject.filterByWeight;
                } else if ($scope.filterObject.filterByWeight) {
                    Notification.error('Sorry, you write incorrect weight. Please, write a positive number');
                }

                var filterByArrivalDate = '';
                if (Date.parse($scope.filterObject.filterByArrivalDate) > Date.now()) {
                    filterByArrivalDate = Date.parse($scope.filterObject.filterByArrivalDate);
                } else if ($scope.filterObject.filterByArrivalDate) {
                    Notification.error('Sorry, you write incorrect date. You can use the calendar');
                }

                $http({
                    url: '/driver/filtered-orders/',
                    method: 'GET',
                    params: {
                        cityFromId: cityFromId,
                        cityToId: cityToId,
                        weight: filterByWeight,
                        date: filterByArrivalDate,
                        itemsPerPage: $scope.paginationObject.itemsPerPage,
                        currentPage: $scope.paginationObject.currentPage
                    }
                }).then(function (response0) {
                    $http.get("/driver/count-items-filter/")
                        .then(function (response1) {
                            $scope.totalItems = response1.data;
                        });
                    $scope.orders.open = response0.data;
                })
            };

            $scope.datePicker = {
                format: 'dd/MM/yyyy',
                options: {
                    formatDay: 'dd',
                    formatMonth: 'MMMM',
                    formatYear: 'yyyy',
                    maxDate: new Date(2017, 12, 31),
                    minDate: new Date(),
                    startingDay: 1,
                    showWeeks: false
                },
                open: () => {
                    $scope.datePicker.opened = true;
                }
            };

            $scope.addOffer = (id) => {
                $orderProperty.setId(id);
                $http.post('/driver/offer/' + $orderProperty.getId()).then(response => {
                    console.log("addOffer");
                    Notification.success('Thank you, your offer added successfully');
                    $scope.retrieveOpenOrdersWithMyOffers();
                }).catch(function () {
                    Notification.error('Sorry, you can not create more than one offer for this order');
                })
            }

            $scope.trackingRedirect = function () {
                $location.url('/tracking');
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

            $scope.showChatByOrder = orderId => {
                $http.get('driver/offer-id/' + orderId).then(response => {
                    $scope.showChat(response.data)
                })
            }

            $scope.showChat = id => {
                $chat.open(id);
            }

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