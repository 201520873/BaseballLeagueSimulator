
public class Pitcher extends Player {
	
	private final String position; // 포지션.
	
	private final int maxStamina; // 투수의 최대 체력.
	private int stamina; // 투수의 현재 체력.
	
	private int scoreStat; // 실점 스탯.
	
	public Pitcher(String aName, Team aTeam, String aPosition, double aStrike, double aBall, double aHbp, double aInfly, double aOutfly, double aGround, double aHit, double aGap, double aTriple, double aPower, int aStamina)
	{
		super(aName, aTeam, aStrike, aBall, aHbp, aInfly, aOutfly, aGround, aHit, aGap, aTriple, aPower);
		position = aPosition;
		maxStamina = aStamina;
		stamina = aStamina;
		scoreStat = 0;
	}
	
	public String getPosition()
	{
		return position;
	}
	
	public int getStamina() // 현재 체력을 반환하는 함수.
	{
		return stamina;
	}
	
	public void setStamina() // 현재 체력을 최대 체력을 초기화하는 함수.
	{
		stamina = maxStamina;
	}
	
	public void minusStamina(int i) // 현재 체력을 i만큼 깎는 함수.
	{
		stamina -= i;
	}
	
	public void addScoreStat() // 실점 스탯 추가해주는 함수.
	{
		scoreStat++;
	}
	
	public double getEraStat() // 투수의 ERA를 반환하는 함수.
	{
		return ((double)scoreStat / ((double)getOutStat() / 3) * 9);
	}
	
	public double getInningStat() // 투수가 던진 이닝을 반환하는 함수.
	{
		return ((double)(getOutStat() / 3) + ((double)(getOutStat() % 3))/10);
	}
}
