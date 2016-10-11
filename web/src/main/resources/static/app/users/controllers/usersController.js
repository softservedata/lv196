angular
	.module('delivery')
    .controller('usersController', ['$scope', '$http', function ($scope, $http) {
        $scope.users = {
            allProfiles: []
        };
        $scope.retrieveUsers = () => {
            $http.get('/users/all').then(response => {
                $scope.users.allProfiles = response.data;
            })
        };
        $scope.retrieveUsers();
        
        $scope.getUserInfo = (user) => {
        	var email = user.email;
            $http.get('/users/email?email=' + email).then(response => {
                $scope.userInfo = response.data;
            })
        };
        
        $scope.userInfo = false;
        
        $scope.hideInfo = () => {
        	 $scope.userInfo = false;
        }
        
        $scope.changeUserStatus = (user, index) => {
        	var email = user.email;
        	var status = user.blocked;
            $http.put('/users/change-status?email=' + email + '&status=' + status).then(response => {
            	if(response.status == 200){
            		$scope.users.allProfiles[index].blocked = response.data.blocked;   
            	}
            }).catch(error => console.log())
        };

        }]);