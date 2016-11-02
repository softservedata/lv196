'use strict';

angular
    .module('delivery')
    .controller('currentUserController', ['$scope', '$http', 'shareUserProfileDTOService',
        function ($scope, $http, shareUserProfileDTOService) {

            $scope.loggedUser;

            $scope.getLoggedUser = function () {

                $http.get("/userProfile/loggedUser")
                    .then(function (response) {
                        $scope.loggedUser = response.data;
                        shareUserProfileDTOService.setLoggedUser($scope.loggedUser);
                    });
            };

            $scope.$on('user data changed for main view', function () {
                $scope.getLoggedUser();
            });

            $scope.$on('user data changed', function () {
                $scope.getLoggedUser();
            });

            $scope.getLoggedUser();

        }]);