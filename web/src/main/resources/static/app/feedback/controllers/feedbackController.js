'use strict';

angular
    .module('delivery')
    .controller('feedbackController', ['$scope', '$http', 'shareFeedbackDataService', '$uibModal', '$location',
        '$anchorScroll', 'Notification', '$filter',
        function ($scope, $http, shareFeedbackDataService, $uibModal, $location, $anchorScroll,
                  Notification, $filter) {

            $scope.feedback = [];
            $scope.feedbackSortIcons = [];
            $scope.sortFeedbacksOrderDesc = [];

            $scope.feedbackFilterDTO = {
                text: null,
                rate: null,
                userName: null,
                transporterName: null,
                createdOn: null,
                approved: null,
                sortBy: null,
                sortDesc: true,
                currentPage: 1,
                itemsPerPage: 5
            };
            $scope.totalItems = 0;
            $scope.popupDatePicker = {
                opened: false
            };

            $scope.rate = null;
            $scope.text = '';
            $scope.createdOn = '';

            const columnNumber = 7;
            var columnPos = 5;
            var toggleFilterApprovedPos = 0;

            var init = function () {
                for (var i = 0; i < columnNumber; i++) {
                    if (i == 5) {
                        $scope.feedbackSortIcons.push("fa fa-sort-desc");
                        $scope.sortFeedbacksOrderDesc.push(true);
                    } else {
                        $scope.feedbackSortIcons.push("fa fa-sort");
                        $scope.sortFeedbacksOrderDesc.push(false);
                    }
                }
                if ($scope.feedback.length == 0) {
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
                        $scope.feedbackFilterDTO.approved = false;
                        return "color: red; text-align: center; vertical-align: middle; line-height: inherit;";
                    case 2:
                        $scope.feedbackFilterDTO.approved = true;
                        return "color: green; text-align: center; vertical-align: middle; line-height: inherit;";
                    default:
                        $scope.feedbackFilterDTO.approved = null;
                        return "color: lightgray; text-align: center; vertical-align: middle; line-height: inherit;";
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

            var prepareFeedbackFilterDTO = function () {
                $scope.feedbackFilterDTO.rate = $scope.rate;
                $scope.feedbackFilterDTO.createdOn = Date.parse($scope.createdOn);
                $scope.feedbackFilterDTO.sortBy = sortBy(columnPos);
                $scope.feedbackFilterDTO.sortDesc = $scope.sortFeedbacksOrderDesc[columnPos];
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

                prepareFeedbackFilterDTO();

                $http.post("/feedback/all", $scope.feedbackFilterDTO)
                    .then(function (response0) {
                            $http.post("/feedback/totalItems", $scope.feedbackFilterDTO)
                                .then(function (response1) {
                                    $scope.totalItems = response1.data;
                                });
                            $scope.feedback = response0.data;
                        },
                        function (response) {
                            Notification.error({
                                message: $filter('translate')(response.data.message),
                                title: $filter('translate')("error")
                            });
                        });
            };

            $scope.changeFeedbackStatus = function (feedbackDTO) {
                feedbackDTO.approved = !feedbackDTO.approved;
                $http.put("/feedback/updateFeedback", feedbackDTO)
                    .then(function (response) {
                        },
                        function (response) {
                            Notification.error({
                                message: $filter('translate')(response.data.message),
                                title: $filter('translate')("error")
                            });
                        });
                console.log(feedbackDTO);
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
                    templateUrl: '/app/feedback/views/editFeedbackText.html',
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
                    templateUrl: '/app/feedback/views/deleteFeedback.html',
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
                    templateUrl: '/app/feedback/views/infoFeedbacks.html',
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
                $scope.feedbackFilterDTO.text = null;
                $scope.rate = null;
                $scope.feedbackFilterDTO.userName = null;
                $scope.feedbackFilterDTO.transporterName = null;
                $scope.createdOn = null;
                $scope.feedbackFilterDTO.approved = null;
                toggleFilterApprovedPos = 2;
                $scope.changeFilterApprovedClass();
            };

            $scope.getUser = function (email) {
                $http.get("/users/email/?email=" + email)
                    .then(function (response) {
                            $scope.showUser(response.data);
                        },
                        function (response) {
                            Notification.error({
                                message: $filter('translate')(response.data.message),
                                title: $filter('translate')("error")
                            });
                        });
            };

            $scope.showUser = function (userDTO) {
                var modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    controller: 'feedbackShowUserController',
                    templateUrl: '/app/feedback/views/feedbackShowUser.html',
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
            templateUrl: '/app/feedback/views/searchFeedbackTemplate.html'
        };
    })
    .directive("disableAnimate", function ($animate) {
        return function (scope, element) {
            $animate.enabled(element, false);
        };
    });