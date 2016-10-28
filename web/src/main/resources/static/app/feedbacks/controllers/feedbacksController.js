'use strict';

angular
    .module('delivery')
    .controller('feedbacksController', ['$scope', '$http', 'shareFeedbackDataService', '$uibModal', '$location',
        '$anchorScroll', 'Notification',
        function ($scope, $http, shareFeedbackDataService, $uibModal, $location, $anchorScroll,
                  Notification) {

            $scope.feedbacks = [];
            $scope.feedbackSortIcons = [];
            $scope.sortFeedbacksOrderDesc = [];

            $scope.feedbackFilter = {
                text: '',
                rate: '',
                userName: '',
                transporterName: '',
                createdOn: '',
                approved: ''
            };
            $scope.currentPage = 1;
            $scope.itemsPerPage = 5;
            $scope.totalItems = 0;
            $scope.popupDatePicker = {
                opened: false
            };

            var rateFactor = 10;
            var columnNumber = 7;
            var columnPos = 0;
            var toggleFilterApprovedPos = 0;
            var rateStep = 0.5;

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

            var recalcRate = function () {
                for (var i = 0, len = $scope.feedbacks.length; i < len; i++) {
                    $scope.feedbacks[i].rate = $scope.feedbacks[i].rate / rateFactor;
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
                        $scope.feedbackFilter.approved = "false";
                        return "color: red;";
                    case 2:
                        $scope.feedbackFilter.approved = "true";
                        return "color: green;";
                    default:
                        $scope.feedbackFilter.approved = "";
                        return "color: lightgray;";
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
                if (text != null && text != '' && text.indexOf("+") != -1) {
                    return text.split("+").join("%2B");
                }
                return text;
            };

            $scope.checkInputLength = function (contents) {
                if (contents.length > 2 || contents.length == 0) {
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
                var requestText = "/feedbacks/all?text=" + replacePlus($scope.feedbackFilter.text) + "&rate=" +
                    ($scope.feedbackFilter.rate - rateStep) * rateFactor + "&userName=" +
                    $scope.feedbackFilter.userName + "&transporterName=" + $scope.feedbackFilter.transporterName +
                    "&createdOn=" + Date.parse($scope.feedbackFilter.createdOn) + "&approved=" +
                    $scope.feedbackFilter.approved + "&sortBy=" + sortBy(columnPos) + "&sortDesc=" +
                    $scope.sortFeedbacksOrderDesc[columnPos] + "&currentPage=" + $scope.currentPage + "&itemsPerPage=" +
                    $scope.itemsPerPage;

                $http.post(requestText)
                    .then(function (response0) {
                            $http.get("/feedbacks/totalItems")
                                .then(function (response1) {
                                    $scope.totalItems = response1.data;
                                });
                            $scope.feedbacks = response0.data;
                            recalcRate();
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.changeFeedbackStatus = function (feedbackDTO) {
                feedbackDTO.approved = !feedbackDTO.approved;
                $http.put("/feedbacks/updateFeedback", feedbackDTO)
                    .then(function (response) {
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
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

            $scope.editFeedbackText = function (feedbackDTO) {
                var modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    controller: 'editFeedbackController',
                    templateUrl: '/app/feedbacks/views/editFeedbackText.html',
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
                    templateUrl: '/app/feedbacks/views/deleteFeedback.html',
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
                    templateUrl: '/app/feedbacks/views/infoFeedbacks.html',
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
                $scope.filterFeedbacks();
            });

            $scope.clearAllFilters = function () {
                $scope.feedbackFilter.text = '';
                $scope.feedbackFilter.rate = '';
                $scope.feedbackFilter.userName = '';
                $scope.feedbackFilter.transporterName = '';
                $scope.feedbackFilter.createdOn = '';
                $scope.feedbackFilter.approved = '';
                toggleFilterApprovedPos = 2;
                $scope.changeFilterApprovedClass();
            };

            $scope.getUser = function (email) {
                $http.get("/users/email/?email=" + email)
                    .then(function (response) {
                            $scope.showUser(response.data);
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.showUser = function (userDTO) {
                var modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    controller: 'feedbackShowUserController',
                    templateUrl: '/app/feedbacks/views/feedbackShowUser.html',
                    resolve: {
                        userDTO: function () {
                            return userDTO;
                        }
                    }
                });
            };

            $scope.openDatePicker = function () {
                $scope.popupDatePicker.opened = true;
            };

            init();
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
            templateUrl: '/app/feedbacks/views/searchFeedbackTemplate.html'
        };
    })
    .directive("disableAnimate", function ($animate) {
        return function (scope, element) {
            $animate.enabled(element, false);
        };
    });