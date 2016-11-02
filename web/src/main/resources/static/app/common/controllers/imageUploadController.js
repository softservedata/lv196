'use strict';

angular
    .module('delivery')
    .controller('imageUploadController', ['$scope', '$http', 'Notification', 'shareUserProfileDTOService',
        function ($scope, $http, Notification, shareUserProfileDTOService) {

            $scope.carId;

            $scope.uploadUserPhoto = function (event) {

                var file = event.target.files[0];

                if (file != null) {

                    var sendData = new FormData();
                    sendData.append('file', file);

                    $http.post("/upload/userPhoto", sendData, {
                        transformRequest: angular.identity,
                        headers: {'Content-Type': undefined}
                    })
                        .then(function (response) {
                                if (response.status == 200) {
                                    Notification.success("The user photo was successfully uploaded");
                                    shareUserProfileDTOService.userDataChanged();
                                }
                            },
                            function (response) {
                                Notification.error({message: response.data.message, title: "Error!"});
                            });
                }
            };

            $scope.uploadCarPhoto = function (event) {

                var file = event.target.files[0];

                var sendData = new FormData();
                sendData.append('file', file);
                sendData.append('carId', shareUserProfileDTOService.getCarId());
                sendData.append('side', shareUserProfileDTOService.getCarSide());

                $http.post("/upload/carPhoto", sendData, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                    .then(function (response) {
                            if (response.status == 200) {
                                shareUserProfileDTOService.carPhotoUploaded();
                                Notification.success("The car photo was successfully uploaded");
                            }
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

        }])
    .directive('customOnChange', function () {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                var onChangeHandler = scope.$eval(attrs.customOnChange);
                element.bind('change', onChangeHandler);
            }
        };
    })
    .factory('shareUserProfileDTOService', function ($rootScope) {
        var service = {};
        var serviceLoggedUser;
        var serviceCarId;
        var serviceCarSide;

        service.userDataChanged = function () {
            $rootScope.$broadcast('user data changed');
        };

        service.userDataChangedForMainView = function () {
            $rootScope.$broadcast('user data changed for main view');
        };

        service.setLoggedUser = function (loggedUser) {
            serviceLoggedUser = loggedUser;
        };

        service.getLoggedUser = function () {
            return serviceLoggedUser;
        };

        service.setCarId = function (carId) {
            serviceCarId = carId;
        };

        service.setCarSide = function (carSide) {
            serviceCarSide = carSide;
        };

        service.getCarId = function () {
            return serviceCarId;
        };

        service.getCarSide = function () {
            return serviceCarSide;
        };

        service.carSelected = function () {
            $rootScope.$broadcast('car selected');
        };

        service.carPhotoUploaded = function () {
            $rootScope.$broadcast('car uploaded');
        };

        return service;
    });