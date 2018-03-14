/**
 * usercontroller
 */
app.controller('UserController',function($scope,$rootScope,$location,UserService,$cookieStore){
	$scope.registerUser=function(user){
		console.log('usercontroller : registerUser')
		UserService.registerUser(user).then(
				function(response){//success
			alert(' \n EMAIL ID: '+user.email+'\n PASSWORD: '+user.password)
			alert('Registration Successful. Please Login to Continue')
			$location.path('/login')
		},function(response){//409, 500 error
			$scope.error=response.data
		})
	}
	
	$scope.login=function(user){
		console.log('usercontroller : login')
		UserService.login(user).then(function(response){
			console.log('	$rootScope.loggedInUser=response.data')
			$rootScope.loggedInUser=response.data
			console.log('$cookieStore.put(currentuser,response.data)')
			$cookieStore.put('currentuser',response.data)
			
			console.log('success'+response.data)
			$location.path('/home')
			console.log('$location.path(/home)')
		}, function(response){
			console.log('error')
			$scope.error=response.data
			$location.path('/home')
			
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