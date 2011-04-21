# IEM Portlet proxy

## To make things work:

* Package the war:

	mvn clean package  

* Copy the war to 

	GateIn-3.1.0-GA/bin  

* Run GateIn container:

	GateIn-3.1.0-GA/bin/gatein.sh run  
	
* Open GateIn at

	http://localhost:8080  

* Go to

	Group -> Administration -> Application Registry  

and 

	Import Applicationss  

* Create your page using portlet-proxy application.