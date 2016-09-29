angular
    .module('delivery')
    .controller('ordersController', ['$scope', '$http', '$uibModal', function ($scope, $http, $uibModal) {
        $scope.orders = {
            inProgress: [],
            open: []
        };
        $scope.retrieveInProgressOrders = () => {
            $http.get('/order/in_progress').then(response => {
                $scope.orders.inProgress = response.data;
            })
        };
        $scope.retrieveInProgressOrders();

        $scope.retrieveOpenOrders = () => {
            $http.get('/order/open').then(response => {
                $scope.orders.open = response.data;
            })
        };
        $scope.retrieveOpenOrders();


        $scope.showOrderCreation = () => {
            const modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/resources/app/views/order.creation.html',
                controller: 'addOrderController'
            });

            modalInstance.result.then(function (added) {
                if (added) {
                    $scope.retrieveOpenOrders();
                }
            });
        }
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

            $scope.form = {
                submit: () => {
                    let data = {
                        cityIdFrom: $scope.form.cityIdFrom,
                        cityIdTo: $scope.form.cityIdTo,
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
        }]);