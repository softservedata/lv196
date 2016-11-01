angular
    .module('delivery')
    .controller('roleController', ['$scope', '$http', function ($scope, $http) {

        $scope.role = '';

        $scope.getRole = function () {
            if ($scope.role == '') {
                $http.get("/role").then(function(response) {
                    $scope.role = response.data.response;
                });
            }
        };

        $scope.forRole = function (role) {
            return $scope.role == role;
        };

        $scope.forRoles = function (roles) {
            for(var i = 0; i < roles.length; i++) {
                if ($scope.role == roles[i]) {
                    return true;
                }
            }
            return false;
        };
    }]);