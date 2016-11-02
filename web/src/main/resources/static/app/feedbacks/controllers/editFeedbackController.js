'use strict';

angular
    .module('delivery')
    .controller('editFeedbackController', ['$scope', '$http', '$uibModalInstance', 'feedbackDTO', 'Notification',
        function ($scope, $http, $uibModalInstance, feedbackDTO, Notification) {

            $scope.feedbackText = feedbackDTO.text;

            $scope.updateFeedbackText = function () {
                $uibModalInstance.close();
                feedbackDTO.text = $scope.feedbackText;
                $http.put("/feedbacks/updateFeedback", feedbackDTO)
                    .then(function (response) {
                            if (response.status == 200) {
                                Notification.success('The feedbacks was successfully updated');
                            }
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.cancelEditFeedbackText = function () {
                $uibModalInstance.dismiss('cancel');
            };

        }]);