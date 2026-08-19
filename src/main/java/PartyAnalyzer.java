import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyAnalyzer {

    private void validateMembers(List<GameCharacter> members) {
    if (members != null && members.contains(null)) {
        throw new IllegalArgumentException();
    }
    }


    /* Role analysis section */
    public Map<Role, Integer> countRoles(List<GameCharacter> members){
        Map<Role, Integer> countRoles = new HashMap<>();

        if (members == null){
            return countRoles;
        }
        validateMembers(members);
        
        for (int i = 0; i < members.size(); i++){
            Role currentRole = members.get(i).getRole();
            countRoles.put(currentRole, countRoles.getOrDefault(currentRole, 0) + 1);
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
    /*Ability analysis section */

    public Map<AbilityType, Integer> countAbilityTypes(List<GameCharacter> members){
        Map<AbilityType, Integer> countAbilities = new HashMap<>();

        if (members == null){
            return countAbilities;
        }
        validateMembers(members);

        for (int i = 0; i < members.size(); i++){
            AbilityType currentAbilityType = members.get(i).getAbilityType();
            countAbilities.put(currentAbilityType, countAbilities.getOrDefault(currentAbilityType, 0) + 1);
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
        int maxCategory = Math.max(Math.max(defensiveAbilityCount, enemyInteractionsAbilityCount),utilityAbilityCount);
        int minCategory = Math.min(Math.min(defensiveAbilityCount, enemyInteractionsAbilityCount),utilityAbilityCount);
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
        validateMembers(members);
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
        validateMembers(members);
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
        validateMembers(members);
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
        double normalizedAttack = (double) totalAttack/ expectedAttack;
        return normalizedAttack;
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
        double normalizedDefense = (double) totalDefense/ expectedDefense;
        return normalizedDefense;
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
        double normalizedHitpoints = (double) totalHitpoints/ expectedHitpoints;
        return normalizedHitpoints;
    }

    public double calculateStatSpread(List<GameCharacter> members){
        if (members == null){
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

    public int calculateStatRating(List<GameCharacter> members){
        if (members == null){
            return 0;
        }
        double statDifference = calculateStatSpread(members);
        int partySize = members.size();
        double roundedStatDifference = Math.round(statDifference * 100.0)/ 100.0;
        if (partySize < 2){
            return 0;
        }
        if (roundedStatDifference <= 0.10){
            return 5;
        }
        else if (roundedStatDifference <= 0.30){
            return 4;
        }
        else if (roundedStatDifference <= 0.60){
            return 3;
        }
        else if (roundedStatDifference <= 1.00){
            return 2;
        }
        else {
            return 1;
        }
    }
}