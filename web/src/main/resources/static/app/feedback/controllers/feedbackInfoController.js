'use strict';

angular
    .module('delivery')
    .controller('feedbackInfoController', ['$scope', '$uibModalInstance', 'text',
        function ($scope, $uibModalInstance, text) {

            $scope.infoText = text;

            $scope.closeInfoFeedback = function () {
                $uibModalInstance.close();
            };

        }]);