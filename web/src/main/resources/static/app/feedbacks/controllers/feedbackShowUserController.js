'use strict';

angular
    .module('delivery')
    .controller('feedbackShowUserController', ['$scope', '$uibModalInstance', 'userDTO',
        function ($scope, $uibModalInstance, userDTO) {

            var rateFactor = 10;

            $scope.userDTO = userDTO;
            $scope.userDTO.rate = $scope.userDTO.rate / rateFactor;

            $scope.closeFeedbackShowUser = function () {
                $uibModalInstance.close();
            };

        }]);