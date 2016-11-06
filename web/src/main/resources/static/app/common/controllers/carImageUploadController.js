'use strict';

angular
    .module('delivery')
    .controller('carImageUploadController', ['$scope', '$http', 'Notification', 'shareCarService', 'Upload',
        'cloudinary',
        function ($scope, $http, Notification, shareCarService, $upload, cloudinary) {

            var carPhotoUrl = '';

            $scope.uploadCarPhotoCloud = function (event) {
                var carPhoto = event.target.files[0];
                if (carPhoto != null) {

                    $scope.selectedCar = shareCarService.getSelectedCar();
                    $scope.selectedCar.progress = 0;
                    $scope.selectedCar.showProgressFront = false;
                    $scope.selectedCar.showProgressBack = false;

                    if (shareCarService.getCarSide() == 'front') {
                        $scope.selectedCar.showProgressFront = true;
                        carPhotoUrl = shareCarService.getSelectedCar().vehicleFrontPhotoURL;
                    } else if (shareCarService.getCarSide() == 'back') {
                        $scope.selectedCar.showProgressBack = true;
                        carPhotoUrl = shareCarService.getSelectedCar().vehicleBackPhotoURL;
                    }

                    carPhoto.upload = $upload
                        .upload({
                            url: "https://api.cloudinary.com/v1_1/" + cloudinary.config().cloud_name + "/upload",
                            data: {
                                upload_preset: cloudinary.config().upload_preset,
                                tags: 'delivery',
                                context: 'photo=' + carPhoto.title,
                                folder: "cars",
                                file: carPhoto
                            }
                        })
                        .progress(function (e) {
                            carPhoto.progress = Math.round((e.loaded * 100.0) / e.total);
                            $scope.selectedCar.progress = carPhoto.progress;
                            carPhoto.status = "Uploading... " + carPhoto.progress + "%";
                        })
                        .success(function (data, status, headers, config) {
                            if (shareCarService.getCarSide() == 'front') {
                                $scope.selectedCar.showProgressFront = false;
                                shareCarService.getSelectedCar().vehicleFrontPhotoURL = data.secure_url;
                            } else if (shareCarService.getCarSide() == 'back') {
                                $scope.selectedCar.showProgressBack = false;
                                shareCarService.getSelectedCar().vehicleBackPhotoURL = data.secure_url;
                            }
                            shareCarService.carPhotoUploaded(carPhotoUrl);
                        }).error(function (data, status, headers, config) {
                            Notification.error('Photo was NOT uploaded ' + status.data);
                        });
                }
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
    .factory('shareCarService', function ($rootScope) {
        var service = {};
        var serviceCarSide;
        var serviceSelectedCar;
        var serviceCarPhotoUrl;
        var serviceCarNumber = 0;
        var serviceIndex = 0;

        service.setCarSide = function (carSide) {
            serviceCarSide = carSide;
        };

        service.getCarSide = function () {
            return serviceCarSide;
        };

        service.carPhotoUploaded = function (photoUrl) {
            serviceCarPhotoUrl = photoUrl;
            $rootScope.$broadcast('car photo uploaded');
        };

        service.getCarPhotoUrl = function () {
            return serviceCarPhotoUrl;
        };

        service.getSelectedCar = function () {
            return serviceSelectedCar;
        };

        service.setSelectedCar = function (car) {
            serviceSelectedCar = car;
        };

        service.setCarNumber = function (number) {
            serviceCarNumber = number;
        };

        service.getCarNumber = function () {
            return serviceCarNumber;
        };

        service.setIndex = function (index) {
            serviceIndex = index;
        };

        service.getIndex = function () {
            return serviceIndex;
        };

        return service;
    });