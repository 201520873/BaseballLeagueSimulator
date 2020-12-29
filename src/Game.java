
public abstract class Game {
	private Team home; // 홈팀.
	private Team away; // 원정팀.
	private int[] stat = new int[5]; // 결과 저장용 배열.
	
	public int getHomeWin() // Series 동안의 home팀의 승수를 반환.
	{
		return stat[0];
	}
	
	public int getDraw() // Series 동안의 비긴 수를 반환.
	{
		return stat[1];
	}
	
	public int getAwayWin() // Series 동안의 away팀의 승수를 반환.
	{
		return stat[2];
	}
	
	public int getHomeScore() // Series 동안의 home팀의 득점을 반환.
	{
		return stat[3];
	}
	
	public int getAwayScore() // Series 동안의 away팀의 득점을 반환.
	{
		return stat[4];
	}
}
