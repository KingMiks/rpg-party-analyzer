import java.util.ArrayList;
import java.util.List;

public class Party {

    private final List<GameCharacter> partyMembers = new ArrayList<>();
    private static final int MAX_PARTY_SIZE = 8;

    /**
     * Adds a member to the party.
     *
     * @param member the GameCharacter to add.
     * @return true if the member was successfully added;
     *         false if the member is null, the party is full, or the member
     *         is already in the party.
     */
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

    /**
     * Removes a member from the party.
     *
     * @param member the GameCharacter to remove.
     * @return true if the member was successfully removed;
     *         false if the member is null or is not in the party.
     */
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

    /**
     * Returns a copy of the party members without exposing the internal list.
     *
     * @return a defensive copy of the party members list.
     */
    public List<GameCharacter> getMembers() {
        return new ArrayList<>(partyMembers);
    }
}