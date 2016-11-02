'use strict';

angular
    .module('delivery')
    .controller('userProfileController', ['$scope', '$http', 'Notification', 'shareUserProfileDTOService', '$uibModal',
        function ($scope, $http, Notification, shareUserProfileDTOService, $uibModal) {

            $scope.isDriver = false;
            $scope.cars = [];
            $scope.initialCars = [];
            $scope.initialUser = {};
            $scope.isCollapsed = [];
            $scope.changePassword = false;
            $scope.password0 = '';
            $scope.password1 = '';

            var rateFactor = 10;
            var deleteCarId;
            var modalInstance;

            $scope.loggedUser = shareUserProfileDTOService.getLoggedUser();
            $scope.initialUser = angular.copy($scope.loggedUser);
            $scope.rate = $scope.loggedUser.rate / rateFactor;

            $scope.getLoggedUser = function () {

                $http.get("/userProfile/loggedUser")
                    .then(function (response) {
                        $scope.loggedUser = response.data;
                        $scope.initialUser = angular.copy($scope.loggedUser);
                        $scope.rate = $scope.loggedUser.rate / rateFactor;
                        shareUserProfileDTOService.setLoggedUser($scope.loggedUser);
                    });
            };

            $scope.getCars = function () {
                $http.get("/userProfile/loggedUser/cars?email=" + $scope.loggedUser.email)
                    .then(function (response) {
                        $scope.cars = response.data;
                        $scope.initialCars = angular.copy($scope.cars);
                        $scope.isCollapsed.length = 0;
                        var carId = shareUserProfileDTOService.getCarId();
                        if (carId == null) {
                            var pos = 0;
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
                form.$setPristine();
                $http.put("/userProfile/updateUser", $scope.loggedUser)
                    .then(function (response) {
                            Notification.success('Your data was successfully updated');
                            shareUserProfileDTOService.setLoggedUser($scope.loggedUser);
                            shareUserProfileDTOService.userDataChangedForMainView();
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.cancelUpdateUser = function (form) {
                form.$setPristine();
                $scope.loggedUser = angular.copy($scope.initialUser);
                shareUserProfileDTOService.setLoggedUser($scope.loggedUser);
                shareUserProfileDTOService.userDataChangedForMainView();
            };

            $scope.clickImage = function (target, carId) {
                var carSide;
                switch (target) {
                    case 'userPhoto':
                        document.getElementById('userPhotoUpload').click();
                        break;
                    case 'carFrontPhoto':
                        carSide = 'front';
                        document.getElementById('carFrontPhotoUpload').click();
                        break;
                    case 'carBackPhoto':
                        carSide = 'back';
                        document.getElementById('carBackPhotoUpload').click();
                        break;
                }
                if (carId != null) {
                    shareUserProfileDTOService.setCarId(carId);
                    shareUserProfileDTOService.setCarSide(carSide);
                    shareUserProfileDTOService.carSelected();
                }
            };

            $scope.$on('user data changed', function () {
                $scope.getLoggedUser();
                $scope.getCars();
            });

            $scope.$on('car uploaded', function () {
                $scope.getCars();
            });

            $scope.addCar = function () {
                var modalInstance = $uibModal.open({
                    ariaLabelledBy: 'modal-title',
                    ariaDescribedBy: 'modal-body',
                    controller: 'addCarController',
                    templateUrl: '/app/users/views/addCar.html',
                    resolve: {
                        loggedUserEmail: function () {
                            return $scope.loggedUser.email;
                        }
                    }
                });
            };

            $scope.updateCar = function (car, form) {
                form.$setPristine();
                $http.put("/userProfile/updateCar", car)
                    .then(function (response) {
                            $scope.initialCars = angular.copy($scope.cars);
                            Notification.success('Your data was successfully updated');

                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.restoreCar = function (carId, index, form) {
                form.$setPristine();
                for (var i = 0; i < $scope.cars.length; i++) {
                    if (carId == $scope.cars[i].id) {
                        $scope.cars[i] = angular.copy($scope.initialCars[i]);
                    }
                    $scope.isCollapsed[index] = false;
                }
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
                modalInstance.close();
                $http.delete("/userProfile/deleteCar/" + deleteCarId)
                    .then(function (response) {
                            for (var i = 0; i < $scope.cars.length; i++) {
                                if ($scope.cars[i].id == deleteCarId) {
                                    $scope.cars.splice(i, 1);
                                }
                            }
                            $scope.initialCars = angular.copy($scope.cars);
                            if (showNotification) {
                                Notification.success('Your car was successfully deleted');
                            }
                        },
                        function (response) {
                            if (showNotification) {
                                Notification.error({message: response.data.message, title: "Error!"});
                            }
                        });
            };

            $scope.cancelDeleteCar = function () {
                modalInstance.dismiss('cancel');
            };

            var checkIfDriver = function () {
                if ($scope.loggedUser.role.toLowerCase() == 'driver') {
                    $scope.isDriver = true;
                    $scope.getCars();
                } else {
                    $scope.isDriver = false;
                }
            };

            checkIfDriver();

        }])
    .directive('carData', function () {
        return {
            templateUrl: '/app/users/views/carData.html'
        };
    });
