public class GameCharacter {
    private String name;
    private CharacterClass characterClass;
    private Role role;
    private AbilityType abilityType;
    private int attack;
    private int defense;
    private int hitpoints;

    public GameCharacter(String name,CharacterClass characterClass,Role role,AbilityType abilityType,int attack,int defense,int hitpoints){
        this.name = name;
        this.characterClass = characterClass;
        this.role = role;
        this.abilityType = abilityType;
        this.attack = attack;
        this.defense = defense;
        this.hitpoints = hitpoints;

    }
    /*Getter methods */
    public String getName(){
        return name;
    }
    public int getAttack(){
        return attack;
    }
    public int getDefense(){
        return defense;
    }
    public int getHitpoints(){
        return hitpoints;
    }
    public Role getRole(){
        return role;
    }
    public CharacterClass getCharacterClass(){
        return characterClass;
    }
    public AbilityType getAbilityType(){
        return abilityType;
    }
    /*Setter methods */
    public void setName(String name){
        this.name = name;
    }
    public void setAttack(int attack){
        this.attack = attack;
    }
    public void setDefense(int defense){
        this.defense = defense;
    }
    public void setHitpoints(int hitpoints){
        this.hitpoints = hitpoints;
    }

    GameCharacter arthur = new GameCharacter("Arthur", CharacterClass.WARRIOR, Role.TANK, AbilityType.SHIELD, 80, 100, 250);

    GameCharacter merlin = new GameCharacter("Merlin", CharacterClass.MAGE, Role.CONTROL, AbilityType.AOE, 100, 80, 100);

    GameCharacter robin = new GameCharacter("Robin", CharacterClass.RANGER, Role.RANGED_DAMAGE, AbilityType.CROWD_CONTROL, 200, 100, 150);

    GameCharacter luna = new GameCharacter("Luna", CharacterClass.CLERIC, Role.HEALER, AbilityType.HEALING, 50, 200, 300);

    GameCharacter shade = new GameCharacter("Shade", CharacterClass.ROGUE, Role.MELEE_DAMAGE, AbilityType.DEBUFF, 300, 50, 100);




}
