import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameCharacterTest{
    
    @Test
    void creatingCharacterWithNegativeAttackThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            -1,
            100,
            300
        );
        });
    }
    @Test
    void creatingCharacterWithNegativeDefenseThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            -1,
            300
        );
        });
    }
    @Test
    void creatingCharacterWithNegativeHitpointsThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            -300
        );
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
            300
        );
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
            300
        );
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
            300
        );
        assertThrows(IllegalArgumentException.class, () -> {
            member.setHitpoints(-1);
        });
    }
    @Test
    void creatingCharacterWithNullRoleThrowsException() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            null,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        });
    }
    @Test
    void creatingCharacterWithNullClassThrowsException() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            GameCharacter member = new GameCharacter(
            "Arthur",
            null,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        });
    }
    @Test
    void creatingCharacterWithNullAbilityThrowsException() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            null,
            100,
            100,
            300
        );
        });
    }
    @Test
    void creatingCharacterWithNullNameThrowsException() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            GameCharacter member = new GameCharacter(
            null,
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        });
    }
    @Test
    void creatingCharacterWithBlankNameThrowsException() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            GameCharacter member = new GameCharacter(
            "",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        });
    }
    @Test
    void creatingCharacterWithWhiteSpaceNameThrowsException() {
        
        assertThrows(IllegalArgumentException.class, () -> {
            GameCharacter member = new GameCharacter(
            "    ",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        });
    }
    @Test
    void setRoleWithNullRejectsNull() {
        GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            member.setRole(null);
        });
    }
    @Test
    void setCharacterClassWithNullRejectsNull() {
        GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            member.setCharacterClass(null);
        });
    }
    @Test
    void setAbilityTypeWithNullRejectsNull() {
        GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            member.setAbilityType(null);
        });
    }
    @Test
    void setNameWithNullRejectsNull() {
        GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            member.setName(null);
        });
    }
    @Test
    void setNameWithEmptyStringRejectsEmptyString() {
        GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            member.setName("");
        });
    }
    @Test
    void setNameWithWhiteSpaceRejectsWhiteSpace() {
        GameCharacter member = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
        
        assertThrows(IllegalArgumentException.class, () -> {
            member.setName("    ");
        });
    }

}