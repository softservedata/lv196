angular
	.module('delivery')
    .controller('statisticsController', ['$scope', '$http',  
    		function ($scope, $http) {
    	
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

	  $scope.dayDatasetOverride = [{
	  			fill: true
	  }];
        
        $scope.dayLabels = [];
        $scope.dateOfAnalyse = new Date();
    	$scope.dayData = [[]];
  	    $scope.dayLabels = [];
        $scope.dayMap = [[]];
        
        $scope.minDate = new Date(
                $scope.dateOfAnalyse.getFullYear(),
                $scope.dateOfAnalyse.getMonth() - 2,
                $scope.dateOfAnalyse.getDate());

        $scope.maxDate = new Date(
                $scope.dateOfAnalyse.getFullYear(),
                $scope.dateOfAnalyse.getMonth() + 2,
                $scope.dateOfAnalyse.getDate());

        $scope.retrieveDayData = (dateOfAnalyse) => {
            $http.get('/data/day-orders?date=' + Date.parse(dateOfAnalyse)).then(response => {
                $scope.dayMap = response.data;
                $scope.dayLabels = getColumn($scope.dayMap, 0);
                $scope.dayData[0] = getColumn($scope.dayMap, 1);
              });
            };
        
        $scope.retrieveDayData($scope.dateOfAnalyse);
        
        $scope.$watch('dateOfAnalyse', (newValue, oldValue) => {
        	if (newValue != oldValue) {
        		$scope.retrieveDayData(newValue);
        	}
        }, true);
        
    	$scope.monthData = [[]];
  	    $scope.monthLabels = [];
        $scope.monthMap = [[]];
        $scope.monthOfAnalyse;

        $scope.retrieveMonthlyData = (month) => {
          $http.get('/data/month-orders' + month).then(response => {
              $scope.monthMap = response.data;
              $scope.monthLabels = getColumn($scope.monthMap, 0);
              $scope.monthData[0] = getColumn($scope.monthMap, 1);              
            });
         };
          
        $scope.retrieveMonthlyData($scope.monthOfAnalyse);
        
        $scope.$watch('monthOfAnalyse', (newValue, oldValue) => {
        	if (newValue != oldValue) {
        		$scope.retrieveMonthlyData(newValue);
        	}
        }, true);
      
  	    $scope.yearData = [[]];
  	    $scope.yearLabels = [];
  	    $scope.yearMap = [[]];
  
        $scope.retrieveYearlyData = (function() {
            $http.get('/data/year-orders').then(response => {
                $scope.yearMap = response.data;
                $scope.yearLabels = getColumn($scope.yearMap, 0);
                $scope.yearData[0] = getColumn($scope.yearMap, 1);
              });
        }()); 
        
       function getColumn(matrix, col){
            var column = [];
            for(var i=0; i<matrix.length; i++){
               column.push(matrix[i][col]);
            }
            return column;
         }

        }]);
