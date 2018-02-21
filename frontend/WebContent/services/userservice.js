/**
 * to make server side calls
 */
app.factory('UserService',function($http){
	var userService={}
	
	userService.registerUser=function(user){
		console.log(user)
		return $http.post("http://localhost:8081/middleware/registeruser",user)
	}
	
	userService.login=function(user){
		console.log('user service to login'+user)
		return $http.post("http://localhost:8081/middleware/login",user)
	}
	userService.logout=function(){
		return $http.put("http://localhost:8081/middleware/logout")
	}
	return userService;
})