import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class PartyTest {

    @Test
    void addMemberSuccessfullyAddsValidMember() {
        Party party = new Party();
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);

        boolean added = party.addMember(member);

        assertTrue(added);
        assertEquals(1, party.getMemberCount());
    }

    @Test
    void addMemberRejectsNull() {
        Party party = new Party();

        boolean rejected = party.addMember(null);

        assertFalse(rejected);
        assertEquals(0, party.getMemberCount());
    }

    @Test
    void addMemberRejectsDuplicateMember() {
        Party party = new Party();
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);

        boolean original = party.addMember(member);
        boolean duplicate = party.addMember(member);

        assertTrue(original);
        assertFalse(duplicate);
        assertEquals(1, party.getMemberCount());
    }

    @Test
    void addMemberRejectsMemberWhenPartyIsFull() {
        Party party = new Party();

        for (int i = 0; i < 8; i++) {
            GameCharacter member = new GameCharacter("Arthur", CharacterClass.WARRIOR, Role.MELEE_DAMAGE,
                    AbilityType.SHIELD, 100, 100, 300);
            party.addMember(member);
        }
        GameCharacter ninthMember = new GameCharacter("Arthur", CharacterClass.WARRIOR, Role.MELEE_DAMAGE,
                AbilityType.SHIELD, 100, 100, 300);
        boolean maxedParty = party.addMember(ninthMember);

        assertFalse(maxedParty);
        assertEquals(8, party.getMemberCount());
    }

    @Test
    void removeMemberRemovesExistingMember() {
        Party party = new Party();
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);

        boolean added = party.addMember(member);

        assertTrue(added);
        assertEquals(1, party.getMemberCount());

        boolean removed = party.removeMember(member);
        assertTrue(removed);
        assertEquals(0, party.getMemberCount());
    }

    @Test
    void removeMemberReturnsFalseForNullMember() {
        Party party = new Party();

        boolean removed = party.removeMember(null);
        assertFalse(removed);
        assertEquals(0, party.getMemberCount());
    }

    @Test
    void removeMemberReturnsFalseForMissingMember() {
        Party party = new Party();
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);

        boolean removed = party.removeMember(member);
        assertFalse(removed);
        assertEquals(0, party.getMemberCount());
    }

    @Test
    void getMembersProtectsInternalList() {
        Party party = new Party();
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        boolean added = party.addMember(member);

        List<GameCharacter> copy = party.getMembers();
        boolean removed = copy.remove(member);

        assertTrue(added);
        assertTrue(removed);
        assertEquals(1, party.getMemberCount());

    }

    @Test
    void getMembersReturnsDefensiveCopy() {
        Party party = new Party();

        List<GameCharacter> firstCopy = party.getMembers();
        List<GameCharacter> secondCopy = party.getMembers();

        assertNotSame(firstCopy, secondCopy);
    }

    @Test
    void isEmptyReflectsPartyState() {
        Party party = new Party();
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        boolean added = party.addMember(member);
        boolean notEmpty = party.isEmpty();

        assertTrue(added);
        assertFalse(notEmpty);
        assertEquals(1, party.getMemberCount());

        boolean removed = party.removeMember(member);
        boolean empty = party.isEmpty();
        assertTrue(removed);
        assertTrue(empty);
        assertEquals(0, party.getMemberCount());
    }

    @Test
    void addMemberSuccessfullyAddsValidMemberWithSimilarValues() {
        Party party = new Party();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter arthur2 = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);

        boolean added = party.addMember(arthur);
        boolean copyAdded = party.addMember(arthur2);

        assertTrue(added);
        assertTrue(copyAdded);
        assertEquals(2, party.getMemberCount());
    }

}
