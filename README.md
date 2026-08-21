# RPG Party Analyzer

## Project Overview

RPG Party Analyzer is a Java-based analysis engine that evaluates the balance
and synergy of fictional RPG parties. Characters are defined by one of nine
classes, nine ability types, six party roles, and individual attack, defense,
and hitpoint statistics.

The analyzer evaluates party composition through role balance, ability
coverage, and normalized stat distribution to produce ratings that describe
how balanced the party is across each category.

## Features

- Create RPG characters with a character class, party role, ability type,
  attack, defense, and hitpoints.
- Build parties containing up to eight characters.
- Count the representation of each role and ability type within a party.
- Calculate total party attack, defense, and hitpoints.
- Evaluate role balance based on the party's distribution of damage and
  complementary roles.
- Evaluate ability balance across defensive, enemy-interaction, and utility
  ability categories.
- Normalize attack, defense, and hitpoints to evaluate stat balance despite
  their different numerical scales.
- Produce separate 1–5 ratings for role, ability, and stat balance.
- Validate character data and reject invalid values.
- Protect the party's internal member collection through defensive copying.
- Verify analyzer and domain behavior with automated JUnit tests.

## Architecture

The project separates character data, party management, and analysis logic
into distinct responsibilities.

### GameCharacter

Represents an individual RPG character and stores:

- Name
- Character class
- Role
- Ability type
- Attack
- Defense
- Hitpoints

`GameCharacter` validates its own data so invalid character states, such as
null required values or negative stats, are rejected.

### Party

Manages a collection of `GameCharacter` objects.

A party:

- Supports up to eight members.
- Rejects null members.
- Prevents the same character object from being added twice.
- Allows different character objects to have identical attributes.
- Supports adding, removing, and checking for members.
- Returns a defensive copy when its member list is requested.

### PartyAnalyzer

Contains the analysis logic for evaluating a collection of characters.

The analyzer is responsible for:

- Counting roles.
- Counting ability types.
- Detecting role synergy.
- Calculating total attack, defense, and hitpoints.
- Evaluating role balance.
- Evaluating ability-type balance.
- Evaluating normalized stat balance.
- Producing separate 1–5 ratings for the three analysis categories.

## Analysis Categories

### Role Balance

Role analysis evaluates the distribution of damage roles and complementary
roles within the party. A more balanced distribution produces a higher rating.

### Ability Balance

Ability types are evaluated across defensive, enemy-interaction, and utility
categories. Parties with stronger coverage across these categories receive
higher balance ratings.

### Stat Balance

The analyzer compares party attack, defense, and hitpoints after normalizing
the three statistics to account for their different numerical scales.

This allows stat balance to be evaluated without directly comparing raw values
such as attack and hitpoints.

### Ratings

Role, ability, and stat balance are each rated independently on a 1–5 scale.

Higher ratings indicate stronger balance within that category. A rating of
0 is used when there is insufficient or invalid input for a rating to be
calculated.

## Validation and Encapsulation

The project includes validation and collection protection to maintain valid
object state.

Examples include:

- Character names cannot be null or blank.
- Character classes, roles, and ability types cannot be null.
- Attack, defense, and hitpoints cannot be negative.
- Parties cannot contain more than eight members.
- The same `GameCharacter` object cannot be added to a party twice.
- Party member collections are returned as defensive copies to prevent
  external modification of the party's internal list.

## Testing

The project uses JUnit 5 for automated testing.

The current test suite contains 73 tests across:

- `GameCharacterTest`
- `PartyTest`
- `PartyAnalyzerTest`

Tests cover character validation, party management, defensive copying,
role and ability analysis, stat calculations, rating boundaries, null handling,
and other edge cases.

The complete test suite can be run with:

```bash
mvn test
```

## Technologies

- Java
- Maven
- JUnit 5
- Git
- GitHub
- Visual Studio Code

## Project Structure

```text
src/
├── main/
│   └── java/
│       ├── AbilityType.java
│       ├── CharacterClass.java
│       ├── GameCharacter.java
│       ├── Party.java
│       ├── PartyAnalyzer.java
│       └── Role.java
└── test/
    └── java/
        ├── GameCharacterTest.java
        ├── PartyAnalyzerTest.java
        └── PartyTest.java
```

## Future Improvements

Potential future versions could include:

- A command-line or graphical user interface.
- An overall party score combining the individual analysis categories.
- More advanced class and ability interactions.
- Equipment and item support.
- Encounter-specific party analysis.
- Expanded character and party customization.

## Version

Version 1.0