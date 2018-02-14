/**
 * Angular JS module and config Spa
 */
var app=angular.module('app',['ngRoute'])
app.config(function($routeProvider) {
	$routeProvider
	.when('/register',{
		templateUrl:'views/registrationform.html',
		controller:'UserController'
	})
	.otherwise({
		templateUrl:'views/index.html'
	})
})