import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyAnalyzerTest {

    @Test
    void countRolesReturnsCorrectRoleCounts() {
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,

                100,
                100,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.SHIELD,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);
        Map<Role, Integer> roleCounts = new PartyAnalyzer().countRoles(members);

        assertEquals(2, roleCounts.get(Role.MELEE_DAMAGE));
        assertEquals(1, roleCounts.get(Role.TANK));
        assertEquals(1, roleCounts.get(Role.HEALER));
    }

    @Test
    void checkIfPartyHasSynergy() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 1);
        roleCounts.put(Role.HEALER, 1);

        boolean results = partyAnalyzer.hasSynergy(roleCounts);
        assertTrue(results);
    }

    @Test
    void checkIfPartyHasSynergyWithDamageOnly() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.RANGED_DAMAGE, 1);

        boolean results = partyAnalyzer.hasSynergy(roleCounts);
        assertFalse(results);
    }

    @Test
    void checkIfPartyHasSynergyWithComplementaryOnly() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.HEALER, 1);

        boolean results = partyAnalyzer.hasSynergy(roleCounts);
        assertFalse(results);
    }

    @Test
    void calculateRoleRatingReturnsFiveForBalancedRoles() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 1);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(5, results);
    }

    @Test
    void calculateRoleRatingReturnsFourForSlightlyUnbalancedRoles() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 3);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(4, results);
    }

    @Test
    void calculateRoleRatingReturnsThreeForModeratelyUnbalancedRoles() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 5);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(3, results);
    }

    @Test
    void calculateRoleRatingReturnsTwoForHighlyUnbalancedRoles() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 7);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(2, results);
    }

    @Test
    void calculateRoleRatingReturnsOneForCompletelyUnbalancedRoles() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 8);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(1, results);
    }

    @Test
    void calculateRoleRatingReturnsZeroForEmptyMap() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(0, results);
    }

    @Test
    void countAbilityTypeReturnsCorrectAbilityTypeCounts() {
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                100,
                100,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.AOE,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);
        Map<AbilityType, Integer> abilityTypeCounts = new PartyAnalyzer().countAbilityTypes(members);
        assertEquals(2, abilityTypeCounts.get(AbilityType.SHIELD));
        assertEquals(1, abilityTypeCounts.get(AbilityType.TAUNT));
        assertEquals(1, abilityTypeCounts.get(AbilityType.AOE));

    }

    @Test
    void countUniqueAbilitiesReturnsCorrectCount() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 1);
        abilityCounts.put(AbilityType.HEALING, 1);
        abilityCounts.put(AbilityType.MOBILITY, 1);

        int results = partyAnalyzer.countUniqueAbilities(abilityCounts);

        assertEquals(3, results);
    }

    @Test
    void countUniqueAbilitiesReturnsZeroForEmptyMap() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        int results = partyAnalyzer.countUniqueAbilities(abilityCounts);

        assertEquals(0, results);
    }

    @Test
    void calculateAbilityRatingReturnsFiveForBalancedCategories() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();

        abilityCounts.put(AbilityType.AOE, 2);
        abilityCounts.put(AbilityType.HEALING, 2);
        abilityCounts.put(AbilityType.BUFF, 2);

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);

        assertEquals(5, results);
    }

    @Test
    void calculateAbilityRatingReturnsFourForSlightlyUnbalancedCategories() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 4);
        abilityCounts.put(AbilityType.HEALING, 3);
        abilityCounts.put(AbilityType.BUFF, 1);

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);

        assertEquals(4, results);
    }

    @Test
    void calculateAbilityRatingReturnsThreeForModeratelyUnbalancedCategories() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 5);
        abilityCounts.put(AbilityType.HEALING, 2);
        abilityCounts.put(AbilityType.BUFF, 1);

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);

        assertEquals(3, results);
    }

    @Test
    void calculateAbilityRatingReturnsTwoForHighlyUnbalancedCategories() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 6);
        abilityCounts.put(AbilityType.HEALING, 2);

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);

        assertEquals(2, results);
    }

    @Test
    void calculateAbilityRatingReturnsOneForSingleCategory() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 8);

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);

        assertEquals(1, results);
    }

    @Test
    void calculateAbilityRatingReturnsZeroForEmptyMap() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);

        assertEquals(0, results);
    }

    @Test
    void totalAttackCalculationIsCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                100,
                100,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.AOE,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(400, partyAnalyzer.calculateTotalAttack(members));
    }

    @Test
    void totalDefenseCalculationIsCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                100,
                100,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.AOE,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(400, partyAnalyzer.calculateTotalDefense(members));
    }

    @Test
    void totalHitpointsCalculationIsCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                100,
                100,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.AOE,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(1200, partyAnalyzer.calculateTotalHitpoints(members));
    }

    @Test
    void calculateStatRatingReturnsFiveForBalancedStats() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                100,
                100,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.AOE,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(5, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsFourForSlightlyUnbalancedStats() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                200,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                100,
                100,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.AOE,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(4, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsThreeForModeratelyUnbalancedStats() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                200,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                200,
                100,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.AOE,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(3, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsTwoForHighlyUnbalancedStats() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                200,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                200,
                100,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                200,
                100,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.AOE,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(2, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsOneForExtremelyUnbalancedStats() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                1000,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                1000,
                10,
                300);
        GameCharacter lancelot = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.TANK,
                AbilityType.SHIELD,
                1000,
                10,
                300);
        GameCharacter gywn = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.HEALER,
                AbilityType.AOE,
                1000,
                10,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(1, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsZeroForEmptyParty() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();

        List<GameCharacter> members = new ArrayList<>();

        assertEquals(0, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateTotalAttackThrowsExceptionForNullMember() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                1000,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(null);

        assertThrows(IllegalArgumentException.class, () -> {
            partyAnalyzer.calculateTotalAttack(members);
        });
    }

    @Test
    void countRolesThrowsExceptionForNullMember() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                1000,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(null);

        assertThrows(IllegalArgumentException.class, () -> {
            partyAnalyzer.countRoles(members);
        });
    }

    @Test
    void countAbilityTypesThrowsExceptionForNullMember() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                1000,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(null);

        assertThrows(IllegalArgumentException.class, () -> {
            partyAnalyzer.countAbilityTypes(members);
        });
    }

    @Test
    void calculateStatRatingReturnsFiveAtUpperThreshold() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                120,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(5, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsFourJustAboveFiveThreshold() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                120,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                102,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(4, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsFourWithinRange() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                120,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                140,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(4, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsThreeJustAboveFourThreshold() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                120,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                142,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(3, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsThreeWithinRange() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                120,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                200,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(3, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsTwoJustAboveThreeThreshold() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                120,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                202,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(2, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsTwoWithinRange() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                300,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                100,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(2, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateStatRatingReturnsOneAboveTwoThreshold() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.SHIELD,
                100,
                100,
                300);
        GameCharacter merlin = new GameCharacter(
                "Arthur",
                CharacterClass.WARRIOR,
                Role.MELEE_DAMAGE,
                AbilityType.TAUNT,
                302,
                100,
                300);
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(1, partyAnalyzer.calculateStatRating(members));
    }

    @Test
    void calculateRoleRatingReturnsFiveForOddPartyWithMoreDamageRoles() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 2);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(5, results);
    }

    @Test
    void calculateRoleRatingReturnsFiveForOddPartyWithMoreComplementaryRoles() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 1);
        roleCounts.put(Role.TANK, 2);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(5, results);
    }

    @Test
    void calculateRoleRatingReturnsFourForSkewedOddParty() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 3);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(4, results);
    }

    @Test
    void calculateAbilityRatingReturnsFiveForBalancedOddParty() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.SHIELD, 2);
        abilityCounts.put(AbilityType.AOE, 2);
        abilityCounts.put(AbilityType.MOBILITY, 1);
        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        assertEquals(5, results);
    }

    @Test
    void calculateAbilityRatingReturnsFourForSkewedOddParty() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.SHIELD, 4);
        abilityCounts.put(AbilityType.AOE, 1);
        abilityCounts.put(AbilityType.MOBILITY, 2);
        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        assertEquals(4, results);
    }
}