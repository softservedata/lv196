angular
    .module('delivery')
    .controller('feedbacksController', ['$scope', '$http', '$state', function ($scope, $http, $state) {

        $scope.feedbacks = [];
        $scope.feedbackFilter = [];

        getAllFeedbacks();

        $scope.btnApprovedStyle = function (approved) {
            if (approved) {
                return "btn btn-success";
            } else {
                return "btn btn-danger";
            }
            ;
        };

        function getAllFeedbacks() {
            var allFiltersEmpty = true;
            for (var i = 0; i < $scope.feedbackFilter.length; i++) {
                if ($scope.feedbackFilter[i] != null && $scope.feedbackFilter[i] != '') {
                    allFiltersEmpty = false;
                    break;
                }
                ;
            }
            ;
            if (allFiltersEmpty) {
                $http.get("/feedbacks/all")
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            }
            ;
        };

        $scope.getFeedbackById = function () {
            if ($scope.feedbackFilter[0].length > 0) {
                if ($scope.feedbackFilter[0].indexOf("+") !== -1 && $scope.feedbackFilter[0].length > 1) {

                    $http.get("/feedbacks/id/greater-than?id=" + $scope.feedbackFilter[0])
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                } else if ($scope.feedbackFilter[0].indexOf("-") !== -1 && $scope.feedbackFilter[0].length > 1) {

                    $http.get("/feedbacks/id/less-than?id=" + $scope.feedbackFilter[0])
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                } else {

                    $http.get("/feedbacks/id?id=" + $scope.feedbackFilter[0])
                        .then(function (response) {
                            $scope.feedbacks.length = 0;
                            $scope.feedbacks.push(response.data);
                        });
                }
                ;
            } else {
                getAllFeedbacks();
            }
            ;
        };

        $scope.getFeedbacksByText = function () {
            if ($scope.feedbackFilter[1].length > 3) {
                $http.get("/feedbacks/text?text=" + $scope.feedbackFilter[1])
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            } else if ($scope.feedbackFilter[1].length == 0) {
                getAllFeedbacks();
            }
            ;
        };

        $scope.getFeedbacksByRate = function () {
            if ($scope.feedbackFilter[2].length > 0) {
                if ($scope.feedbackFilter[2].indexOf("+") !== -1 && $scope.feedbackFilter[2].length > 1) {

                    $http.get("/feedbacks/rate/greater-than?rate=" + $scope.feedbackFilter[2])
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                } else if ($scope.feedbackFilter[2].indexOf("-") !== -1 && $scope.feedbackFilter[2].length > 1) {

                    $http.get("/feedbacks/rate/less-than?rate=" + $scope.feedbackFilter[2])
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                } else {

                    $http.get("/feedbacks/rate?rate=" + $scope.feedbackFilter[2])
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                }
                ;
            } else {
                getAllFeedbacks();
            }
            ;
        };

        $scope.getFeedbacksByUserName = function () {
            if ($scope.feedbackFilter[3].length > 3) {
                $http.get("/feedbacks/userName?userName=" + $scope.feedbackFilter[3])
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            } else if ($scope.feedbackFilter[3].length == 0) {
                getAllFeedbacks();
            }
            ;
        };

        $scope.getFeedbacksByTransporterName = function () {
            if ($scope.feedbackFilter[4].length > 3) {
                $http.get("/feedbacks/transporterName?transporterName=" + $scope.feedbackFilter[4])
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            } else if ($scope.feedbackFilter[4].length == 0) {
                getAllFeedbacks();
            }
            ;
        };

        $scope.getFeedbacksByDate = function () {
            if ($scope.feedbackFilter[5] == null) {
                getAllFeedbacks();
            } else {
                $http.get("/feedbacks/feedbackDate?feedbackDate=" + Date.parse($scope.feedbackFilter[5]))
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            }
            ;
        };

        $scope.getFeedbacksByApproved = function () {
            if ($scope.feedbackFilter[6].length > 2) {
                var approved;
                if ("true".includes($scope.feedbackFilter[6])) {
                    approved = "true";
                } else if ("false".includes($scope.feedbackFilter[6])) {
                    approved = "false";
                }
                if (approved !== undefined) {
                    $http.get("/feedbacks/approved?approved=" + approved)
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                }
                ;
            } else if ($scope.feedbackFilter[6].length == 0) {
                getAllFeedbacks();
            }
            ;
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