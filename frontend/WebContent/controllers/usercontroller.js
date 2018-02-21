/**
 * 
 */
app.controller('UserController',function($scope,$rootScope,$location,UserService){
	$scope.registerUser=function(user){
		UserService.registerUser(user).then(function(response){//success
			alert(' \n EMAIL ID: '+user.email+'\n PASSWORD: '+user.password+'\n PHONE NUMBER: '+user.phone)
			alert('Registration Successful. Please Login to Continue')
			$location.path('/login')
		},function(response){//409, 500 error
			$scope.error=response.data
		})
	}
	
	$scope.login=function(user){
		console.log('usercontroller to login'+user)
		UserService.login(user).then(function(response){
			$rootScope.loggedInUser=response.data
			console.log('success'+response.data)
			$location.path('/home')
		}, function(response){
			console.log('error')
			$scope.error=response.data
			$location.path('/login')
			
		})
	}
})