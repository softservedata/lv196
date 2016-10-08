angular
    .module('delivery')
    .controller('feedbacksController', ['$scope', '$http', '$window', function ($scope, $http, $window) {
        $http.get("/feedbacks?all")
            .then(function (response) {
                $scope.feedbacks = response.data;
            });

        $scope.btnApprovedStyle = function (approved) {
            if (approved) {
                return "btn btn-success";
            } else {
                return "btn btn-danger";
            }
        };

        $scope.changeFeedbackStatus = function (feedbackDTO) {
            feedbackDTO.approved = !feedbackDTO.approved;
            $http.put("/feedbacks/changeFeedbackStatus", feedbackDTO)
                .then(function (response) {
                    $state.reload();
                })
        };

        $scope.user;
        $scope.showUser = function (userEmail) {
            $window.alert(userEmail);
            $scope.user = userEmail;
        }
    }]);