'use strict';

angular
    .module('delivery')
    .controller('imageUploadController', ['$scope', '$http', 'Notification',
        function ($scope, $http, Notification) {

            $scope.uploadImage = function (event) {

                var file = event.target.files[0];

                var sendData = new FormData();
                sendData.append('file', file);

                $http.post("/upload/userPhoto", sendData, {
                    transformRequest: angular.identity,
                    headers: {'Content-Type': undefined}
                })
                    .then(function (response) {
                            if (response.status == 200) {
                                Notification.success("The image was successfully uploaded");
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