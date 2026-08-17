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

    //checks whether party is empty or not
    public boolean isEmpty(){
        return partyMembers.isEmpty();
    }

    public List<GameCharacter> getMembers(){
        List<GameCharacter> partyMembersCopy = new ArrayList<>(partyMembers);
        return partyMembersCopy;
        
    }
}