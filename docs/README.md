# JRand
![jrand](jrand-logo.png)
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

### `.gen();`

`.gen();` will generate a new random value every time based on your configured object.

### `.genMany(int n)`

`.genMany()` returns a list with `n` many random results

### `.genString()`

`genString()` returns a `String` version of your random value




## Basic

The basic generators are used to generate different kinds of common data including numbers and strings.

### bool

Returns a random boolean value.

**Methods**
```java$ 
JRand.bool()
// Set the likelihood of generating true
JRand.bool().likelihood(float)
```

**Example**
```java 
BoolGenerator bool = JRand.bool();
```

You can generate a uniform random boolean using:

```java 
bool.gen();
=> true
```

Alternatively, you can set the likelihood of returning `true` using:

```java 
bool.likelihood(0.1).gen();
=> false
```

### character

Returns a random character (`char`).

**Methods**

```java$
JRand.character()
// Set the pool of characters to return from
JRand.character().pool(String charPool)
// Return only symbols
JRand.character().symbols()
// Return only alphanumeric
JRand.character().alpha()
// Return only digits
JRand.character().digit()
// Set the casing
JRand.character().casing(String casing)
```

**Examples**

```java 
CharacterGenerator character = JRand.character()
```

By default this generates a character which can **include uppercase**
as well as **lowercase letters**, **symbols**, and **numbers**.
```java 
character.gen()
=> 'A'
```

You can provide a string containing a character pool from which random characters are
to be selected from:
```java 
character.pool("abcdef").gen();
=> 'b'
```

You can turn on the `symbols` flag to return only symbols, the `alpha` flag to return only
alphabet characters or the `digit` to return only numerical digits:
```java 
character.symbols().gen();
=> '$'

character.alpha().gen();
=> 'w'

character().digit().gen();
=> '2'
```

The `casing` flag will decide whether the returned alphabet characters are uppercase
```java 
character.casing("upper").gen();
=> 'Z'
```

?> Remember you can always use `.genString()` to get a `String` instead of `Character`


### decimal

Returns a random decimal number.

!> This is designed to return `String` or `BigDecimal`, 
if you require a double or float there are other generators for them.

**Methods**

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

**Examples**

```java 
DecimalGenerator decimal = JRand.decimal()
```

To generate a random decimal.  `0` to `100` with arbitrary number of digits.
```java 
decimal.gen(); // Return as String
decimal.genAsDecimal() // Return BigDecimal
=> 2.14
```

Set the maximum number to generate
```java 
decimal.max(3.0).gen();
=> 2.78
``````

Set the minimum number to generate
```java 
decimal.min(16.0).gen();
=> 32.8
```

Set a range between which to generate
```java 
decimal.range(0,1).gen();
=> 0.54
```

Set the number of digits. By default they are rounded up.
```java 
decimal.digits(3).gen();
=> 0.321
```

Alternatively, you can disable/re-enable rounding up
```java 
decimal.roundUp(false).gen();
=> 45.3
```

### double (dbl)

Returns a random double.

**Methods**
```java$
JRand.dbl()
JRand.dbl().min(double min)
JRand.dbl().max(double max)
JRand.dbl().range(double min, double max)
```

**Examples**

Generate a random `double`:

```java 
DoubleGenerator dbl = JRand.dbl();
```

You can set the `min`,`max` using:
```java 
dbl.min(500.0).gen();
=> 723.0
```

!> If you set a `min` without updating `max`, `max` will be updated on its own to be *twice*
the size of `min`

You can also set `min` and `max` using a single method using `range`:
```java 
dbl.range(2.0,3.0).gen();
=> 2.9286619065030964
```

### float (flt)

Returns a random `float`.

**Methods**
```java$
JRand.flt()
JRand.flt().min(double min)
JRand.flt().max(double max)
JRand.flt().range(double min, double max)
```
See [double](#double-dbl), they have the same interface except return `float`.


### natural

Returns a random natural number.


```java$
JRand.natural()
JRand.natural().min(int min)
JRand.natural().max(int max)
JRand.natural().range(int min, int max)
JRand.natural().sample(int sampleSize, int population)
```

**Examples**

```java 
NaturalGenerator nat = JRand.natural();
```

Return a random natural number between `0` and `Integer.MAX_VALUE`:
```java 
nat.gen();
```

Similar to the other numeric generators you can set `min`, `max` and `range`:
```java 
nat.min(5).max(10).gen();
=> 7
```

!> Note that `min` and `max` are **inclusive**

Also available is `range` which allows you to set `min` and `max` in one go:
```java 
nat.range(0,100).gen();
=> 53
```

There is also a *helper* function included which allows to retrieve n uniform samples
from a population without replacement. (Note that this doesn't need `gen`)
```java 
nat.sample(3,10);
=> [1,5,9]
```

### string

Returns a random string.

**Methods**

```java$
JRand.string()
JRand.string().pool(String charPool)
JRand.string().symbols()
JRand.string().alpha()
JRand.string().casing(String casing)
JRand.string().digits()
JRand.string().range(int min, int max)
JRand.string().length(int length)
```

**Examples**

```java 
StringGenerator string = JRand.string();
```

By default this generates a string between `1` and `6` characters (at random) which can **include uppercase**
as well as **lowercase letters**, **symbols**, and **numbers**.
```java 
string.gen();
=> "dj4fn$"
```

You can alternatively specify a character pool yourself:
 ```java 
string.pool("abc123").gen();
=> "cca21" 
 ```
 
You can turn on the `symbols` flag to return only symbols, the `alpha` flag to return only
alphabet characters or the `digit` to return only numerical digits:
```java 
string.symbols();
string.alpha();
string.digit();
```

You can set the casing using:
```java 
string.casing("upper").gen();
=> "A112"
string.casing("lower").gen();
=> "lo23#"
```

You can set a fixed number of digits:
```java 
string.digits(2).gen();
=> "xa"
```

Or a number of digits between a range (both `min` and `max` are **inclusive**):
```java 
string.range(1,4).gen();
=> "x0k"
```

## Person

### firstname

Returns a random firstname

**Methods**

```java$
JRand.firstname()
```

**Examples**

```java 
FirstnameGenerator firstname = JRand.firstname();
```

Returns a random firstname:
```java 
firstname.gen();
=> "Betty"
```
### lastname

Returns a random lastname

**Methods**

```java$
JRand.lastname()
```

**Examples**

```java 
LastnameGenerator lastname = JRand.lastname();
```

Returns a random lastname:
```java 
lastname.gen();
=> "Alianiello"
```
