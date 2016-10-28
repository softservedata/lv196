angular
	.module('delivery')
    .controller('statisticsController', ['$scope', '$location', function ($scope, $location) {
    	$scope.imagePath = './img/user-photo.png';
    	
        $scope.redirect = function(){
            $location.url('/adminMaps');
        };
        
        }]);
        
//    .directive('admin-track', function () {
//            return {
//                templateUrl: 'app/tracking/view/adminMaps.html'
//            };
//        });