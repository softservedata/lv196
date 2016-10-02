
var Appl = angular.module("myAppl", []);
var model = [
    {name: "Lviv", region: "Lviv region", state: "Lviv state", date: "2016/09/24, 15:30"},
    {name: "Brodu", region: "Brodu region", state: "Lviv state",date: "2016/09/24, 17:00"},
    {name: "Rivne", region: "Rivne region", state: "Rivne state",date: "2016/09/24, 19:35"},
    {name: "Kiev", region: "Kiev region", state: "Kiev state",date: "2016/09/25, 05:00"},
];

Appl.controller("pleaceController",function ($scope, $http) {
    $scope.listOfPleaces = model;

    $scope.getStateList = function () {
        $http.get('http://localhost:8080/tracking/state').success(function (inf) {
            $scope.stateList = inf;
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
    $scope.add = function () {
        if($scope.selectedCity.value != "") {
            var city = {
                name: $scope.selectedCity.name,
                region: {regionName: $scope.selectedRegion.name,
                state: {stateName: $scope.selectedState.name}}};
            $http.post('http://localhost:8080/tracking/add', city)
                .then(response = function (inf) {
                    alert('I am OK');
                }, response = function () {
                    alert('I am bad');
                })
            console.log(city);
        }
        else{
            alert("You must select city!!!")
        }
    }


});

