angular
    .module('delivery')
    .factory('$chat', ['$http', '$q', '$uibModal', '$rootScope', ($http, $q, $uibModal, $rootScope) => {
        const opened = {
            chatId: null,
            subscription: null,
            modal: null
        };
        const isOpened = () => opened.modal !== null;

        const stompClient = () => $rootScope.stomp === null ?
            Stomp.client('ws://localhost:8080/chat') : $rootScope.stomp;

        const closeChat = () => {
            opened.chatId = null;
            if (opened.subscription !== null) {
                opened.subscription.unsubscribe();
                opened.subscription = null;
            }
            if (isOpened()) {
                opened.modal.close();
                opened.modal = null;
            }
        };

        const open = chatId => {
            const deferred = $q.defer();
            if (isOpened()) {
                closeChat();
                open(chatId);
            } else {
                opened.chatId = chatId;
                opened.modal = $uibModal.open({
                    animation: true,
                    templateUrl: '/app/common/views/chat.html',
                    controller: 'chatController',
                    resolve: {
                        chatId: () => chatId
                    }
                });
                opened.modal.result.finally(() => {
                    closeChat();
                    deferred.resolve(chatId);
                });
            }

            return deferred.promise;
        };

        return {
            history: chatId => $http.get('chat/' + chatId),
            create: chatId => $http.post('chat/' + chatId, {}),
            isOpened: isOpened,
            openedChatId: () => opened.chatId,
            open: open,
            subscribe: (chatId, onMessage) => {
                const deferred = $q.defer();
                if (opened.subscription !== null) {
                    deferred.reject('Chat is already created. Please, close the old one to create new.');
                } else {
                    opened.subscription = stompClient().subscribe('/topic/chat/' + chatId, response => {
                        onMessage(response);
                    });
                    deferred.resolve(opened.subscription);
                }
                return deferred.promise;
            },
            sendMessage: (chatId, message) => stompClient().send('/app/chat/' + chatId, {}, JSON.stringify(message)),
            stomp: stompClient
        };
    }])
    .controller('chatController', ['$scope', '$uibModalInstance', '$chat', '$location', '$anchorScroll', '$timeout', 'chatId',
        function ($scope, $uibModalInstance, $chat, $location, $anchorScroll, $timeout, chatId) {
            let subscription = null;
            let userEmail = sessionStorage.getItem('email');
            $scope.messages = [];

            $chat.history(chatId).then(response => {
                $scope.messages = response.data;
                if ($scope.messages.length > 0) {
                    $timeout(() => scrollTo($scope.messages[$scope.messages.length - 1].id), 0, false);
                }
                connectAndSend();
            });

            $scope.classByMsg = msg => msg.authorEmail == userEmail ? 'right' : 'left';

            $scope.sendMessage = () => {
                const msg = {text: $scope.inputText};
                if (subscription === null) {
                    $chat.create(chatId).then(() => {
                        connectAndSend(msg);
                    });
                } else {
                    sendMessageToStomp(msg);
                }
            };

            function sendMessageToStomp(message) {
                $chat.sendMessage(chatId, message);
                $scope.inputText = undefined;
            }

            function receiveMessage(message) {
                $scope.$apply(() => {
                    $scope.messages.push(message);
                });
                scrollTo(message.id)
            }

            function connectAndSend(message) {
                subscription = $chat.subscribe(chatId, response => {
                    receiveMessage(JSON.parse(response.body))
                });
                if (message) {
                    sendMessageToStomp(message);
                }
            }

            function scrollTo(msgId) {
                $location.hash('message-' + msgId);
                $anchorScroll();
            }

            $scope.close = () => {
                $uibModalInstance.dismiss('cancel');
            };
        }]);
