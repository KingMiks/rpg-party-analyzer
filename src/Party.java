import java.util.ArrayList;
import java.util.List;

public class Party {
    //create party members list
    private List<GameCharacter> partyMembers = new ArrayList<>();
    private static final int MAX_PARTY_SIZE = 8;
    //add member to party
    public boolean addMember(GameCharacter member){
        if (member == null){
            return false;
        }
        if (partyMembers.size() >= MAX_PARTY_SIZE){
            return false;
        }
        if (partyMembers.contains(member)){
            return false;
        }
        partyMembers.add(member);
        return true;
    }
    //Get party members size
    public int getMemberCount(){
        return partyMembers.size();
    }

    //delete member form party
    public boolean removeMember(GameCharacter member){
        if (member == null){
            return false;
        }
        return partyMembers.remove(member);
    }

    //contains member within party
    public boolean containsMember(GameCharacter member){
        if (member == null){
            return false;
        }
        return partyMembers.contains(member);
    }

    public static void main(String[] args){
        Party party = new Party();

        GameCharacter arthur = new GameCharacter("Arthur", CharacterClass.WARRIOR, Role.TANK, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter lancelot = new GameCharacter("Lancelot", CharacterClass.WARRIOR, Role.TANK, AbilityType.SHIELD, 100, 200, 300);

        GameCharacter theSeparateArthur = new GameCharacter("Arthur", CharacterClass.WARRIOR, Role.TANK, AbilityType.SHIELD, 100, 200, 300);
        
        System.out.println(party.addMember(arthur));
        System.out.println(party.containsMember(lancelot));
        System.out.println(party.containsMember(null));
        System.out.println(party.containsMember(theSeparateArthur));
        System.out.println(party.getMemberCount());
        System.out.println(party.addMember(lancelot));
        System.out.println(party.containsMember(lancelot));
        System.out.println(party.getMemberCount());
        System.out.println(party.removeMember(lancelot));
        System.out.println(party.containsMember(lancelot));
        System.out.println(party.getMemberCount());
        System.out.println(party.removeMember(lancelot));
        System.out.println(party.getMemberCount());
        System.out.println(party.removeMember(null));
        System.out.println(party.getMemberCount());
        System.out.println(party.removeMember(arthur));
        System.out.println(party.containsMember(arthur));
        System.out.println(party.getMemberCount());
    
    
    
    
    
    }
}