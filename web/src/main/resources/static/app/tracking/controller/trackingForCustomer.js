angular
    .module("delivery")

    .controller("pleaceController",["$scope", "$http", "$timeout",'$stateParams', "$interval",
        function ($scope, $http, $timeout, $stateParams, $interval){
        $scope.route = null;
        $scope.id = $stateParams.id;
        let LeafIcon = L.Icon.extend({
            options: {
                iconSize:     [38, 38],
                iconAnchor:   [22, 22],
                popupAnchor:  [-3, -76]
            }
        });
        let car2 = new LeafIcon({iconUrl: '../../img/car2.png'});

        $scope.getRandomColor = () => {
            return '#'+(0x1000000+(Math.random())*0xffffff).toString(16).substr(1,6);
        };
        $scope.buildRoute = (from, to) => {
            L.marker(from, {icon: L.AwesomeMarkers.icon({icon: 'star', markerColor: 'cadetblue', prefix: 'fa', iconColor: 'white'}) }).addTo(map);
            L.marker(to, {icon: L.AwesomeMarkers.icon({icon: 'flag', markerColor: 'cadetblue', prefix: 'fa', iconColor: 'white'}) }).addTo(map);
            control = L.Routing.control({
                plan: L.Routing.plan([from, to], {
                    createMarker: () => {return null;}
                }),
                lineOptions: {
                    styles: [{color: $scope.getRandomColor()}]
                },
            }).addTo(map);
        };

        $scope.update = function () {
            $scope.layerGroup = L.layerGroup().addTo(map);
            $http.get('/track/' + $scope.id).then((result) => {
                $scope.route = result.data;
                let from = L.latLng($scope.route.idFrom.x, $scope.route.idFrom.y);
                let to = L.latLng($scope.route.idTo.x, $scope.route.idTo.y);
                $scope.buildRoute(from, to);
                let current = L.latLng($scope.route.points.point.x, $scope.route.points.point.y);
                L.marker(current, {
                    icon: car2,
                }).addTo($scope.layerGroup).on('click', $scope.onButtonClick);
            });

        };
        $scope.updateCurrentLocation = () => {
            $http.get('/track/' + $scope.id).then((result) => {
                let res = result.data;
                let current = L.latLng(res.points.point.x, res.points.point.y);
                    $scope.deleteMarkers();
                    L.marker(current, {
                        icon: car2,
                    }).addTo($scope.layerGroup).on('click', $scope.onButtonClick);

            });
        };

        $scope.initializeMap = function () {
            $timeout(() => {
                map = new L.Map('mapCustomer');
                map.setView([48.8573822, 31.1200367], 6);
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                }).addTo(map);
                $scope.update();
            }, 0, false);
        };

       $interval($scope.updateCurrentLocation, 4000);

        $scope.deleteMarkers = () => {
            $scope.layerGroup.getLayers().forEach((el) => {
                    $scope.layerGroup.removeLayer(el);
            });
        };
        }]
    )






