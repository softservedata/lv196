
angular
    .module("delivery")
    .controller("pleaceController",["$scope", "$http", "$uibModal", function ($scope, $http, $uibModal){
    $scope.sortType     = 'city.cityName';
    $scope.sortReverse  = false;
    $scope.search   = '';
    $scope.listOfPleaces ={
        list: []
     }

    $scope.updateTable = function () {
        $http.get('/track').success(function (result) {
            $scope.listOfPleaces.list= result;
            console.log($scope.listOfPleaces.list)
        })
    }

    $scope.addLocation = function () {
         const modalInstance = $uibModal.open({
            animation: true,
            templateUrl: '/app/tracking/view/dialog.html',
         });
        modalInstance.result.then(function (add) {
            if(add){
                $scope.updateTable();
            }
        })
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
                name: $scope.selectedRegion.name,
                state: {
                    stateId:$scope.selectedState.stateId,
                    name: $scope.selectedState.name}}};
        $http.post('/add', city).then(response = function (inf) {

            $scope.$dismiss();


        }, response = function () {
            alert('failed');
        })
    }


    }]
    )






