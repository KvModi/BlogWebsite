/**
 * 
 */
app.factory('JobService',function($http){
	var jobService={}
	
	jobService.addJob=function(job){
		return $http.post("http://localhost:8081/middleware/addjob",job)
	}
	
	jobService.getAllJObs=function(){
		return $http.get("http://localhost:8081/middleware/alljobs"); 
	}
	return jobService;
})