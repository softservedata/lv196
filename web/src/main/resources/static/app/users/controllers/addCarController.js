'use strict';

angular
    .module('delivery')
    .controller('addCarController', ['$scope', '$http', 'Notification', 'shareUserProfileDTOService', '$uibModalInstance',
        'loggedUserEmail',
        function ($scope, $http, Notification, shareUserProfileDTOService, $uibModalInstance, loggedUserEmail) {

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
                driverEmail: ''
            };

            $scope.clickImage = function (target) {
                if ($scope.car.id < 0) {
                    $scope.addNewCar();
                    $scope.car.driverEmail = loggedUserEmail;
                }
                switch (target) {
                    case 'carFrontPhoto':
                        $scope.side = 'front';
                        document.getElementById('carFrontPhotoUpload').click();
                        break;
                    case 'carBackPhoto':
                        $scope.side = 'back';
                        document.getElementById('carBackPhotoUpload').click();
                        break;
                }
            };

            $scope.addNewCar = function (close) {
                $http.put("/userProfile/addNewCar", $scope.car)
                    .then(function (response) {
                            $scope.car = response.data;
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
                if (close) {
                    shareUserProfileDTOService.userDataChanged();
                    $uibModalInstance.close();
                }
            };

            $scope.uploadCarPhoto = function (event) {
                var file = event.target.files[0];

                var sendData = new FormData();
                sendData.append('file', file);
                sendData.append('carId', $scope.car.id);
                sendData.append('side', $scope.side);

                $http.post("/upload/carPhoto", sendData, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                    .then(function (response) {
                            if (response.status == 200) {
                                if ($scope.side == 'front') {
                                    $scope.car.vehicleFrontPhotoURL = response.data.carPhotoPath;
                                } else if ($scope.side == 'back') {
                                    $scope.car.vehicleBackPhotoURL = response.data.carPhotoPath;
                                }
                            }
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.closeAddNewCar = function () {
                $http.delete("/userProfile/deleteCar/" + $scope.car.id);
                $uibModalInstance.dismiss('cancel');
            };

        }]);