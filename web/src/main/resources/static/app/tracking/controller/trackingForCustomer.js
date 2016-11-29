angular
    .module("delivery")
    .filter('htmlToPlaintext', function() {
        return function (text) {
            return text ? String(text).replace(/<[^>]+>/gm, '') : '';
        }
    })
    .controller("pleaceController",["$scope", "$http", "$timeout",'$stateParams', "$interval", function ($scope, $http, $timeout, $stateParams, $interval){
        $scope.route;
        $scope.id = $stateParams.id;
        var LeafIcon = L.Icon.extend({
            options: {
                iconSize:     [38, 38],
                iconAnchor:   [22, 22],
                popupAnchor:  [-3, -76]
            }
        });
        var icon = new LeafIcon({iconUrl: '../../img/localization-icon-27.png'});
        var car = new LeafIcon({iconUrl: '../../img/car.png'});
        var car2 = new LeafIcon({iconUrl: '../../img/car2.png'});

        $scope.buildRoute = (from, to) => {
            L.marker(from, {icon: L.AwesomeMarkers.icon({icon: 'star', markerColor: 'cadetblue', prefix: 'fa', iconColor: 'white'}) }).addTo(map);
            L.marker(to, {icon: L.AwesomeMarkers.icon({icon: 'flag', markerColor: 'cadetblue', prefix: 'fa', iconColor: 'white'}) }).addTo(map);
            control = L.Routing.control({
                plan: L.Routing.plan([from, to], {
                    createMarker: () => {return null;}
                }),
                lineOptions: {
                    styles: [{color: 'blue'}]
                },
            }).addTo(map);
        };

        $scope.update = function () {
            var id = $scope.id;
            console.log(id);
            $scope.layerGroup = L.layerGroup().addTo(map);
            $http.get('/track/' + id).then((result) => {
                $scope.route = result.data;
                var from = L.latLng($scope.route.idFrom.x, $scope.route.idFrom.y);
                var to = L.latLng($scope.route.idTo.x, $scope.route.idTo.y);
                $scope.buildRoute(from, to);
                var current = L.latLng($scope.route.points.point.x, $scope.route.points.point.y);
                L.marker(current, {
                    icon: car2,
                }).addTo($scope.layerGroup).on('click', $scope.onButtonClick);
            });

        };
        $scope.updateCurrentLocation = () => {
            $http.get('/track/' + $scope.id).then((result) => {
                var res = result.data;
                var current = L.latLng(res.points.point.x, res.points.point.y);
                var currentOld = L.latLng($scope.route.points.point.x, $scope.route.points.point.y);
                if(current != currentOld) {
                    $scope.deleteMarkers(currentOld);
                    L.marker(current, {
                        icon: car2,
                    }).addTo($scope.layerGroup).on('click', $scope.onButtonClick);
                }
            });
        };

        $scope.initializeMap = function () {
            $timeout(() => {
                map = new L.Map('mymap');
                map.setView([48.8573822, 31.1200367], 6);
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                }).addTo(map);
                $scope.update();
            }, 0, false);
        };

       // $interval($scope.updateCurrentLocation, 4000);

        $scope.deleteMarkers = (o) => {
            $scope.layerGroup.getLayers().forEach((el, i) => {
                if (el.getLatLng().lat == o.lat && el.getLatLng().lng == o.lng)
                    $scope.layerGroup.removeLayer(el);
            });
        };
        }]
    )






