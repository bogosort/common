java-common Developer Readme
============================

Set up The Dev Environment
--------------------------

### GitHub

1. [GitHub: Generating SSH Keys](https://help.github.com/articles/generating-ssh-keys)
2. `git clone git@github.com:bogosort/java-common.git`

### Eclipse

#### Set up Eclipse

1. in Eclipse go to __Window > Preferences > General > Network Connections > SSH2__
2. in the __Key Management__ tab click __Load Existing Key__
3. select your __id\_rsa__ file (not your _id\_rsa.pub_ file)
4. enter your password
5. click __OK__

#### Import Project

1. in Eclipse go to __File > Import... > Git > Projects from Git__
2. Select Repository Source: __Clone URI__
3. Source Git Repository:
	* URI: `git@github.com:bogosort/java-common.git`
	* Protocol: `ssh`
	* __User: `git`__
	* __Password: (left blank)__

See also [Stackoverflow: “Auth Failed” error with EGit and GitHub](http://stackoverflow.com/questions/3601805/auth-failed-error-with-egit-and-github).

Maven
-----

	# clean project
	mvn clean
	
	# run the tests
	mvn test
	
	# build a new release in the releases folder
	mvn deploy

