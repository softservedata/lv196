angular
    .module('delivery')
    .controller('ordersOpenController', ['$scope','orderService', '$http', '$uibModal', '$orders',
        function ($scope,orderService, $http, $uibModal, $orders) {
            $scope.orders = {
                open: []
            };

            $scope.retrieveOpenOrders = () => {
                $orders.findOpen().then(response => {
                    $scope.orders.open = response.data;
                })
            };
            $scope.retrieveOpenOrders();

            $scope.showOrderCreation = () => {
                const modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/app/orders/views/order.creation.html',
                    controller: 'addOrderController'
                });

                modalInstance.result.then(function (added) {
                    if (added) {
                        $scope.retrieveOpenOrders();
                    }
                });
            };

            $scope.showOffers = (order) => {
                orderService.setId(order.id);
                $uibModal.open({
                    animation: true,
                    templateUrl: '/app/orders/views/show.offers.html',
                    controller: 'showOffersController'
                });
            };
        }])
    .controller('addOrderController', ['$scope', '$http', '$uibModalInstance', '$orders', '$locations',
        function ($scope, $http, $uibModalInstance, $orders, $locations) {
            $scope.datePicker = {
                format: 'yyyy/MM/dd',
                options: {
                    formatYear: 'yyyy',
                    formatMonth: 'MMMM',
                    formatDay: 'dd',
                    maxDate: new Date(2016, 12, 31),
                    minDate: new Date(),
                    startingDay: 1,
                    showWeeks: false
                },
                open: () => {
                    $scope.datePicker.opened = true;
                }
            };

            $scope.findLocations = val => {
                return $locations.find(val).then(response => response.data);
            };

            $scope.form = {
                submit: () => {
                    const data = {
                        cityIdFrom: $scope.form.locationFrom.cityId,
                        cityIdTo: $scope.form.locationTo.cityId,
                        arrivalDate: $scope.form.arrivalDate,
                        height: $scope.form.height,
                        width: $scope.form.width,
                        length: $scope.form.length,
                        weight: $scope.form.weight,
                        description: $scope.form.description
                    };
                    $orders.add(data).then(response => {
                        $uibModalInstance.close(true)
                    }, response => {
                        alert('failed to add order')
                    });
                }
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
        }]
    )
    .controller('showOffersController',function ($scope, orderService, $http) {
        $scope.offers = {
            offers: []
        };
        $scope.retrieveOffers = () => {
            $http.get('/order/offers/'+orderService.getId()).then(response => {
                $scope.offers = response.data;
            })
        };
        $scope.retrieveOffers();

        $scope.btnApprovedStyle = function (approved) {
            if (approved) {
                return "btn btn-primary btn-xs";
            } else {
                return "btn btn-danger btn-xs";
            }
        };

        $scope.changeStatus = (offer) => {
            $http.put('/order/change/',offer).then(response => {
                $scope.retrieveOffers();
                });

        };

    });