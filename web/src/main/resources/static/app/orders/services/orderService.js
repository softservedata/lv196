angular
    .module('delivery')
    .factory('$orders', ['$http', $http => {
        return {
            findOpen: () => $http.get('/order/open'),
            findInProgress: () => $http.get('/order/in-progress'),
            add: order => $http.post('/order', order)
        }
    }]);