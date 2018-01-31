<a href="https://jrand.xdrop.me"><img src="https://jrand.xdrop.me/_media/jrand.svg" width="168.5px"/></a>


[![Build Status](https://travis-ci.org/xdrop/jRand.svg?branch=master)](https://travis-ci.org/xdrop/jRand)
[![Documentation](https://img.shields.io/badge/documentation-0.2.4--alpha-green.svg)](https://jrand.xdrop.me)
[ ![Download](https://api.bintray.com/packages/xdrop/jrand/jrand/images/download.svg) ](https://bintray.com/xdrop/jrand/jRand/_latestVersion)
> Probably the *best* Java library for random data generation.*

JRand is heavily inspired by [ChanceJS](http://chancejs.com) and tries to bring together a lot of that functionality to Java.
In JRand there are `Generator`'s which are reusable components to generate all sorts of things. You can get
access to any generator you wish via the `JRand` facade class.

**The library is still under development with *weekly* releases of new generators.**

## Examples
> **Builder** style random data generators with **a lot** of options
```java
JRand.string().pool("abcd").length(3).gen();
=> "bcc"

JRand.firstname().gender("m").gen();
=> "Sean"

// ...Read our documentation for a lot more examples!
```

[>>> Documentation](https://jrand.xdrop.me)

## Installation

You can install `JRand` via Maven Central:

**Maven**
```xml
<dependency>
    <groupId>me.xdrop</groupId>
    <artifactId>jrand</artifactId>
    <version>0.2.4-alpha</version>
</dependency>
```

**Gradle**:
```gradle
compile 'me.xdrop:jrand:0.2.4-alpha'
```



## Generators

#### Basic
* [Natural numbers](https://jrand.xdrop.me/#/?id=natural)
* [Boolean](https://jrand.xdrop.me/#/?id=bool)
* [Decimal](https://jrand.xdrop.me/#/?id=decimal)
* [Double](https://jrand.xdrop.me/#/?id=double-dbl)
* [Float](https://jrand.xdrop.me/#/?id=float-flt)
* [Character](https://jrand.xdrop.me/#/?id=character)
* [String](https://jrand.xdrop.me/#/?id=string)

#### Text
* [Syllable](https://jrand.xdrop.me/#/?id=syllable)
* [Word](https://jrand.xdrop.me/#/?id=word)
* [Sentence](https://jrand.xdrop.me/#/?id=sentence)
* [Paragraph](https://jrand.xdrop.me/#/?id=paragraph)
* [Lorem Ipsum](https://jrand.xdrop.me/#/?id=lorem)

#### Person
* [Firstname](https://jrand.xdrop.me/#/?id=firstname)
* [Lastname](https://jrand.xdrop.me/#/?id=lastname)
* [Name](https://jrand.xdrop.me/#/?id=name)
* [Age](https://jrand.xdrop.me/#/?id=age)
* [Birthday](https://jrand.xdrop.me/#/?id=birthday)
* [Gender](https://jrand.xdrop.me/#/?id=gender)
* [Person](https://jrand.xdrop.me/#/?id=person-object)
* [Prefix](https://jrand.xdrop.me/#/?id=prefix)

#### Location
* [Street](https://jrand.xdrop.me/#/?id=street)
* [Country](https://jrand.xdrop.me/#/?id=country)
* [City](https://jrand.xdrop.me/#/?id=city)
* [Postcode](https://jrand.xdrop.me/#/?id=postcode)
* [Altitude](https://jrand.xdrop.me/#/?id=altitude)


#### Money
* [Credit Card](https://jrand.xdrop.me/#/?id=card)
* [Credit Card Number](https://jrand.xdrop.me/#/?id=cardno)
* [Card Type](https://jrand.xdrop.me/#/?id=cardtype)
* [CVV](https://jrand.xdrop.me/#/?id=cvv)
* [Expiry Date](https://jrand.xdrop.me/#/?id=expiryDate)
* [Issue Date](https://jrand.xdrop.me/#/?id=issueDate)



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
