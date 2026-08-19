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
        setName(name);
        setCharacterClass(characterClass);
        setRole(role);
        setAbilityType(abilityType);
        setAttack(attack);
        setDefense(defense);
        setHitpoints(hitpoints);

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
        if (name == null){
            throw new IllegalArgumentException();
        }
        if (name.isBlank()){
            throw new IllegalArgumentException();
        }
        this.name = name;
    }
    public void setAttack(int attack){
        if (attack < 0){
            throw new IllegalArgumentException();
        }
        this.attack = attack;
    }
    public void setDefense(int defense){
        if (defense < 0){
            throw new IllegalArgumentException();
        }
        this.defense = defense;
    }
    public void setHitpoints(int hitpoints){
        if (hitpoints < 0){
            throw new IllegalArgumentException();
        }
        this.hitpoints = hitpoints;
    }
    public void setRole(Role role){
        if (role == null){
            throw new IllegalArgumentException();
        }
        this.role = role;
    }
    public void setCharacterClass(CharacterClass characterClass){
        if (characterClass == null){
            throw new IllegalArgumentException();
        }
        this.characterClass = characterClass;
    }
    public void setAbilityType(AbilityType abilityType){
        if (abilityType == null){
            throw new IllegalArgumentException();
        }
        this.abilityType = abilityType;
    }
}
