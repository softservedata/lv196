angular
    .module('delivery')
    .factory('$chat', ['$http', '$q', '$uibModal', '$rootScope', ($http, $q, $uibModal, $rootScope) => {
        const opened = {
            chatId: null,
            subscriptions: [],
            modal: null
        };
        const isOpened = () => opened.modal !== null;

        let wsEndpoint = sessionStorage.getItem('wsEndpoint');
        const stompClient = () => $rootScope.stomp === null ?
            Stomp.client(wsEndpoint + '/chat') : $rootScope.stomp;

        const closeChat = () => {
            opened.chatId = null;
            if (opened.subscriptions.length > 0) {
                opened.subscriptions.forEach(subs => subs.unsubscribe());
                opened.subscriptions = [];
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
                    templateUrl: '/app/chat/views/chat.html',
                    controller: 'chatController',
                    resolve: {
                        chatId: () => chatId
                    }
                });
                opened.modal.result.finally(() => {
                    closeChat();
                    $rootScope.refreshMessageNotifications();
                    deferred.resolve(chatId);
                });
            }

            return deferred.promise;
        };

        return {
            history: (chatId, page, size) => $http.get('chat/' + chatId + '/' + page + '/' + size),
            create: chatId => $http.post('chat/' + chatId, {}),
            isOpened: isOpened,
            openedChatId: () => opened.chatId,
            open: open,
            subscribe: (chatId, onMessage, onMessageSeen) => {
                const deferred = $q.defer();
                if (opened.subscriptions.length > 0) {
                    deferred.reject('Chat is already created. Please, close the old one to create new.');
                } else {
                    opened.subscriptions = [
                        stompClient().subscribe('/topic/chat/' + chatId, onMessage),
                        stompClient().subscribe('/topic/chat-seen/' + chatId, onMessageSeen)
                    ];
                    deferred.resolve(opened.subscriptions);
                }
                return deferred.promise;
            },
            sendMessage: (chatId, message) => stompClient().send('/app/chat/' + chatId, {}, JSON.stringify(message)),
            readMessage: (chatId, messageIds) => stompClient().send('/app/chat-seen/' + chatId, {}, JSON.stringify(messageIds)),
            stomp: stompClient
        };
    }])
    .controller('chatController', ['$scope', '$uibModalInstance', '$chat', '$location', '$anchorScroll', '$timeout', 'chatId',
        function ($scope, $uibModalInstance, $chat, $location, $anchorScroll, $timeout, chatId) {
            let subscriptions = [];
            let userEmail = sessionStorage.getItem('email');
            $scope.messages = [];
            $scope.page = 0;

            $chat.history(chatId, $scope.page, 7).then(response => {
                connect(() => processChatHistoryResponse(response.data, true))
            });

            $scope.classByMsg = msg => {
                const classes = msg.authorEmail == userEmail ? 'right' : 'left';
                return msg.seen ? classes : (classes + ' unseen');
            };

            $scope.sendMessage = () => {
                const msg = {text: $scope.inputText};
                if (msg.text != null) {
                    if (subscriptions.length == 0) {
                        $chat.create(chatId).then(() => {
                            connect(() => sendMessageToStomp(msg));
                        });
                    } else {
                        sendMessageToStomp(msg);
                    }
                }
            };

            $scope.loadMore = () => {
                $chat
                    .history(chatId, $scope.page, 7)
                    .then(response => processChatHistoryResponse(response.data, false));
            };

            function processChatHistoryResponse(data, checkMsgSeen) {
                $scope.page += 1;
                $scope.messages = data.messages.concat($scope.messages);
                $scope.havingMore = data.havingMore;

                if (checkMsgSeen) {
                    let msgs = $scope.messages
                        .filter(m => !m.seen && m.authorEmail != userEmail)
                        .map(m => m.id);
                    $chat.readMessage(chatId, msgs);
                }

                const scrollInx = $scope.page == 1 ? $scope.messages.length - 1 : 0;
                $timeout(() => scrollTo($scope.messages[scrollInx].id), 0, false);
            }

            function sendMessageToStomp(message) {
                $chat.sendMessage(chatId, message);
                $scope.inputText = undefined;
            }

            function receiveMessage(message) {
                $scope.$apply(() => {
                    $scope.messages.push(message);
                    if (message.authorEmail != userEmail) {
                        $chat.readMessage(chatId, [message.id]);
                    }
                });
                scrollTo(message.id)
            }

            function messagesSeen(ids) {
                if (Array.isArray(ids) && ids.length > 0) {
                    $scope.messages
                        .filter(m => ids.indexOf(m.id) > -1 && !m.seen)
                        .forEach(m => m.seen = true);
                    $scope.$apply();
                }
            }

            function connect(callback) {
                $chat.subscribe(
                    chatId,
                    response => {
                        receiveMessage(JSON.parse(response.body))
                    },
                    response => {
                        messagesSeen(JSON.parse(response.body))
                    }
                ).then(subs => {
                    subscriptions = subs;
                    callback();
                });
            }

            function scrollTo(msgId) {
                $location.hash('message-' + msgId);
                $anchorScroll();
            }

            $scope.close = () => {
                $uibModalInstance.dismiss('cancel');
            };
        }]);
