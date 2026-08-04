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
}