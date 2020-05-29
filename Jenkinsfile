def imagename = "projeto_es2"
def container = "helloworld"
node {
   echo 'Building Hello World Docker Image'
    
    stage('Git Checkout') {
        git 'https://github.com/jmams11-iscteiul/ProjetoES2'
    }

    stage('Build jar file'){
        powershell "mvn package"
    }

    stage('Removing previous image'){
        powershell "docker rmi ${imagename}"
    }

    stage('Build Docker Image'){
        powershell "docker build -t  ${imagename} ."
    }
                
    // stage ('Runing Container to test built Docker Image'){
    //     powershell "docker run -dit --name ${container} -p 80:80 ${imagename}"
    // }

}
