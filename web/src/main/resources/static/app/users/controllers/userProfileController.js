'use strict';

angular
    .module('delivery')
    .controller('userProfileController', ['$scope', '$http', 'Notification', 'shareUserService', '$uibModal',
        'shareCarService',
        function ($scope, $http, Notification, shareUserService, $uibModal, shareCarService) {

            const rateFactor = 10;
            const carsFolder = 'cars';
            const usersFolder = 'users';
            const cloudUrl = 'cloudinary';

            $scope.isDriver = false;
            $scope.cars = [];
            $scope.initialCars = [];
            $scope.initialUser = {};
            $scope.isCollapsed = [];
            $scope.changePassword = false;
            $scope.password = {
                current: '',
                new0: '',
                new1: ''
            };
            $scope.loggedUser = shareUserService.getLoggedUser();
            $scope.initialUser = angular.copy($scope.loggedUser);
            $scope.rate = $scope.loggedUser.rate / rateFactor;

            $scope.car = {
                id: -1,
                vehicleName: '',
                vehicleNumber: '',
                vehicleVIN: '',
                vehicleFrontPhotoURL: '',
                vehicleBackPhotoURL: '',
                vehicleWeight: '',
                vehicleLength: '',
                vehicleWidth: '',
                vehicleHeight: '',
                driverEmail: '',
                progress: 0,
                showProgressFront: false,
                showProgressBack: false
            };

            var initialCar = $scope.car;
            var deleteCarId;
            var modalInstance;

            var getPublicId = function (fileUrl, folder) {
                var posBegin = fileUrl.indexOf(folder);
                var posEnd = fileUrl.lastIndexOf('.');

                if (posBegin != -1 && posEnd != -1) {
                    return fileUrl.substr(posBegin, posEnd - posBegin);
                }
            };

            var deleteExistingPhoto = function (photoUrl, folder) {
                if (photoUrl != null && photoUrl != '' && photoUrl.indexOf(cloudUrl) != -1) {
                    $http.delete("/upload/deleteCloudPhoto/" + getPublicId(photoUrl, folder))
                        .then(function (response) {
                            },
                            function (response) {
                            });
                }
            };

            var prepareCarUpload = function (car, index, side) {
                shareCarService.setCarNumber($scope.cars.length);
                shareCarService.setIndex(index);
                shareCarService.setSelectedCar(car);
                shareCarService.setCarSide(side);
            };

            var checkIfDriver = function () {
                if ($scope.loggedUser.role.toLowerCase() == 'driver') {
                    $scope.isDriver = true;
                    $scope.getCars();
                } else {
                    $scope.isDriver = false;
                }
            };

            $scope.getLoggedUser = function () {
                $http.get("/userProfile/loggedUser")
                    .then(function (response) {
                        $scope.loggedUser = response.data;
                        $scope.initialUser = angular.copy($scope.loggedUser);
                        $scope.rate = $scope.loggedUser.rate / rateFactor;
                        shareUserService.setLoggedUser($scope.loggedUser);
                    });
            };

            $scope.getCars = function () {
                $http.get("/userProfile/loggedUser/cars?email=" + $scope.loggedUser.email)
                    .then(function (response) {
                        $scope.cars = response.data;
                        $scope.initialCars = angular.copy($scope.cars);
                        $scope.isCollapsed.length = 0;
                        var pos = -1;
                        var carId = 0;
                        try {
                            carId = shareCarService.getSelectedCar().id;
                        } catch (err) {
                            pos = 0;
                        }
                        for (var i = 0; i < $scope.cars.length; i++) {
                            if (carId == $scope.cars[i].id || pos == i) {
                                $scope.isCollapsed.push(false);
                            } else {
                                $scope.isCollapsed.push(true);
                            }
                        }
                    });
            };

            $scope.updateUser = function (form) {
                if (form != null) {
                    form.$setPristine();
                }
                $http.put("/userProfile/updateUser", $scope.loggedUser)
                    .then(function (response) {
                            Notification.success('Your data was successfully updated');
                            shareUserService.setLoggedUser($scope.loggedUser);
                            shareUserService.userDataChangedForMainView();
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.cancelUpdateUser = function (form) {
                form.$setPristine();
                $scope.loggedUser = angular.copy($scope.initialUser);
                shareUserService.setLoggedUser($scope.loggedUser);
                shareUserService.userDataChangedForMainView();
            };

            $scope.clickImage = function (target, car, index) {
                if (car != null && car.id < 0) {
                    $scope.car.driverEmail = $scope.loggedUser.email;
                    $scope.addNewCar();
                }
                switch (target) {
                    case 'userPhoto':
                        document.getElementById('userPhotoUpload').click();
                        break;
                    case 'carFrontPhoto':
                        prepareCarUpload(car, index, 'front');
                        document.getElementById('carFrontPhotoUpload').click();
                        break;
                    case 'carBackPhoto':
                        prepareCarUpload(car, index, 'back');
                        document.getElementById('carBackPhotoUpload').click();
                        break;
                }
            };

            $scope.addNewCar = function () {
                $http.put("/userProfile/addNewCar", $scope.car)
                    .then(function (response) {
                            $scope.car = response.data;
                            shareCarService.setSelectedCar($scope.car);
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.$on('user photo uploaded', function () {
                $scope.loggedUser = shareUserService.getLoggedUser();
                deleteExistingPhoto(shareUserService.getServiceUserPhotoUrl(), usersFolder);
                $scope.updateUser();
            });

            $scope.$on('car photo uploaded', function () {
                deleteExistingPhoto(shareCarService.getCarPhotoUrl(), carsFolder);
                $scope.updateCar(shareCarService.getSelectedCar());
            });

            $scope.updateCar = function (car, form, notification) {
                if (form != null) {
                    form.$setPristine();
                }
                $http.put("/userProfile/updateCar", car)
                    .then(function (response) {
                            shareCarService.setSelectedCar(car);
                            $scope.getCars();
                            if (notification) {
                                Notification.success('Your data was successfully updated');
                            }
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        }
                    );
            };

            $scope.restoreCar = function (carId, index, form) {
                if (form != null) {
                    form.$setPristine();
                }
                for (var i = 0; i < $scope.cars.length; i++) {
                    if (carId == $scope.cars[i].id) {
                        $scope.cars[i] = angular.copy($scope.initialCars[i]);
                    }
                    $scope.isCollapsed[index] = false;
                }
            };

            $scope.addCar = function () {
                modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: '/app/users/views/addCar.html',
                    scope: $scope
                });
            };

            $scope.confirmAddCar = function () {
                modalInstance.close();
                $scope.updateCar($scope.car, null, true);
                $scope.car = initialCar;
            };

            $scope.cancelAddCar = function () {
                modalInstance.dismiss('cancel');
                deleteCarId = $scope.car.id;
                $scope.car = initialCar;
                $scope.confirmDeleteCar(false);
            };

            $scope.deleteCar = function (carId) {
                deleteCarId = carId;
                modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    templateUrl: '/app/users/views/deleteCar.html',
                    scope: $scope
                });
            };

            $scope.confirmDeleteCar = function (showNotification) {
                if (deleteCarId > 0) {
                    $http.delete("/userProfile/deleteCar/" + deleteCarId)
                        .then(function (response) {
                                for (var i = 0; i < $scope.cars.length; i++) {
                                    if ($scope.cars[i].id == deleteCarId) {
                                        deleteExistingPhoto($scope.cars[i].vehicleFrontPhotoURL, carsFolder);
                                        deleteExistingPhoto($scope.cars[i].vehicleBackPhotoURL, carsFolder);
                                        $scope.cars.splice(i, 1);
                                    }
                                }
                                $scope.initialCars = angular.copy($scope.cars);
                                $scope.isCollapsed[0] = false;

                                if (showNotification) {
                                    modalInstance.close();
                                    Notification.success('Your car was successfully deleted');
                                }
                            },
                            function (response) {
                                if (showNotification) {
                                    Notification.error({message: response.data.message, title: "Error!"});
                                }
                            });
                }
            };

            $scope.cancelDeleteCar = function () {
                modalInstance.dismiss('cancel');
            };

            $scope.clearPasswordForm = function () {
                $scope.changePassword = false;
                $scope.password.current = '';
                $scope.password.new0 = '';
                $scope.password.new1 = '';
            };

            $scope.updateUserPassword = function () {
                $http.post("/userProfile/updateUserPassword?" + 'current=' + $scope.password.current +
                '&new0=' + $scope.password.new0 + '&new1=' + $scope.password.new1)
                    .then(function (response) {
                            Notification.success(response.data.message);
                            $scope.clearPasswordForm();
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        }
                    );
            };

            checkIfDriver();

        }])
    .directive('carData', function () {
        return {
            controller: 'carImageUploadController',
            templateUrl: '/app/users/views/carData.html'
        };
    });
