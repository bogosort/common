common
======
Some common auxiliaries:

* concurrent - some basic annotations etc.

maven repository
================

add following to your _pom.xml_:

	...
	<repositories>
		<repository>
			<id>bogosort-releases</id>
			<url>https://raw.github.com/bogosort/common/master/releases</url>
		</repository>
	</repositories>
	...
	<dependencies>
		<dependency>
			<groupId>example</groupId>
			<artifactId>common</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
	</dependencies>
	...