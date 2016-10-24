angular
.module('delivery')
	.run(['$rootScope', function($rootScope) {
	  $rootScope.lang = 'en';
}]);

angular
.module('delivery')
	.controller('LanguageSwitchController', ['$scope','$rootScope', '$translate',
	  function($scope, $rootScope, $translate) {
	    $scope.changeLanguage = function(langKey) {
	      $translate.use(langKey);
	    };

	    $rootScope.$on('$translateChangeSuccess', function(event, data) {
	      var language = data.language;

	      $rootScope.lang = language;

	    });
	}]);

