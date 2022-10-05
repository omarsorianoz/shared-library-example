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
