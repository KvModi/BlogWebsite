/**
 * to make server side calls
 */
app.factory('UserService',function($http){
	var userService={}
	
	userService.registerUser=function(user){
		console.log(user)
		return $http.post("http://localhost:8081/middleware/registeruser",user)
	}
	return userService;
})