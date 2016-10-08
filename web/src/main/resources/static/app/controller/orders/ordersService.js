angular
    .module('delivery')
    .service('orderService', function () {
    var _id = null;
    return {
        setId: function (id) {
            _id = id;
        },
        getId: function () {
            return _id;
        }
    }
});