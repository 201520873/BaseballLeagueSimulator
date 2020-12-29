
public class Series extends Game {
	private Team home; // 홈팀.
	private Team away; // 원정팀.
	private int towin;
	private int[] stat = new int[5]; // instance field.
	
	public Series(Team aHome, Team aAway, int aTowin) // Series 생성자.
	{
		home = aHome; // home팀.
		away = aAway; // away팀.
		towin = aTowin; // towin전제 진행.
	}
	
	public void proceed() // 하나의 시리즈를 진행시킴.
	{
		int count = 1; // count차전을 의미.
		if (towin == 4) // 7전 4선승제일 때. (한국시리즈)
		{
			do
			{
				if (count == 3 || count == 4 || count == 5) // 3,4,5차전은 홈-어웨이를 바꿔서 진행.
				{
					Match match = new Match(away, home, count);
					match.proceed(away, home, count);
					stat[2] += match.getHomeWin();
					stat[1] += match.getDraw();
					stat[0] += match.getAwayWin();
					stat[4] += match.getHomeScore();
					stat[3] += match.getAwayScore();
				}
				else
				{
					Match match = new Match(home, away, count);
					match.proceed(home, away, count);
					stat[0] += match.getHomeWin();
					stat[1] += match.getDraw();
					stat[2] += match.getAwayWin();
					stat[3] += match.getHomeScore();
					stat[4] += match.getAwayScore();
				}
				count++;
			} while(stat[0] <= (towin-1) && stat[2] <= (towin-1)); // 한쪽이 towin승할때까지 진행.
		}
		
		else if (towin == 3) // 5전 3선승제일 때. (플레이오프)
		{
			do
			{
				if (count == 3 || count == 4) // 3,4차전은 홈-어웨이를 바꿔서 진행.
				{
					Match match = new Match(away, home, count);
					match.proceed(away, home, count);
					stat[2] += match.getHomeWin();
					stat[1] += match.getDraw();
					stat[0] += match.getAwayWin();
					stat[4] += match.getHomeScore();
					stat[3] += match.getAwayScore();
				}
				else
				{
					Match match = new Match(home, away, count);
					match.proceed(home, away, count);
					stat[0] += match.getHomeWin();
					stat[1] += match.getDraw();
					stat[2] += match.getAwayWin();
					stat[3] += match.getHomeScore();
					stat[4] += match.getAwayScore();
				}
				count++;
			} while(stat[0] <= (towin-1) && stat[2] <= (towin-1)); // 한쪽이 towin승할때까지 진행.
		}
		
		else if (towin == 2) // 3전 2선승제일 때. (준플레이오프)
		{
			do
			{
				if (count == 2) // 2차전은 홈-어웨이를 바꿔서 진행.
				{
					Match match = new Match(away, home, count);
					match.proceed(away, home, count);
					stat[2] += match.getHomeWin();
					stat[1] += match.getDraw();
					stat[0] += match.getAwayWin();
					stat[4] += match.getHomeScore();
					stat[3] += match.getAwayScore();
				}
				else
				{
					Match match = new Match(home, away, count);
					match.proceed(home, away, count);
					stat[0] += match.getHomeWin();
					stat[1] += match.getDraw();
					stat[2] += match.getAwayWin();
					stat[3] += match.getHomeScore();
					stat[4] += match.getAwayScore();
				}
				count++;
			} while(stat[0] <= (towin-1) && stat[2] <= (towin-1)); // 한쪽이 towin승할때까지 진행.
		}
		else if (towin == 1) // 2전 1선승제일 때(경우에 따라 다름). (와일드카드)
		{
			do
			{
				Match match = new Match(home, away, count);
				match.proceed(home, away, count);
				stat[0] += match.getHomeWin();
				stat[1] += match.getDraw();
				stat[2] += match.getAwayWin();
				stat[3] += match.getHomeScore();
				stat[4] += match.getAwayScore();
				count++;
			} while(stat[0] == 0 && stat[1] == 0 && stat[2] <= 1); // home팀이 1승or1무를 하거나 away팀이 2승할때까지 진행.
		}
		//System.out.println("\n >> " + home + " " + stat[0] + " : " + stat[2] + " " + away); // 시리즈의 결과를 출력
	}
	
	public int getResult() // Series의 결과를 반환.
	{
		if (towin == 4)
		{
			if(stat[0] == 4 && stat[2] == 0)
			{
				return 0;
			}
			if(stat[0] == 4 && stat[2] == 1)
			{
				return 1;
			}
			if(stat[0] == 4 && stat[2] == 2)
			{
				return 2;
			}
			if(stat[0] == 4 && stat[2] == 3)
			{
				return 3;
			}
			if(stat[0] == 3 && stat[2] == 4)
			{
				return 4;
			}
			if(stat[0] == 2 && stat[2] == 4)
			{
				return 5;
			}
			if(stat[0] == 1 && stat[2] == 4)
			{
				return 6;
			}
			if(stat[0] == 0 && stat[2] == 4)
			{
				return 7;
			}
		}
		
		else if (towin == 3)
		{
			if(stat[0] == 3 && stat[2] == 0)
			{
				return 0;
			}
			if(stat[0] == 3 && stat[2] == 1)
			{
				return 1;
			}
			if(stat[0] == 3 && stat[2] == 2)
			{
				return 2;
			}
			if(stat[0] == 2 && stat[2] == 3)
			{
				return 3;
			}
			if(stat[0] == 1 && stat[2] == 3)
			{
				return 4;
			}
			if(stat[0] == 0 && stat[2] == 3)
			{
				return 5;
			}
		}

		else if (towin == 2)
		{
			if(stat[0] == 2 && stat[2] == 0)
			{
				return 0;
			}
			if(stat[0] == 2 && stat[2] == 1)
			{
				return 1;
			}
			if(stat[0] == 1 && stat[2] == 2)
			{
				return 2;
			}
			if(stat[0] == 0 && stat[2] == 2)
			{
				return 3;
			}
		}
		
		else if (towin == 1)
		{
			if(stat[0] == 1 && stat[1] == 0 && stat[2] == 0)
			{
				return 0;
			}
			if(stat[0] == 0 && stat[1] == 1 && stat[2] == 0)
			{
				return 1;
			}
			if(stat[0] == 1 && stat[1] == 0 && stat[2] == 1)
			{
				return 2;
			}
			if(stat[0] == 0 && stat[1] == 1 && stat[2] == 1)
			{
				return 3;
			}
			if(stat[0] == 0 && stat[1] == 0 && stat[2] == 2)
			{
				return 4;
			}
		}	
		return 100;
	}
	
	public Team getHome() // home팀을 반환.
	{
		return home;
	}
	
	public Team getAway() // away팀을 반환.
	{
		return away;
	}
	
	@Override
	public int getHomeWin() // Series 동안의 home팀의 승수를 반환.
	{
		return stat[0];
	}
	
	@Override
	public int getDraw() // Series 동안의 비긴 수를 반환.
	{
		return stat[1];
	}
	
	@Override
	public int getAwayWin() // Series 동안의 away팀의 승수를 반환.
	{
		return stat[2];
	}
	
	@Override
	public int getHomeScore() // Series 동안의 home팀의 득점을 반환.
	{
		return stat[3];
	}
	
	@Override
	public int getAwayScore() // Series 동안의 away팀의 득점을 반환.
	{
		return stat[4];
	}
}
