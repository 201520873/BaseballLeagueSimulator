
public class Batter extends Player {

	private Pitcher own; // 타자 출루시 책임투수 표시.
	private int rbiStat; // 타점 스탯.
	
	public Batter(String aName, Team aTeam, double aStrike, double aBall, double aHbp, double aInfly, double aOutfly, double aGround, double aHit, double aGap, double aTriple, double aPower)
	{
		super(aName, aTeam, aStrike, aBall, aHbp, aInfly, aOutfly, aGround, aHit, aGap, aTriple, aPower);
		own = null;
		rbiStat = 0;
	}
	
	/*
	 * Getter 함수. (Overriding)
	 */
	
	public Pitcher getOwn() // 타자의 책임투수를 반환.
	{
		return own;
	}

	public void setOwn(Pitcher P) // 책임투수 설정.
	{
		own = P;
	}

	public void addRbiStat() // 타점 추가.
	{
		rbiStat++;
	}
	
	public int getRbiStat() // 타점 반환.
	{
		return rbiStat;
	}

	public double getAvgStat() // 타율 스탯 계산.
	{
		return ((double)(getHitStat() + getGapStat() + getTripleStat() + getPowerStat()) / (double)(getStrikeStat() + getOutStat() + getHitStat() + getGapStat() + getTripleStat() + getPowerStat()));
	}
	
	public double getObpStat() // 출루율 스탯 계산.
	{
		return ((double)(getBallStat() + getHitStat() + getGapStat() + getTripleStat() + getPowerStat()) / (double)(getStrikeStat() + getBallStat() + getOutStat() + getHitStat() + getGapStat() + getTripleStat() + getPowerStat()));
	}
	
	public double getSlgStat() // 장타율 스탯 계산.
	{
		return ((double)(getHitStat() + (getGapStat() * 2) + (getTripleStat() * 3) + (getPowerStat() * 4)) / (double)(getStrikeStat() + getOutStat() + getHitStat() + getGapStat() + getTripleStat() + getPowerStat()));
	}
}
