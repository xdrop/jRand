# JRand

> A Java library to generate random data for all sorts of things.

JRand is heavily inspired by [ChanceJS](http://chancejs.com) and tries to bring together a lot of that functionality to Java.
In JRand there are `Generator`'s which are reusable components to generate all sorts of things. You can get
access to any generator you wish via the `JRand` facade class.

## Installation

You can install `JRand` via Maven Central:

**Maven**
```
<dependency>
    <groupId>me.xdrop</groupId>
    <artifactId>jrand</artifactId>
    <version>0.0.1</version>
</dependency>
```

**Gradle**:
```
compile 'me.xdrop:jrand:0.0.1'
```

## Generator

Every random generator will extend the `Generator` class in which there are a few methods to generate your random
results.

### `.gen()`

`.gen()` will generate a new random value every time based on your configured object.

### `.genMany(int n)`

`.genMany()` returns a list with `n` many random results

### `.genString()`

`genString()` returns a `String` version of your random value




## Basic

The basic generators are used to generate different kinds of common data including numbers and strings.

### bool

Returns a random boolean value.

**Methods:**
```java$ 
JRand.bool()
// Set the likelihood of generating true
JRand.bool().likelihood(float)
```

**Example:**
```java
BoolGenerator bool = JRand.bool();

bool.gen();
=> true

bool.likelihood(0.1).gen();
=> false
```

### character

Returns a random character (`char`).

**Methods:**

```java$
JRand.character()
// Set the pool of characters to return from
JRand.character().pool(String)
// Return only symbols
JRand.character().symbols()
// Return only alphanumeric
JRand.character().alpha()
// Return only digits
JRand.character().digit()
// Set the casing
JRand.character().casing(String)
```

**Examples:**

```java
CharacterGenerator character = JRand.character()

// Set the pool of characters to return from
character.pool("abcdef").gen()
=> 'b'

// Return only symbols
character.symbols().gen()
=> '$'

// Return only alphanumeric
character.alpha().gen()
=> 'w'

// Return only digits
character().digit().gen()
=> '2'

// Set the casing
character.casing("upper").gen()
=> 'Z'
```

?> Remember you can always use `.genString()` to get a `String` instead of `Character`


### decimal

Returns a random decimal number.

**Methods:**

```java$
JRand.decimal()
// Set max
JRand.decimal().max(double max)
// Set min
JRand.decimal().min(double min)
// Set range
JRand.decimal().range(double min, double max)
// Set number of digits
JRand.decimal().digits(int digits)
// Set whether to round up or not
JRand.decimal().roundUp(boolean roundUp)
```

**Examples:**

```java
DecimalGenerator decimal = JRand.decimal()

// Set the pool of characters to return from
decimal.gen()
=> 2.14

// Set the maximum to 3
decimal.max(3.0).gen()
=> 2.78

// Set the minimum to 16 
decimal.min(16.0).gen()
=> 32.8

// Return decimal between 0 and 1
decimal.range(0,1).gen()
=> 0.54

// Set the number of digits to 3
decimal.digits(3).gen()
=> 0.321

// Round up
decimal.roundUp().gen()
=> 45.3
```
