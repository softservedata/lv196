angular
	.module('delivery')
    .controller('userController', ['$scope', '$http', function ($scope, $http) {
        $scope.users = {
            allProfiles: []
        };
        $scope.retrieveUsers = () => {
            $http.get('/users/all').then(response => {
                $scope.users.allProfiles = response.data;
            })
        };
        $scope.retrieveUsers();
        
        

        }]);