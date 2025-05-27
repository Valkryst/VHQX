This is a fork of the original [hqx-java](https://github.com/Arcnor/hqx-java) project, with no changes made to the
original code. The purpose of this repository is to make the code available VIA Maven, Gradle, and SBT.

I have chosen the GPL-3.0 license, provided by _hqx-java_, as it appears to be the more restrictive of the two licenses
found in that repository and thus _may_ be the intended license for it.

## Table of Contents

* [Introductions](https://github.com/Valkryst/VHQX#introductions) 
  * [Original Introduction](https://github.com/Valkryst/VHQX#original-introduction)
  * [hqx-java Introduction](https://github.com/Valkryst/VHQX#hqx-java-introduction)
* [Installation](https://github.com/Valkryst/VHQX#installation)
  * [Gradle](https://github.com/Valkryst/VHQX#-gradle)
  * [Maven](https://github.com/Valkryst/VHQX#-maven)
  * [sbt](https://github.com/Valkryst/VHQX#-scala-sbt)
* [Example](https://github.com/Valkryst/VHQX#example)

## Introductions

### Original Introduction

__hqx__ ("hq" stands for "high quality" and "x" stands for magnification) is one of the pixel art scaling algorithms
developed by Maxim Stepin, used in emulators such as Nestopia, bsnes, ZSNES, Snes9x, FCE Ultra and many more. There are
3 hqx filters: hq2x, hq3x, and hq4x, which magnify by factor of 2, 3, and 4 respectively.

### hqx-java Introduction
__hqx-java__ is a Java port of the excellent [hqxSharp](http://code.google.com/p/hqx-sharp) C# port, which itself is a port of the original
[hqx](http://code.google.com/p/hqx) C project

Like the hqxSharp project, the focus of this code is asset creation and usage in tools, so no optimizations were done,
just an almost-direct copy of the code.

For examples, go to Maxim Stepin hqx pages:
* [hq2x](https://web.archive.org/web/20071211205031/http://www.hiend3d.com/hq2x.html)
* [hq3x](https://web.archive.org/web/20071214012226/http://www.hiend3d.com/hq3x.html)
* [hq4x](https://web.archive.org/web/20071202070252/http://www.hiend3d.com/hq4x.html)

## Installation

VHQX is hosted on the [JitPack package repository](https://jitpack.io/#Valkryst/VHQX)
which supports Gradle, Maven, and sbt.

### ![Gradle](https://i.imgur.com/qtc6bXq.png?1) Gradle

Add JitPack to your `build.gradle` at the end of repositories.

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add VHQX as a dependency.

```
dependencies {
	implementation 'com.github.Valkryst:VHQX:2025.5.27-2'
}
```

### ![Maven](https://i.imgur.com/2TZzobp.png?1) Maven

Add JitPack as a repository.

``` xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add VHQX as a dependency.

```xml
<dependency>
    <groupId>com.github.Valkryst</groupId>
    <artifactId>VHQX</artifactId>
    <version>2025.5.27-2</version>
</dependency>
```

### ![Scala SBT](https://i.imgur.com/Nqv3mVd.png?1) Scala SBT

Add JitPack as a resolver.

```
resolvers += "jitpack" at "https://jitpack.io"
```

Add VHQX as a dependency.

```
libraryDependencies += "com.github.Valkryst" % "VHQX" % "2025.5.27-2"
```

## Example

Though you _can_ read through the original code and use it as you wish, I have added the `VHQX` class with a 
`scale(BufferedImage, int)` method which is capable of applying the 2x, 3x, and 4x HQX scaling algorithms to a given
image.

```java
public class Example {
    public static void main(final String[] args) {
        final BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        
        final VHQX scaler = new VHQX();
        final BufferedImage scaledImage2x = scaler.scale(image, 2);
        final BufferedImage scaledImage3x = scaler.scale(image, 3);
        final BufferedImage scaledImage4x = scaler.scale(image, 4);
    }
}
```