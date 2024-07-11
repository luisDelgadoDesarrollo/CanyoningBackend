def version;
def isNewApp = false;

pipeline {
    agent any

    parameters {
        string description: 'The project where deploy the app in Openshift', name: 'project', trim: true;
        string description: 'The name of the app of Openshift where it deploys', name: 'appName', trim: true;
        string description: 'Branch of git repository from checkout', name: 'branch', trim: true;
        string description: 'The git repository name', name: 'repository', trim: true;
        choice choices: ['', 'DES', 'PRE', 'PRO'], description: 'The environment where deploy the app', name: 'environment';
        string description: 'Image of the docker container', name: 'image', trim: true;
        booleanParam description: 'Check if you want to delete all resources of application before starting script', name: 'deleteFirst';
    }

    triggers {
      BitbucketWebhookTriggerImpl(pullRequestTrigger: false, refTrigger: true);
    }

    options {
      buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '7', numToKeepStr: '5');
    }

    stages {
        stage('parameters') {
            steps {
                script {
                    if (!env.branch && env.GIT_BRANCH) {branch = env.GIT_BRANCH} else {branch = 'master'}
                    if (!env.environment) {
                        environment = branch == 'master' ? 'PRE' : branch == 'develop' ? 'DES' : '';
                    }
                    if (!env.project) project = 'kio' + (environment ? "-${environment}" : '')
                    if (!env.repository) repository = 'java_architecture'
                    if (!env.appName) appName = "${repository}-${branch}"
                    if (!env.image) image = 'amazoncorretto:21'
                    if (!env.deleteFirst) deleteFirst = false;
                }
            }
        }
        stage('check') {
            steps {
                script {
                    openshift.withCluster() {
                        openshift.withProject("${project}") {
                            println "[PIPELINE] Printing actual deployment of application...";
                            def allAppSelector = openshift.selector("all", [appName: "${appName}"]);
                            allAppSelector.describe();
                            if (deleteFirst == true || deleteFirst == 'true') {
                                echo "[PIPELINE] Deleting all ${appName} resources";
                                allAppSelector.delete();
                            }
                        }
                    }
                }
            }
        }
        /*stage('git') {
            steps {
                script {
                    println "[PIPELINE] Checking out source code in git repository...";
                    bbs_checkout branches: [[name: "${branch}"]], credentialsId: '4a3e9e4a-a088-4b0d-81e5-95b65a2627b9', id: '09282004-d3f1-4167-9d9d-81236f471868', projectName: "${project}", repositoryName: "${repository}", serverId: '419308ed-1ed8-4d58-9fdb-e25db1ce8c96'
                }
            }
        }*/
        stage('maven') {
            steps {
                script {
                    def pom = readMavenPom file: 'pom.xml'
                    version = pom.version
                    println "[PIPELINE] The version of pom.xml is ${version}...";
                }
                withMaven(
                        maven: 'Maven 3.9.4',
                        jdk: 'OpenJDK 21',
                        traceability: true
                    ) {
                        println "[MAVEN] Compiling source code...";
                        sh "mvn clean package -X"
                    }
            }
        }
        stage('deploy') {
            steps {
                script {
                    openshift.withCluster() {
                        openshift.withProject("${project}") {

                            println "[PIPELINE] Building and deploying image in Openshift...";
                            //If deployment is not null, app was already created
                            def deployment = openshift.selector('deployment', "${appName}");
                            println deployment;
                            if (deployment == null || !deployment.exists()) {
                                println "[DEPLOY] Creating app from zero (BuildConfig, Deployment, Service, Route, Health checks...)";
                                isNewApp = true;
                            } else {
                                println "[DEPLOY] App already is created, skipping creation of resources...";
                            }

                            if (isNewApp == true) {
                                println "[DEPLOY] Creating new build with Docker strategy...";
                                openshift.newBuild("--strategy=docker", "--binary", "--image=${image}", "--name='${appName}'", "--labels='appName=${appName}'");
                            }

                            println "[DEPLOY] Starting build of image...";
                            openshift.startBuild("${appName}", "--from-dir='${pwd()}'", "--follow", "--wait");
                            openshift.tag("${project}/${appName}:latest", "${project}/${appName}:${version}");

                            def service;
                            if (isNewApp == true) {
                                println "[DEPLOY] Creating deployment and service...";
                                service = openshift.newApp("${appName}", "--labels='appName=${appName}'").narrow('service');
                                sleep 5;
                            }

                            if (isNewApp == true) {
                                println "[DEPLOY] Exposing service in a route...";
                                service.expose("--name='${appName}'", "--labels='appName=${appName}'");
                                openshift.patch("route/${appName}", '\'{"spec":{"tls": {"termination": "edge", "insecureEdgeTerminationPolicy": "Redirect"}}}\'');
                            }

                            def route = openshift.selector('route', "${appName}").object();
                            println "[DEPLOY] App is exposed on URL: ${route.spec.host}"
                        }
                    }
                }
            }
        }
        stage('health check') {
            steps {
                script {
                    openshift.withCluster() {
                        openshift.withProject("${project}") {
                            if (isNewApp == true) {
                                println "[PIPELINE] Setting up health checks of deployment pods...";
                                deployment = openshift.selector('deployment', "${appName}").object();
                                def healthPatch = """{
                                    "spec": {
                                        "template": {
                                            "spec": {
                                                "containers": [{
                                                    "name": "${appName}",
                                                    "readinessProbe": {
                                                        "httpGet": {
                                                            "path": "/actuator/health/readiness",
                                                            "port": 8080,
                                                            "scheme": "HTTP"
                                                        },
                                                        "timeoutSeconds": 1,
                                                        "periodSeconds": 10,
                                                        "successThreshold": 1,
                                                        "failureThreshold": 3
                                                    },
                                                    "livenessProbe": {
                                                        "httpGet": {
                                                            "path": "/actuator/health/liveness",
                                                            "port": 8080,
                                                            "scheme": "HTTP"
                                                        },
                                                        "timeoutSeconds": 1,
                                                        "periodSeconds": 10,
                                                        "successThreshold": 1,
                                                        "failureThreshold": 3
                                                    },
                                                    "startupProbe": {
                                                        "httpGet": {
                                                            "path": "/actuator/health",
                                                            "port": 8080,
                                                            "scheme": "HTTP"
                                                        },
                                                        "timeoutSeconds": 1,
                                                        "periodSeconds": 10,
                                                        "successThreshold": 1,
                                                        "failureThreshold": 30
                                                    }
                                                }]
                                            }
                                        }
                                    }
                                }""";
                                openshift.patch("deployment/${appName}", "'${healthPatch}'");
                            }
                            sleep 5;
                            println "[PIPELINE] Checking deployment pods are in ready state...";
                            openshift.raw("wait", "--for='condition=Ready'", "pod", "--selector='deployment=${appName}'", "--timeout=60s");
                            println "[PIPELINE] App deployed successfully!";
                        }
                    }
                }
            }
        }
    }
}
