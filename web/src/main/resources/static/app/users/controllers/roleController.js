angular
    .module('delivery')
    .controller('roleController', ['$scope', '$http', 'shareUserService', '$rootScope',
        function ($scope, $http, shareUserService, $rootScope) {

        $scope.role = '';

        $scope.getRole = function () {
            if ($scope.role == '') {
                $http.get("/role").then(function(response) {
                    $scope.role = response.data.response;
                });
            }
        };

        $scope.forRole = function (role) {
            return $scope.role == role;
        };

        $scope.forRoles = function (roles) {
            for(var i = 0; i < roles.length; i++) {
                if ($scope.role == roles[i]) {
                    return true;
                }
            }
            return false;
        };

        $scope.$on('user data changed for main view', function () {
            $scope.role = shareUserService.getLoggedUser().role;
        });


        $scope.showMsgNotify = false;
        $rootScope.refreshMessageNotifications = () => {
            $http.get("/chat/count").then(resp => {
                $scope.showMsgNotify = resp.data > 0;
                if(!$scope.$$phase) {
                    $scope.$apply();
                }
            })
        };
        $rootScope.refreshMessageNotifications();
    }]);