angular
    .module('delivery')
    .factory('$orders', ['$http', $http => {
        return {
            findOpen: () => $http.get('/order/open'),
            findInProgress: () => $http.get('/order/in-progress'),
            save: order => order.id ? $http.put('/order', order) : $http.post('/order', order),
            remove: id => $http.delete('/order/' + id)
        }
    }]);