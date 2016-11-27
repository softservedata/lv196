angular
    .module('delivery')
    .factory('$locations', ['$http', '$q', ($http, $q) => {

        function findByType(components, type) {
            const comp = components.find(comp => comp.types.find(t => t == type));
            return comp ? comp.short_name : undefined;
        }

        return {
            find: val => $http.get('location/?city=' + val),
            googleGeocode: address => {
                const deferred = $q.defer();

                $http.get('https://maps.googleapis.com/maps/api/geocode/json', {
                    params: {
                        address: address,
                        key: sessionStorage.getItem('google-key'),
                        language: 'uk',
                        region: 'UK'
                    }
                }).then(resp => {
                   const data = resp.data;
                   console.log('request for: ' + address);
                   console.dir(data);
                   if (data.status == 'OK') {
                        const locations = data.results
                            .map(res =>  {
                                let x =  {
                                    id: res.place_id,
                                    latitude: res.geometry.location.lat,
                                    longitude: res.geometry.location.lng,
                                    formatted: res.formatted_address,
                                    city: findByType(res.address_components, 'locality'),
                                    region: findByType(res.address_components, 'administrative_area_level_2'),
                                    oblast: findByType(res.address_components, 'administrative_area_level_1')
                                };
                                return x;
                            });
                        deferred.resolve(locations);
                   } else {
                       deferred.reject();
                   }
                });

                return deferred.promise;
            }
        }
    }])
    .factory('$utils', ['$uibModal', $uibModal => {
        return {
            confirmDialog: config => {
                const modalInstance = $uibModal.open({
                    animation: true,
                    size: 'sm',
                    templateUrl: '/app/common/views/modal.html',
                    controller: ['$scope', '$uibModalInstance', function ($scope, $uibModalInstance) {
                        $scope.message = config.message ? config.message : 'Are you sure?';
                        $scope.yesLabel = config.yes ? config.yes : 'Yes';
                        $scope.noLabel = config.no ? config.no : 'No';
                        $scope.yesBtnClass = config.yesBtnClass ? config.yesBtnClass : 'btn-primary';
                        $scope.yes = () =>  $uibModalInstance.close(true);
                        $scope.no = () => $uibModalInstance.dismiss('cancel');
                    }]
                });

                return modalInstance.result;
            }
        }
    }]);
