public class GameCharacter {
    private String name;
    private CharacterClass characterClass;
    private Role role;
    private AbilityType abilityType;
    private int attack;
    private int defense;
    private int hitpoints;

    /**
     * Creates a GameCharacter with the specified name, class, role, ability type,
     * and stats.
     *
     * @param name           the character's name; must not be null or blank.
     * @param characterClass the character's class; must not be null.
     * @param role           the character's role; must not be null.
     * @param abilityType    the character's ability type; must not be null.
     * @param attack         the character's attack value; must not be negative.
     * @param defense        the character's defense value; must not be negative.
     * @param hitpoints      the character's hitpoints value; must not be negative.
     * @throws IllegalArgumentException if any required reference value is null,
     *                                  if the name is blank, or if any stat value
     *                                  is negative.
     */
    public GameCharacter(String name, CharacterClass characterClass,
            Role role,
            AbilityType abilityType, int attack,
            int defense, int hitpoints) {
        setName(name);
        setCharacterClass(characterClass);
        setRole(role);
        setAbilityType(abilityType);
        setAttack(attack);
        setDefense(defense);
        setHitpoints(hitpoints);
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public Role getRole() {
        return role;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }
/**
 * Sets the character's name.
 * 
 * @param name the character's name; must not be blank or null.
 * @throws IllegalArgumentException when name is null or blank.
 */
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null, blank, or whitespace.");
        }
        this.name = name;
    }

    /**
     * Sets the character's attack value.
     *
     * @param attack the character's attack value; must not be negative.
     * @throws IllegalArgumentException if the attack value is negative.
     */
    public void setAttack(int attack) {
        if (attack < 0) {
            throw new IllegalArgumentException("Attack cannot be negative.");
        }
        this.attack = attack;
    }

    /**
     * Sets the character's defense value.
     *
     * @param defense the character's defense value; must not be negative.
     * @throws IllegalArgumentException if the defense value is negative.
     */
    public void setDefense(int defense) {
        if (defense < 0) {
            throw new IllegalArgumentException("Defense cannot be negative.");
        }
        this.defense = defense;
    }

    /**
     * Sets the character's hitpoints value.
     *
     * @param hitpoints the character's hitpoints value; must not be negative.
     * @throws IllegalArgumentException if the hitpoints value is negative.
     */
    public void setHitpoints(int hitpoints) {
        if (hitpoints < 0) {
            throw new IllegalArgumentException("Hitpoints cannot be negative.");
        }
        this.hitpoints = hitpoints;
    }

    /**
     * Assigns the character's role.
     * 
     * @param role the character's assigned role; must not be null.
     * @throws IllegalArgumentException if the role assigned is null.
     */
    public void setRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null.");
        }
        this.role = role;
    }

    /**
     * Assigns the character's class.
     * 
     * @param characterClass the character's assigned class; must not be null.
     * @throws IllegalArgumentException if the class assigned is null.
     */
    public void setCharacterClass(CharacterClass characterClass) {
        if (characterClass == null) {
            throw new IllegalArgumentException("Character class cannot be null.");
        }
        this.characterClass = characterClass;
    }

    /**
     * Assigns the character's ability type.
     * 
     * @param abilityType the character's assigned ability type; must not be null.
     * @throws IllegalArgumentException if the ability type assigned is null.
     */
    public void setAbilityType(AbilityType abilityType) {
        if (abilityType == null) {
            throw new IllegalArgumentException("Ability type cannot be null.");
        }
        this.abilityType = abilityType;
    }
}
