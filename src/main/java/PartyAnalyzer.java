import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyAnalyzer {
    /* Role analysis section */
    public Map<Role, Integer> countRoles(List<GameCharacter> members){
        Map<Role, Integer> countRoles = new HashMap<>();

        if (members == null){
            return countRoles;
        }
        
        for (int i = 0; i < members.size(); i++){
            Role currentRole = members.get(i).getRole();

            if (!countRoles.containsKey(currentRole)){
                countRoles.put(currentRole, 1);
            }
            else {
                int currentCount = countRoles.get(currentRole);
                int updateCount = currentCount + 1;
                countRoles.put(currentRole, updateCount);
            }
        }
        return countRoles;
    }

    public boolean hasDamage(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return false;
        }
        return (countRoles.containsKey(Role.MELEE_DAMAGE) || countRoles.containsKey(Role.RANGED_DAMAGE));
    }
    public boolean hasComplementaryRole(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return false;
        }
        return (countRoles.containsKey(Role.CONTROL)) || (countRoles.containsKey(Role.HEALER)) || 
        (countRoles.containsKey(Role.TANK)) ||(countRoles.containsKey(Role.SUPPORT));
    }
    public boolean hasSynergy(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return false;
        }
        return hasDamage(countRoles) && hasComplementaryRole(countRoles);
    }

    public int countDamageRoles(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return 0;
        }
        int meleeRole = countRoles.getOrDefault(Role.MELEE_DAMAGE, 0);
        int rangedRole = countRoles.getOrDefault(Role.RANGED_DAMAGE, 0);
        return rangedRole + meleeRole;
    }
    public int countComplementaryRoles(Map<Role,Integer> countRoles){
        if (countRoles == null){
            return 0;
        }
        int tankRole = countRoles.getOrDefault(Role.TANK, 0);
        int supportRole = countRoles.getOrDefault(Role.SUPPORT, 0);
        int controlRole = countRoles.getOrDefault(Role.CONTROL, 0);
        int healerRole = countRoles.getOrDefault(Role.HEALER, 0);
        return tankRole + supportRole + controlRole + healerRole;
    }
    public int calculateRoleRating(Map<Role, Integer> countRoles){
        if (countRoles == null){
            return 0;
        }
        int damageRoles = countDamageRoles(countRoles);
        int complementaryRoles = countComplementaryRoles(countRoles);
        int partySize = damageRoles + complementaryRoles;
        if (partySize < 2){
            return 0;
        }
        int actualDifference = Math.abs(damageRoles - complementaryRoles);
        int idealDifference = partySize % 2;
        int distanceFromIdeal = actualDifference - idealDifference;
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
    /*Ability analysis section */

    public Map<AbilityType, Integer> countAbilityTypes(List<GameCharacter> members){
        Map<AbilityType, Integer> countAbilities = new HashMap<>();

        if (members == null){
            return countAbilities;
        }

        for (int i = 0; i < members.size(); i++){
            AbilityType currentAbilityType = members.get(i).getAbilityType();

            if (!countAbilities.containsKey(currentAbilityType)){
                countAbilities.put(currentAbilityType, 1);
            }
            else {
                int currentCount = countAbilities.get(currentAbilityType);
                int updateCount = currentCount + 1;
                countAbilities.put(currentAbilityType, updateCount);
            }
        }
        return countAbilities;
    }

    public int countDefensiveAbilities(Map<AbilityType, Integer> countAbilities){
        if (countAbilities == null){
            return 0;
        }
        int healingAbility = countAbilities.getOrDefault(AbilityType.HEALING, 0);
        int reviveAbility = countAbilities.getOrDefault(AbilityType.REVIVE, 0);
        int shieldAbility = countAbilities.getOrDefault(AbilityType.SHIELD, 0);
        return healingAbility + shieldAbility + reviveAbility;
    }

    public int countEnemyInteractionAbilities(Map<AbilityType, Integer> countAbilities){
        if (countAbilities == null){
            return 0;
        }
        int crowdControlAbility = countAbilities.getOrDefault(AbilityType.CROWD_CONTROL, 0);
        int tauntAbility = countAbilities.getOrDefault(AbilityType.TAUNT, 0);
        int aoeAbility = countAbilities.getOrDefault(AbilityType.AOE, 0);
        return crowdControlAbility + tauntAbility + aoeAbility;
    }

    public int countUtilityAbilities(Map<AbilityType, Integer> countAbilities){
        if (countAbilities == null){
            return 0;
        }
        int buffAbility = countAbilities.getOrDefault(AbilityType.BUFF, 0);
        int debuffAbility = countAbilities.getOrDefault(AbilityType.DEBUFF, 0);
        int movementAbility = countAbilities.getOrDefault(AbilityType.MOBILITY, 0);
        return buffAbility + debuffAbility + movementAbility;
    }

    public int calculateCategorySpread(Map<AbilityType, Integer> countAbilities){
        if (countAbilities == null){
            return 0;
        }
        int defensiveAbilityCount = countDefensiveAbilities(countAbilities);
        int enemyInteractionsAbilityCount = countEnemyInteractionAbilities(countAbilities);
        int utilityAbilityCount = countUtilityAbilities(countAbilities);
        int findMax = Math.max(defensiveAbilityCount, enemyInteractionsAbilityCount);
        int maxCategory = Math.max(findMax, utilityAbilityCount);
        int findMin = Math.min(defensiveAbilityCount, enemyInteractionsAbilityCount);
        int minCategory = Math.min(findMin, utilityAbilityCount);
        int categoryDifference = maxCategory - minCategory;
        return categoryDifference;
    }

    public int countUniqueAbilities (Map<AbilityType, Integer> countAbilities){
        if (countAbilities == null){
            return 0;
        }
        return countAbilities.size();
    }

    public int calculateAbilityRating(Map<AbilityType, Integer> countAbilities){
        if (countAbilities == null){
            return 0;
        }
        int categoryDifference = calculateCategorySpread(countAbilities);
        int defensiveAbilityCount = countDefensiveAbilities(countAbilities);
        int enemyInteractionsAbilityCount = countEnemyInteractionAbilities(countAbilities);
        int utilityAbilityCount = countUtilityAbilities(countAbilities);
        int partySize = defensiveAbilityCount + enemyInteractionsAbilityCount + utilityAbilityCount;
        if (partySize < 2){
            return 0;
        }
        if (categoryDifference <= 1){
            return 5;
        }
        else if (categoryDifference <= 3){
            return 4;
        }
        else if (categoryDifference <= 5){
            return 3;
        }
        else if (categoryDifference <= 7){
            return 2;
        }
        else {
            return 1;
        }
    }
    /* Stat calculation section */

    public int calculateTotalAttack(List<GameCharacter> members){
        if (members == null){
            return 0;
        }
        int totalAttack = 0;
        for (int i = 0; i < members.size(); i++){
            totalAttack += members.get(i).getAttack();
        }
        return totalAttack;
    }

    public int calculateTotalDefense(List<GameCharacter> members){
        if (members == null){
            return 0;
        }
        int totalDefense = 0;
        for (int i = 0; i < members.size(); i++){
            totalDefense += members.get(i).getDefense();
        }
        return totalDefense;
    }


    public int calculateTotalHitpoints(List<GameCharacter> members){
        if (members == null){
            return 0;
        }
        int totalHitpoints = 0;
        for (int i = 0; i < members.size(); i++){
            totalHitpoints += members.get(i).getHitpoints();
        }
        return totalHitpoints;
    }
    public double normalizeAttack(List<GameCharacter> members){
        if (members == null){
            return 0.0;
        }
        int partySize = members.size();
        if (partySize < 2){
            return 0.0;
        }
        int totalAttack = calculateTotalAttack(members);
        int expectedAttack = partySize * 100;
        double normalizeAttack = (double)totalAttack/expectedAttack;
        return normalizeAttack;
    }
    public double normalizeDefense(List<GameCharacter> members){
        if (members == null){
            return 0.0;
        }
        int partySize = members.size();
        if (partySize < 2){
            return 0.0;
        }
        int totalDefense = calculateTotalDefense(members);
        int expectedDefense = partySize *100;
        double normalizeDefense = (double)totalDefense/expectedDefense;
        return normalizeDefense;
    }
    public double normalizeHitpoints(List<GameCharacter> members){
        if (members == null){
            return 0.0;
        }
        int partySize = members.size();
        if (partySize < 2){
            return 0.0;
        }
        int totalHitpoints = calculateTotalHitpoints(members);
        int expectedHitpoints = partySize * 300;
        double normalizeHitpoints = (double)totalHitpoints/expectedHitpoints;
        return normalizeHitpoints;
    }

    public double calculateStatSpread(List<GameCharacter> members){
        if (members == null){
            return 0;
        }
        double attackStatsCount = normalizeAttack(members);
        double defenseStatsCount = normalizeDefense(members);
        double hitpointsStatsCount = normalizeHitpoints(members);
        double findMax = Math.max(attackStatsCount, defenseStatsCount);
        double maxStat = Math.max(findMax, hitpointsStatsCount);
        double findMin = Math.min(attackStatsCount, defenseStatsCount);
        double minStat = Math.min(findMin, hitpointsStatsCount);
        double statDifference = maxStat - minStat;
        return statDifference;
    }

    public int calculateStatRating(List<GameCharacter> members){
        if (members == null){
            return 0;
        }
        double statDifference = calculateStatSpread(members);
        int partySize = members.size();
        if (partySize < 2){
            return 0;
        }
        if (statDifference <= 0.10){
            return 5;
        }
        else if (statDifference <= 0.30){
            return 4;
        }
        else if (statDifference <= 0.60){
            return 3;
        }
        else if (statDifference <= 1.00){
            return 2;
        }
        else {
            return 1;
        }
    }
}