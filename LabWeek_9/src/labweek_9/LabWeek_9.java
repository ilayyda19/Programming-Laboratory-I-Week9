package labweek_9;


public class LabWeek_9 {

    
    public static void main(String[] args) {
        Team heroes = new Team("Vanguard");

       
        Warrior w1 = new Warrior("Arthur", 150, 140, 20);
        Mage m1 = new Mage("Merlin", 10, 90, 45);
        Healer h1 = new Healer("Elara", 8, 100, 25);

        heroes.addCharacter(w1);
        heroes.addCharacter(m1);
        heroes.addCharacter(h1);

    
        Warrior duplicateArthur = new Warrior("Arthur", 5, 100, 10);
        heroes.addCharacter(duplicateArthur);

    
        Warrior golem = new Warrior("Golem", 15, 260, 18);

       
        System.out.println("\n--- ROUND 1 ---");
        heroes.attackAll(golem);
        if (golem.isAlive()) {
            int golemDmg = golem.attack(w1);
            System.out.println(golem.getName() + " attacks " + w1.getName() + " for " + golemDmg + " damage.");
        }
        heroes.healTeam();

       
        System.out.println("\n--- ROUND 2 ---");
        heroes.attackAll(golem);
        if (golem.isAlive()) {
            int golemDmg = golem.attack(m1);
            System.out.println(golem.getName() + " attacks " + m1.getName() + " for " + golemDmg + " damage.");
        }
        heroes.healTeam();

       
        System.out.println("\n--- SEARCH & REMOVE ---");
        GameCharacter searched = heroes.findByName("Merlin");
        if (searched != null) {
            System.out.println("Found character: " + searched.getCharacterInfo());
        }

        boolean removed = heroes.removeCharacter("Elara");
        System.out.println("Removed Elara: " + removed);


        System.out.println("\n--- FINAL TEAM STATUS ---");
        heroes.displayTeam();
    }
}
