angular
    .module("delivery")
    .controller('adminMapsController', ['$scope','$http','$timeout', function ($scope,$http, $timeout) {

        var collors = ['blue', 'green', 'red', 'brown', 'orange'];
        $scope.listOfRoutes = [];
        $scope.size = 1;
        $scope.currentPage = 1;
        $scope.real = $scope.currentPage-1;
        $scope.mapInit = function () {

            map = new L.Map('adminMap');

            map.setView([48.8573822,31.1200367], 6);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
            layerGroup = L.layerGroup().addTo(map);
            let promise = new Promise((resolve, reject) => {
                $scope.getAllRoutes();
                resolve('Success!');
            });
            promise.then(data => {
                $scope.getCount();
            });
        }
        $scope.buildRoute = function(from, to, color, t){
            /*control = L.Routing.control({
                waypoints: [
                    from, to
                ],
                lineOptions: {
                    styles: [{color: color}]
                },
            }).addTo(layerGroup);*/
            if(t){
                L.marker(from).addTo(layerGroup);
                L.marker(to, {
                    icon: car2,
                }).addTo(layerGroup);
            }
            else {
                L.marker(to).addTo(layerGroup);
            }
            L.polyline([from, to], {
                color: color
            }).addTo(layerGroup);
        }
        var LeafIcon = L.Icon.extend({
            options: {
                iconSize:     [38, 38],
                iconAnchor:   [22, 22],
                popupAnchor:  [-3, -76]
            }

        });
        var car2 = new LeafIcon({iconUrl: '../../img/car2.png'});
        $scope.getCount = function () {
            $http.get('/count').success(function(innf) {
                setTimeout(function() {
                    $scope.$apply(function () {
                        $scope.count = innf;
                        $scope.size = $scope.count;
                        $scope.getAllRoutes();
                    });
                },0);
            });
        }
        $scope.getAllRoutes = function (){
            $scope.real = $scope.currentPage - 1;
            $http.get('/routes/'+$scope.size +'/'+$scope.real).success(function(result) {
                $scope.listOfRoutes = result;
                layerGroup.clearLayers();
                for(var i = 0; i<$scope.listOfRoutes.length; i++){
                    var from = L.latLng($scope.listOfRoutes[i].idFrom.x, $scope.listOfRoutes[i].idFrom.y);
                    var current = L.latLng($scope.listOfRoutes[i].points.point.x, $scope.listOfRoutes[i].points.point.y);
                    var to = L.latLng($scope.listOfRoutes[i].idTo.x, $scope.listOfRoutes[i].idTo.y);
                    $scope.buildRoute(from, current, collors[i], true);
                    $scope.buildRoute(current, to, 'white', false);
                }
        });

        }
    }])
