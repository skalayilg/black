var jjCtrl;
function httpGet(theUrl)
{   var xmlHttp = new XMLHttpRequest();
		xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
		xmlHttp.send( null );
		return xmlHttp.responseText;
}

function httpPost(theUrl)
{   var xmlHttp = new XMLHttpRequest();
		xmlHttp.open( "POST", theUrl, false ); // false for synchronous request
		xmlHttp.send( null );
		return xmlHttp.responseText;
}

function getJson(url){
    return JSON.parse(httpGet(url));
}

(function(){
	var angApp = angular.module('jj',[]);
	
	angApp.directive('fileModel', ['$parse', function ($parse) {
	    return {
	        restrict: 'A',
	        link: function(scope, element, attrs) {
	            var model = $parse(attrs.fileModel);
	            var modelSetter = model.assign;
	            
	            element.bind('change', function(){
	                scope.$apply(function(){
	                    modelSetter(scope, element[0].files[0]);
	                });
	            });
	        }
	    };
	}]);
	
	angApp.directive('checkFileSize',function(){
	    return{
	    	restrict: 'A',
	        link: function(scope, elem) {
	            $(elem).bind('change', function() {
	            	
	            	if(this.files[0].size > 1048576){
	            		alert('File size:' + this.files[0].size +"\nPlease Choose a smaller file.");
	            		 scope.$apply(function(){
	 	                    scope.file='';
	     	            	$('input[type=file]').val('');
	 	                    
	 	                });
	            	}
	          });
	        }
	    }
	});

	
	
	angApp.service('publish', ['$http', function ($http) {
	    this.uploadFileToUrl = function(fd){
	        $http.post('publish', fd, {
	            transformRequest: angular.identity,
	            headers: {'Content-Type': undefined}
	        })
	        .then(function(resp){
	        	console.log("publish success");
	        	console.log(resp);
	        	jjCtrl.resetForm();
	        	
	        },
	        function(resp){
	        	console.log("publish failed");
	        	console.log(resp);
	        })
	    }
	}]);

	
    
	
    
	//jj controller
	angApp.controller('jjController', ['$scope', 'publish', function($scope,publish){
			jjCtrl = this;
            
            this.status;
            
            this.sjournals;
            this.pjournals;
            
            this.atopics ;
            this.stopics ;
            this.username = '';
            this.loginStatus='IN';
            
			this.selectedPanel="ALL";
			
			this.uploadFile = function(){
				//alert("upload file");
		        var file = $scope.file;
		        console.log('file is ' );
		        console.dir(file);
		        const fd = new FormData();
		        fd.append('file', $scope.file);
		        fd.append('fileTopic',$scope.fileTopic);
		        fd.append('fileTitle',$scope.fileTitle);
		        
		        publish.uploadFileToUrl(fd);
		        console.log("upload triggered");
		        this.loadPublished();
		    };
            
		    this.resetForm = function() {
		    	alert("Jounal submitted successfully");
		    	$('#publishForm')[0].reset();
	        	
	        	
		    }
        
            this.setPanel = function(p){
					this.selectedPanel=p;
					
				}
			
            this.checkStatus = function(){
                $.getJSON( "status", function( data ) {
                            
                            $scope.$apply(function(){
								jjCtrl.status=data;
							});
                            console.log("sjournals");
                            console.log(data);
                            console.log(data[0].status);
                            if(jjCtrl.status.status === 'loggedin'){
                                jjCtrl.load();
                            }
                            
                        });
            
            }
            
            this.checkStatusSync = function(){
                jjCtrl.status = getJson("status");
                
                console.log("status");
                console.log(jjCtrl.status);
                console.log(jjCtrl.status.status);
                if(jjCtrl.status.status === 'loggedin'){
                    jjCtrl.load();
                }
                $("#subcriptions").removeClass("hide");
                $("#sjournals").removeClass("hide");
                $("#pjournals").removeClass("hide");
                $("#publish").removeClass("hide");
                $("#loginModal").removeClass("hide").addClass("show");
            
            }
            
			
            this.load = function(){
                    console.log("loading data..");
                    
                    $.getJSON( "journal/subscribed", function( data ) {
                            
                            $scope.$apply(function(){
								jjCtrl.sjournals=data;
							});
                            console.log("sjournals");
                            console.log(data);
                        });
                    
                    
 
  
                    $.getJSON( "topic/subscribed", function( data ) {
                            
                            $scope.$apply(function(){
								jjCtrl.stopics=data;
							});
                            console.log("stopics");
                            console.log(jjCtrl.stopics);
                        });
                    
                    $.getJSON( "topic", function( data ) {
                        
                        $scope.$apply(function(){
							jjCtrl.atopics=data;
						});
                        console.log("atopics");
                        console.log(jjCtrl.atopics);
                    });
                        
                    this.loadPublished();
                }
            this.loadPublished = function (){
            	$.getJSON( "journal/published", function( data ) {
                    $scope.$apply(function(){
						jjCtrl.pjournals=data;
					});
                    console.log("pjournals");
                    console.log(data);
                });
            }

            this.checkStatusSync();
			this.print = function(){
                    console.log("stopics : " + JSON.stringify(jjCtrl1.stopics));
                }
			
				
				
			
		}]);
		
        

})();

