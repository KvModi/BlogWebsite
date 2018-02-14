/**
 * 
 */
app.controller('UserController',function($scope,$location,UserService){
	$scope.registerUser=function(user){
		UserService.registerUser(user).then(function(response){//success
			alert('Registeration Successful. Please Login to Continue')
			$location.path('/home')
		},function(response){//409, 500 error
			$scope.error=response.data
		})
	}
})