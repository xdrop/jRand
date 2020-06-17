<a href="https://xdrop.github.io/jRand/#/"><img src="https://raw.githubusercontent.com/xdrop/jRand/c129c793f75345b8847c25d9ff1a5c721aa4a76a/docs/_media/jrand2.svg" width="168.5px"/></a>


[![Build Status](https://travis-ci.org/xdrop/jRand.svg?branch=master)](https://travis-ci.org/xdrop/jRand)
[![Documentation](https://img.shields.io/badge/documentation-0.2.5--alpha-green.svg)](https://xdrop.github.io/jRand)
[ ![Download](https://api.bintray.com/packages/xdrop/jrand/jrand/images/download.svg) ](https://bintray.com/xdrop/jrand/jRand/_latestVersion)
> Java library for *random data* generation.

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

[>>> Documentation](https://xdrop.github.io/jRand)

## Installation

You can install `JRand` via Maven Central:

**Maven**
```xml
<dependency>
  <groupId>me.xdrop</groupId>
  <artifactId>jrand</artifactId>
  <version>0.2.5-alpha</version>
</dependency>
```

**Gradle**:
```gradle
compile 'me.xdrop:jrand:0.2.5-alpha'
```



## Generators

#### Basic
* [Natural numbers](https://xdrop.github.io/jRand/#/?id=natural)
* [Boolean](https://xdrop.github.io/jRand/#/?id=bool)
* [Decimal](https://xdrop.github.io/jRand/#/?id=decimal)
* [Double](https://xdrop.github.io/jRand/#/?id=double-dbl)
* [Float](https://xdrop.github.io/jRand/#/?id=float-flt)
* [Character](https://xdrop.github.io/jRand/#/?id=character)
* [String](https://xdrop.github.io/jRand/#/?id=string)

#### Text
* [Syllable](https://xdrop.github.io/jRand/#/?id=syllable)
* [Word](https://xdrop.github.io/jRand/#/?id=word)
* [Sentence](https://xdrop.github.io/jRand/#/?id=sentence)
* [Paragraph](https://xdrop.github.io/jRand/#/?id=paragraph)
* [Lorem Ipsum](https://xdrop.github.io/jRand/#/?id=lorem)

#### Person
* [Firstname](https://xdrop.github.io/jRand/#/?id=firstname)
* [Lastname](https://xdrop.github.io/jRand/#/?id=lastname)
* [Name](https://xdrop.github.io/jRand/#/?id=name)
* [Age](https://xdrop.github.io/jRand/#/?id=age)
* [Birthday](https://xdrop.github.io/jRand/#/?id=birthday)
* [Gender](https://xdrop.github.io/jRand/#/?id=gender)
* [Person](https://xdrop.github.io/jRand/#/?id=person-object)
* [Prefix](https://xdrop.github.io/jRand/#/?id=prefix)

#### Location
* [Street](https://xdrop.github.io/jRand/#/?id=street)
* [Country](https://xdrop.github.io/jRand/#/?id=country)
* [City](https://xdrop.github.io/jRand/#/?id=city)
* [Postcode](https://xdrop.github.io/jRand/#/?id=postcode)
* [Altitude](https://xdrop.github.io/jRand/#/?id=altitude)
* [Depth](https://xdrop.github.io/jRand/#/?id=depth)
* [Longitude](https://xdrop.github.io/jRand/#/?id=longitude)
* [Latitude](https://xdrop.github.io/jRand/#/?id=latitude)
* [Coordinates](https://xdrop.github.io/jRand/#/?id=coordinates)
* [Geohash](https://xdrop.github.io/jRand/#/?id=coordinates)
* [Phone Number](https://xdrop.github.io/jRand/#/?id=phone)

#### Time
* [Millisecond](https://xdrop.github.io/jRand/#/?id=milli)
* [Second](https://xdrop.github.io/jRand/#/?id=second)
* [Minute](https://xdrop.github.io/jRand/#/?id=minute)
* [Hour](https://xdrop.github.io/jRand/#/?id=hour)

#### Money
* [Credit Card](https://xdrop.github.io/jRand/#/?id=card)
* [Credit Card Number](https://xdrop.github.io/jRand/#/?id=cardno)
* [Card Type](https://xdrop.github.io/jRand/#/?id=cardtype)
* [CVV](https://xdrop.github.io/jRand/#/?id=cvv)
* [Expiry Date](https://xdrop.github.io/jRand/#/?id=expiryDate)
* [Issue Date](https://xdrop.github.io/jRand/#/?id=issueDate)



## Documentation

[You can access documentation here](https://xdrop.github.io/jRand/)

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
