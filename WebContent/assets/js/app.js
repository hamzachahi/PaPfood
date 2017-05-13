var app = angular.module("app", []);

app.controller("formController", function($scope) {

	$scope.user = {};

	$scope.showData = function(user) {

		console.log(user);

	};

});