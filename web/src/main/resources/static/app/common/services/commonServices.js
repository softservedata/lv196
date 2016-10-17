angular
    .module('delivery')
    .factory('$locations', ['$http', $http => {
        return {
            find: val => $http.get('location/?city=' + val)
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
