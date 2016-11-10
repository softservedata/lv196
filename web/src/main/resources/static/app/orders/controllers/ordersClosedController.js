    angular
    .module('delivery')
    .controller('ordersClosedController',['$scope', '$http', '$uibModal',
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

        $scope.addFeedback = function (orderForFeedback) {
            const modalInstance = $uibModal.open({
                animation: true,
                templateUrl: '/app/feedbacks/views/add.feedback.html',
                controller: 'addFeedbackController',
                resolve:{
                    order: ()=> orderForFeedback
                }
            });

        };
        }]
    )
    .controller('addFeedbackController', ['$scope','$orders', '$http', '$uibModalInstance', 'Notification', '$filter', 'order',
        function ($scope, $orders, $http, $uibModalInstance, Notification, $filter, order) {
            $scope.idForFeedback = order.id;

            $scope.form = {
                submit: () => {

                    const data = {
                        feedbackId: $scope.form.feedbackId,
                        rate: $scope.form.rate*10,
                        text: $scope.form.text,
                        orderId: $scope.idForFeedback
                    };

                    $orders.saveFeedback(data).then(response => {
                        Notification.success($filter('translate')('saved_feedback'));
                        $uibModalInstance.close(true)
                    }, response => {
                        Notification.error($filter('translate')('failed_add_feedback'));
                    });
                }
            };

            $scope.cancel = function () {
                $uibModalInstance.dismiss('cancel');
            };

            $http.get('/order/getFeedback/' + order.id).then(response => {
                $scope.feedback = response.data;
                $scope.form.feedbackId = $scope.feedback.feedbackId;
                $scope.form.rate = $scope.feedback.rate/10;
                $scope.form.text = $scope.feedback.text;
                $scope.form.approved = $scope.feedback.approved;
                $scope.form.orderId = $scope.feedback.orderId;
                $scope.form.userEmail = $scope.feedback.userEmail;
                $scope.form.createdOn = $scope.feedback.createdOn;
            })
        }]
    );

