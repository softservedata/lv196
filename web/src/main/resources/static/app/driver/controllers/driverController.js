angular
    .module('delivery')
    .controller('driverController', ['$scope', '$chat', '$locations', '$orderProperty', '$http', 'Notification', '$uibModal', '$location', '$anchorScroll', '$filter',
        function ($scope, $chat, $locations, $orderProperty, $http, Notification, $uibModal, $location, $anchorScroll, $filter) {

            $scope.filterObject = {
                cityFrom: null,
                cityTo: null,
                weight: null,
                arrivalDate: null,
                currentPage: 1,
                itemsPerPage: 10
            };

            $scope.orderFilterDto = {
                cityFromId: '',
                cityToId: '',
                weight: '',
                arrivalDate: '',
                currentPage: 1,
                itemsPerPage: 10
            };

            $scope.orders = {
                open: []
            };

            $scope.setItemsPerPage = function () {
                $scope.filterObject.currentPage = 1;
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
                        itemsPerPage: $scope.filterObject.itemsPerPage,
                        currentPage: $scope.filterObject.currentPage
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
                $scope.orderFilterDto.cityFromId = null;
                if ($scope.filterObject.cityFrom && $scope.filterObject.cityFrom.cityId) {
                    $scope.orderFilterDto.cityFromId = $scope.filterObject.cityFrom.cityId;
                } else if ($scope.filterObject.cityFrom) {
                    Notification.error($filter('translate')('incorrect_city_from'));
                }

                $scope.orderFilterDto.cityToId = null;
                if ($scope.filterObject.cityTo && $scope.filterObject.cityTo.cityId) {
                    $scope.orderFilterDto.cityToId = $scope.filterObject.cityTo.cityId;
                } else if ($scope.filterObject.cityTo) {
                    Notification.error($filter('translate')('incorrect_city_to'));
                }

                $scope.orderFilterDto.weight = null;
                if ($scope.filterObject.weight > 0) {
                    $scope.orderFilterDto.weight = $scope.filterObject.weight;
                } else if ($scope.filterObject.weight) {
                    Notification.error($filter('translate')('incorrect_weight'));
                }

                $scope.orderFilterDto.arrivalDate = null;
                var currentTime = Date.now();
                if (Date.parse($scope.filterObject.arrivalDate) > currentTime) {
                    $scope.orderFilterDto.arrivalDate = Date.parse($scope.filterObject.arrivalDate);
                }
                else if ($scope.filterObject.arrivalDate) {
                    Notification.error($filter('translate')('incorrect_date'));
                }

                $scope.orderFilterDto.currentPage = $scope.filterObject.currentPage;
                $scope.orderFilterDto.itemsPerPage = $scope.filterObject.itemsPerPage;

                $http.post('/driver/filtered-orders', $scope.orderFilterDto)
                    .then(function (response0) {
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
                    Notification.success($filter('translate')('offer_added'));
                    $scope.retrieveOpenOrdersWithMyOffers();
                }).catch(function () {
                    Notification.error($filter('translate')('more_than_one_offer'));
                })
            };

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
            };

            $scope.showChat = id => {
                $chat.open(id);
            };

            $scope.cancelOffer = (id) => {
                $orderProperty.setId(id);
                $http.delete('/driver/cancel-offer/' + $orderProperty.getId()).then(response => {
                    console.log("cancelOffer");
                    Notification.success($filter('translate')('offer_canceled'));
                    $scope.retrieveOpenOrdersWithMyOffers();
                })
            };

            $scope.orders = {
                approved: []
            };

            $scope.retrieveMyApprovedOrders = () => {
                $http.get('/driver/my-approved-orders').then(response => {
                    $scope.orders.approved = response.data;
                })
            };
            $scope.retrieveMyApprovedOrders();

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
                    templateUrl: '/app/feedback/views/add.feedback.html',
                    controller: 'addFeedbackController',
                    resolve:{
                        order: ()=> orderForFeedback
                    }
                });
            };

            $scope.approveDelivery = (id) => {
                $orderProperty.setId(id);
                modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: '/app/orders/views/approveDelivery.html',
                    scope: $scope
                });
            };

            $scope.confirmApproveDelivery = () => {
                $http.put('/driver/approve-delivery/', $orderProperty.getId()).then(response => {
                    Notification.info($filter('translate')('message_send'));
                });
                modalInstance.close()
            };

            $scope.cancelApproveDelivery = () => {
                modalInstance.dismiss('cancel');
            }
        }]);