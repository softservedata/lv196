angular
    .module('delivery')
    .factory('$locations', ['$http', $http => {
        return {
            find: val => $http.get('location/?city=' + val)
        }
    }]);
