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
    .controller('addFeedbackController' ['$scope', '$http', '$uibModalInstance',
        function ($scope, $http, $uibModalInstance, orderService) {   //визиваю мій Сервіс
            $scope.idForFeedback = orderService.getId();              //витягую з Сервісу та призначаю Id змінній

            $scope.form = {
                submit: () => {
                    const data = {
                        rate: $scope.form.rate,
                        text: $scope.form.text,
                        orderId: $scope.idForFeedback      //призначаю orderId відгуку
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
        }]
    )
    .service('orderService', function () {
        var _id = 3;                         //Захардкодив orderId для перевірки
        return {
            setId: function (id) {
                _id = id;
            },
            getId: function () {
                return _id;
            }
        }
    });

