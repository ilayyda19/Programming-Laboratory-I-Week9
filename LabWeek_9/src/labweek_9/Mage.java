package labweek_9;


public class Mage extends GameCharacter implements Attacker, Supporter {
    
    private int mana;

    public Mage(String name, int level, int maxHealth, int mana) {
        super(name, level, maxHealth);
        this.mana = Math.max(0, mana);
    }

    public int getMana() {
        return mana;
    }

    @Override
    public int calculatePower() {
        return getLevel() * 8 + mana;
    }

    @Override
    public int attack(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        if (mana < 20) {
            System.out.println(getName() + " does not have enough mana (20 required) to attack!");
            return 0;
        }
        int dmg = calculatePower() / 3;
        mana -= 20;
        return target.takeDamage(dmg);
    }

    @Override
    public int heal(GameCharacter target) {
        if (!isAlive() || target == null || !target.isAlive()) {
            return 0;
        }
        if (mana < 15) {
            System.out.println(getName() + " does not have enough mana (15 required) to heal!");
            return 0;
        }
        int healAmt = getLevel() * 2;
        mana -= 15;
        return target.restoreHealth(healAmt);
    }

    @Override
    public String getCharacterInfo() {
        return "[Mage] " + getName()
                + " | Lv: " + getLevel()
                + " | HP: " + getHealth() + "/" + getMaxHealth()
                + " | Mana: " + mana
                + " | Power: " + calculatePower()
                + " | Alive: " + isAlive();
    }
}
