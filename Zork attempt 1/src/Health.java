import java.util.Random;

class Health {
	static int hp = 100, damageAmount;
	static Random rand = new Random();
	
	public static void playerDamaged()
	{
		damageAmount = 0;
		damageAmount = rand.nextInt(15) + 5;
		hp -= damageAmount;
		System.out.println("You lost " + damageAmount + " health. You now have " + hp + " health");
	}
	public static void killPlayer()
	{
		hp -= 101;
	}
	
}
