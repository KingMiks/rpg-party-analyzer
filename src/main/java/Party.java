import java.util.ArrayList;
import java.util.List;

public class Party {

    private final List<GameCharacter> partyMembers = new ArrayList<>();
    private static final int MAX_PARTY_SIZE = 8;

    public boolean addMember(GameCharacter member) {
        if (member == null) {
            return false;
        }
        if (partyMembers.size() >= MAX_PARTY_SIZE) {
            return false;
        }
        if (partyMembers.contains(member)) {
            return false;
        }
        partyMembers.add(member);
        return true;
    }

    public int getMemberCount() {
        return partyMembers.size();
    }

    public boolean removeMember(GameCharacter member) {
        if (member == null) {
            return false;
        }
        return partyMembers.remove(member);
    }

    public boolean containsMember(GameCharacter member) {
        if (member == null) {
            return false;
        }
        return partyMembers.contains(member);
    }

    public boolean isEmpty() {
        return partyMembers.isEmpty();
    }

    public List<GameCharacter> getMembers() {
        return new ArrayList<>(partyMembers);
    }
}