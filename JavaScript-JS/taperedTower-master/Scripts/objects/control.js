/// <reference path="../../typings/tsd.d.ts"/>
var objects;
(function (objects) {
    // CONTROL CLASS ++++++++++++++++++++++++++++++++++++++++++
    var Control = (function () {
        // CONSTRUCTOR ++++++++++++++++++++++++++++++++++++++++
        function Control(numberOne, numberTwo, numberThree, numberFour, numberFive) {
            this.rotationSpeed = numberOne;
            this.rotationSpeedSecond = numberTwo;
            this.rotationSpeedThird = numberThree;
            this.rotationSpeedFourth = numberFour;
            this.rotationSpeedFifth = numberFive;
        }
        Control.prototype.add = function (numbers, stringName) {
        };
        return Control;
    }());
    objects.Control = Control;
})(objects || (objects = {}));
//# sourceMappingURL=control.js.map