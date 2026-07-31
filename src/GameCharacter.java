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
}
