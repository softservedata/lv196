'use strict';

angular
    .module('delivery')
    .controller('deleteFeedbackController', ['$scope', '$http', '$uibModalInstance', 'feedbackId',
        'shareFeedbackDataService', 'Notification',
        function ($scope, $http, $uibModalInstance, feedbackId, shareFeedbackDataService, Notification) {

            $scope.confirmDeleteFeedback = function () {
                $uibModalInstance.close();
                $http.delete("/feedbacks/deleteFeedback/" + feedbackId)
                    .then(function (response) {
                            if (response.status == 200) {
                                Notification.success('The feedbacks was succesfully deleted');
                                shareFeedbackDataService.itemDeleted();
                            }
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.cancelDeleteFeedback = function () {
                $uibModalInstance.dismiss('cancel');
            };

        }]);