    angular
    .module('delivery')
    .controller('ordersClosedController',['$scope', '$orderProperty', '$http', '$uibModal',
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
                templateUrl: '/app/feedbacks/views/add.feedback.html',
                controller: 'addFeedbackController'
            });

        };
        }]
    )
    .controller('addFeedbackController', ['$scope', '$orderProperty','$orders', '$http', '$uibModalInstance', 'Notification',
        function ($scope, $orderProperty,$orders, $http, $uibModalInstance, Notification) {
            $scope.idForFeedback = $orderProperty.getId();

            $scope.form = {
                submit: () => {

                    const data = {
                        feedbackId: $scope.form.feedbackId,
                        rate: $scope.form.rate,
                        text: $scope.form.text,
                        orderId: $scope.idForFeedback
                    };

                    $orders.saveFeedback(data).then(response => {
                        Notification.success('Success : Your feedback have been saved')
                        $uibModalInstance.close(true)
                    }, response => {
                        Notification.error('Error : Failed to add feedback');
                    });
                }
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };

            $http.get('/order/getFeedback/' + $orderProperty.getId()).then(response => {
                $scope.feedback = response.data;
                $scope.form.feedbackId = $scope.feedback.feedbackId;
                $scope.form.rate = $scope.feedback.rate;
                $scope.form.text = $scope.feedback.text;
                $scope.form.approved = $scope.feedback.approved;
                $scope.form.orderId = $scope.feedback.orderId;
                $scope.form.userEmail = $scope.feedback.userEmail;
                $scope.form.createdOn = $scope.feedback.createdOn;
            })
        }]
    );

