angular
    .module('delivery')
    .controller('feedbacksController', ['$scope', '$http', 'shareFeedbackDataService', '$uibModal', '$location', '$anchorScroll',
        function ($scope, $http, shareFeedbackDataService, $uibModal, $location, $anchorScroll) {

            $scope.feedbacks = [];
            $scope.feedbackFilter = [];
            $scope.feedbackSortIcons = [];
            $scope.sortFeedbacksOrderDesc = [];
            $scope.currentPage = 1;
            $scope.itemsPerPage = 5;
            $scope.totalItems = 0;

            var columnNumber = 7; // including pos 0 for getAllFeedbacks()
            var columnPos = 0;
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
                if ($scope.feedbacks.length == 0) {
                    $scope.filterFeedbacks();
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
                        $scope.feedbackFilter[6] = "";
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

            var replacePlus = function (text) {
                if (text != null && text != '' && text.includes("+")) {
                    return text.split("+").join("%2B");
                }
                return text;
            };

            $scope.checkInputLength = function (columnIndex) {
                if ($scope.feedbackFilter[columnIndex].length > 2 || $scope.feedbackFilter[columnIndex].length == 0) {
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

            $scope.setItemsPerPage = function () {
                $scope.currentPage = 1;
                $scope.filterFeedbacks();
            };

            $scope.filterFeedbacks = function () {
                var requestText = "/feedbacks/all?text=" + replacePlus($scope.feedbackFilter[1]) + "&rate=" + $scope.feedbackFilter[2] +
                    "&userName=" + $scope.feedbackFilter[3] + "&transporterName=" + $scope.feedbackFilter[4] +
                    "&createdOn=" + Date.parse($scope.feedbackFilter[5]) + "&approved=" + $scope.feedbackFilter[6] +
                    "&sortBy=" + sortBy(columnPos) + "&sortDesc=" + $scope.sortFeedbacksOrderDesc[columnPos] +
                    "&currentPage=" + $scope.currentPage + "&itemsPerPage=" + $scope.itemsPerPage;

                $http.post(requestText)
                    .then(function (response0) {
                        $scope.feedbacks = response0.data;
                        $http.get("/feedbacks/totalItems")
                            .then(function (response1) {
                                $scope.totalItems = response1.data;
                            });
                    });
            };

            $scope.changeFeedbackStatus = function (feedbackDTO) {
                feedbackDTO.approved = !feedbackDTO.approved;
                $http.put("/feedbacks/updateFeedback", feedbackDTO);
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

            $scope.editFeedbackText = function (feedbackDTO) {
                var modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    controller: 'editFeedbackController',
                    templateUrl: '/app/views/feedbacks/editFeedbackText.html',
                    resolve: {
                        feedbackDTO: function () {
                            return feedbackDTO;
                        }
                    }
                });
            };

            $scope.deleteFeedback = function (feedbackId) {
                var modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    controller: 'deleteFeedbackController',
                    templateUrl: '/app/views/feedbacks/deleteFeedback.html',
                    resolve: {
                        feedbackId: function () {
                            return feedbackId;
                        }
                    }
                });
            };

            var showInfo = function (text) {
                var modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    controller: 'feedbackInfoController',
                    templateUrl: '/app/views/feedbacks/infoFeedbacks.html',
                    resolve: {
                        text: function () {
                            return text;
                        }
                    }
                });
            };

            $scope.scroll = function (dir) {
                $location.hash(dir);
                $anchorScroll();
            };

            $scope.pageChanged = function () {
                $scope.scroll('top');
                $scope.filterFeedbacks();
            };

            $scope.$on('item_deleted', function () {
                showInfo("The feedback was successfully deleted");
                $scope.filterFeedbacks();
            });

            $scope.clearAllFilters = function () {
                for (var i = 0; i < $scope.feedbackFilter.length; i++) {
                    $scope.feedbackFilter[i] = '';
                }
                toggleFilterApprovedPos = 2;
                $scope.changeFilterApprovedClass();
            };

            $scope.getUser = function (email) {
                $http.get("/users/email/?email=" + email)
                    .then(function (response) {
                        $scope.showUser(response.data);
                    });
            };

            $scope.showUser = function (userDTO) {
                var modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    controller: 'feedbackShowUserController',
                    templateUrl: '/app/views/feedbacks/feedbackShowUser.html',
                    resolve: {
                        userDTO: function () {
                            return userDTO;
                        }
                    }
                });
            };

            init();
        }])
    .controller('editFeedbackController', ['$scope', '$http', '$uibModalInstance', 'feedbackDTO',
        function ($scope, $http, $uibModalInstance, feedbackDTO) {

            $scope.feedbackText = feedbackDTO.text;

            $scope.updateFeedbackText = function () {
                $uibModalInstance.close();
                feedbackDTO.text = $scope.feedbackText;
                $http.put("/feedbacks/updateFeedback", feedbackDTO);
            };

            $scope.cancelEditFeedbackText = function () {
                $uibModalInstance.dismiss('cancel');
            };

        }])
    .controller('deleteFeedbackController', ['$scope', '$http', '$uibModalInstance', 'feedbackId', 'shareFeedbackDataService',
        function ($scope, $http, $uibModalInstance, feedbackId, shareFeedbackDataService) {

            $scope.confirmDeleteFeedback = function () {
                $uibModalInstance.close();
                $http.delete("/feedbacks/deleteFeedback/" + feedbackId)
                    .then(function (response) {
                        if (response.status == 200) {
                            shareFeedbackDataService.itemDeleted();
                        }
                    });
            };

            $scope.cancelDeleteFeedback = function () {
                $uibModalInstance.dismiss('cancel');
            };

        }])
    .controller('feedbackInfoController', ['$scope', '$uibModalInstance', 'text',
        function ($scope, $uibModalInstance, text) {

            $scope.infoText = text;

            $scope.closeInfoFeedback = function () {
                $uibModalInstance.close();
            };

        }])
    .controller('feedbackShowUserController', ['$scope', '$uibModalInstance', 'userDTO',
        function ($scope, $uibModalInstance, userDTO) {

            $scope.userDTO = userDTO;

            $scope.closeFeedbackShowUser = function () {
                $uibModalInstance.close();
            };

        }])
    .factory('shareFeedbackDataService', function ($rootScope) {
        var service = {};

        service.itemDeleted = function () {
            $rootScope.$broadcast('item_deleted');
        };

        return service;

    })
    .directive('searchForm', function () {
        return {
            templateUrl: '/app/views/feedbacks/searchFeedbackTemplate.html'
        };
    });