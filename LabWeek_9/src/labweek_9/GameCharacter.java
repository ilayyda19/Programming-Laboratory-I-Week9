package labweek_9;


public abstract class GameCharacter {
    private String name;
    private int level;
    private int health;
    private int maxHealth;

    public GameCharacter(String name, int level, int maxHealth) {
        this.name = name;

        if (level < 1 || level > 100) {
            System.out.println("Warning: Invalid level (" + level + ") for " + name + ". Setting level to 1.");
            this.level = 1;
        } else {
            this.level = level;
        }

        if (maxHealth <= 0) {
            System.out.println("Warning: Invalid maxHealth (" + maxHealth + ") for " + name + ". Setting maxHealth to 100.");
            this.maxHealth = 100;
        } else {
            this.maxHealth = maxHealth;
        }

        this.health = this.maxHealth;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    protected void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
        } else if (health > maxHealth) {
            this.health = maxHealth;
        } else {
            this.health = health;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int takeDamage(int damage) {
        if (damage <= 0 || !isAlive()) {
            return 0;
        }
        int previousHealth = health;
        setHealth(health - damage);
        return previousHealth - health;
    }

    public int restoreHealth(int amount) {
        if (!isAlive()) {
            return 0;
        }
        if (amount <= 0) {
            return 0;
        }
        int previousHealth = health;
        setHealth(health + amount);
        return health - previousHealth;
    }

    public abstract String getCharacterInfo();

    public abstract int calculatePower();
}
