# commitCounter
API that returns total number of commits per user given a time range.

###info
Written in Java, using Springboot

###environment setup
1) Ensure maven (https://maven.apache.org/) is installed on deployment server/local machine
2) From a terminal/command line, navigate to project root (you should see pom.xml file)
3) Execute 'mvn clean install'

###deployment
From the project root, execute `./mvnw spring-boot:run`.

###how to use api
* <b>url:</b> http://{host_name}:8082/commits (eg. http://localhost:8082/commits)
* <b>takes 3 parameters: </b> String githubRepoUrl, String timeUnit, String timeAmount
* <b>expected request format:</b> http://{host_name}:8082/commits?githubRepoUrl="https://github.com/facebook/react/"&timeUnit="HOUR"&timeAmount="24"