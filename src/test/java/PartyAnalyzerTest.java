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
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
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
    void testIfPartyHasMeleeDamageRoleOrNot() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 1);
        

        boolean results = partyAnalyzer.hasDamage(roleCounts);
        
        assertTrue(results);
    }
    @Test 
    void testIfPartyHasNoDamageRole(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();

        boolean results = partyAnalyzer.hasDamage(roleCounts);

        assertFalse(results);
    }
    @Test
    void testIfPartyHasRangedDamageRoleOrNot() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.RANGED_DAMAGE, 1);

        boolean results = partyAnalyzer.hasDamage(roleCounts);
        
        assertTrue(results);
    }
    @Test
    void checkIfPartyHasComplementaryRole(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.HEALER, 1);

        boolean results = partyAnalyzer.hasComplementaryRole(roleCounts);
        assertTrue(results);
    }

    @Test
    void checkIfPartyDoesNotHaveComplementaryRole(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 1);

        boolean results = partyAnalyzer.hasComplementaryRole(roleCounts);
        assertFalse(results);
    }
    @Test
    void checkIfPartyHasOneComplementaryRole(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.TANK, 1);

        boolean results = partyAnalyzer.hasComplementaryRole(roleCounts);
        assertTrue(results);
    }
    @Test
    void checkIfPartyHasSynergy(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 1);
        roleCounts.put(Role.HEALER, 1);

        boolean results = partyAnalyzer.hasSynergy(roleCounts);
        assertTrue(results);
    }
    @Test
    void checkIfPartyHasSynergyWithDamageOnly(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.RANGED_DAMAGE, 1);

        boolean results = partyAnalyzer.hasSynergy(roleCounts);
        assertFalse(results);
    }
    @Test
    void checkIfPartyHasSynergyWithComplementaryOnly(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.HEALER, 1);

        boolean results = partyAnalyzer.hasSynergy(roleCounts);
        assertFalse(results);
    }
    @Test
    void testIfDamageRoleCountIsCorrect(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 2);
        roleCounts.put(Role.RANGED_DAMAGE, 3);
        roleCounts.put(Role.TANK, 1);

        int results = partyAnalyzer.countDamageRoles(roleCounts);
        assertEquals(5, results);
    }
    @Test
    void testIfComplementaryRoleCountIsCorrect(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.TANK, 2);
        roleCounts.put(Role.HEALER, 1);
        roleCounts.put(Role.SUPPORT, 2);
        roleCounts.put(Role.CONTROL, 1);

        int results = partyAnalyzer.countComplementaryRoles(roleCounts);
        assertEquals(6, results);
    }
    @Test
    void testIfRoleRatingIsFive(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 1);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(5, results);
    }
    @Test
    void testIfRoleRatingIsFour(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 3);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(4, results);
    }
    @Test
    void testIfRoleRatingIsThree(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 5);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(3, results);
    }
    @Test
    void testIfRoleRatingIsTwo(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 7);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(2, results);
    }
    @Test
    void testIfRoleRatingIsOne(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 8);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(1, results);
    }
    @Test
    void testIfRoleRatingIsZero(){
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
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);
    Map<AbilityType, Integer> abiliityTypeCounts = new PartyAnalyzer().countAbilityTypes(members);
        assertEquals(2, abiliityTypeCounts.get(AbilityType.SHIELD));
        assertEquals(1, abiliityTypeCounts.get(AbilityType.TAUNT));
        assertEquals(1, abiliityTypeCounts.get(AbilityType.AOE));

    }

    @Test
    void testIfPartyHasDefensiveAbilities() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.HEALING, 1);
        

        int results = partyAnalyzer.countDefensiveAbilities(abilityCounts);
        
        assertEquals(1, results);
    }
    @Test 
    void testIfPartyHasNoDefensiveAbilities(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();

        int results = partyAnalyzer.countDefensiveAbilities(abilityCounts);

        assertEquals(0, results);
    }
    @Test
    void testIfPartyHasEnemyInteractionAbilities() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 1);

        int results = partyAnalyzer.countEnemyInteractionAbilities(abilityCounts);
        
        assertEquals(1, results);
    }
    @Test
    void testIfPartyHasNoEnemyInteractionAbilities() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();

        int results = partyAnalyzer.countEnemyInteractionAbilities(abilityCounts);
        
        assertEquals(0, results);
    }
    @Test
    void testIfPartyHasUtilityAbilities() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.MOBILITY,1);

        int results = partyAnalyzer.countUtilityAbilities(abilityCounts);
        
        assertEquals(1, results);
    }

    @Test
    void testIfPartyHasNoUtilityAbilities() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();

        int results = partyAnalyzer.countUtilityAbilities(abilityCounts);
        
        assertEquals(0, results);
    }

    @Test
    void testIfPartyHasUniqueAbilities() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE,1);
        abilityCounts.put(AbilityType.HEALING,1);
        abilityCounts.put(AbilityType.MOBILITY,1);

        int results = partyAnalyzer.countUniqueAbilities(abilityCounts);
        
        assertEquals(3, results);
    }

    @Test
    void testIfPartyHasNOUniqueAbilities() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        int results = partyAnalyzer.countUniqueAbilities(abilityCounts);
        
        assertEquals(0, results);
    }

    @Test
    void testIfCategorySpreadIsCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 2);
        abilityCounts.put(AbilityType.HEALING, 2);
        abilityCounts.put(AbilityType.BUFF, 2);
        

        int results = partyAnalyzer.calculateCategorySpread(abilityCounts);
        
        assertEquals(0, results);
    }

    @Test
    void testIfAbilityRatingIsFive() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();

        abilityCounts.put(AbilityType.AOE, 2);
        abilityCounts.put(AbilityType.HEALING, 2);
        abilityCounts.put(AbilityType.BUFF, 2);
        

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        
        assertEquals(5, results);
    }
    @Test
    void testIfAbilityRatingIsFour() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 4);
        abilityCounts.put(AbilityType.HEALING, 3);
        abilityCounts.put(AbilityType.BUFF, 1);
        
        

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        
        assertEquals(4, results);
    }

    @Test
    void testIfAbilityRatingIsThree() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 5);
        abilityCounts.put(AbilityType.HEALING, 2);
        abilityCounts.put(AbilityType.BUFF, 1);
        

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        
        assertEquals(3, results);
    }

    @Test
    void testIfAbilityRatingIsTwo() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 6);
        abilityCounts.put(AbilityType.HEALING, 2);

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        
        assertEquals(2, results);
    }

    @Test
    void testIfAbilityRatingIsOne() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.AOE, 8);
        

        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        
        assertEquals(1, results);
    }

    @Test
    void testIfAbilityRatingIsZero() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        
        assertEquals(0, results);
    }

    @Test
    void testTotalAttackCaculationisCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(400, partyAnalyzer.calculateTotalAttack(members));
    }

    @Test
    void testTotalDefenseCaculationisCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(400, partyAnalyzer.calculateTotalDefense(members));
    }

    @Test
    void testTotalHitpointsCaculationisCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(1200, partyAnalyzer.calculateTotalHitpoints(members));
    }

    @Test
    void testNormalizedAttackCaculationisCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(1.0, partyAnalyzer.normalizeAttack(members));
    }

    @Test
    void testNormalizedAttackCaculationisNotCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
    List<GameCharacter> members = new ArrayList<>();
        assertEquals(0.0, partyAnalyzer.normalizeAttack(members));
    }

    @Test
    void testNormalizedDefenseCaculationisCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(1.0, partyAnalyzer.normalizeDefense(members));
    }

    @Test
    void testNormalizedDefenseCaculationisNotCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
    List<GameCharacter> members = new ArrayList<>();

        assertEquals(0.0, partyAnalyzer.normalizeDefense(members));
    }
    @Test
    void testNormalizedHitpointsCaculationisCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(1.0, partyAnalyzer.normalizeHitpoints(members));
    }

    @Test
    void testNormalizedHitpointsCaculationisNotCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();

    List<GameCharacter> members = new ArrayList<>();
        assertEquals(0.0, partyAnalyzer.normalizeHitpoints(members));
    }
    @Test
    void testIfCalculateStatSpreadIsCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(0.0, partyAnalyzer.calculateStatSpread(members));
    }

    @Test
    void testIfCalculateStatSpreadIsNotCorrect() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        
    List<GameCharacter> members = new ArrayList<>();
    
        assertEquals(0.0, partyAnalyzer.calculateStatSpread(members));
    }

    @Test
    void testIfStatRatingIsFive() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(5, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingIsFour() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            200,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(4, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingIsThree() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            200,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            200,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(3, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingIsTwo() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            200,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            200,
            100,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            200,
            100,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(2, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingIsOne() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            1000,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            1000,
            10,
            300
        );
    GameCharacter lancelot = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.TANK,
            AbilityType.SHIELD,
            1000,
            10,
            300
        );
    GameCharacter gywn = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.HEALER,
            AbilityType.AOE,
            1000,
            10,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);
        members.add(lancelot);
        members.add(gywn);

        assertEquals(1, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingIsZero() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        
    List<GameCharacter> members = new ArrayList<>();
    
        assertEquals(0, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testTotalAttackCalculationWithNull() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            1000,
            100,
            300
        );
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(null);

        assertThrows(IllegalArgumentException.class, () -> {
            partyAnalyzer.calculateTotalAttack(members);
        });
    }
    @Test
    void testTotalRoleCountwithNull() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            1000,
            100,
            300
        );
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(null);

        assertThrows(IllegalArgumentException.class, () -> {
            partyAnalyzer.countRoles(members);
        });
    }
    @Test
    void testTotalAbilityCountWithNull() {
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            1000,
            100,
            300
        );
        List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(null);

        assertThrows(IllegalArgumentException.class, () -> {
            partyAnalyzer.countAbilityTypes(members);
        });
    }
    @Test
    void testIfStatRatingReturnsExpectedValueOfFive(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            120,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(5, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingReturnsExpectedValueOfFourJustAboveThreshold(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            120,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            102,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(4, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingReturnsExpectedValueOfFour(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            120,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            140,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(4, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingReturnsExpectedValueOfThreeJustAboveThreshold(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            120,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            142,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(3, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingReturnsExpectedValueOfThree(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            120,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            200,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(3, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingReturnsExpectedValueOfTwoJustAboveThreshold(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            120,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            202,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(2, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingReturnsExpectedValueOfTwo(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            300,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            100,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(2, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfStatRatingReturnsExpectedValueOfOneAboveThreshold(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        GameCharacter arthur = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.SHIELD,
            100,
            100,
            300
        );
    GameCharacter merlin = new GameCharacter(
            "Arthur",
            CharacterClass.WARRIOR,
            Role.MELEE_DAMAGE,
            AbilityType.TAUNT,
            302,
            100,
            300
        );
    List<GameCharacter> members = new ArrayList<>();
        members.add(arthur);
        members.add(merlin);

        assertEquals(1, partyAnalyzer.calculateStatRating(members));
    }
    @Test
    void testIfCountRolesRatingReturnsCorrectRatingWithOddParties(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 2);
        roleCounts.put(Role.TANK, 1);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(5, results);
    }
    @Test
    void testRoleCountRatingWithMoreComplementaryRoles(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 1);
        roleCounts.put(Role.TANK, 2);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(5, results);
    }
    @Test
    void testRoleCountRatingWithSkewedOddPartyNumber(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<Role, Integer> roleCounts = new HashMap<>();
        roleCounts.put(Role.MELEE_DAMAGE, 3);
        int results = partyAnalyzer.calculateRoleRating(roleCounts);
        assertEquals(4, results);
    }
    @Test
    void testIfCountAbilityRatingReturnsCorrectRatingWithOddParties(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.SHIELD, 2);
        abilityCounts.put(AbilityType.AOE, 2);
        abilityCounts.put(AbilityType.MOBILITY, 1);
        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        assertEquals(5, results);
    }
    @Test
    void testAbilityCountRatingWithSkewedOddPartyNumber(){
        PartyAnalyzer partyAnalyzer = new PartyAnalyzer();
        Map<AbilityType, Integer> abilityCounts = new HashMap<>();
        abilityCounts.put(AbilityType.SHIELD, 4);
        abilityCounts.put(AbilityType.AOE, 1);
        abilityCounts.put(AbilityType.MOBILITY, 2);
        int results = partyAnalyzer.calculateAbilityRating(abilityCounts);
        assertEquals(4, results);
    }
}