## Initial Architecture

### GameCharacter

Stores information about one RPG character.

Fields:

- name
- characterClass
- role
- attack
- defense
- maxHp
- abilityTypes

Responsibilities:

- Store character data
- Provide access to character information

The character should not calculate party scores or synergy.

---

### Party

Stores a group of GameCharacter objects.

Fields:

- partyName
- members
- maximumPartySize

Responsibilities:

- Add members
- Remove members
- Return the current party members
- Check whether the party is full

The party owns the character collection but does not evaluate it.

---

### PartyAnalyzer

Analyzes a Party and produces category scores.

Responsibilities:

- Calculate attack score
- Calculate defense score
- Calculate survivability score
- Calculate ability coverage score
- Calculate synergy score
- Calculate the overall percentage
- Generate an AnalysisReport

---

### AnalysisReport

Stores the completed analysis results.

Fields:

- attackScore
- defenseScore
- survivabilityScore
- abilityCoverageScore
- synergyScore
- overallPercentage
- fivePointRating
- strengths
- weaknesses

This allows the analyzer logic to remain separate from the console or future GUI.

---

## Initial Enums

### CharacterClass

- WARRIOR
- KNIGHT
- MAGE
- RANGER
- ROGUE
- CLERIC
- PALADIN
- BARD
- NECROMANCER

### Role

- TANK
- MELEE_DAMAGE
- RANGED_DAMAGE
- HEALER
- SUPPORT
- CONTROL

### AbilityType

- AOE
- HEALING
- SHIELD
- CROWD_CONTROL
- BUFF
- DEBUFF
- TAUNT
- REVIVE
- MOBILITY

---

## Version 1 Rules

- Parties contain up to four characters.
- Attack and defense use a 1–10 scale.
- HP must be a positive whole number.
- Duplicate classes are allowed.
- Duplicate roles are allowed.
- Unbalanced role coverage may reduce the party rating.
- Users may combine any class and role.
- Version 1 will use a console interface.
- A graphical interface may be added after the analyzer logic works.