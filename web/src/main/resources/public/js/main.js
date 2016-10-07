
var App = angular.module("myAppl", ['ui.bootstrap']);
    App.controller("pleaceController",["$scope", "$http", "$uibModal", function ($scope, $http, $uibModal){
    $scope.sortType     = 'city.cityName';
    $scope.sortReverse  = false;
    $scope.searchFish   = '';

    $scope.updateTable = function () {
        $http.get('http://localhost:8080/tracking/track').success(function (result) {
            $scope.listOfPleaces = result;
        })
    }
    $scope.updateTable();

    $scope.addLocation = function () {
         const modalInstance = $uibModal.open({
            animation: true,
            templateUrl: '/dialog.html',
         });

    }
    $scope.sentState = function () {
          var data = $scope.selectedState.name;
                $http.post('http://localhost:8080/tracking/region', data)
                    .then(response = function (inf) {
                        $scope.regionList = inf.data;
                    }, response = function () {
                        alert('failed');
                    })
            }

    $scope.sentRegion = function() {
        var d = $scope.selectedRegion.name;
        $http.post('http://localhost:8080/tracking/city', d)
            .then(response = function (inf) {
                $scope.cityList = inf.data;
            }, response = function () {
                alert('failed');
            })
    }
    $scope.getStateList = function () {
        $http.get('http://localhost:8080/tracking/state').success(function (inf) {
            $scope.stateList = inf;
        });

    }
    $scope.add = function () {
        var city = {
            name: $scope.selectedCity.name,
            cityId: $scope.selectedCity.cityId,
            region: {
                regionId: $scope.selectedRegion.regionId,
                regionName: $scope.selectedRegion.name,
                state: {
                    stateId:$scope.selectedState.stateId,
                    stateName: $scope.selectedState.name}}};
        $http.post('http://localhost:8080/tracking/add', city) .then(response = function (inf) {
            $scope.$dismiss();
            $scope.updateTable();

        }, response = function () {
            alert('failed');
        })
    }
    }]
    )






