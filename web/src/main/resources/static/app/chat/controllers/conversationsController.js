angular
    .module('delivery')
    .factory('$conversations', ['$http', $http => {
        return {
            findAll: () => $http.get('conversation')
        };
    }])
    .controller('conversationsController', ['$scope', '$conversations', '$chat',
        function ($scope, $conversations, $chat) {
            $scope.conversations = [];

            $scope.retrieveConversations = () => {
                $conversations.findAll().then(response => {
                    $scope.conversations = response.data;
                })
            };
            $scope.retrieveConversations();

            $scope.showChat = (offerId) => {
                $chat.open(offerId);
            };

        }]);