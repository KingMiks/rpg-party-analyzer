import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameCharacterTest {

    @Test
    void creatingCharacterWithNegativeAttackThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GameCharacter(
                    "Arthur",
                    CharacterClass.WARRIOR,
                    Role.MELEE_DAMAGE,
                    AbilityType.SHIELD,
                    -1,
                    100,
                    300);
        });
    }

    @Test
    void creatingCharacterWithNegativeDefenseThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GameCharacter(
                    "Arthur",
                    CharacterClass.WARRIOR,
                    Role.MELEE_DAMAGE,
                    AbilityType.SHIELD,
                    100,
                    -1,
                    300);
        });
    }

    @Test
    void creatingCharacterWithNegativeHitpointsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GameCharacter(
                    "Arthur",
                    CharacterClass.WARRIOR,
                    Role.MELEE_DAMAGE,
                    AbilityType.SHIELD,
                    100,
                    100,
                    -300);
        });
    }

    @Test
    void setAttackRejectsNegativeValue() {
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        assertThrows(IllegalArgumentException.class, () -> {
            member.setAttack(-1);
        });
    }

    @Test
    void setDefenseRejectsNegativeValue() {
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        assertThrows(IllegalArgumentException.class, () -> {
            member.setDefense(-1);
        });
    }

    @Test
    void setHitpointsRejectsNegativeValue() {
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        assertThrows(IllegalArgumentException.class, () -> {
            member.setHitpoints(-1);
        });
    }

    @Test
    void creatingCharacterWithNullCharacterClassThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new GameCharacter(
                    "Arthur",
                    null,
                    Role.TANK,
                    AbilityType.SHIELD,
                    100,
                    100,
                    300);
        });
    }

    @Test
    void creatingCharacterWithNullRoleThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> {
            new GameCharacter(
                    "Arthur",
                    CharacterClass.WARRIOR,
                    null,
                    AbilityType.SHIELD,
                    100,
                    100,
                    300);
        });
    }

    @Test
    void creatingCharacterWithNullAbilityTypeThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> {
            new GameCharacter(
                    "Arthur",
                    CharacterClass.WARRIOR,
                    Role.TANK,
                    null,
                    100,
                    100,
                    300);
        });
    }

    @Test
    void creatingCharacterWithNullNameThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> {
            new GameCharacter(
                    null,
                    CharacterClass.WARRIOR,
                    Role.TANK,
                    AbilityType.SHIELD,
                    100,
                    100,
                    300);
        });
    }

    @Test
    void creatingCharacterWithBlankNameThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> {
            new GameCharacter(
                    "",
                    CharacterClass.WARRIOR,
                    Role.TANK,
                    AbilityType.SHIELD,
                    100,
                    100,
                    300);
        });
    }

    @Test
    void creatingCharacterWithWhitespaceNameThrowsException() {

        assertThrows(IllegalArgumentException.class, () -> {
            new GameCharacter(
                    "    ",
                    CharacterClass.WARRIOR,
                    Role.TANK,
                    AbilityType.SHIELD,
                    100,
                    100,
                    300);
        });
    }

    @Test
    void setRoleThrowsExceptionForNull() {
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);

        assertThrows(IllegalArgumentException.class, () -> {
            member.setRole(null);
        });
    }

    @Test
    void setCharacterClassThrowsExceptionForNull() {
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);

        assertThrows(IllegalArgumentException.class, () -> {
            member.setCharacterClass(null);
        });
    }

    @Test
    void setAbilityTypeThrowsExceptionForNull() {
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);

        assertThrows(IllegalArgumentException.class, () -> {
            member.setAbilityType(null);
        });
    }

    @Test
    void setNameThrowsExceptionForNull() {
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);

        assertThrows(IllegalArgumentException.class, () -> {
            member.setName(null);
        });
    }

    @Test
    void setNameThrowsExceptionForEmptyString() {
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);

        assertThrows(IllegalArgumentException.class, () -> {
            member.setName("");
        });
    }

    @Test
    void setNameThrowsExceptionForWhitespace() {
        GameCharacter member = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);

        assertThrows(IllegalArgumentException.class, () -> {
            member.setName("    ");
        });
    }
}