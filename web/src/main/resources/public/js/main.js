
var App = angular.module("myAppl", ['ui.bootstrap']);

    App.controller("pleaceController",["$scope", "$http", "$uibModal", function ($scope, $http, $uibModal){
    $scope.sortType     = 'city.cityName';
    $scope.sortReverse  = false;
    $scope.search   = '';


        $scope.listOfPleaces ={
            list: []
        }

    $scope.updateTable = function () {
        $http.get('/track').success(function (result) {
            $scope.listOfPleaces.list= result;
        })
    }
    $scope.updateTable();

    $scope.addLocation = function () {
         const modalInstance = $uibModal.open({
            animation: true,
            templateUrl: '/dialog.html',
         });

        /*modalInstance.result.then(function (added) {
            if (added) {
                $scope.updateTable();
            }
        });*/
    };

    $scope.sentState = function () {
          var data = $scope.selectedState.name;
                $http.post('/region', data)
                    .then(response = function (inf) {
                        $scope.regionList = inf.data;
                    }, response = function () {
                        alert('failed');
                    })
            }

    $scope.sentRegion = function() {
        var d = $scope.selectedRegion.name;
        $http.post('/city', d)
            .then(response = function (inf) {
                $scope.cityList = inf.data;
            }, response = function () {
                alert('failed');
            })
    }
    $scope.getStateList = function () {
        $http.get('/state').success(function (inf) {
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
        $http.post('/add', city).then(response = function (inf) {
            $scope.$dismiss();
           $scope.updateTable();

        }, response = function () {
            alert('failed');
        })
    }


    }]
    )






