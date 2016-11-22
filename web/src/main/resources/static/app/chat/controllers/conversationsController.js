angular
    .module('delivery')
    .factory('$conversations', ['$http', $http => {
        return {
            findAll: (page, size) => $http.get('conversations'+ '/' + page + '/' + size)
        };
    }])
    .controller('conversationsController', ['$scope', '$conversations', '$chat',
        function ($scope, $conversations, $chat) {
            $scope.chats = [];
            $scope.page = 0;

            $conversations.findAll($scope.page, 8).then(response => {
                processConversationsResponse(response.data);
            });

            function processConversationsResponse(data) {
                $scope.page += 1;
                $scope.chats = data.chats.concat($scope.chats);
                $scope.havingMore = data.havingMore;
            }

            $scope.loadMore = () => {
                $conversations
                    .findAll($scope.page, 7)
                    .then(response => processConversationsResponse(response.data));
            };

            $scope.showChat = (offerId) => {
                $chat.open(offerId);
            };

        }]);