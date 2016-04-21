var jjCtrl;
function httpGet(theUrl)
{   var xmlHttp = new XMLHttpRequest();
		xmlHttp.open( "GET", theUrl, false ); // false for synchronous request
		xmlHttp.send( null );
		return xmlHttp.responseText;
}

function getJson(url){
    return JSON.parse(httpGet(url));
}

(function(){
	var angApp = angular.module('jj',[]);
	

    
	
    
	//jj controller
	angApp.controller('jjController',function($scope){
			jjCtrl = this;
            
            this.status;
            
            this.sjournals;
            this.pjournals;
            
            this.atopics ;
            this.stopics ;
            this.username = '';
            this.loginStatus='IN';
            
			this.selectedPanel="ALL";
			
            
            
            this.test = function(){
                    alert("test func called");
                }
            this.setPanel = function(p){
					//alert ("selected filter : " + ftr);
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
                            if(jjCtrl.status.status == 'loggedin'){
                                jjCtrl.load();
                            }
                            
                        });
            
            }
            
            this.checkStatusSync = function(){
                jjCtrl.status = getJson("status");
                
                console.log("status");
                console.log(jjCtrl.status);
                console.log(jjCtrl.status.status);
                if(jjCtrl.status.status == 'loggedin'){
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
                           //console.log(data[0].file);
                        });
                    
                    $.getJSON( "journal/published", function( data ) {
                            $scope.$apply(function(){
								jjCtrl.pjournals=data;
							});
                            console.log("pjournals");
                            console.log(data);
                           //console.log(data[0].file);
                        });
 
  
                    $.getJSON( "topic/subscribed", function( data ) {
                            
                            $scope.$apply(function(){
								jjCtrl.stopics=data;
							});
                            console.log("stopics");
                            console.log(jjCtrl.stopics);
                            //console.log(jjCtrl.stopics[0].name);
                        });
                        
                    
                }

            this.checkStatusSync();
			this.print = function(){
                    console.log("stopics : " + JSON.stringify(jjCtrl1.stopics));
                }
			
				
				
			
		});
		
        

})();

