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
			stage("enviando Email") {
						steps {
							echo 'demo'
						}
			}
		}
	}
}
