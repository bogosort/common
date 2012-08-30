common
======
Some common auxiliaries:

*  concurrent
   *  ConcurrentHashSet: a lock free implementation of java.util.HashSet
   *  ConcurrentIdentityMap: a concurrent implementation of the [IdentityMap](http://www.martinfowler.com/eaaCatalog/identityMap.html "IdentityMap") pattern
*  io
   *  InputStream implementations
   *  LookAheadStream interface and default implementation
*  lang
   *  StringUtils: some join implementations, found in many programming languages (e.g. PHP)
*  util
   *  a generic Listener interface
   *  methods - at ListenerUtils - for casting listeners with contravariant parameters:
      1.  ListenerUtils#cast exploiting javas [Type Erasure](http://docs.oracle.com/javase/tutorial/java/generics/erasure.html "Type Erasure").
      2.  ListenerUtils#buildAdaptor creating an new wrapper/adapter object

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
