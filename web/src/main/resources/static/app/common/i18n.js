angular
.module('delivery')
	.run(['$rootScope', '$cookies', '$http', '$translate', function($rootScope, $cookies, $http, $translate) {
	  $rootScope.lang = $cookies.get('myLocaleCookie');
	  $translate.use($rootScope.lang);
	  $http.put('/notification', $rootScope.lang);
}]);

angular
.module('delivery')
	.controller('LanguageSwitchController', ['$scope','$rootScope', '$translate', '$cookies', '$http',
	  function($scope, $rootScope, $translate, $cookies, $http) {
	    $scope.changeLanguage = function(langKey) {
	      $translate.use(langKey);
	    };

	    $rootScope.$on('$translateChangeSuccess', function(event, data) {
	      var language = data.language;

	      $rootScope.lang = language;
	      $cookies.put('myLocaleCookie', $rootScope.lang);

		  $http.put('/notification', $rootScope.lang);
	    });
	}]);