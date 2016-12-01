angular
    .module("delivery")
    .controller('driverModalController', ['$scope', '$http','$uibModalInstance','$timeout', 'orderId',
        function ($scope,$http, $uibModalInstance, $timeout, orderId) {

            $scope.route = null;
            $scope.waypoint = [];
            $scope.set = true;

            $scope.mapInit = () => {
                $timeout(() =>{
                    map = new L.Map('driverMap').setView([48.8573822,31.1200367], 6);
                    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                    }).addTo(map);
                    $scope.layerGroup = L.layerGroup().addTo(map);
                    $scope.getRouteById();
                }, 0, false);
            };

            $scope.cleanAllMap = () => {
                $scope.layerGroup.getLayers().forEach((el, i) => {
                    $scope.layerGroup.removeLayer(el);
                });
            };

            $scope.getRouteById = () => {
                $http.get('/getRoute/' + orderId).then((result)=> {
                        $scope.route = result.data;
                        $scope.buildRoute(L.latLng($scope.route.from.x, $scope.route.from.y),
                            L.latLng($scope.route.to.x, $scope.route.to.y));
                    }
                )
            };
            $scope.buildRoute = (from, to) => {
                L.marker(from, {icon: L.AwesomeMarkers.icon({icon: 'star', markerColor: 'cadetblue', prefix: 'fa', iconColor: 'white'}) }).addTo(map);
                L.marker(to, {icon: L.AwesomeMarkers.icon({icon: 'flag', markerColor: 'cadetblue', prefix: 'fa', iconColor: 'white'}) }).addTo(map);
                $scope.control = L.Routing.control({
                    plan: L.Routing.plan([from, to], {
                        createMarker: () => {return null;}
                    }),
                    lineOptions: {
                        styles: [{color: 'blue'}]
                    },
                    routeLine: function(route) {
                        let line = L.Routing.line(route, {
                            addWaypoints: false
                        }).eachLayer(function(l) {
                            l.on('click', function(e) {
                                L.marker(e.latlng).addTo($scope.layerGroup);
                            });
                        });
                        return line;
                    }
                }).addTo(map);
                $scope.getPoints();
            };
            $scope.setPoints = () => {
                let list = [];
                list.push($scope.route.from.x +' '+$scope.route.from.y);
                $scope.layerGroup.getLayers().forEach((p) => {
                        list.push(p.getLatLng().lat + ' ' + p.getLatLng().lng);
                });
                list.push($scope.route.to.x +' '+$scope.route.to.y);
                $http.put('/setPoints?id='+ orderId+'&points='+ list).then(()=>{
                    $uibModalInstance.dismiss('cancel');
                })

            };
            $scope.getPoints = () => {
                $http.get('/points/' + orderId).then((result) => {
                    $scope.points = result.data;
                    if($scope.points.length != 0) {
                        $scope.set = false;
                    }
                    $scope.points.forEach((el, i) => {
                        if(i != 0 && i != $scope.points.length - 1 ) {
                            let point = L.latLng(el.point.x, el.point.y);
                            L.marker(point).addTo($scope.layerGroup);
                            $scope.waypoint.push(point);
                        }
                    })

                })
            };

        }]);

