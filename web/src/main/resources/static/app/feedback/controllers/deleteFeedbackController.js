'use strict';

angular
    .module('delivery')
    .controller('deleteFeedbackController', ['$scope', '$http', '$uibModalInstance', 'feedbackId',
        'shareFeedbackDataService', 'Notification', '$filter',
        function ($scope, $http, $uibModalInstance, feedbackId, shareFeedbackDataService, Notification, $filter) {

            $scope.confirmDeleteFeedback = function () {
                $uibModalInstance.close();
                $http.delete("/feedback/deleteFeedback/" + feedbackId)
                    .then(function (response) {
                            if (response.status == 200) {
                                Notification.success($filter('translate')('delete_feedback_success'));
                                shareFeedbackDataService.itemDeleted();
                            }
                        },
                        function (response) {
                            Notification.error({message: $filter('translate')('delete_feedback_error'), title: "Error!"});
                        });
            };

            $scope.cancelDeleteFeedback = function () {
                $uibModalInstance.dismiss('cancel');
            };

        }]);