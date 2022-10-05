import jenkins.model.*
import hudson.security.*
import java.util.*
import com.michelin.cio.hudson.plugins.rolestrategy.*
import java.lang.reflect.*

def call(){                       
    pipeline{                      
       agent any                       
       stages {        
          stage("Promp Configuración de Ejecución") {
                steps {
                        script {
                                     
                                    env.RELEASE_SCOPE = input( id: 'userInput', message: 'Selecciona target', 
										parameters: [ [
										  $class: 'ChoiceParameterDefinition', 
										  choices: 'DEV\QA\PRD', 
										  description: 'Ambiente Target', 
										  name: 'target'] ])

                                    env.RAMA = input message: 'por favor inserta rama a Ejecutar',
                                         parameters: [string(defaultValue: 'master',
                                                      description: 'Rama a considerar en la ejecucion',
                                                      name: 'rama')]

                                    env.COMMAND = input message: 'por favor inserta comando a Ejecutar',
                                         parameters: [string(defaultValue: 'mvn test',
                                                      description: 'comando a ejecutar',
                                                      name: 'comando')]
                                }
                                echo "${env.RELEASE_SCOPE}"
                                echo "${env.RAMA}"
                                echo "${env.COMMAND}"
                            }
                    }
          stage('Descarga Codigo') {                       
              steps {                       
                  git branch: 'main', credentialsId: 'secret-git', url: 'https://github.com/omarsorianoz/karate-gentera.git'  
                  //git(
                  //      url: 'https://github.com/omarsorianoz/karate-gentera.git',
                  //      credentialsId: 'secret-git',
                  //      branch: 'main'
                  //  )
              }                      
          } 
          stage('Genera Imagen Docker') {                       
              steps {                       
                  sh 'ls'   
                
                if (!dockerApp) {
                    throw new Exception("Docker build image failed")
                }
              }                      
          } 
          stage('Test Stage') {                       
              steps {                       
                  script {                       
                      timeout(time: 5, unit: 'MINUTES') {                       
                          input 'Pipeline Executing!'                       
                      }                      
                      println "Pipeline ejecutado!"                       
                  }                      
              }                      
          }                      
       }                       
    }       
 }
