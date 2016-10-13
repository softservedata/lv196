angular
    .module('delivery')
    .controller('feedbacksController', ['$scope', '$http', '$state', function ($scope, $http, $state) {

        $scope.feedbacks = [];
        $scope.feedbackFilter = [];
        $scope.feedbackSortIcons = [];
        $scope.sortFeedbacksOrderDesc = [];
        var toggleFilterApprovedPos = 0;

        var columnNumber = 7; // including pos 0 for getAllFeedbacks()
        var lastUsedFilterIndex;
        var columnPos = 0;
        var firstTimeRun = true;
        var toggleFilterApprovedPos = 0;

        var init = function () {
            for (var i = 0; i < columnNumber; i++) {
                $scope.sortFeedbacksOrderDesc.push(false);
                if (i == 5) {
                    $scope.feedbackSortIcons.push("fa fa-sort-asc");
                } else {
                    $scope.feedbackSortIcons.push("fa fa-sort");
                }
            }
        };

        var resetSortIcons = function () {
            for (var i = 0; i < columnNumber; i++) {
                $scope.feedbackSortIcons[i] = ("fa fa-sort");
            }
        };

        $scope.changeFilterApprovedClass = function () {
            if (++toggleFilterApprovedPos > 2) {
                toggleFilterApprovedPos = 0;
            }
            $scope.toggleFilterApprovedClass();
            $scope.toggleFilterApprovedStyle();
            $scope.filterFeedbacks();
        };

        $scope.toggleFilterApprovedClass = function () {
            switch (toggleFilterApprovedPos) {
                case 2:
                    return "fa fa-toggle-on fa-2x";
                default:
                    return "fa fa-toggle-off fa-2x";
            }
        };

        $scope.toggleFilterApprovedStyle = function () {
            switch (toggleFilterApprovedPos) {
                case 1:
                    $scope.feedbackFilter[6] = "false";
                    return "color:red";
                case 2:
                    $scope.feedbackFilter[6] = "true";
                    return "color:green";
                default:
                    $scope.feedbackFilter[6] = "undefined";
                    return "color:lightgray";
            }
        };

        var sortBy = function (columnPos) {
            switch (columnPos) {
                case 1:
                    return "text";
                case 2:
                    return "rate";
                case 3:
                    return "userName";
                case 4:
                    return "transporterName";
                case 6:
                    return "approved";
                default:
                    return "createdOn";
            }
        };

        var filtersEmpty = function () {
            for (var i = 0; i < $scope.feedbackFilter.length; i++) {
                if ($scope.feedbackFilter[i] != null && $scope.feedbackFilter[i] != '') {
                    return false;
                }
            }
            return true;
        };

        var replacePlus = function (text) {
            if (text != null && text != '' && text.includes("+")) {
                return text.split("+").join("%2B");
            }
            return text;
        };
        
        $scope.checkInputLength = function (columnIndex) {
            if ($scope.feedbackFilter[columnIndex].length > 2 || $scope.feedbackFilter[columnIndex].length == 0){
                $scope.filterFeedbacks();
            }
        };

        $scope.feedbackSorting = function (columnIndex) {
            columnPos = columnIndex;
            resetSortIcons();
            $scope.sortFeedbacksOrderDesc[columnIndex] = !$scope.sortFeedbacksOrderDesc[columnIndex];
            if ($scope.sortFeedbacksOrderDesc[columnIndex]) {
                $scope.feedbackSortIcons[columnIndex] = "fa fa-sort-desc";
            } else {
                $scope.feedbackSortIcons[columnIndex] = "fa fa-sort-asc";
            }
            $scope.filterFeedbacks();
        };

        $scope.filterFeedbacks = function () {
            var requestText = "/feedbacks/all?text=" + replacePlus($scope.feedbackFilter[1]) + "&rate=" + $scope.feedbackFilter[2] +
                "&userName=" + $scope.feedbackFilter[3] + "&transporterName=" + $scope.feedbackFilter[4] +
                "&createdOn=" + Date.parse($scope.feedbackFilter[5]) + "&approved=" + $scope.feedbackFilter[6] +
                "&sortBy=" + sortBy(columnPos) + "&sortDesc=" + $scope.sortFeedbacksOrderDesc[columnPos];

            $http.get(requestText)
                .then(function (response) {
                    $scope.feedbacks = response.data;
                });
        };

        if (filtersEmpty()) {
            $scope.filterFeedbacks();
        }

        $scope.changeFeedbackStatus = function (feedbackDTO) {
            feedbackDTO.approved = !feedbackDTO.approved;
            $http.put("/feedbacks/changeFeedbackStatus", feedbackDTO)
                .then(function (response) {

                });
        };

        $scope.toggleApproved = function (approved) {
            if (approved) {
                return "fa fa-toggle-on fa-2x";
            }
            return "fa fa-toggle-off fa-2x";
        };

        $scope.toggleStyle = function (approved) {
            if (approved) {
                return "color:green";
            }
            return "color:red";
        };

        init();

    }])
    .directive('searchForm', function () {
        return {
            templateUrl: '/app/views/feedbacks/searchFeedbackTemplate.html'
        };
    })
    .directive('pagination', function () {
        return {
            templateUrl: '/app/views/feedbacks/feedbacksPagination.html'
        };
    });