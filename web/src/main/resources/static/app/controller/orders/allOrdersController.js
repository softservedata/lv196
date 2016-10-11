angular
    .module('delivery')
    .controller('allOrdersController', ['$scope', 'orderService', '$http',
    function ($scope, orderService, $http) {
        $scope.orders = {
            open: []
        };

        $scope.retrieveOpenOrders = () => {
            $http.get('/all-orders/open').then(response => {
                $scope.orders.open = response.data;
            })
        };
        $scope.retrieveOpenOrders();

        $scope.filter = () => {
            select = document.getElementById('filter')
            filter_by = select.options[select.selectedIndex].value
            filter_value = document.getElementById('filter_value').value
            url = {
                'city_from': '/all-orders/filtered-by-city-from/?city=',
                'city_to': '/all-orders/filtered-by-city-to/?city=',
                'weight': '/all-orders/filtered-by-weight/?weight=',
                'arrival_date': '/all-orders/filtered-by-arrival-date/?date='
            };
            $http.get(url[filter_by] + filter_value).then(response => {
                console.log(response.data),
                $scope.orders.open = response.data
            })
        };

        $scope.addOffer = (id) => {
            orderService.setId(id);
            $http.post('/all-orders/offer/' + orderService.getId()).then(response => {
                console.log("addOffer");
            })
        }
   }]);
