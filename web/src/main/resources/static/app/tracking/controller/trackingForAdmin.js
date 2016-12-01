angular
    .module("delivery")
    .controller('adminMapsController', ['$scope', '$http', function ($scope, $http) {
        $scope.listOfRoutes = [];
        $scope.listOfClosed = [];
        $scope.fromDate =  null;
        $scope.toDate = null;
        $scope.drivers = [];
        var booleans = [];
        $scope.selectedDriver = null;
        var LeafIcon = L.Icon.extend({
            options: {
                iconSize: [38, 38],
                iconAnchor: [22, 22],
                popupAnchor: [-3, -76]
            }
        });
        var car2 = new LeafIcon({iconUrl: '../../img/car2.png'});

        $scope.mapInit = () => {
            map = new L.Map('adminMap').setView([48.8573822,31.1200367], 6);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
            $scope.controlList = [];
            $scope.layerGroup = L.layerGroup().addTo(map);
            $scope.layerGroupForCar = L.layerGroup().addTo(map);
        };
        $scope.getRandomColor = () => {
            return '#'+(0x1000000+(Math.random())*0xffffff).toString(16).substr(1,6);
        };
        $scope.buildRoute = (from, to) => {
            L.marker(from, {icon: L.AwesomeMarkers.icon({icon: 'star', markerColor: 'cadetblue', prefix: 'fa', iconColor: 'white'}) }).addTo($scope.layerGroup);
            L.marker(to, {icon: L.AwesomeMarkers.icon({icon: 'flag', markerColor: 'cadetblue', prefix: 'fa', iconColor: 'white'}) }).addTo($scope.layerGroup);
            control = L.Routing.control({
                plan: L.Routing.plan([from, to], {
                    createMarker: () => {return null;}
                }),
                lineOptions: {
                    styles: [{color: $scope.getRandomColor()}]
                },
            }).addTo(map);
            $scope.controlList.push(control);
        };
        $scope.getClosedByDate = (fromDate, toDate) => {
            $scope.cleanAllMap();
            if(toDate == 0){
                toDate = null;
            }
            if(fromDate == 0){
                fromDate = null;
            }
            $http.get('/closedByDates?fromDate='+fromDate+'&toDate='+ toDate).then(function (result) {
                $scope.listOfClosed = result.data;
                if($scope.drivers.length == 0){
                    $scope.listOfClosed.forEach((el) => {
                        $scope.drivers.push(el.driverName);
                    })
                }
                for (var i = 0; i < $scope.listOfClosed.length; i++) {
                    if($scope.selectedDriver == null || $scope.listOfClosed[i].driverName ==  $scope.selectedDriver) {
                        $scope.buildRoute(L.latLng($scope.listOfClosed[i].from.x, $scope.listOfClosed[i].from.y),
                            L.latLng($scope.listOfClosed[i].to.x, $scope.listOfClosed[i].to.y));
                    }
                }
            });
        };
        $scope.deleteDates = () => {
            $scope.toDate = null;
            $scope.fromDate =null;
            $scope.seletedDriver = "";
            $scope.getClosedByDate($scope.fromDate, $scope.toDate)
        };
        $scope.setDates = () => {
            const toDate = new Date($scope.toDate).getTime();
            const fromDate = new Date($scope.fromDate).getTime();
            $scope.getClosedByDate(fromDate, toDate)
        };

         $scope.cleanAllMap = () => {
             $scope.deleteMarkers();
             for (var i = 0; i < $scope.controlList.length; i++) {
                 $scope.controlList[i].setWaypoints([]);
             }
         };
        $scope.showAll = () => {
            booleans.forEach((el, i) => {
                if(el != undefined && !el) {
                    var from = L.latLng($scope.listOfRoutes[i].idFrom.x, $scope.listOfRoutes[i].idFrom.y);
                    var to = L.latLng($scope.listOfRoutes[i].idTo.x, $scope.listOfRoutes[i].idTo.y);
                    booleans[i] = true;
                    $scope.buildRoute(from, to);
                }
            })
        };
        $scope.clear = (points, j) => {
            $scope.controlList[j].setWaypoints([]);
            $scope.deleteMarkers(points[0]);
            $scope.deleteMarkers(points[1]);
        };
        $scope.clearAll = (from, to) => {
            $scope.controlList.forEach((el, j) => {
                var points = el.getWaypoints();
                if (points[0].latLng != null) {
                    $scope.check(points, from, to,$scope.clear, j);
                }
            })
        };
        $scope.deleteMarkers = (o) => {
            console.log(o);
            $scope.layerGroup.getLayers().forEach((el, i) => {
                if(o != undefined) {
                    if (el.getLatLng().lat == o.latLng.lat && el.getLatLng().lng == o.latLng.lng)
                        $scope.layerGroup.removeLayer(el);
                }
                else {
                    $scope.layerGroup.removeLayer(el);

                }
            });
            if(o == undefined) {
                console.log($scope.layerGroupForCar.getLayers());
                $scope.layerGroupForCar.getLayers().forEach((el, i) => {
                    $scope.layerGroupForCar.removeLayer(el);
                });
            }

            };
        $scope.check = (array, arg1,args2, toDo, j) =>{
            if (arg1 != undefined && args2 != undefined) {
                if (array[0].latLng.lat == arg1.lat && array[0].latLng.lng == arg1.lng) {
                    toDo(array, j);
                }
            }
            else{
                $scope.fillBooleans();
                toDo(array, j);
            }
        };
        $scope.fillBooleans = () =>{
            for (var i = 0; i < booleans.length; i++) {
                if(booleans[i] != undefined) {
                    booleans[i] = false;
                }
            }
        };
        $scope.onButtonClick = (e) => {
           $scope.listOfRoutes.forEach((el, i)=> {
               if (e.latlng.lat == el.points.point.x && e.latlng.lng == el.points.point.y) {
                   var from = L.latLng(el.idFrom.x, el.idFrom.y);
                   var to = L.latLng(el.idTo.x, el.idTo.y);
                   if (!booleans[i]) {
                       booleans[i] = true;
                       $scope.buildRoute(from, to);
                   }
                   else {
                       booleans[i] = false;
                       $scope.clearAll(from, to);
                   }
               }
            })
        };
        $scope.showCars = (el, i) => {
            if ($scope.selectedDriver == null || el.driverName == $scope.selectedDriver.driverName) {
                booleans[i] = false;
                    var current = L.latLng(el.points.point.x, el.points.point.y);
                    var marker = L.marker(current, {
                        icon: car2,
                    }).bindPopup(String("Driver name:" + el.driverName + "<br>" +
                        "Customer name:" + el.customerName + "<br>" +
                        "Description:" + el.description)
                    ).addTo($scope.layerGroupForCar).on('click', $scope.onButtonClick);
                }

        };
        $scope.getAllRoutes = () => {
            $scope.cleanAllMap();
            $http.get('/routes').success((result) => {
                $scope.listOfRoutes = result;
                booleans = [];
                $scope.listOfRoutes.forEach((el, i) => {
                    $scope.showCars(el, i);

                })
            });
        }
    }]);
