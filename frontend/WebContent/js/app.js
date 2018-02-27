/**
 * Angular JS module and config Spa
 */
var app=angular.module('app',['ngRoute','ngCookies'])
app.config(function($routeProvider) {
	$routeProvider
	.when('/register',{
		templateUrl:'views/registrationform.html',
		controller:'UserController'
	})
	.when('/login',{
		templateUrl:'views/login.html',
		controller:'UserController'
	})
	.when('/home',{
		templateUrl:'views/home.html',
		controller:'UserController'
	})
	
	.when('/addjob',{
		templateUrl:'views/jobform.html',
		controller:'JobCtrl'
	})
	
	.when('/edituserprofile',{
		templateUrl:'views/edituserprofile.html',
		controller:'UserController'
	})
	.otherwise({
		templateUrl:'views/home.html'
	})
})

app.run(function ($location,$rootScope,$cookieStore,UserService){
	if ($rootScope.loggedInUser==undefined)
		$rootScope.loggedInUser=$cookieStore.get('currentuser')
		
		$rootScope.logout=function(){
		UserService.logout().then(
		function(response){
			delete $rootScope.loggedInUser;
			$cookieStore.remove('currentuser')
			$rootScope.message="SuccessFull Logout"
				alert('Logout Successful. Please Login to Continue')
				$location.path="/login"
		},function(response){
			if(response.status=401)
				$location.path=('/login')
				
		})
	}
})