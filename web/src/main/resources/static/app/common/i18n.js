angular
.module('delivery')
	.run(['$rootScope', '$cookies', function($rootScope, $cookies) {
	  $rootScope.lang = $cookies.get('myLocaleCookie');
}]);

angular
.module('delivery')
	.controller('LanguageSwitchController', ['$scope','$rootScope', '$translate', '$cookies',
	  function($scope, $rootScope, $translate, $cookies) {
	    $scope.changeLanguage = function(langKey) {
	      $translate.use(langKey);
	    };

	    $rootScope.$on('$translateChangeSuccess', function(event, data) {
	      var language = data.language;

	      $rootScope.lang = language;
	      $cookies.put('myLocaleCookie', $rootScope.lang);

	    });
	}]);