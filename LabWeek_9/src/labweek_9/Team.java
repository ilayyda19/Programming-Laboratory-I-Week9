package labweek_9;


import java.util.ArrayList;

public class Team {
    
    private String teamName;
    private ArrayList<GameCharacter> members;

    public Team(String teamName) {
        this.teamName = teamName;
        this.members = new ArrayList<>();
    }

    public boolean addCharacter(GameCharacter character) {
        if (character == null) {
            return false;
        }
        if (findByName(character.getName()) != null) {
            System.out.println("Cannot add '" + character.getName() + "': duplicate name in team " + teamName + ".");
            return false;
        }
        members.add(character);
        return true;
    }

    public boolean removeCharacter(String name) {
        GameCharacter found = findByName(name);
        if (found != null) {
            members.remove(found);
            return true;
        }
        return false;
    }

    public GameCharacter findByName(String name) {
        for (GameCharacter c : members) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public void attackAll(GameCharacter target) {
        if (target == null || !target.isAlive()) {
            System.out.println("Target is already defeated or invalid.");
            return;
        }
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Attacker && target.isAlive()) {
                Attacker attacker = (Attacker) c;
                int dealt = attacker.attack(target);
                System.out.println(c.getName() + " attacks " + target.getName() + " for " + dealt + " damage.");
            }
        }
    }

    public void healTeam() {
        for (GameCharacter c : members) {
            if (c.isAlive() && c instanceof Supporter) {
                GameCharacter lowestTarget = null;
                double lowestRatio = Double.MAX_VALUE;

                for (GameCharacter candidate : members) {
                    if (candidate.isAlive()) {
                        double ratio = (double) candidate.getHealth() / candidate.getMaxHealth();
                        if (ratio < lowestRatio) {
                            lowestRatio = ratio;
                            lowestTarget = candidate;
                        }
                    }
                }

                if (lowestTarget != null) {
                    Supporter supporter = (Supporter) c;
                    int restored = supporter.heal(lowestTarget);
                    System.out.println(c.getName() + " heals " + lowestTarget.getName() + " for " + restored + " HP.");
                }
            }
        }
    }

    public int getTotalPower() {
        int total = 0;
        for (GameCharacter c : members) {
            if (c.isAlive()) {
                total += c.calculatePower();
            }
        }
        return total;
    }

    public void displayTeam() {
        System.out.println("=== Team: " + teamName + " (Total Alive Power: " + getTotalPower() + ") ===");
        for (GameCharacter c : members) {
            System.out.println(c.getCharacterInfo());
        }
    }

    public ArrayList<GameCharacter> getMembers() {
        return members;
    }
}
