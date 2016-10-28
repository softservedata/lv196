angular
    .module("delivery")
    .filter('htmlToPlaintext', function() {
        return function (text) {
            return text ? String(text).replace(/<[^>]+>/gm, '') : '';
        }
    })
    .controller("pleaceController",["$scope", "$http", "$timeout",'$stateParams', function ($scope, $http, $timeout, $stateParams){
        $scope.sortType     = 'city.cityName';
        $scope.sortReverse  = false;
        $scope.search   = '';
        $scope.listOfPleaces ={
            list: []
         }
        $scope.id = $stateParams.id;
        $scope.varModal = true;
         $scope.addresses = [];
         $scope.points = [];
         $scope.list = [];
         $scope.resultList = [];
         var map = null;
         $scope.myFunc = function () {
            $scope.resultList = $scope.list;
            console.log($scope.resultList);
            $scope.$apply();
        }
        var LeafIcon = L.Icon.extend({
            options: {
                iconSize:     [38, 38],
                iconAnchor:   [22, 22],
                popupAnchor:  [-3, -76]
            }
        });
        var icon = new LeafIcon({iconUrl: '../../img/localization-icon-27.png'})
        var car = new LeafIcon({iconUrl: '../../img/car.png'})
        var car2 = new LeafIcon({iconUrl: '../../img/car2.png'})

        $scope.updateTable = function () {
            var id = $scope.id;
            console.log(id);
            $http.get('/track/' + id).then(response = function(result) {
                $scope.listOfPleaces.list = result.data;
                for (var i = 0; i < $scope.listOfPleaces.list.length; i++) {
                    $scope.points.push(new L.LatLng($scope.listOfPleaces.list[i].point.x, $scope.listOfPleaces.list[i].point.y));
                    control = new L.Control.Geocoder({geocoder: null});
                    control.options.geocoder.reverse($scope.points[i], map.options.crs.scale(map.getZoom()), function (i) {
                        return function (results) {
                           var r = results[0];
                            if (r) {
                                $scope.addresses[i] = r.name;
                                $scope.list.push({
                                    date: $scope.listOfPleaces.list[i].date,
                                    address: $scope.addresses[i],
                                });
                                if ($scope.list.length == $scope.listOfPleaces.list.length) {
                                    $scope.myFunc();
                                    L.Routing.control({
                                        plan: L.Routing.plan($scope.points, {
                                            createMarker: function(i, wp) {
                                                return L.marker(wp.latLng, {
                                                    draggable: true,
                                                    icon: i == 0 || i == $scope.points.length - 1 ? icon : car2,
                                                });
                                            },
                                            geocoder: L.Control.Geocoder.nominatim(),
                                            routeWhileDragging: true
                                        }),
                                        //routeWhileDragging: true,
                                        show: false,
                                        lineOptions: {
                                            styles: [{color: 'blue', opacity: 1, weight: 5}]
                                        },

                                    }).addTo(map);
                                   // L.marker([50.505, 30.57], {icon: icon}).addTo(map);
                                }
                            }
                            }
                    }(i));

                }
            });
            };

            $scope.initializeMap = function () {
                $timeout(() => {
                    map = new L.Map('mymap');
                    map.setView([48.8573822,31.1200367], 6);
                    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                    }).addTo(map);
                    $scope.updateTable();
                }, 0, false);
                /*map = new L.Map('mymap');
                map.setView([48.8573822,31.1200367], 6);
                L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                    attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
                }).addTo(map);
                $scope.updateTable();*/
            }
           /* $interval(function() {
                $scope.updateTable();
            }, 5010);*/
        }]
    )






