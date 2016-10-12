angular
    .module('delivery')
    .controller('ordersOpenController', ['$scope','$orderProperty', '$uibModal', '$orders',
        function ($scope, $orderProperty, $uibModal, $orders) {
            $scope.orders = {
                open: []
            };

            $scope.retrieveOpenOrders = () => {
                $orders.findOpen().then(response => {
                    if (response.data.length==0){
                        alert('Now you have no open orders. To create an order, click "+" button in the right corner.');
                    }
                    else{
                    $scope.orders.open = response.data;}
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

            $scope.showOrderTrash = (id) => {
                $orderProperty.setId(id);
                const modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/app/orders/views/submit.remove.html',
                    controller: 'removeOrderController'
                });

                modalInstance.result.then(function (del) {
                    if (del) {
                        $scope.retrieveOpenOrders();
                    }
                });
            };

            $scope.showOffers = (order) => {
                if (order.numberOfOffers==0){
                    alert('Looks like you do not have any Offers');
                }
                else {
                $orderProperty.setId(order.id);
                $uibModal.open({
                    animation: true,
                    templateUrl: '/app/orders/views/show.offers.html',
                    controller: 'showOffersController'
                });
                }
            };
        }])
    .controller('addOrderController', ['$scope', '$uibModalInstance', '$orders', '$locations',
        function ($scope, $uibModalInstance, $orders, $locations) {
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

            $scope.initMapFrom = function ($item, $model, $label) {
                $scope.$item = $item;
                $scope.$model = $model;
                $scope.$label = $label;

                const map = L.map('mapid').setView([49.7, 28.3], 5);

                L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
                    maxZoom: 18,
                    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
                    '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
                    'Imagery © <a href="http://mapbox.com">Mapbox</a>',
                    id: 'mapbox.streets',
                    accessToken: 'pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpandmbXliNDBjZWd2M2x6bDk3c2ZtOTkifQ._QA7i5Mpkd_m30IGElHziw'
                }).addTo(map);

                L.marker([49.8326678, 23.942024]).addTo(map);
                L.marker([50.4016974, 30.2518212]).addTo(map);
            };
        }]
    )
    .controller('removeOrderController', ['$scope', '$orderProperty', '$uibModalInstance', '$orders',
        function ($scope, $orderProperty, $uibModalInstance, $orders) {
            $scope.form = {
                submit: () => {
                    $orders.remove($orderProperty.getId()).then(response => {
                        $uibModalInstance.close(true)
                    }, response => {
                        alert('failed to remove order')
                    });
                }
            };
            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
        }]
    )
    .controller('showOffersController',function ($scope, $orderProperty, $http) {
        $scope.offers = {
            offers: []
        };

        $scope.retrieveOffers = () => {
            $http.get('/order/offers/'+$orderProperty.getId()).then(response => {
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