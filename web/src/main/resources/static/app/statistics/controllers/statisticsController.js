angular
	.module('delivery')
    .controller('statisticsController', ['$scope', '$http', 
    		function ($scope, $http) {

    	$scope.dayData = [[]];
    	$scope.dayLabels = [];
    
        $scope.countOrdersByTime = (function() {
            $http.get('/data/day-orders').then(response => {
          	  $scope.dayData = response.data;
              $http.get('/data/hours').then(response2 => {
              	$scope.dayLabels = response2.data;
              });
            });
        }());
  	  
  	  	$scope.dayDatasetOverride = [{
  	  			fill: true
  	  	}];
  	  	
    	$scope.monthData = [[]];
    	$scope.monthLabels = [];
    
        $scope.countOrdersByDay = (function() {
            $http.get('/data/month-orders').then(response => {
          	  $scope.monthData = response.data;
              $http.get('/data/days').then(response2 => {
              	$scope.monthLabels = response2.data;
              });
            });
        }());
        
    	$scope.yearData = [[]];
    	$scope.yearLabels = [];
    
        $scope.countOrdersByMonth = (function() {
            $http.get('/data/year-orders').then(response => {
          	  $scope.yearData = response.data;
              $http.get('/data/month').then(response2 => {
              	$scope.yearLabels = response2.data;
              });
            });
        }());
    	  
    	  $scope.userData = [];

          $scope.countUsers = (function() {
              $http.get('/data/users-by-role').then(response => {
            	  $scope.userData = response.data;
              });
          }());

    	  $scope.userLabels = ["Customers", "Drivers", "Moderators", "Admins"];
    	  
    	  $scope.rateData = [];
    	  
          $scope.countRate = (function() {
              $http.get('/data/users-by-rate').then(response => {
            	  $scope.rateData = response.data;
              });
          }());

    	  $scope.rateLabels = ["1 star", "2 stars", "3 stars", "4 stars", "5 stars"];
    	  
          $scope.drivers = {
                  topFive: []
              };
          
          $scope.retrieveUsers = (function() {
              $http.get('/data/top-5-drivers').then(response => {
                  $scope.drivers.topFive = response.data;
              });
          }());

        }]);
