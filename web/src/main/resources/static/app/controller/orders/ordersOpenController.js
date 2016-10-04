angular
    .module('delivery')
    .controller('ordersOpenController', ['$scope', '$http', '$uibModal',
        function ($scope, $http, $uibModal) {
            $scope.orders = {
                open: []
            };

            $scope.retrieveOpenOrders = () => {
                $http.get('/order/open').then(response => {
                    $scope.orders.open = response.data;
                })
            };
            $scope.retrieveOpenOrders();

            $scope.showOrderCreation = () => {
                const modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: '/app/views/orders/order.creation.html',
                    controller: 'addOrderController'
                });

                modalInstance.result.then(function (added) {
                    if (added) {
                        $scope.retrieveOpenOrders();
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