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

    public static void main(String[] args){
        Party party = new Party();
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();

        GameCharacter arthur = new GameCharacter("Arthur", CharacterClass.WARRIOR, Role.TANK, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter lancelot = new GameCharacter("Lancelot", CharacterClass.MAGE, Role.CONTROL, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter theSeparateArthur = new GameCharacter("Arthur", CharacterClass.RANGER, Role.SUPPORT, AbilityType.SHIELD, 100, 200, 300);
        
        System.out.println(party.addMember(arthur));
        System.out.println(partyAnalyzer.countRoles(party.getMembers()));
        System.out.println(party.addMember(null));
        System.out.println(partyAnalyzer.countRoles(party.getMembers()));
        System.out.println(party.addMember(lancelot));
        System.out.println(partyAnalyzer.countRoles(party.getMembers()));
        System.out.println(party.addMember(theSeparateArthur));
        System.out.println(partyAnalyzer.countRoles(party.getMembers()));
        System.out.println(party.removeMember(theSeparateArthur));
        System.out.println(party.removeMember(lancelot));
        System.out.println(party.removeMember(arthur));
        System.out.println(partyAnalyzer.countRoles(party.getMembers()));
        System.out.println(partyAnalyzer.countRoles(null));

    }
}