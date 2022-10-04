import jenkins.model.*
import hudson.security.*
import java.util.*
import com.michelin.cio.hudson.plugins.rolestrategy.*
import java.lang.reflect.*

def call() {

	// A Declarative Pipeline is defined within a 'pipeline' block.
	pipeline {

		// agent defines where the pipeline will run.
		agent {
			// This also could have been 'agent any' - that has the same meaning.
			label ""
			// Other possible built-in agent types are 'agent none', for not running the
			// top-level on any agent (which results in you needing to specify agents on
			// each stage and do explicit checkouts of scm in those stages), 'docker',
			// and 'dockerfile'.
		}

		// The tools directive allows you to automatically install tools configured in
		// Jenkins - note that it doesn't work inside Docker containers currently.
		tools {
			// Here we have pairs of tool symbols (not all tools have symbols, so if you
			// try to use one from a plugin you've got installed and get an error and the
			// tool isn't listed in the possible values, open a JIRA against that tool!)
			// and installations configured in your Jenkins master's tools configuration.
			//jdk "jdk8"
			// Uh-oh, this is going to cause a validation issue! There's no configured
			// maven tool named "mvn3.3.8"!
		}

		environment {
			// Environment variable identifiers need to be both valid bash variable
			// identifiers and valid Groovy variable identifiers. If you use an invalid
			// identifier, you'll get an error at validation time.
			// Right now, you can't do more complicated Groovy expressions or nesting of
			// other env vars in environment variable values, but that will be possible
			// when https://issues.jenkins-ci.org/browse/JENKINS-41748 is merged and
			// released.
		}
		stages{
			stage('Descargar y compilar') {
				steps{

					echo "Iniciando JOB de Karate"
					//sh 'env > env.txt'
					//script{
					//	if (gitlabActionType == "PUSH" && (gitlabBranch == "master" || gitlabBranch == "develop")) {
					//		echo 'La tarea no se ejecuta si el push viene de "master" o "develop"'
					//		currentBuild.result = 'ABORTED'
					//		return
					//	}
					}
					deleteDir()

					checkout([
						$class: 'GitSCM',
						branches: [[name: "${gitlabBranch}"]],
						extensions: scm.extensions + [[$class: 'CleanCheckout']],
						userRemoteConfigs: scm.userRemoteConfigs
					])

					script{
						def pom = readMavenPom file: 'pom.xml'
					}
					echo 'Construyendo codigo..'
					//sh 'mvn -Dmaven.test.failure.ignore=true clean package'

				}
			}

			stage('resultados') {
				steps {
					// Note that parallel can only be used as the only step for a stage.
					// Also, if you want to have your parallel branches run on different
					// nodes, you'll need control that manually with "node('some-label') {"
					// blocks inside the parallel branches, and per-stage post won't be able
					// to see anything from the parallel workspaces.
					// This'll be improved by https://issues.jenkins-ci.org/browse/JENKINS-41334,
					// which adds Declarative-specific syntax for parallel stage execution.
					parallel("Unit Test": {
						echo 'Ejecutando resultados....'
					}

			}
			stage("enviando Email") {
				steps {
					echo 'Revisando calificacion'
				}
			}
			
		}

		post {
			// Always runs. And it runs before any of the other post conditions.
			always {
				// Let's wipe out the workspace before we finish!
				deleteDir()
				echo "Resultado ejecucion ${currentBuild.result}"
				echo "always" }

			success { echo "Success" }
			}
		}

		// The options directive is for configuration that applies to the whole job.
		options {
			// For example, we'd like to make sure we only keep 10 builds at a time, so
			// we don't fill up our storage!
			buildDiscarder(logRotator(numToKeepStr:'10'))

			// And we'd really like to be sure that this build doesn't hang forever, so
			// let's time it out after an hour.
			timeout(time: 60, unit: 'MINUTES')
		}
	}
}
