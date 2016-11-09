'use strict';

angular
    .module('delivery')
    .controller('editFeedbackController', ['$scope', '$http', '$uibModalInstance', 'feedbackDTO', 'Notification', '$filter',
        function ($scope, $http, $uibModalInstance, feedbackDTO, Notification, $filter) {

            $scope.feedbackText = feedbackDTO.text;

            $scope.updateFeedbackText = function () {
                $uibModalInstance.close();
                feedbackDTO.text = $scope.feedbackText;
                $http.put("/feedbacks/updateFeedback", feedbackDTO)
                    .then(function (response) {
                            if (response.status == 200) {
                                Notification.success($filter('translate')('edit_feedback_success'));
                            }
                        },
                        function (response) {
                            Notification.error({message: $filter('translate')('edit_feedback_error'), title: "Error!"});
                        });
            };

            $scope.cancelEditFeedbackText = function () {
                $uibModalInstance.dismiss('cancel');
            };

        }]);