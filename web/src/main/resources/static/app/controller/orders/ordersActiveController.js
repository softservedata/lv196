angular
    .module('delivery')
    .controller('ordersActiveController', ['$scope', '$http', '$uibModal',
        function ($scope, $http, $uibModal) {
            $scope.orders = {
                inProgress: [],
                open: []
            };

            $scope.retrieveActiveOrders = () => {
                $http.get('/order/active').then(response => {
                    $scope.orders.inProgress = [];
                    $scope.orders.open = [];
                    response.data.forEach(order => {
                        if (order.status === "In progress") {
                            $scope.orders.inProgress.push(order);
                        } else {
                            $scope.orders.open.push(order);
                        }
                    })
                })
            };
            $scope.retrieveActiveOrders();

            $scope.showOrderCreation = () => {
                const modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/app/views/order.creation.html',
                    controller: 'addOrderController'
                });

                modalInstance.result.then(function (added) {
                    if (added) {
                        $scope.retrieveActiveOrders();
                    }
                });
            };
        }])
    .controller('addOrderController', ['$scope', '$http', '$uibModalInstance',
        function ($scope, $http, $uibModalInstance) {
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
                return $http.get('location/?city=' + val).then(response => response.data);
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
                    $http.post('/order', data).then(response => {
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
    );