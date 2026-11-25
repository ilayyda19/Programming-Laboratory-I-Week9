package labweek_9;


public class Warrior extends GameCharacter implements Attacker {
    
    private int armor;

    public Warrior(String name, int level, int maxHealth, int armor) {
        super(name, level, maxHealth);
        this.armor = Math.max(0, armor);
    }

    public int getArmor() {
        return armor;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 10 + armor * 2;
    }

    @Override
    public int takeDamage(int damage) {
        if (damage <= 0 || !isAlive()) {
            return 0;
        }
        int reducedDamage = damage - (armor / 2);
        int actualDamage = Math.max(1, reducedDamage);
        return super.takeDamage(actualDamage);
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        int dmg = calculatePower() / 4;
        return target.takeDamage(dmg);
    }

    @Override
    public String getCharacterInfo() {
        return "[Warrior] " + getName()
                + " | Lv: " + getLevel()
                + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Armor: " + armor
                + " | Power: " + calculatePower()
                + " | Alive: " + isAlive();
    }
}