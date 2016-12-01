'use strict';

angular
    .module('delivery')
    .controller('currentUserController', ['$scope', '$http', 'shareUserService',
        function ($scope, $http, shareUserService) {

            $scope.loggedUser;

            $scope.getLoggedUser = function () {

                $http.get("/userProfile/loggedUser")
                    .then(function (response) {
                        $scope.loggedUser = response.data;
                        shareUserService.setLoggedUser($scope.loggedUser);
                    });
            };

            $scope.$on('user data changed for main view', function () {
                $scope.loggedUser = shareUserService.getLoggedUser();
            });

            $scope.getLoggedUser();

        }]);