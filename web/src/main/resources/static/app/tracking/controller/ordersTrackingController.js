angular
    .module('delivery')
    .controller('ordersTrackingController', ['$scope', '$http',
        function ($scope, $http) {
            $scope.markers = [];

            var LeafIcon = L.Icon.extend({
                options: {
                    iconSize:     [38, 38],
                    iconAnchor:   [19, 38],
                    popupAnchor:  [0, -40]
                }
            });
            var icon = new LeafIcon({iconUrl: '../../img/car.png'});

            $scope.trackOrder = function(orderId) {
                if(!orderId) {
                    return;
                }
                console.log($scope.markers.length);
                if($scope.path) { $scope.map.removeLayer($scope.path);}
                for(var i = 0; i < $scope.markers.length; i++) {
                    $scope.map.removeLayer($scope.markers[i]);
                }

                $scope.markers = [];
                if($scope.route) {
                    $scope.map.removeControl($scope.route);
                }

                $http.get('/order/tracking/' + orderId).then(function(response) {
                    console.log(response.data);
                    $scope.ordersTracking = response.data;
                    $scope.points = [];
                    for(var i = 0; i < response.data.routeCityDTOs.length; i++) {
                        var point = response.data.routeCityDTOs[i].placeDTO.point;
                        var marker = L.marker([point.x, point.y], {icon: icon})
                        .addTo($scope.map);

                        $scope.points.push(L.latLng(point.x, point.y));

                        $scope.markers.push(marker);
                    }

                    $scope.route = L.Routing.control({
                        waypoints: $scope.points,
                        routeWhileDragging: false,
                        plan: L.Routing.plan($scope.points, {
                            createMarker: function(i, wp) {
                                return L.marker(wp.latLng, {
                                    draggable: false,
                                    icon: icon,
                                }).bindPopup(response.data.routeCityDTOs[i].date);
                            },
                        }),
                        lineOptions: {
                            styles: [{color: 'blue', opacity: 0.6, weight: 3}]
                        },
                    }).addTo($scope.map);
                });
            };

            $scope.mapInit = function () {
                $scope.map = new L.Map('trackingMap').setView([48.8573822,31.1200367], 6);

                L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
                    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                }).addTo($scope.map);

            }

        }]);