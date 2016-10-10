angular
    .module('delivery')
    .controller('feedbacksController', ['$scope', '$http', '$state', function ($scope, $http, $state) {

        $scope.feedbacks = [];
        $scope.feedbackFilter = [];
        $scope.feedbackSortIcons = [];
        $scope.sortFeedbacksOrderDesc = [true, true, true, true, true, true, true, true];

        var lastUsedFilterIndex;
        var columnPos = 0;

        function init(){
            for (var i = 0; i < $scope.sortFeedbacksOrderDesc.length; i++){
                $scope.sortFeedbacksOrderDesc[i] = true;
            }

            for (var i = 0; i < $scope.feedbackSortIcons.length; i++){
                $scope.feedbackSortIcons[i] = "fa fa-sort-desc";
            }
        }

        function getAllFeedbacks(sortOrderDesc) {
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
                columnPos = 0;
                lastUsedFilterIndex = 0;
                $http.get("/feedbacks/all?sortDesc=" + sortOrderDesc)
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
            }
            ;
        };

        init();

        getAllFeedbacks($scope.sortFeedbacksOrderDesc[0]);

        $scope.feedbackSorting = function (columnIndex) {
            $scope.sortFeedbacksOrderDesc[columnIndex] = !$scope.sortFeedbacksOrderDesc[columnIndex];
            if ($scope.sortFeedbacksOrderDesc[columnIndex]){
                $scope.feedbackSortIcons[columnIndex] = "fa fa-sort-desc";
            } else {
                $scope.feedbackSortIcons[columnIndex] = "fa fa-sort-asc";
            }
            switch(columnIndex){
                case 1:
                    $scope.getFeedbackById($scope.sortFeedbacksOrderDesc[columnIndex]);
                    break;
            }
        }

        $scope.getFeedbackById = function (sortOrderDesc) {
            columnPos = 1;
            if ($scope.feedbackFilter[columnPos] != null && $scope.feedbackFilter[columnPos].length > 0) {
                if ($scope.feedbackFilter[columnPos].indexOf("+") !== -1 && $scope.feedbackFilter[columnPos].length > 1) {

                    $http.get("/feedbacks/id/greater-than?id=" + $scope.feedbackFilter[columnPos] + "&sortDesc=" + sortOrderDesc)
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                } else if ($scope.feedbackFilter[columnPos].indexOf("-") !== -1 && $scope.feedbackFilter[columnPos].length > 1) {

                    $http.get("/feedbacks/id/less-than?id=" + $scope.feedbackFilter[columnPos] + "&sortDesc=" +sortOrderDesc)
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                } else {

                    $http.get("/feedbacks/id?id=" + $scope.feedbackFilter[columnPos])
                        .then(function (response) {
                            $scope.feedbacks.length = 0;
                            $scope.feedbacks.push(response.data);
                        });
                }
                ;
                lastUsedFilterIndex = 1;
            } else {
                getAllFeedbacks(sortOrderDesc);
            }
            ;
        };

        $scope.getFeedbacksByText = function () {
            columnPos = 2;
            if ($scope.feedbackFilter[columnPos].length > 3) {
                $http.get("/feedbacks/text?text=" + $scope.feedbackFilter[columnPos])
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
                lastUsedFilterIndex = 2;
            } else if ($scope.feedbackFilter[columnPos].length == 0) {
                getAllFeedbacks();
            }
            ;
        };

        $scope.getFeedbacksByRate = function () {
            columnPos = 3;
            if ($scope.feedbackFilter[columnPos].length > 0) {
                if ($scope.feedbackFilter[columnPos].indexOf("+") !== -1 && $scope.feedbackFilter[columnPos].length > 1) {

                    $http.get("/feedbacks/rate/greater-than?rate=" + $scope.feedbackFilter[columnPos])
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                } else if ($scope.feedbackFilter[columnPos].indexOf("-") !== -1 && $scope.feedbackFilter[columnPos].length > 1) {

                    $http.get("/feedbacks/rate/less-than?rate=" + $scope.feedbackFilter[columnPos])
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                } else {

                    $http.get("/feedbacks/rate?rate=" + $scope.feedbackFilter[columnPos])
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                }
                ;
                lastUsedFilterIndex = 3;
            } else {
                getAllFeedbacks();
            }
            ;
        };

        $scope.getFeedbacksByUserName = function () {
            columnPos = 4;
            if ($scope.feedbackFilter[columnPos].length > 3) {
                $http.get("/feedbacks/userName?userName=" + $scope.feedbackFilter[columnPos])
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
                lastUsedFilterIndex = 4;
            } else if ($scope.feedbackFilter[columnPos].length == 0) {
                getAllFeedbacks();
            }
            ;
        };

        $scope.getFeedbacksByTransporterName = function () {
            columnPos = 5;
            if ($scope.feedbackFilter[columnPos].length > 3) {
                $http.get("/feedbacks/transporterName?transporterName=" + $scope.feedbackFilter[columnPos])
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
                lastUsedFilterIndex = 5;
            } else if ($scope.feedbackFilter[columnPos].length == 0) {
                getAllFeedbacks();
            }
            ;
        };

        $scope.getFeedbacksByDate = function () {
            columnPos = 6;
            if ($scope.feedbackFilter[columnPos] == null) {
                getAllFeedbacks();
            } else {
                $http.get("/feedbacks/feedbackDate?feedbackDate=" + Date.parse($scope.feedbackFilter[columnPos]))
                    .then(function (response) {
                        $scope.feedbacks = response.data;
                    });
                lastUsedFilterIndex = 6;
            }
            ;
        };

        $scope.getFeedbacksByApproved = function () {
            columnPos = 7;
            if ($scope.feedbackFilter[columnPos].length > 2) {
                var approved;
                if ("true".includes($scope.feedbackFilter[columnPos])) {
                    approved = "true";
                } else if ("false".includes($scope.feedbackFilter[columnPos])) {
                    approved = "false";
                }
                if (approved !== undefined) {
                    $http.get("/feedbacks/approved?approved=" + approved)
                        .then(function (response) {
                            $scope.feedbacks = response.data;
                        });
                }
                ;
                lastUsedFilterIndex = 7;
            } else if ($scope.feedbackFilter[columnPos].length == 0) {
                getAllFeedbacks();
            }
            ;
        };

        $scope.changeFeedbackStatus = function (feedbackDTO) {
            feedbackDTO.approved = !feedbackDTO.approved;
            $http.put("/feedbacks/changeFeedbackStatus", feedbackDTO)
                .then(function (response) {
                    //$state.reload();
                });
        };

        $scope.btnApprovedStyle = function (approved) {
            if (approved) {
                return "btn btn-success";
            } else {
                return "btn btn-danger";
            }
            ;
        };

    }])
    .directive('searchForm', function () {
        return {
            templateUrl: '/app/views/feedbacks/searchFeedbackTemplate.html'
        };
    });