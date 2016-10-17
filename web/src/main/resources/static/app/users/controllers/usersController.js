angular
	.module('delivery')
    .controller('usersController', ['$scope', '$http', function ($scope, $http) {
        $scope.users = {
            allProfiles: []
        };
        
        $scope.currentPage = 1;
        $scope.rows = 10;
        $scope.pages;
        $scope.filterStatus = false;
        $scope.status = '';
        $scope.userInfo = false;
        $scope.fname = ''; 
        $scope.lname = ''; 
        $scope.email = ''; 
        $scope.role = '';
        
        $scope.changefilterStatus = () => {
        	$scope.filterStatus = !$scope.filterStatus;
        	$scope.status = $scope.filterStatus;
        }
        
        $scope.retrieveUsers = (rows, currentPage, fname, lname, email, status, role) => {
            $http.get('/users/filter?rows=' + rows + '&page=' + currentPage + '&fname=' + fname + '&lname=' + lname +
            		'&email=' + email  +'&status=' + status + '&role=' + role).then(response1 => {
                $scope.users.allProfiles = response1.data;
                $http.get('/users/count-pages').then(response2 => {
                	$scope.pages = response2.data;
                });
            });
        };
                   
        $scope.retrieveUsers($scope.rows, $scope.currentPage, $scope.fname, $scope.lname, $scope.email, $scope.status, $scope.role);
 
        $scope.$watchGroup(['rows', 'currentPage', 'fname', 'lname', 'email', 'status', 'role'], (newValue, oldValue) => {
        	if (newValue != oldValue) {
        		$scope.retrieveUsers(newValue[0], newValue[1], newValue[2], newValue[3],
        							  newValue[4], newValue[5], newValue[6]);
        	  } 
        	}
        )
        
        $scope.skip = () => {
        	$scope.fname = '';
        	$scope.lname = '';
        	$scope.email = '';
        	$scope.role = '';
        	$scope.retrieveUsers($scope.rows, $scope.currentPage, $scope.fname, $scope.lname, $scope.email, $scope.role, $scope.status);
        }
        
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