import jenkins.model.*
import hudson.security.*
import java.util.*
import com.michelin.cio.hudson.plugins.rolestrategy.*
import java.lang.reflect.*

def call(){                       
    pipeline{                      
       agent any                       
       stages {        
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
