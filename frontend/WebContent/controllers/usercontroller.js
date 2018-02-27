/**
 * usercontroller
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
	if($rootScope.loggedInUser!=undefined){
	UserService.getUser().then(
		function(response){
			$scope.user=response.data
		},
		function(response){
			if(response.status==401)
				$location.path('/login')
			else
				$scope.error=response.data;
		})
		}
	$scope.updateUser=function(user){
		UserService.updateUser(user).then(function(response){
			
			alert('User Profile Updated Successfully!')
			$rootScope.loggedInUser=response.data
			$cookieStore.put('loggedInUser',response.data)
			$location.path('/home')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			else
				$scope.error=response.data
		})
	}
	//view  to controller
	$scope.updateUser=function(user){
		UserService.updateUser(user).then(function(response){
			alert('updated user details')
			$rootScope.loggedInUser=response.data
			$cookieStore.put('loggedInUser',response.data)
				$location.path('/home')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			else
				$scope.error=response.data
			
		})
	}
})