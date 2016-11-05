angular
    .module('delivery')
    .factory('$chat', ['$http', '$uibModal', ($http, $uibModal) => {

        return {
            history: chatId => $http.get('chat/' + chatId),
            create: chatId => $http.post('chat/' + chatId),
            open: chatId => $uibModal.open({
                animation: true,
                templateUrl: '/app/common/views/chat.html',
                controller: 'chatController',
                resolve: {
                    chatId: () => chatId
                }
            })
        };
    }])
    .controller('chatController', ['$scope', '$uibModalInstance', '$chat', '$location', '$anchorScroll', '$timeout', 'chatId',
        function ($scope, $uibModalInstance, $chat, $location, $anchorScroll, $timeout, chatId) {
            let stompClient = null;
            let userEmail = localStorage.getItem('email');
            $scope.messages = [];
            $chat.history(chatId).then(response => {
                $scope.messages = response.data;
                if ($scope.messages.length > 0) {
                    $timeout(() => scrollTo($scope.messages[$scope.messages.length - 1].id), 0, false);
                }
                initStompConnection();
            });

            $scope.classByMsg = msg => msg.authorEmail == userEmail ? 'right' : 'left';

            $scope.sendMessage = () => {
                const msg = {text: $scope.inputText};
                if (stompClient == null) {
                    $chat.create(chatId).then(response => {
                        initStompConnection(() => sendMessageToStomp(msg));
                    });
                } else {
                    sendMessageToStomp(msg);
                }
            };

            $scope.close = () => {
                if (stompClient != null) {
                    stompClient.disconnect();
                }
                $uibModalInstance.dismiss('cancel');
            };

            function sendMessageToStomp(message) {
                stompClient.send('/app/chat/' + chatId, {}, JSON.stringify(message));
                $scope.inputText = undefined;
            }

            function initStompConnection(callback) {
                stompClient = Stomp.client('ws://localhost:8080/chat');
                stompClient.connect({}, () => {
                    stompClient.subscribe('/topic/chat/' + chatId, response => {
                        const msg = JSON.parse(response.body);
                        $scope.$apply(() => {
                            $scope.messages.push(msg);
                        });
                        scrollTo(msg.id)
                    });
                    typeof callback == 'function' ? callback() : null;
                });
            }

            function scrollTo(msgId) {
                $location.hash('message-' + msgId);
                $anchorScroll();
            }
        }]);
