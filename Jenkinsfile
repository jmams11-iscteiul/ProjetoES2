def imagename = "projeto_es2"
def container = "helloworld"
node {
   echo 'Building Hello World Docker Image'
    
    stage('Git Checkout') {
        git 'https://github.com/jmams11-iscteiul/-projeto_es2_grupo11'
    }

    stage('Build jar file'){
        powershell "mvn package"
    }

    stage('Build Docker Image'){
        powershell "docker build -t  ${imagename} ."
    }
                
    // stage ('Runing Container to test built Docker Image'){
    //     powershell "docker run -dit --name ${container} -p 80:80 ${imagename}"
    // }

}
