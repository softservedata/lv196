angular
    .module('delivery')
    .controller('ordersOpenController', ['$scope', '$orderProperty', '$uibModal', '$orders', '$utils', 'Notification', '$rootScope',
        function ($scope, $orderProperty, $uibModal, $orders, $utils, Notification, $rootScope) {
            $scope.orders = {
                open: []
            };

            $scope.retrieveOpenOrders = () => {
                $orders.findOpen().then(response => {
                    $scope.orders.open = response.data;
                })
            };
            $scope.retrieveOpenOrders();

            $scope.showOrderCreation = (orderForEdit) => {
                const modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/app/orders/views/order.creation.html',
                    controller: 'addOrderController',
                    resolve: {
                        order: () => orderForEdit
                    }
                });

                modalInstance.result.then(added => {
                    if (added) {
                        $scope.retrieveOpenOrders();
                    }
                });
            };

            $scope.deleteOrder = id => {
                $utils.confirmDialog({
                    message: 'Are you sure you want to delete this order?',
                    yes: 'Remove',
                    no: 'Cancel',
                    yesBtnClass: 'btn-danger'
                }).then(answer => {
                    if (answer) {
                        $orders.remove(id).then(
                            () => $scope.retrieveOpenOrders(),
                            () => Notification('Could not delete order'));
                    }
                })
            };

            $scope.showOffers = (order) => {
                if (order.amountOfOffers == 0) {
                    $scope.primary = function () {
                        ($rootScope.lang === 'en') ?
                            Notification('Info : Sorry there are no offers for your Order at this time'):
                            Notification('Інформація : Вибачте, але на данний момент немає жодної заявки');
                    };
                    $scope.primary();
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
    .controller('addOrderController', ['$scope', '$timeout', '$uibModalInstance', '$orders', '$locations', 'Notification', 'order',
        function ($scope, $timeout, $uibModalInstance, $orders, $locations, Notification, order) {
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

            $scope.locationLabel = location => {
                if (location) {
                    let arr = [location.cityName];
                    location.regionName ? arr.push(location.regionName + ' rg.') : {};
                    location.stateName ? arr.push(location.stateName + ' obl.') : {};

                    return arr.join(', ');
                }
            };

            $scope.findLocations = val => {
                return $locations.find(val).then(response => response.data);
            };

            $scope.cancel = () => $uibModalInstance.dismiss('cancel');

            $scope.form = {
                submit: () => {
                    const data = {
                        id: $scope.form.id,
                        locationFrom: $scope.form.locationFrom,
                        locationTo: $scope.form.locationTo,
                        arrivalDate: $scope.form.arrivalDate,
                        height: $scope.form.height,
                        width: $scope.form.width,
                        length: $scope.form.length,
                        weight: $scope.form.weight,
                        description: $scope.form.description
                    };
                    $orders.save(data).then(response => {
                        $uibModalInstance.close(true)
                    }, response => {
                        Notification('failed to add order')
                    });
                }
            };

            $scope.map = {
                created: false,
                init: () => {
                    if (!$scope.map.created) {
                        $scope.map.created = true;

                        // build the map after the scope is rebuilt with new `$scope.map.created` value
                        $timeout(() => {
                            $scope.map.container = L.map('orderCreateMap')
                            // Ukraine map centering
                                .setView([49.7, 28.3], 5);

                            L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
                                attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                            }).addTo($scope.map.container);

                            $scope.map.routing.addTo($scope.map.container);

                        }, 0, false);
                    }

                },
                routing: L.Routing.control({
                    plan: L.Routing.plan({
                        draggableWaypoints: false
                    }),
                    showAlternatives: false
                }),
                open: () => {
                    let from = $scope.form.locationFrom;
                    let to = $scope.form.locationTo;
                    let fromInfo = checkLocation(from);
                    let toInfo = checkLocation(to);

                    if (fromInfo.valid && toInfo.valid) {
                        $scope.map.init();
                        $scope.map.routing.spliceWaypoints(0, 2);
                        $scope.map.routing.setWaypoints([
                            {
                                latLng: L.latLng(from.latitude, from.longitude),
                                name: from.cityName
                            },
                            {
                                latLng: L.latLng(to.latitude, to.longitude),
                                name: to.cityName
                            }
                        ]);
                    } else if (fromInfo.message) {
                        Notification(fromInfo.message);
                    } else if (toInfo.message) {
                        Notification(toInfo.message);
                    }
                }
            };

            if (order) {
                $scope.form.id = order.id;
                $scope.form.locationFrom = order.locationFrom;
                $scope.form.locationTo = order.locationTo;
                $scope.form.arrivalDate = order.arrivalDate;
                $scope.form.height = order.height;
                $scope.form.width = order.width;
                $scope.form.length = order.length;
                $scope.form.weight = order.weight;
                $scope.form.description = order.description;
                $timeout(() => $scope.map.open(), 0, false);
            }

            function checkLocation(location) {
                let info = {valid: false};

                if (location) {
                    info.valid = Number.isFinite(location.latitude) && Number.isFinite(location.latitude);
                    if (!info.valid) {
                        info.message = 'Location is not defined for ' + $scope.locationLabel(location);
                    }
                }
                return info;
            }
        }
    ])
    .controller('showOffersController',['$scope', '$orderProperty', '$http', 'Notification','$rootScope',
        function ($scope, $orderProperty, $http, Notification, $rootScope) {
        $scope.offers = {
            offers: []
        };

        $scope.retrieveOffers = () => {
            $http.get('/order/offers/' + $orderProperty.getId()).then(response => {
                $scope.offers = response.data;
                for (var i=0;i<$scope.offers.length;i++){
                    $scope.offers[i].rate = $scope.offers[i].rate/10;
                }
            })
        };
        $scope.retrieveOffers();

        $scope.btnApprovedStyle = function (approved) {
            if (approved) {
                return "offertrue";
            } else {
                return "offerfalse";
            }
        };

        $scope.GetApprovedOffers = function () {
            for (var i=0;i<$scope.offers.length;i++) {
                if ($scope.offers[i].approved) {
                   return $scope.offers[i]
                }
            }
        };

        $scope.update = function(offer) {
            for (var i=0;i<$scope.offers.length;i++) {
                    $scope.offers[i].approved = false;
            }
            offer.approved = true;
        };

        $scope.changeStatus = () => {
            $http.put('/order/change/', $scope.GetApprovedOffers()).then(response => {
                $scope.retrieveOffers();
            });
            $scope.info = function() {
                ($rootScope.lang === 'en') ?
                    Notification.success('Success : Succesful change Offer status'):
                    Notification.success('Успіх : Успішно змінено статус заявки');
            };
            $scope.info();
        };
    }]);