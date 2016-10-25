'use strict';

angular
    .module('delivery')
    .controller('imageUploadController', ['$scope', '$http', 'Notification',
        function ($scope, $http, Notification) {

            $scope.uploadUserPhoto = function (event) {

                var file = event.target.files[0];

                var sendData = new FormData();
                sendData.append('file', file);

                $http.post("/upload/userPhoto", sendData, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                    .then(function (response) {
                            if (response.status == 200) {
                                Notification.success("The user photo was successfully uploaded");
                            }
                        },
                        function (response) {
                            Notification.error({message: response.data.message, title: "Error!"});
                        });
            };

            $scope.uploadCarFrontPhoto = function (event) {

                var file = event.target.files[0];

                var sendData = new FormData();
                sendData.append('file', file);
                sendData.append('carId', 1);
                sendData.append('side', 'front');

                $http.post("/upload/carPhoto", sendData, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                    .then(function (response) {
                            if (response.status == 200) {
                                Notification.success("The car front photo was successfully uploaded");
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
    });