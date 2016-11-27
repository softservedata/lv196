angular
    .module('delivery')
    .controller('ordersOpenController', ['$scope', '$uibModal', '$orders', '$utils', 'Notification', '$filter',
        function ($scope, $uibModal, $orders, $utils, Notification, $filter) {
            $scope.orders = {
                open: []
            };

            $scope.templateForOffers = {
                templateUrl: '/app/orders/views/show.offers.html'
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
                            () => Notification($filter('translate')('could_not_delete_order')));
                    }
                })
            };

            $scope.showOffers = (orderWithOffers) => {
                $scope.orderIdWithOffers = orderWithOffers.id;
            };

            $scope.hasOffersToShow = function (orderWithOffers) {
                return (orderWithOffers.amountOfOffers > 0);
            };

            $scope.showConversation = (orderWithOffers) => {
                $uibModal.open({
                    animation: true,
                    templateUrl: '/app/orders/views/conversation.html',
                    controller: 'showConversationController',
                    resolve: {
                        order: () => orderWithOffers
                    }
                });
            }
        }])
    .controller('addOrderController', ['$scope', '$timeout', '$uibModalInstance', '$orders', '$locations', 'Notification', 'order', '$filter',
        function ($scope, $timeout, $uibModalInstance, $orders, $locations, Notification, order, $filter) {
            $scope.datePicker = {
                format: 'yyyy/MM/dd',
                options: {
                    formatYear: 'yyyy',
                    formatMonth: 'MMMM',
                    formatDay: 'dd',
                    maxDate: new Date(2017, 12, 31),
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
                    return location.formatted;
                }
            };

            $scope.findLocations = val => {
                return $locations.googleGeocode(val);
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
                        Notification($filter('translate')('failed_to_add_order'))
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
                                name: from.formatted
                            },
                            {
                                latLng: L.latLng(to.latitude, to.longitude),
                                name: to.formatted
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
                $scope.isEdit = true;
                $scope.orderStatusTitle = 'Edit';
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
                    info.valid = Number.isFinite(location.latitude) && Number.isFinite(location.longitude);
                    if (!info.valid) {
                        info.message = $filter('translate')('location_is_not_defined') + $scope.locationLabel(location);
                    }
                }
                return info;
            }
        }
    ])
    .controller('showOffersController',['$scope', '$http', 'Notification','$filter',
        function ($scope, $http, Notification, $filter) {
        $scope.offers = {
            offers:[]
        };

        $scope.retrieveOffers = () => {
            $http.get('/order/offers/' + $scope.orderIdWithOffers).then(response => {
                $scope.offers.offers = response.data;
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

        $scope.GetApprovedOffer = function () {
            for (var i=0; i<$scope.offers.offers.length; i++) {
                if ($scope.offers.offers[i].approved) {
                   return $scope.offers.offers[i]
                }
            }
        };

        $scope.update = function(offer) {
            for (var i=0;i<$scope.offers.offers.length;i++) {
                    $scope.offers.offers[i].approved = false;
            }
            offer.approved = true;
        };

        $scope.changeStatus = () => {
            $scope.offer = $scope.GetApprovedOffer();
            if (angular.isUndefined($scope.offer)){
                    Notification.warning($filter('translate')('did_not_chose_offer'));
            }
            else{
                $http.put('/order/change/', $scope.offer).then(response => {
                    $scope.retrieveOpenOrders();
                    Notification.success($filter('translate')('change_offer_status'));
                });

            }

            };
        }])
    .controller('showConversationController', ['$scope', '$http', '$chat', '$uibModalInstance', 'order',
        function ($scope, $http, $chat, $uibModalInstance, order) {
            $scope.offers = [];

            $scope.showChat = (offerId) => {
                $chat.open(offerId);
            }
            $scope.retrieveDriverNames = () => {
                $http.get('/order/offer/' + order.id).then(response => {
                    $scope.offers = response.data;
                })
            };
            $scope.retrieveDriverNames();

            $scope.cancel = () => $uibModalInstance.dismiss('cancel');
        }]);
