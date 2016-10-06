    angular
    .module('delivery')
    .controller('ordersClosedController',
        ['$scope', '$http', '$uibModal',
        function ($scope, $http, $uibModal) {
        $scope.orders = {
            closed: []
        };

        $scope.retrieveClosedOrders = () => {
            $http.get('/order/closed').then(response => {
                $scope.orders.closed = response.data;
            })
        };
        $scope.retrieveClosedOrders();

        $scope.addFeedback = function () {
            const modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/app/views/add.feedback.html',
                controller: 'addFeedbackController'
            });

        }
        }]
    )
    .controller('addFeedbackController', function ($scope,orderService, $http, $uibModalInstance) {
            $scope.idForFeedback = orderService.getId();

            $scope.form = {
                submit: () => {
                    const data = {
                        rate: $scope.form.rate,
                        text: $scope.form.text,
                        orderId: $scope.idForFeedback
                    };
                    $http.post('/order/addfeedback', data).then(response => {
                        $uibModalInstance.close(true)
                    }, response => {
                        alert('failed to add feedback')
                    });
                }
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
        }
    );
