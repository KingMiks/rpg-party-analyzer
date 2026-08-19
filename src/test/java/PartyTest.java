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
            300
        );

        boolean result = party.addMember(member);

        assertTrue(result);
        assertEquals(1, party.getMemberCount());
    }

    @Test
    void addMembersUsingNullValue() {
        Party party = new Party();

        boolean result = party.addMember(null);

        assertFalse(result);
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
            300
        );

        boolean first_result = party.addMember(member);
        boolean second_result = party.addMember(member);

        assertTrue(first_result);
        assertFalse(second_result);
        assertEquals(1, party.getMemberCount());
    }

    @Test
    void addMembersTilPartyIsMaxed() {
        Party party = new Party();

        for (int i = 0; i < 8; i++){
            GameCharacter member = new GameCharacter("Arthur", CharacterClass.WARRIOR, Role.MELEE_DAMAGE, AbilityType.SHIELD, 100, 100, 300);
            party.addMember(member);
        }
        GameCharacter ninthMember = new GameCharacter("Arthur", CharacterClass.WARRIOR, Role.MELEE_DAMAGE, AbilityType.SHIELD, 100, 100, 300);
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
            300
        );

        boolean result = party.addMember(member);

        assertTrue(result);
        assertEquals(1, party.getMemberCount());

        boolean nextResult = party.removeMember(member);
        assertTrue(nextResult);
        assertEquals(0, party.getMemberCount());
    }

    @Test
    void removeMemberWithEmptyParty(){
        Party party = new Party();
        
        boolean result = party.removeMember(null);
        assertFalse(result);
        assertEquals(0, party.getMemberCount());
    }

    @Test
    void removeNonExistentMember(){
        Party party = new Party();
        GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );

        boolean result = party.removeMember(member);
        assertFalse(result);
        assertEquals(0, party.getMemberCount());
    }

    @Test
    void getMembersProtectsInternalList(){
        Party party = new Party();
        GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        boolean result = party.addMember(member);

        List<GameCharacter> copy = party.getMembers();
        boolean nextResult = copy.remove(member);

        assertTrue(result);
        assertTrue(nextResult);
        assertEquals(1, party.getMemberCount());

    }
    @Test
    void getMembersReturnsDefensiveCopy(){
        Party party = new Party();

        List<GameCharacter> firstCopy = party.getMembers();
        List<GameCharacter> secondCopy = party.getMembers();

        assertNotSame(firstCopy, secondCopy);
    }

    @Test
    void checkIfPartyIsEmpty(){
        Party party = new Party();
        GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        boolean result = party.addMember(member);
        boolean notEmpty = party.isEmpty();

        assertTrue(result);
        assertFalse(notEmpty);
        assertEquals(1, party.getMemberCount());

        boolean nextResult = party.removeMember(member);
        boolean empty = party.isEmpty();
        assertTrue(nextResult);
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
            300
        );
        GameCharacter arthur2 = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );

        boolean result = party.addMember(arthur);
        boolean nextResult = party.addMember(arthur2);

        assertTrue(result);
        assertTrue(nextResult);
        assertEquals(2, party.getMemberCount());
    }

}
