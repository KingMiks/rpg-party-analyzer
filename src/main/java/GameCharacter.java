public class GameCharacter {
    private String name;
    private CharacterClass characterClass;
    private Role role;
    private AbilityType abilityType;
    private int attack;
    private int defense;
    private int hitpoints;

    public GameCharacter(String name,CharacterClass characterClass,Role role,
        AbilityType abilityType,int attack,int defense,int hitpoints)
        {
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
}
