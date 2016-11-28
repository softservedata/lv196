angular
    .module('delivery')
    .controller('usersController', ['$scope', '$http', '$location', '$anchorScroll', 'Notification', '$filter',
        function ($scope, $http, $location, $anchorScroll, Notification, $filter) {
        $scope.users = {
            allProfiles: [],
            roles: ['Customer', 'Driver', 'Moderator', 'Admin']
        };

        $scope.totalItems;
        $scope.filterStatus = false;
        $scope.userInfo = false;
        $scope.userRate;

        $scope.filter = {
            rows: 10,
            currentPage: 1,
            firstName: null,
            lastName: null,
            email: null,
            blocked: null,
            role: null,
            sortType: 'lastName',
            sortReverse: true
        };

        var replaceVoidWithNull = (filter) => {
            for (var key in filter){
                if (key == ''){
                    key = null;
                }
            }
        };

        $scope.changefilterStatus = () => {
            $scope.filterStatus = !$scope.filterStatus;
            $scope.filter.blocked = $scope.filterStatus;
        };

        $scope.retrieveUsers = (filter) => {

            replaceVoidWithNull(filter);

            $http.post('/users/filter', filter).then(response1 => {
                $scope.users.allProfiles = response1.data;
                $http.get('/users/count-items').then(response2 => {
                    $scope.totalItems = response2.data;
                });
            });
        };

        $scope.retrieveUsers($scope.filter);

        $scope.$watch('filter', (newValue, oldValue) => {
            if (newValue != oldValue) {
                $scope.retrieveUsers(newValue);
            }
        }, true);


        $scope.skip = () => {
            $scope.filter.firstName = null;
            $scope.filter.lastName = null;
            $scope.filter.email = null;
            $scope.filter.role = null;
            $scope.filter.blocked = null;
            $scope.retrieveUsers($scope.filter);
        };

        $scope.getUserInfo = (user) => {
            var email = user.email;
            var rate = user.rate;
            $http.get('/users/email?email=' + email).then(response => {
                $scope.userInfo = response.data;
                $scope.userRate = rate / 10;
            })
        };

        $scope.hideInfo = () => {
            $scope.userInfo = false;
        };

        $scope.changeUserStatus = (user, index) => {
            var email = user.email;
            var status = user.blocked;
            $http.put('/users/change-status?email=' + email + '&status=' + status).then(response => {
                if (response.status == 200) {
                    $scope.users.allProfiles[index].blocked = response.data.blocked;
                }
            }).catch(error => console.log())
        };

        $scope.scrollTo = (id) => {
            $location.hash(id);
            $anchorScroll();
        };

        $scope.saveRole = (user) => {
            $http.put('/users/change-role', user).then(response => {
                Notification.success($filter('translate')('save_role'));
                $scope.retrieveUsers($scope.filter);
            })
        }
    }]);