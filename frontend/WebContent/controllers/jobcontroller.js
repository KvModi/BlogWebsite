/**
 * 
 */
app.controller('JobCtrl',function($scope,$rootScope,$location,JobService, $routeParams){
	var id=$routeParams.
	
	$scope.addJob=function(job){
		JobService.addJob(job).then(
				function(response){
					console.log('jobcontroller : addition job success')
					alert('Job details posted')
					$location.path('/home')
				},function(response){
					$rootScope.error=response.data
					console.log('jobcontroller : addition job NOT successful')
					if(response.status==401)
						$location.path('/login')
						alert('something is not right . Try Again')
				})
	}
				
	JobService.getAllJobs().then(function(response){
		$scope.jobs=response.data
	},function(response){
		$rootScope.error=response.data
		if(response.status==401)
			$location.path('/login')
			alert('something is not right ')})
	
			if(id!=undefined){
		JobService.getJob(id).then(function(response){
		$scope.job=response.data
	},function(response){
		$rootScope.error=response.data
		console.log('jobcontroller :  job NOT found')
		if(response.status==401)
			$location.path('/login')
			alert('something is not right ')
	}) }
	
})
	