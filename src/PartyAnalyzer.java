import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyAnalyzer {

    public Map<Role, Integer> countRoles(List<GameCharacter> members){
        Map<Role, Integer> countRoles = new HashMap<>();

        if (members == null){
            return countRoles;
        }
        
        for (int i = 0; i < members.size(); i++){
            Role currentRole = members.get(i).getRole();

            if (!countRoles.containsKey(currentRole)){
                countRoles.put(currentRole, 1);
            }
            else {
                int currentCount = countRoles.get(currentRole);
                int updateCount = currentCount + 1;
                countRoles.put(currentRole, updateCount);
            }
        }
        return countRoles;
    }

    public boolean hasDamage(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return false;
        }
        return (countRoles.containsKey(Role.MELEE_DAMAGE) || countRoles.containsKey(Role.RANGED_DAMAGE));
    }
    public boolean hasComplementaryRole(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return false;
        }
        return (countRoles.containsKey(Role.CONTROL)) || (countRoles.containsKey(Role.HEALER)) || 
        (countRoles.containsKey(Role.TANK)) ||(countRoles.containsKey(Role.SUPPORT));
    }
    public boolean hasSynergy(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return false;
        }
        return hasDamage(countRoles) && hasComplementaryRole(countRoles);
    }

    public int countDamageRoles(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return 0;
        }
        int meleeRole = countRoles.getOrDefault(Role.MELEE_DAMAGE, 0);
        int rangedRole = countRoles.getOrDefault(Role.RANGED_DAMAGE, 0);
        return rangedRole + meleeRole;
    }
    public int countComplementaryRoles(Map<Role,Integer> countRoles){
        if (countRoles == null){
            return 0;
        }
        int tankRole = countRoles.getOrDefault(Role.TANK, 0);
        int supportRole = countRoles.getOrDefault(Role.SUPPORT, 0);
        int controlRole = countRoles.getOrDefault(Role.CONTROL, 0);
        int healerRole = countRoles.getOrDefault(Role.HEALER, 0);
        return tankRole + supportRole + controlRole + healerRole;
    }
    public int calculateRoleRating(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return 0;
        }
        int damageRoles = countDamageRoles(countRoles);
        int complementaryRoles = countComplementaryRoles(countRoles);
        int partySize = damageRoles + complementaryRoles;
        if (partySize < 2){
            return 0;
        }
        int actualDifference = Math.abs(damageRoles - complementaryRoles);
        int idealDifference = partySize % 2;
        int distanceFromIdeal = actualDifference - idealDifference;
        switch (distanceFromIdeal) {
            case 0:
                return 5;
            case 2:
                return 4;
            case 4:
                return 3;
            case 6:
                return 2;
            case 8:
                return 1;
            default:
                return 0;
        }
    }
    public static void main(String[] args){
        Party party = new Party();
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();

        GameCharacter arthur = new GameCharacter("Arthur", CharacterClass.WARRIOR, Role.MELEE_DAMAGE, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter lancelot = new GameCharacter("Lancelot", CharacterClass.MAGE, Role.RANGED_DAMAGE, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter theSeparateArthur = new GameCharacter("Arthur", CharacterClass.RANGER, Role.MELEE_DAMAGE, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter theSeparateLancelot = new GameCharacter("Arthur", CharacterClass.RANGER, Role.RANGED_DAMAGE, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter moon = new GameCharacter("Arthur", CharacterClass.RANGER, Role.RANGED_DAMAGE, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter sun = new GameCharacter("Arthur", CharacterClass.RANGER, Role.MELEE_DAMAGE, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter star = new GameCharacter("Arthur", CharacterClass.RANGER, Role.MELEE_DAMAGE, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter astroid = new GameCharacter("Arthur", CharacterClass.RANGER, Role.MELEE_DAMAGE, AbilityType.SHIELD, 100, 200, 300);
        
        System.out.println(party.addMember(arthur));
        System.out.println(party.addMember(theSeparateArthur));
        System.out.println(party.addMember(lancelot));
        System.out.println(partyAnalyzer.countRoles(party.getMembers()));
        System.out.println(partyAnalyzer.hasDamage(partyAnalyzer.countRoles(party.getMembers())));
        System.out.println(party.addMember(theSeparateLancelot));
        System.out.println(party.addMember(moon));
        System.out.println(party.addMember(sun));
        System.out.println(party.addMember(star));
        System.out.println(party.addMember(astroid));
        System.out.println(partyAnalyzer.countRoles(party.getMembers()));
        System.out.println(partyAnalyzer.hasComplementaryRole(partyAnalyzer.countRoles(party.getMembers())));
        System.out.println(partyAnalyzer.countDamageRoles(partyAnalyzer.countRoles(party.getMembers())));
        System.out.println(partyAnalyzer.countComplementaryRoles(partyAnalyzer.countRoles(party.getMembers())));
        System.out.println(partyAnalyzer.calculateRoleRating(partyAnalyzer.countRoles(party.getMembers())));

        
    }
}