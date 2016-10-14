    angular
    .module('delivery')
    .controller('ordersClosedController',
        function ($scope, $orderProperty, $http, $uibModal) {

        $scope.orders = {
            closed: []
        };

        $scope.retrieveClosedOrders = () => {
            $http.get('/order/closed').then(response => {
                $scope.orders.closed = response.data;
            })
        };
        $scope.retrieveClosedOrders();

        $scope.addFeedback = function (order) {
            $orderProperty.setId(order.id);
            const modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/app/views/feedbacks/add.feedback.html',
                controller: 'addFeedbackController'
            });

        };
        }
    )
    .controller('addFeedbackController', function ($scope, $orderProperty, $http, $uibModalInstance, Notification) {
            $scope.idForFeedback = $orderProperty.getId();

            $http.get('/order/checkfeedback/' + $orderProperty.getId()).then(response => {
                $scope.answer = response.data;
            });

            $scope.form = {
                submit: () => {
                    const data = {
                        rate: $scope.form.rate,
                        text: $scope.form.text,
                        orderId: $scope.idForFeedback
                    };

                    $scope.retrieveNotice = () => {
                        if ($scope.answer.Amount > 2){
                               $scope.message = () => { Notification.warning('Warning : You already wrote several feedbacks on this Order. Please wait for moderation or contact with support.');}
                        }
                        else {
                               $scope.message = () => { Notification('Info : Your feedback have been saved');}
                        }
                        $scope.primary = function() {
                            $scope.message();
                        };
                        $scope.primary();
                    };
                    $scope.retrieveNotice();

                    $http.post('/order/addfeedback', data).then(response => {
                        $uibModalInstance.close(true)
                    }, response => {
                        Notification.error('Error : Failed to add feedback');
                    });
                }
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };
        }
    );

