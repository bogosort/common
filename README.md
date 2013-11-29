java-common
===========

Some common auxiliaries:

*  concurrent
   *  ConcurrentHashSet: a [lock](http://en.wikipedia.org/wiki/Lock_%28computer_science%29) free implementation of java.util.HashSet
   *  ConcurrentIdentityMap: a concurrent implementation of the [IdentityMap](http://www.martinfowler.com/eaaCatalog/identityMap.html "IdentityMap") pattern
*  io
   *  LookAheadStream: especially useful for efficient, handwritten parser implementations
*  lang
   *  StringUtils: some join implementations, found in many programming languages (e.g. PHP)
*  util
   *  a generic Listener interface
   *  methods for casting listeners with [contravariant parameters](http://en.wikipedia.org/wiki/Covariance_and_contravariance_%28computer_science%29):
      1.  ListenerUtils#cast exploiting javas [Type Erasure](http://docs.oracle.com/javase/tutorial/java/generics/erasure.html "Type Erasure").
      2.  ListenerUtils#buildAdaptor creating an new wrapper/adapter object

maven repository
================

add following to your _pom.xml_:

	...
	<repositories>
		<repository>
			<id>bogosort-releases</id>
			<url>https://raw.github.com/bogosort/java-common/master/releases</url>
		</repository>
	</repositories>
	...
	<dependencies>
		<dependency>
			<groupId>de.schnittstabil</groupId>
			<artifactId>java-common</artifactId>
			<version>0.2.0</version>
		</dependency>
	</dependencies>
	...

