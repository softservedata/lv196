'use strict';

angular
    .module('delivery')
    .controller('userImageUploadController', ['$scope', '$http', 'Notification', 'shareUserService', 'Upload',
        'cloudinary',
        function ($scope, $http, Notification, shareUserService, $upload, cloudinary) {

            $scope.progress = 0;
            $scope.showProgressBar = false;

            $scope.uploadUserPhotoCloud = function (event) {

                var userPhoto = event.target.files[0];
                var existingUserPhotoUrl = shareUserService.getLoggedUser().photoUrl;

                if (userPhoto != null) {

                    $scope.progress = 0;
                    $scope.showProgressBar = true;

                    userPhoto.upload = $upload
                        .upload({
                            url: "https://api.cloudinary.com/v1_1/" + cloudinary.config().cloud_name + "/upload",
                            data: {
                                upload_preset: cloudinary.config().upload_preset,
                                tags: 'delivery',
                                context: 'photo=' + userPhoto.title,
                                folder: 'users',
                                file: userPhoto
                            }
                        })
                        .progress(function (e) {
                            userPhoto.progress = Math.round((e.loaded * 100.0) / e.total);
                            $scope.progress = userPhoto.progress;
                            userPhoto.status = "Uploading... " + userPhoto.progress + "%";
                        })
                        .success(function (data, status, headers, config) {
                            $scope.showProgressBar = false;
                            shareUserService.getLoggedUser().photoUrl = data.secure_url;
                            shareUserService.userPhotoUploaded(existingUserPhotoUrl);
                        }).error(function (data, status, headers, config) {
                            Notification.error('Photo was NOT uploaded ' + status.data);
                        });
                }
            };

        }])
    .factory('shareUserService', function ($rootScope) {
        var service = {};
        var serviceLoggedUser;
        var serviceUserPhotoUrl;

        service.userDataChangedForMainView = function () {
            $rootScope.$broadcast('user data changed for main view');
        };

        service.setLoggedUser = function (loggedUser) {
            serviceLoggedUser = loggedUser;
        };

        service.getLoggedUser = function () {
            return serviceLoggedUser;
        };

        service.userPhotoUploaded = function (photoUrl) {
            serviceUserPhotoUrl = photoUrl;
            $rootScope.$broadcast('user photo uploaded');
        };

        service.getServiceUserPhotoUrl = function(){
            return serviceUserPhotoUrl;
        };

        return service;
    });