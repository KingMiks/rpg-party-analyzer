import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyAnalyzer {
    private static final int BASE_ATTACK = 100;
    private static final int BASE_DEFENSE = 100;
    private static final int BASE_HITPOINTS = 300;
    private static final double STAT_RATING_5_MAX_SPREAD = 0.10;
    private static final double STAT_RATING_4_MAX_SPREAD = 0.30;
    private static final double STAT_RATING_3_MAX_SPREAD = 0.60;
    private static final double STAT_RATING_2_MAX_SPREAD = 1.00;

    private void validateMembers(List<GameCharacter> members) {
        if (members != null && members.contains(null)) {
            throw new IllegalArgumentException("Members list cannot contain null characters.");
        }
    }

    /* Role analysis section */

    /**
     * Counts how many party members have each role.
     *
     * @param members the list of party members to analyze.
     * @return a map containing each role and its number of occurrences;
     *         returns an empty map if the list is null.
     * @throws IllegalArgumentException if the list contains a null member.
     */
    public Map<Role, Integer> countRoles(List<GameCharacter> members) {
        Map<Role, Integer> countRoles = new HashMap<>();

        if (members == null) {
            return countRoles;
        }
        validateMembers(members);

        for (int i = 0; i < members.size(); i++) {
            Role currentRole = members.get(i).getRole();
            countRoles.put(currentRole, countRoles.getOrDefault(currentRole, 0) + 1);
        }
        return countRoles;
    }

    private boolean hasDamage(Map<Role, Integer> countRoles) {
        if (countRoles == null) {
            return false;
        }
        return (countRoles.containsKey(Role.MELEE_DAMAGE) || countRoles.containsKey(Role.RANGED_DAMAGE));
    }

    private boolean hasComplementaryRole(Map<Role, Integer> countRoles) {
        if (countRoles == null) {
            return false;
        }
        return (countRoles.containsKey(Role.CONTROL)) || (countRoles.containsKey(Role.HEALER)) ||
                (countRoles.containsKey(Role.TANK)) || (countRoles.containsKey(Role.SUPPORT));
    }

    /**
     * Determines whether the party contains both a damage role and a complementary
     * role.
     *
     * @param countRoles a map containing each role and its number of occurrences.
     * @return true if the party contains at least one damage role and at least one
     *         complementary role; false otherwise or if the map is null.
     */
    public boolean hasSynergy(Map<Role, Integer> countRoles) {
        if (countRoles == null) {
            return false;
        }
        return hasDamage(countRoles) && hasComplementaryRole(countRoles);
    }

    private int countDamageRoles(Map<Role, Integer> countRoles) {
        if (countRoles == null) {
            return 0;
        }
        int meleeRole = countRoles.getOrDefault(Role.MELEE_DAMAGE, 0);
        int rangedRole = countRoles.getOrDefault(Role.RANGED_DAMAGE, 0);
        return rangedRole + meleeRole;
    }

    private int countComplementaryRoles(Map<Role, Integer> countRoles) {
        if (countRoles == null) {
            return 0;
        }
        int tankRole = countRoles.getOrDefault(Role.TANK, 0);
        int supportRole = countRoles.getOrDefault(Role.SUPPORT, 0);
        int controlRole = countRoles.getOrDefault(Role.CONTROL, 0);
        int healerRole = countRoles.getOrDefault(Role.HEALER, 0);
        return tankRole + supportRole + controlRole + healerRole;
    }

    /**
     * Calculates a role-balance rating based on the distribution of damage
     * and complementary roles in the party.
     *
     * @param countRoles a map containing each role and its number of party members.
     * @return a rating from 1 to 5, where higher values indicate better role
     *         balance;
     *         returns 0 if the map is null or represents fewer than two members.
     */
    public int calculateRoleRating(Map<Role, Integer> countRoles) {
        if (countRoles == null) {
            return 0;
        }
        int damageRoles = countDamageRoles(countRoles);
        int complementaryRoles = countComplementaryRoles(countRoles);
        int partySize = damageRoles + complementaryRoles;
        if (partySize < 2) {
            return 0;
        }
        int distanceFromIdeal = Math.abs(Math.abs(damageRoles - complementaryRoles) - (partySize % 2));
        switch (distanceFromIdeal) {
            case 0:
                return 5;
            case 2:
                return 4;
            case 4:
                return 3;
            case 6:
                return 2;
            case 8:
                return 1;
            default:
                return 0;
        }
    }
    /* Ability analysis section */

    /**
     * Counts how many party members have each ability type.
     *
     * @param members the list of party members to analyze.
     * @return a map containing each ability type and its number of occurrences;
     *         returns an empty map if the list is null.
     * @throws IllegalArgumentException if the list contains a null member.
     */

    public Map<AbilityType, Integer> countAbilityTypes(List<GameCharacter> members) {
        Map<AbilityType, Integer> countAbilities = new HashMap<>();

        if (members == null) {
            return countAbilities;
        }
        validateMembers(members);

        for (int i = 0; i < members.size(); i++) {
            AbilityType currentAbilityType = members.get(i).getAbilityType();
            countAbilities.put(currentAbilityType, countAbilities.getOrDefault(currentAbilityType, 0) + 1);
        }
        return countAbilities;
    }

    private int countDefensiveAbilities(Map<AbilityType, Integer> countAbilities) {
        if (countAbilities == null) {
            return 0;
        }
        int healingAbility = countAbilities.getOrDefault(AbilityType.HEALING, 0);
        int reviveAbility = countAbilities.getOrDefault(AbilityType.REVIVE, 0);
        int shieldAbility = countAbilities.getOrDefault(AbilityType.SHIELD, 0);
        return healingAbility + shieldAbility + reviveAbility;
    }

    private int countEnemyInteractionAbilities(Map<AbilityType, Integer> countAbilities) {
        if (countAbilities == null) {
            return 0;
        }
        int crowdControlAbility = countAbilities.getOrDefault(AbilityType.CROWD_CONTROL, 0);
        int tauntAbility = countAbilities.getOrDefault(AbilityType.TAUNT, 0);
        int aoeAbility = countAbilities.getOrDefault(AbilityType.AOE, 0);
        return crowdControlAbility + tauntAbility + aoeAbility;
    }

    /**
     * Counts the number of unique ability types represented in the party.
     *
     * @param countAbilities a map containing each ability type and its number of
     *                       occurrences.
     * @return the number of unique ability types, or 0 if the map is null.
     */
    private int countUtilityAbilities(Map<AbilityType, Integer> countAbilities) {
        if (countAbilities == null) {
            return 0;
        }
        int buffAbility = countAbilities.getOrDefault(AbilityType.BUFF, 0);
        int debuffAbility = countAbilities.getOrDefault(AbilityType.DEBUFF, 0);
        int movementAbility = countAbilities.getOrDefault(AbilityType.MOBILITY, 0);
        return buffAbility + debuffAbility + movementAbility;
    }

    private int calculateCategorySpread(Map<AbilityType, Integer> countAbilities) {
        if (countAbilities == null) {
            return 0;
        }
        int defensiveAbilityCount = countDefensiveAbilities(countAbilities);
        int enemyInteractionsAbilityCount = countEnemyInteractionAbilities(countAbilities);
        int utilityAbilityCount = countUtilityAbilities(countAbilities);
        int maxCategory = Math.max(Math.max(defensiveAbilityCount, enemyInteractionsAbilityCount), utilityAbilityCount);
        int minCategory = Math.min(Math.min(defensiveAbilityCount, enemyInteractionsAbilityCount), utilityAbilityCount);
        int categoryDifference = maxCategory - minCategory;
        return categoryDifference;
    }

    public int countUniqueAbilities(Map<AbilityType, Integer> countAbilities) {
        if (countAbilities == null) {
            return 0;
        }
        return countAbilities.size();
    }

    /**
     * Calculates an ability type balance rating based on the distribution of
     * defensive abilities,
     * enemy interaction abilities, and utility abilities.
     *
     * @param countAbilities a map containing each ability type and its number of
     *                       party members.
     * @return a rating from 1 to 5, where higher values indicate better ability
     *         type balance;
     *         returns 0 if the map is null or represents fewer than two members.
     */
    public int calculateAbilityRating(Map<AbilityType, Integer> countAbilities) {
        if (countAbilities == null) {
            return 0;
        }
        int categoryDifference = calculateCategorySpread(countAbilities);
        int defensiveAbilityCount = countDefensiveAbilities(countAbilities);
        int enemyInteractionsAbilityCount = countEnemyInteractionAbilities(countAbilities);
        int utilityAbilityCount = countUtilityAbilities(countAbilities);
        int partySize = defensiveAbilityCount + enemyInteractionsAbilityCount + utilityAbilityCount;
        if (partySize < 2) {
            return 0;
        }
        if (categoryDifference <= 1) {
            return 5;
        } else if (categoryDifference <= 3) {
            return 4;
        } else if (categoryDifference <= 5) {
            return 3;
        } else if (categoryDifference <= 7) {
            return 2;
        } else {
            return 1;
        }
    }
    /* Stat calculation section */

    /**
     * Calculates the total attack value of all party members.
     *
     * @param members the list of party members whose attack values are totaled.
     * @return the combined attack value of all members, or 0 if the list is null.
     * @throws IllegalArgumentException if the list contains a null member.
     */
    public int calculateTotalAttack(List<GameCharacter> members) {
        if (members == null) {
            return 0;
        }
        validateMembers(members);
        int totalAttack = 0;
        for (int i = 0; i < members.size(); i++) {
            totalAttack += members.get(i).getAttack();
        }
        return totalAttack;
    }

    /**
     * Calculates the total defense value of all party members.
     *
     * @param members the list of party members whose defense values are totaled.
     * @return the combined defense value of all members, or 0 if the list is null.
     * @throws IllegalArgumentException if the list contains a null member.
     */
    public int calculateTotalDefense(List<GameCharacter> members) {
        if (members == null) {
            return 0;
        }
        validateMembers(members);
        int totalDefense = 0;
        for (int i = 0; i < members.size(); i++) {
            totalDefense += members.get(i).getDefense();
        }
        return totalDefense;
    }

    /**
     * Calculates the total hitpoints of all party members.
     *
     * @param members the list of party members whose hitpoints are totaled.
     * @return the combined hitpoints of all members, or 0 if the list is null.
     * @throws IllegalArgumentException if the list contains a null member.
     */
    public int calculateTotalHitpoints(List<GameCharacter> members) {
        if (members == null) {
            return 0;
        }
        validateMembers(members);
        int totalHitpoints = 0;
        for (int i = 0; i < members.size(); i++) {
            totalHitpoints += members.get(i).getHitpoints();
        }
        return totalHitpoints;
    }

    private double normalizeAttack(List<GameCharacter> members) {
        if (members == null) {
            return 0.0;
        }
        int partySize = members.size();
        if (partySize < 2) {
            return 0.0;
        }
        int totalAttack = calculateTotalAttack(members);
        int expectedAttack = partySize * BASE_ATTACK;
        double normalizedAttack = (double) totalAttack / expectedAttack;
        return normalizedAttack;
    }

    private double normalizeDefense(List<GameCharacter> members) {
        if (members == null) {
            return 0.0;
        }
        int partySize = members.size();
        if (partySize < 2) {
            return 0.0;
        }
        int totalDefense = calculateTotalDefense(members);
        int expectedDefense = partySize * BASE_DEFENSE;
        double normalizedDefense = (double) totalDefense / expectedDefense;
        return normalizedDefense;
    }

    private double normalizeHitpoints(List<GameCharacter> members) {
        if (members == null) {
            return 0.0;
        }
        int partySize = members.size();
        if (partySize < 2) {
            return 0.0;
        }
        int totalHitpoints = calculateTotalHitpoints(members);
        int expectedHitpoints = partySize * BASE_HITPOINTS;
        double normalizedHitpoints = (double) totalHitpoints / expectedHitpoints;
        return normalizedHitpoints;
    }

    private double calculateStatSpread(List<GameCharacter> members) {
        if (members == null) {
            return 0;
        }
        double attackStats = normalizeAttack(members);
        double defenseStats = normalizeDefense(members);
        double hitpointsStats = normalizeHitpoints(members);
        double maxStat = Math.max(Math.max(attackStats, defenseStats), hitpointsStats);
        double minStat = Math.min(Math.min(attackStats, defenseStats), hitpointsStats);
        double statDifference = maxStat - minStat;
        return statDifference;
    }

    /**
     * Calculates a party's stat-balance rating based on the distribution of
     * normalized
     * attack, defense, and hitpoints values.
     *
     * @param members a list containing the party members whose stats are analyzed.
     * @return a rating from 1 to 5, where higher values represent better stat
     *         balance;
     *         returns 0 if the list is null or contains fewer than two members.
     */
    public int calculateStatRating(List<GameCharacter> members) {
        if (members == null) {
            return 0;
        }
        double statDifference = calculateStatSpread(members);
        int partySize = members.size();
        double roundedStatDifference = Math.round(statDifference * 100.0) / 100.0;
        if (partySize < 2) {
            return 0;
        }
        if (roundedStatDifference <= STAT_RATING_5_MAX_SPREAD) {
            return 5;
        } else if (roundedStatDifference <= STAT_RATING_4_MAX_SPREAD) {
            return 4;
        } else if (roundedStatDifference <= STAT_RATING_3_MAX_SPREAD) {
            return 3;
        } else if (roundedStatDifference <= STAT_RATING_2_MAX_SPREAD) {
            return 2;
        } else {
            return 1;
        }
    }
}