# IEM Portlet proxy

## To make things work:

* Package the war:

	mvn clean package  

* Go to http://www.jboss.org/gatein and download the latest version (mine runs on Tomcat)

* Copy the war to	GateIn-3.1.0-GA/webapps

* Change connector port to other then 8080 (for instance 8090) to avoid conflicts with IEM, if running on same host

* Run GateIn container:

	./GateIn-3.1.0-GA/bin/gatein.sh run  
	
* Open GateIn at

	http://localhost:8080  

* Go to Group -> Administration -> Application Registry and Import Applicationss  

* Create your page using portlet-proxy application.