angular
    .module('delivery')
    .controller('ordersClosedController', ['$scope', '$http', '$uibModal', 
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
            
        $scope.addFeedback = () => {
            const modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/app/views/add.feedback.html',
                controller: 'addFeedbackController'
            });

        }
    }])
    .controller('addFeedbackController', ['$scope', '$http', '$uibModalInstance',
        function ($scope, $http, $uibModalInstance) {
            $scope.form = {
                submit: () => {
                    const data = {
                        rate: $scope.form.rate,
                        text: $scope.form.text
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
        }]);