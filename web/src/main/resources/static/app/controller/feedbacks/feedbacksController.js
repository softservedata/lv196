angular
    .module('delivery')
    .controller('feedbacksController', ['$scope', '$http', '$state', function ($scope, $http, $state) {
        // $http.get("/feedbacks/all")
        //     .then(function (response) {
        //         $scope.feedbacks = response.data;
        //     });

        $scope.feedbacks = [];

        $scope.btnApprovedStyle = function (approved) {
            if (approved) {
                return "btn btn-success";
            } else {
                return "btn btn-danger";
            }
            ;
        };

        $scope.getAllFeedbacks = function () {
            $http.get("/feedbacks/all")
                .then(function (response) {
                    $scope.feedbacks = response.data;
                });
        };

        $scope.getFeedbackById = function () {
            if ($scope.feedbackId.indexOf("+") !== -1 && $scope.feedbackId.length > 1) {

                $http.get("/feedbacks/id/greater-than?id=" + $scope.feedbackId)
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            } else if ($scope.feedbackId.indexOf("-")  !== -1  && $scope.feedbackId.length > 1) {

                $http.get("/feedbacks/id/less-than?id=" + $scope.feedbackId)
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            } else {

                $http.get("/feedbacks/id?id=" + $scope.feedbackId)
                    .then(function (response) {
                        $scope.feedbacks.length = 0;
                        $scope.feedbacks.push(response.data);
                    });
            };
        };

        $scope.getFeedbacksByText = function () {
            if ($scope.feedbackText.length > 3) {
                $http.get("/feedbacks/text?text=" + $scope.feedbackText)
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            };
        };

        $scope.getFeedbacksByRate = function () {
            if ($scope.feedbackRate.indexOf("+") !== -1 && $scope.feedbackRate.length > 1) {

                $http.get("/feedbacks/rate/greater-than?rate=" + $scope.feedbackRate)
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            } else if ($scope.feedbackRate.indexOf("-")  !== -1  && $scope.feedbackRate.length > 1) {

                $http.get("/feedbacks/rate/less-than?rate=" + $scope.feedbackRate)
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            } else {

                $http.get("/feedbacks/rate?rate=" + $scope.feedbackRate)
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            };
        };

        $scope.getFeedbacksByUserName = function () {
            if ($scope.feedbackUser.length > 3) {
                $http.get("/feedbacks/userName?userName=" + $scope.feedbackUser)
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            };
        };

        $scope.getFeedbacksByTransporterName = function () {
            if ($scope.feedbackTransporter.length > 3) {
                $http.get("/feedbacks/transporterName?transporterName=" + $scope.feedbackTransporter)
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            };
        };

        $scope.changeFeedbackStatus = function (feedbackDTO) {
            feedbackDTO.approved = !feedbackDTO.approved;
            $http.put("/feedbacks/changeFeedbackStatus", feedbackDTO)
                .then(function (response) {
                    $state.reload();
                });
        };

    }])
    .directive('searchForm', function () {
        return {
            templateUrl: '/app/views/feedbacks/searchFeedbackTemplate.html'
        };
    });