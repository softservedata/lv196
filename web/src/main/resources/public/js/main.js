
var Appl = angular.module("myAppl", []);
var model = [
    {name: "Lviv", region: "Lviv region", state: "Lviv state", date: "2016/09/24, 15:30"},
    {name: "Brodu", region: "Brodu region", state: "Lviv state",date: "2016/09/24, 17:00"},
    {name: "Rivne", region: "Rivne region", state: "Rivne state",date: "2016/09/24, 19:35"},
    {name: "Kiev", region: "Kiev region", state: "Kiev state",date: "2016/09/25, 05:00"},
];

Appl.controller("listOfPleacesCtrl",function ($scope){
    $scope.listOfPleaces = model;
});
Appl.controller("StateListCtr", function($scope, $http){
    $scope.stateList = [];
    $http.get('http://localhost:8080/tracking/state').success(function (inf) {
        $scope.stateList=inf;
    });
})
Appl.controller("RegionListCtr", function($scope, $http) {
    $scope.regionList = [];
    $http.get('http://localhost:8080/tracking/region').success(function (inf) {
        $scope.regionList = inf;
    });
})
Appl.controller("CityListCtr", function($scope, $http) {
    $scope.cityList = [];
    $http.get('http://localhost:8080/tracking/city').success(function (inf) {
        $scope.cityList = inf;
    });
})
