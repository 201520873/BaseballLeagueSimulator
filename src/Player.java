
public class Player {

	private final String name; // ÀÌ¸§.
	private final Team team; // ÆÀ.
	private final double strike; // »ïÁø.
	private final double ball; // º¼³Ý.
	private final double hbp; // »ç±¸.
	private final double infly; // ³»¾ß¶á°ø.
	private final double outfly; // ¿Ü¾ß¶á°ø.
	private final double ground; // ¶¥º¼.
	private final double hit; // ´ÜÅ¸.
	private final double gap; // 2·çÅ¸.
	private final double triple; // 3·çÅ¸.
	private final double power; // È¨·±.

	private int gameStat; // ÃâÀü ½ºÅÈ.
	private int strikeStat; // »ïÁø ½ºÅÈ.
	private int ballStat; // º¼³Ý ½ºÅÈ.
	private int hbpStat; // »ç±¸ ½ºÅÈ.
	private int hitStat; // ´ÜÅ¸ ½ºÅÈ.
	private int gapStat; // 2·çÅ¸ ½ºÅÈ.
	private int tripleStat; // 3·çÅ¸ ½ºÅÈ.
	private int powerStat; // È¨·± ½ºÅÈ.
	private int outStat; // ¾Æ¿ôÄ«¿îÆ® ½ºÅÈ.
	
	// , »ïÁø(17.6), º¼³Ý(9.5), »ç±¸(1.3), ³»¶á(7.8), ¿Ü¶á(16.4), ¶¥º¼(23.3), ´ÜÅ¸(17.3), 2·çÅ¸(4.1), 3·çÅ¸(0.3), È¨·±(2.4)
	public Player(String aName, Team aTeam, double aStrike, double aBall, double aHbp, double aInfly, double aOutfly, double aGround, double aHit, double aGap, double aTriple, double aPower)
	{
		name = aName;
		team = aTeam;
		strike = aStrike; // 17.6 (Ç¥ÁØ).
		ball = aBall; // 9.5 (Ç¥ÁØ).
		hbp = aHbp; // 1.3 (Ç¥ÁØ).
		infly = aInfly; // 7.8 (Ç¥ÁØ).
		outfly = aOutfly; // 16.4 (Ç¥ÁØ).
		ground = aGround; // 23.3 (Ç¥ÁØ).
		hit = aHit; // 17.3 (Ç¥ÁØ).
		gap = aGap; // 4.1 (Ç¥ÁØ).
		triple = aTriple; // 0.3 (Ç¥ÁØ).
		power = aPower; // 2.4 (Ç¥ÁØ).
		gameStat = 0;
		strikeStat = 0;
		ballStat = 0;
		hbpStat = 0;
		hitStat = 0;
		gapStat = 0;
		tripleStat = 0;
		powerStat = 0;
		outStat = 0;
	} 
	
	@Override
	public String toString()
	{
		return name;
	}	

	/*
	 * Getter ÇÔ¼öµé.
	 */
	
	public Team getTeam()
	{
		return team;
	}
	
	public double getStrike()
	{
		return strike;
	}
	
	public double getBall()
	{
		return ball;
	}
	
	public double getHbp()
	{
		return hbp;
	}
	
	public double getInfly()
	{
		return infly;
	}
	
	public double getOutfly()
	{
		return outfly;
	}
	
	public double getGround()
	{
		return ground;
	}
	
	public double getHit()
	{
		return hit;
	}
	
	public double getGap()
	{
		return gap;
	}
	
	public double getTriple()
	{
		return triple;
	}
	
	public double getPower()
	{
		return power;
	}

	public int getGameStat()
	{
		return gameStat;
	}

	public int getStrikeStat()
	{
		return strikeStat;
	}
	
	public int getBallStat()
	{
		return (ballStat + hbpStat);
	}
	
	public int getHitStat()
	{
		return hitStat;
	}
	
	public int getGapStat()
	{
		return gapStat;
	}
	
	public int getTripleStat()
	{
		return tripleStat;
	}
	
	public int getPowerStat()
	{
		return powerStat;
	}
	
	public int getOutStat()
	{
		return outStat;
	}

	/*
	 * Stat Ãß°¡½ÃÅ°´Â ÇÔ¼öµé.
	 */
	
	public void addGameStat()
	{
		gameStat++;
	}
	
	public void addStrikeStat()
	{
		strikeStat++;
	}
	
	public void addBallStat()
	{
		ballStat++;
	}
	
	public void addHbpStat()
	{
		hbpStat++;
	}
	
	public void addHitStat()
	{
		hitStat++;
	}

	public void addGapStat()
	{
		gapStat++;
	}

	public void addTripleStat()
	{
		tripleStat++;
	}

	public void addPowerStat()
	{
		powerStat++;
	}
	
	public void addOutStat()
	{
		outStat++;
	}

}
