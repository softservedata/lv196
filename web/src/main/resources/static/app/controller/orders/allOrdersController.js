angular
    .module('delivery')
    .controller('allOrdersController', ['$scope', '$http',
    function ($scope, $http) {
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
           switch (filter_by) {
               case 'city_from':
                   url = '/all-orders/filtered-by-city-from/?city='
                   break;
               case 'city_to':
                   url = '/all-orders/filtered-by-city-to/?city='
                   break;
               case 'weight':
                   url = '/all-orders/filtered-by-weight/?weight='
                   break;
               case 'arrival_date':
                   url = '/all-orders/filtered-by-arrival-date/?date='
                   break;
           }
           $http.get(url + filter_value).then(response => {
               console.log(response.data)
               $scope.orders.open = response.data
           })
       };
   }]);
