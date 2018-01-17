<!-- Load the header without a physical h1 tag -->
<!-- hidden-header:JRand:1 -->
![jrand](https://jrand.xdrop.me/_media/jrand.svg)

> Probably the *best* Java library for random data generation.*


JRand is heavily inspired by [ChanceJS](http://chancejs.com) and tries to bring together a lot of that functionality to Java.
In JRand there are `Generator`'s which are reusable components to generate all sorts of things. You can get
access to any generator you wish via the `JRand` facade class.

The library is under development with *weekly* releases of new generators.

## Installation

You can install `JRand` via Maven Central:

**Maven**
```xml
<dependency>
    <groupId>me.xdrop</groupId>
    <artifactId>jrand</artifactId>
    <version>0.2.1-alpha</version>
</dependency>
```

**Gradle**:
```gradle
compile 'me.xdrop:jrand:0.2.1-alpha'
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


?> **Remember:** Different options and flags on generators can be *chained* in a builder
fashion

## Basic

The basic generators are used to generate different kinds of common data including numbers and strings.

### bool

Returns a random boolean value.

**Methods**
```java$ 
JRand.bool()
// Set the likelihood of generating true
JRand.bool().likelihood(int)
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

You can also set the likelihood as an integer out of 100 to indicate the probablity
of returning `true` using:

```java 
bool.likelihood(50).gen();
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
// Pick a random element from the pool and return it with its index
JRand.character().pool(String charPool).genWithIndex()
```

**Examples**

```java 
CharacterGenerator character = JRand.character()
```

By default this generates a character which can **include uppercase**
as well as **lowercase letters**, and **numbers**.
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

You can optionally *add* (as opposed to only return) symbols to the pool using:
```java 
character.addSymbols().gen();
=> 'x'
```

You can chain different *add* methods together:
```java 
character.addAlpha().addSymbols().gen();
=> '$'
```

Or even add your own charset:
```java 
character.addCharset(CHARSET.CHARS_LOWER)
         .addCharset(CHARSET.SYMBOLS)
         .gen();
=> 'l'
```

The available `CHARSET`s are: `SYMBOLS`,`CHARS_LOWER`,`CHARS_UPPER`,`NUMBERS`

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
// Returns the decimal as BigDecimal
JRand.decimal().genAsDecimal();
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

Or generate your result as `BigDecimal`:
```java 
decimal.genAsDecimal();
=> BigDecimal(2.0)
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
JRand.string().digits()
JRand.string().addDigits()
JRand.string().addSymbols()
JRand.string().addAlpha()
JRand.string().addCharset(CHARSET charset)
JRand.string().casing(String casing)
JRand.string().range(int min, int max)
JRand.string().length(int length)
```

**Examples**

```java 
StringGenerator string = JRand.string();
```

By default this generates a string between `1` and `6` characters (at random) which can **include uppercase**
as well as **lowercase letters**, and **numbers**.
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

You can optionally *add* (as opposed to only return) symbols to the pool using:
```java 
string.addSymbols().gen();
=> 'x'
```

You can chain different *add* methods together:
```java 
string.addAlpha().addSymbols().gen();
=> '$'
```

Or even add your own charset:
```java 
string.addCharset(CHARSET.CHARS_LOWER)
      .addCharset(CHARSET.SYMBOLS)
      .gen();
=> 'l'
```

The available `CHARSET`s are: `SYMBOLS`,`CHARS_LOWER`,`CHARS_UPPER`,`NUMBERS`

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
## Text

### syllable

Returns a random syllable.

**Methods**

```java$
JRand.syllable()
JRand.syllable().length(int length)
JRand.syllable().capitalize()
```

**Examples**

```java 
SyllableGenerator syllable = JRand.syllable()
```

Generate a syllable:
```java 
syllable.gen();
=> "foo"
```

Set a custom length:
```java 
syllable().length(2).gen();
=> "ka"
```

Or choose to capitalize the first letter
```java 
syllable().capitalize().gen()
=> "Ken"
```

### word

Returns a random word by joining together syllables.

**Methods**

```java$
JRand.word()
JRand.word().length(int length)
JRand.word().syllables(int syllables)
JRand.word().syllables(int min, int max)
JRand.word().setSyl(SyllableGenerator syl)
JRand.word().capitalize()
JRand.word().capitalize(boolean enable)
```

**Examples**

```java 
WordGenerator word = JRand.word();
```

By default words are generated by joining between `1` and `3` syllables
```java 
word.gen();
=> "fae"
```

You can if you wish set a maximum `length`:
```java 
word.length(10).gen();
=> "reliecizve"
```

Or set the number of syllables
```java 
word.syllables(3).gen();
=> "homebi"
```

Or specify a range:
```java 
word.syllables(1,2).gen()
=> "lee"
```

Optionally, capitalize the word:
```java 
word.capitalize().gen();
=> "Lee"
```

Or set your own syllable generator:
```java 
word.setSyl(JRand.syllable());
```

### sentence

Returns a random sentence.

**Methods**

```java$
JRand.sentence()
JRand.sentence().punctuation()
JRand.sentence().words(int numberOfWords)
JRand.sentence().words(int min, int max)
```

**Examples**

```java 
SentenceGenerator sentence = JRand.sentence();
```

Generate a random sentence:
```java 
sentence.gen();
=> "Xocela dife ri edbovi xo imboer yakili moex go hooc radaja qe."
```

You can specify a fixed number of words:
```java 
sentence.words(2).gen();
=> "Ra sidi."
```

Or specify a range of words:
```java 
sentence.words(1,3).gen();
=> "Ona"
```

The `punctuation` flag will randomly include other punctuation characters (`?"'s'`)
in different portions of the sentence:
```java 
sentence.punctuation().gen();
=> "Zeod cokofo, zi."
```

### paragraph

Returns a random paragraph

**Methods**

```java$
JRand.paragraph()
JRand.paragraph().sentences(int sentences)
JRand.paragraph().sentences(int min, int max)
JRand.paragraph().punctuation()
JRand.paragraph().wordsPerSentence(int wordsPerSentence)
JRand.paragraph().wordsPerSentence(int min, int max)
JRand.paragraph().joining(String joining)
```

**Examples**

```java 
ParagraphGenerator paragraph = JRand.paragraph();
```

To generate a paragraph with the default settings (between 3 to 7) sentences
```java 
paragraph.gen();
=> "Ri nibe corawo lono rocahe idzawo zaigzo fe tovovo noam tata jadi is wadeni noca. 
Oviviz to hala qi yeqa ap ve apkeha peti ibpo de la tonowa renopo oj. 
Mote woveki ro yo sare vevama maavva goox sizeha fove dego wade qezaaz jiax watoim. 
Siko ve veasfo awnici xizaso ol it mi ra nidogi ja fo leqela foto wotega."
```

We can optionally specify the number of sentences to return:
```java 
paragraph.sentences(1).gen();
=> "Dagowe diof ij iw illoag locona eqti mesizi jenesa pasiih ecja ga weaval zidece."
```

You can also generate a number of sentences between a range:
```java 
paragraph.sentences(1,2).gen();
=> "Meze yahite reji bikina biwo dexome zo layeri xoaw nais be ja gehe. 
Mela se qi pagi wo laye ta acte weto fale neoh hemi wa."
```

The `punctuation` flag will randomly include other punctuation characters (`?"'s'`)
in different portions of sentences:
```java 
paragraph.punctuation().sentences(1).gen();
=> "Naodiizio hoo gaijaaizo heemoe weiipe haamea geireo feihioeri boezoodoa axaxaoita's gai roe. 
Gia qaixaovia beadeatie's feokeeita's ciieko rie xiefei's yoexoenao waowoo xeonaimae 
yaiohosia kei?"
```

We can also control the number of words per sentence:
```java 
paragraph.wordsPerSentence(2).gen();
=> "Vilofe lool."
```
Or again within a range:
```java 
paragraph.wordsPerSentence(2,4).gen();
=> "Deiqiejee woo koeriojae."
```

You can set the joining string to anything you want (default is `" "`):
```java 
paragraph.joining("\n").gen();
=> 
"One.
Two."
```
## Person

### firstname

Returns a random firstname

**Methods**

```java$
JRand.firstname()
JRand.gender(String gender)
JRand.gender(Gender gender)
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

You can set the gender to be male or female:
```java 
firstname.gender(Gender.MALE).gen();
=> "Andrew"
firstname.gender("female").gen();
=> "Laura"
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

### prefix

Returns a random name prefix.

**Methods**

```java$
JRand.prefix()
JRand.prefix().gender(String gender)
JRand.prefix().gender(Gender gender)
```

**Examples**

```java 
PrefixGenerator prefix = JRand.prefix();
```

Generate a name prefix as `String`
```java 
prefix.gen();
=> "Mr"
```

Or generate as a `Prefix` object:
```java 
prefix.genAsPrefix();
=> Prefix {Full: Mister, Abbreviation: Mr}
```

You can specify the gender either via passing a `String` or `Gender`:
```java 
prefix.gender("m").gen();
=> "Mr"
prefix.gender("f").gen();
=> "Ms"
prefix.gender("n").gen(); // For neutral
=> "Dr"
prefix.gender(Gender.NEUTRAL).gen();
=> "Dr"
```

### name

Returns a random name

**Methods**

```java$
JRand.name()
JRand.name().withPrefix()
JRand.name().withPrefix(boolean enabled)
JRand.name().withMiddleName()
JRand.name().withMiddleName(boolean enabled)
JRand.name().separator(String sep)
JRand.name().gender(String gender)
JRand.name().gender(Gender gender)
JRand.name().reverseOrder(boolean enabled)
JRand.name().reverseOrder()
JRand.name().cardName(boolean enabled)
JRand.name().cardName()
```

**Examples**

```java 
NameGenerator name = JRand.name();
```

Return a random name of any gender, without a prefix:
```java 
name.gen();
=> "John Smith"
```

Return lastname first then firstname:
```java 
name.reverseOrder().gen();
=> "Smith John"
```

Include a middle name:
```java 
name.withMiddleName().gen();
=> "John Albert Smith"
```

Include a prefix:
```java 
name.withPrefix().gen();
=> "Mr John Albert"
```

Set the gender with:
```java 
name.gender("f").gen();
=> "Veronica Hastings"
```

You can also return a short version (typically found on cards):
```java 
name.cardName().gen();
=> "R. Z. SMITH"
```

### age

Returns a random age.

**Methods**

```java$
JRand.age()
JRand.age().child()
JRand.age().adult()
JRand.age().adult()
JRand.age().teen()
JRand.age().senior()
JRand.age().personType()
```

**Examples**

```java 
AgeGenerator age = JRand.age();
```

Return a random age as `Integer`:
```java 
age.gen();
=> 45
```

Alternatively you can use the following helper functions to set the person type:
```java 
age.child().gen();
=> 10
age.teen().gen();
=> 15
age.adult().gen();
=> 25
age.senior().gen();
=> 45
```

Or you can set the person type via the `PersonType` enum:
```java 
age.personType(PersonType.SENIOR).gen();
=> 60
```

Available `PersonType`'s: `CHILD(1,12)`, `TEEN(13,17)`,`ADULT(18,40)`,`SENIOR(41,120)`,`GENERIC(1,120)` 

### birthday

Returns a random birthday.

**Methods**

```java$
JRand.birthday()
JRand.birthday().child()
JRand.birthday().adult()
JRand.birthday().teen()
JRand.birthday().senior()
JRand.birthday().type(PersonType type)
JRand.birthday().american()
JRand.birthday().american(boolean enabled)
JRand.birthday().format(String format)
JRand.birthday().getDateTime(); // Joda DateTime
```

**Examples**

```java 
BirthdayGenerator birthday = JRand.birthday();
```

Generate a random birthday (of any person type) as Java `Date`.
```java 
birthday.gen();
=> java.util.Date {01/01/1994}
```

Or generate it as a `String` (default format is dd/M/yy):
```java 
birthday.genString();
=> "01/01/94"
```

Select the person type using the `child(), adult(), teen(), senior(), type(PersonType type)`.
See [Age](#age) for more details on how they are used.

If returning a string then you can return an american date instead (M/dd/yy):
```java 
birthday.american().genString();
=> "03/21/93"
```

Or specify your own format using:
```java 
birthday.format("dd/M/yy").genString();
=> "21/03/94"
```

### gender

Returns a random gender.

**Methods**

```java$
JRand.gender()
JRand.gender().full()
JRand.gender().full(boolean enabled)
JRand.gender().likelihood(int likelihood)
JRand.gender().format(String male, String female)
```

**Examples**

```java 
GenderGenerator gender = new GenderGenerator();
```

Generate a random gender as `String` from ("M"/"F"):
```java 
gender.gen();
=> "M"
```

You can return `Male` or `Female` with:
```java 
gender.full().gen();
=> "Male"
```

You can set the *likelihood* as an integer **0-100** of returning male with:
```java 
gender.likelihood(90).gen();
=> "Male"
```

You can also specify your own male and female strings with:
```java 
gender.format("Man","Woman").gen();
=> "Woman"
```

If preferred you can also return a `Gender.MALE` or `Gender.FEMALE` enum:
```java 
gender.genAsGender();
=> Gender.MALE
```

## Money

### card

Returns a random `Card` object.


**Examples**

Generate a `Card` object.
```java 
JRand.gen();
=> "[Name]: Hal Goodrich
    [CardType]: Discover
    [CardNo]: 6523079816424856
    [Address]: 18 Sagois Way
    [Postcode]: S1252JEL
    [Country]: Argentina
    [IssueDate]: 07/10
    [ExpiryDate]: 06/21
    [CVV]: 120"
```

The `Card` object has the following methods:
```java 
getCardNumber();
getCvv();
getExpiryDate();
getIssueDate();
getName();
getCountry();
getBillingAddress();
getPostcode();
getCardType();
```

### cardNo

Returns a random card number. 

**Methods**

```java$
JRand.cardNo()
JRand.cardNo().cardType(CardType cardType)
JRand.cardNo().cardType(String cardType)
JRand.cardNo().format()
JRand.cardNo().format(boolean useDefault)
JRand.cardNo().common()
JRand.cardNo().common(boolean enabled)
JRand.cardNo().only(String ... names)
JRand.cardNo().only(CardType ... cardTypes)
JRand.cardNo().luhnCalculate(String cardNum)
```

**Examples**

```java 
CardNumberGenerator cardNo = JRand.cardNo();
```

Return a random card number (with correct lengths and prefixes) from the following card issuers:
`Visa`, `Visa Electron`, `Mastercard`,`China UnionPay`,`Maestro`,`American Express`,`Discover`,`JCB`,
`Diners Club Carte Blanche`, `Diners Club International`, `Diners Club United States & Canada`,`InstaPayment`,
`Laser`,`Solo`,`Switch`.

The card numbers start with the right prefix for each network and end with a check digit
calculated using [Luhn's algorithm](https://en.wikipedia.org/wiki/Luhn_algorithm).
```java 
cardNo.gen();
=> "5497658596955975"
```

Include only the four common ones (Visa, Mastercard, American Express, Discover):
```java 
cardNo.common().gen();
=> "344369886838383"
```

There is a default formatting associated with some networks which you can enable via:
```java 
cardNo.format(true).gen();
=> "5544 1705 6751 9841"
```

Or you can specify your own. Note that any instance of the character `X` in the string will
be replaced by a card digit while any instance of `_` will obscure a digit
by returning `X` instead.
```java 
cardNo.format("XXXX XXXX XXXX XXXX").gen();
=> "5544 1705 6751 9841"
```

You can optionally set the card type using:
```java 
cardNo.cardType("amex").gen();
=> "347220089369775"
```

Or return from a set using:
```java 
cardNo.only("amex","visa").gen();
=> "347220089369775"
```

You can also calculate the last [Luhn digit](https://en.wikipedia.org/wiki/Luhn_algorithm) 
 (used as the last digit for checksum purposes on different numbers including 
 credit card numbers) using:
```java 
cardNo.luhnCalculate("630403851107382")
=> 7
```

Available string options are: 

`AMEX`,`AMERICAN EXPRESS`,`VISA`,`ELECTRON`,`VISA ELECTRON`,`MASTERCARD`,`MC`,`CHINA UNIONPAY`,
`CUP`,`MAES`,`MAESTRO`,`DISCOVER`,`DISC`, `DC-CB`, `DC-INT`, `DC-UC`,`JCB`,`INSTAPAYMENT`,
`IPI`,`LASER`,`LASR`,`SOLO`,`SWCH`, `SWITCH`

Alternatively have a look at the `CardType` enum


### cardType

Returns a random `CardType` object.

**Methods**

```java$
JRand.cardType()
JRand.cardType().common();
JRand.cardType().common(boolean enabled);
JRand.cardType().only(String ... names);
JRand.cardType().only(CardTypes ... cardTypes);
JRand.cardType().getTypeByName(String name);
```

**Examples**

Generate a random `CardType`.

```java 
CardTypeGenerator cardType = JRand.cardType();
```

Choose from all available card types:
```java 
cardType.gen();
=> CardType {Diners Club United States & Canada [DC-UC] Prefixes: 54,55}
```

You can specify the set of types to choose from using:
```java 
cardType.only("visa","amex").gen();
=> CardType {Visa [Visa] Prefixes: 4}
cardType.only(CardType.VISA,CardType.AMERICAN_EXPRESS).gen();
=> CardType {American Express [AmEx] Prefixes: 34,37}
```

Or include only the four common ones (Visa, Mastercard, American Express, Discover):
```java 
cardType.common().gen();
=> CardType {Visa [Visa] Prefixes: 4}
```

### expiryDate

Returns a random expiry date.

**Methods**

```java$
JRand.expiryDate()
JRand.expiryDate().longVersion()
JRand.expiryDate().longVersion(boolean enabled)
JRand.expiryDate().expired()
JRand.expiryDate().expired(boolean enabled)
JRand.expiryDate().canExpire(boolean enabled)
```

**Examples**

```java 
ExpiryDateGenerator expiryDate = JRand.expiryDate();
```

Generate a random expiry date in the form of `MM/yy`:
```java 
expiryDate.gen();
=> "12/19"
```

You can also choose to return a longer version:
```java 
expiryDate.longVersion().gen();
=> "12/2019"
```

You can force the date to be expired:
```java 
expiryDate.expired().gen();
=> "03/15"
```

Or allow it to *optionally* be expired:
```java 
expiryDate.canExpire(true).gen();
=> "04/16"
```

### issueDate

Returns a random issue date. 

**Methods**

```java$
JRand.issueDate()
JRand.issueDate().longVersion()
JRand.issueDate().longVersion(boolean enabled)
```

**Examples**

```java 
IssueDateGenerator issueDate = JRand.issueDate();
```

?> An issue date will *always* occur in the past 

Generate a random issue date as `MM/YY`.
```java 
issueDate.gen();
=> "04/09"
```

You can also print it in `MM/YYYY`:
```java 
issueDate.longVersion().gen();
=> "12/2011"
```

### CVV

Returns a random CVV code.

**Methods**

```java$
JRand.cvv()
JRand.cvv().amex()
JRand.cvv().amex(boolean enabled)
```

**Examples**

```java 
CVVGenerator cvv = JRand.cvv();
```

Generate a random three-digit `CVV` code.

```java 
cvv.gen();
=> "344"
```

Generate a **four** digit CVV code (for American Express cards):

```java 
cvv.amex().gen();
=> "3221"
```




## Location

### country

Returns a random country.

**Methods**

```java$
JRand.country()
JRand.country().prefix()
JRand.country().prefix(boolean enabled)
JRand.country().genAsCountry()
```

**Examples**

```java 
CountryGenerator country = JRand.country();
```

Generate a random country returning its full name:

```java 
country.gen();
=> "United States"
```

You can optionally return the country prefix (short version):
```java 
country.prefix().gen();
=> "US"
```

You can also get a `Country` object instead of a `String` using:
```java 
country.genAsCountry();
=> Country {Name: United States, Prefix: US, Postal format: 99999)
```

### postcode

Returns a random postcode.

**Methods**

```java$
JRand.postcode()
JRand.postcode().country(String countryPrefix)
JRand.postcode().fromFormat(String postalFormat)
```

**Examples**

```java 
PostcodeGenerator postcode = JRand.postcode();
```

Return a random postcode for any country:
```java 
postcode.gen();
=> "DH34LL"
```

Optionally you can specify the country prefix:
```java 
postcode.country("us").gen();
=> "34555"
```



There is also a *helper* function that can generate a postcode given a postal format,
where 'A' indicates alphabet character and '9' indicates a digit:
```java 
postcode.fromFormat("999");
=> "827"
```

!> **Note**: This doesn't return an instance so a call to `gen` is not needed.

### street

Returns a random street.

**Methods**

```java$
JRand.street()
JRand.street().uk()
JRand.street().us()
JRand.street().houseNumber()
JRand.street().shortSuffix(boolean enable)
JRand.street().shortSuffix()
```

**Examples**

```java 
StreetGenerator street = new StreetGenerator();
```

Generate a random street using:
```java 
street.gen();
=> "Edmimo Heights"
```

This includes both UK and US street prefixes, if you use to use only one of them 
set the `uk()` or `us()` flags.
```java 
street.uk().gen();
=> "Isa Garden"
street.us().gen();
=> "Hakemi Place"
```

You can optinally use the short suffix version with the `shortSuffix()` flag:
```java 
street.shortSuffix().gen();
=> "Pavel Str"
```

Or include a house number using `houseNumber()`
```java 
street.houseNumber().gen();
=> "36 Obel Street"
```


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
