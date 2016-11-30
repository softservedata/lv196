'use strict';

angular
    .module('delivery')
    .controller('feedbackShowUserController', ['$scope', '$uibModalInstance', 'userDTO',
        function ($scope, $uibModalInstance, userDTO) {

            $scope.userDTO = userDTO;
            $scope.userDTO.rate = $scope.userDTO.rate;

            $scope.closeFeedbackShowUser = function () {
                $uibModalInstance.close();
            };

        }]);