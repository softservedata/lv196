angular
	.module('delivery')
    .controller('usersController', ['$scope', '$http', '$location', '$anchorScroll', function ($scope, $http, $location, $anchorScroll) {
        $scope.users = {
            allProfiles: []
        };

        $scope.totalItems;
        $scope.filterStatus = false;
        $scope.userInfo = false;

        $scope.filter = {
        	rows: 10,
        	currentPage: 1,
        	firstName: '',
        	lastName: '',
        	email: '',
        	blocked: '',
        	role: '',
        	sortType: 'lastName',
        	sortReverse: true
        };
        
        $scope.changefilterStatus = () => {
        	$scope.filterStatus = !$scope.filterStatus;
        	$scope.filter.blocked = $scope.filterStatus;
        }
        
        $scope.retrieveUsers = (filter) => {
            $http.post('/users/filter', filter).then(response1 => {
                $scope.users.allProfiles = response1.data;
                $http.get('/users/count-items').then(response2 => {
                	$scope.totalItems = response2.data;
                });
            });
        };
            
        $scope.retrieveUsers($scope.filter);
        
        $scope.$watch('filter', (newValue, oldValue) => {
        	if (newValue != oldValue) {
        		$scope.retrieveUsers(newValue);
        	}
        }, true);
        
        
        $scope.skip = () => {
        	$scope.filter.firstName = '';
        	$scope.filter.lastName = '';
        	$scope.filter.email = '';
        	$scope.filter.role = '';
        	$scope.filter.blocked = '';
        	$scope.retrieveUsers($scope.filter);
        }
        
        $scope.getUserInfo = (user) => {
        	var email = user.email;
            $http.get('/users/email?email=' + email).then(response => {
                $scope.userInfo = response.data;
            })
        };
        
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
        
        $scope.scrollTo = (id) => {
            $location.hash(id);
            $anchorScroll();
        };

        }]);