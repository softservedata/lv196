angular
    .module('delivery')
    .controller('ordersInProgressController', ['$scope', '$chat', '$orders', '$orderProperty', '$uibModal', '$http', 'Notification', '$location',
        function ($scope, $chat, $orders, $orderProperty, $uibModal, $http, Notification, $location) {

            var modalInstance;

            $scope.orders = {
                inProgress: []
            };

            $scope.retrieveInProgressOrders = () => {
                $orders.findInProgress().then(response => {
                    $scope.orders.inProgress = response.data;
                })
            };
            $scope.retrieveInProgressOrders();

            $scope.showChat = (offerId) => {
                $chat.open(offerId);
            };

            $scope.showMap = function (orderThis) {
                order: () => orderThis
            };

            $scope.approveDelivery = (id) => {
                $orderProperty.setId(id);
                modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: '/app/orders/views/approveDelivery.html',
                    scope: $scope
                });
            };

            $scope.confirmApproveDelivery = () => {
                $http.put('/order/approve-delivery/', $orderProperty.getId()).then(response => {
                    Notification.success('Thank you, your delivery was approved successfully. You can write a feedback');
                    window.location.href = '/#/orders/closed';
                });
            };

            $scope.cancelApproveDelivery = () => {
                modalInstance.dismiss('cancel');
            }

        }]);