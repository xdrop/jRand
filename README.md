<a href="https://jrand.xdrop.me"><img src="https://jrand.xdrop.me/_media/jrand.svg" width="168.5px"/></a>


[![Build Status](https://travis-ci.org/xdrop/jRand.svg?branch=master)](https://travis-ci.org/xdrop/jRand)
[![Documentation](https://img.shields.io/badge/documentation-0.2.0--alpha-green.svg)](https://jrand.xdrop.me)
[ ![Download](https://api.bintray.com/packages/xdrop/jrand/jRand/images/download.svg) ](https://bintray.com/xdrop/jrand/jRand/_latestVersion)
> A Java library to generate random data for all sorts of things.

JRand is heavily inspired by [ChanceJS](http://chancejs.com) and tries to bring together a lot of that functionality to Java.
In JRand there are `Generator`'s which are reusable components to generate all sorts of things. You can get
access to any generator you wish via the `JRand` facade class.

## Installation

You can install `JRand` via Maven Central:

**Maven**
```xml
<dependency>
    <groupId>me.xdrop</groupId>
    <artifactId>jrand</artifactId>
    <version>0.2.0-alpha</version>
</dependency>
```

**Gradle**:
```gradle
compile 'me.xdrop:jrand:0.2.0-alpha'
```

## Examples
```java
JRand.string().pool("abcd").gen();
=> "b"

JRand.firstname().gen();
=> "Sean"
```

## Documentation

[You can access documentation here](https://jrand.xdrop.me/)

## License
Copyright 2018 - xdrop

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
