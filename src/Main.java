import java.util.*;


public class Main {

    /*****************************************************
	
		소프트웨어학과 201520871 이석훈
		소프트웨어학과 201520873 박성진
	
	******************************************************/
	
	public final static int MATCH = 1000; // 시뮬레이션을 돌릴 횟수. (초기 계획엔 100번이었는데 정확성을 위해 1000번으로 높였습니다.)
	
	public static void main(String[] args)
	{
		int seriesKind; // 원하는 시리즈를 입력받을 변수.
		int teamKind; // 원하는 팀을 입력받을 변수.
		int retryNum = 1; // 다시 시뮬레이션할지 사용자에게 정보를 받는 변수.
		
		while(retryNum == 1)
		{
			introduce("Opening"); // 1:한국시리즈, 2:플레이오프, 3:준플레이오프, 4:와일드카드
			seriesKind = inputNumber(4); // 1-4 중에 원하는 숫자를 입력받음.
	
			if (seriesKind == 1) // 한국시리즈 선택
			{
				introduce("KoreanSeries"); // 1:KT, 2:두산, 3:LG, 4:키움
				teamKind = inputNumber(4); // 1-4 중에 원하는 숫자를 입력받음.
				Team home = new Team("NC");
				Team away = awayTeamSelect(1, teamKind); // 사용자가 선택한 팀을 설정.
				seriesProceed(home, away, 4); // MATCH번의 시리즈 시뮬레이션을 진행하고 그에 따른 통계를 출력함.
			}
			else if (seriesKind == 2) // 플레이오프 선택
			{
				introduce("Playoff"); // 1:두산, 2:LG, 3:키움
				teamKind = inputNumber(3); // 1-3 중에 원하는 숫자를 입력받음.
				Team home = new Team("KT");
				Team away = awayTeamSelect(2, teamKind); // 사용자가 선택한 팀을 설정.
				seriesProceed(home, away, 3); // MATCH번의 시리즈 시뮬레이션을 진행하고 그에 따른 통계를 출력함.
			}
			else if (seriesKind == 3) // 준플레이오프 선택
			{
				introduce("SemiPlayoff"); // 1:LG, 2:키움
				teamKind = inputNumber(2); // 1-2 중에 원하는 숫자를 입력받음.
				Team home = new Team("두산");
				Team away = awayTeamSelect(3, teamKind); // 사용자가 선택한 팀을 설정.
				seriesProceed(home, away, 2); // MATCH번의 시리즈 시뮬레이션을 진행하고 그에 따른 통계를 출력함.
			}
			else if (seriesKind == 4) // 와일드카드 선택
			{
				introduce("Wildcard"); // LG vs 키움.
				Team home = new Team("LG");
				Team away = awayTeamSelect(4, 0); // 와일드카드라 키움이 자동으로 설정.
				seriesProceed(home, away, 1); // MATCH번의 시리즈 시뮬레이션을 진행하고 그에 따른 통계를 출력함.
			}
			retryNum = retry(); // 다시 시뮬레이션할 지 물어봄.
		}
	}
	
	public static void introduce(String intro) // 사용자에게 선택지를 알려주는 함수.
	{
		if (intro == "Opening")
		{
			System.out.println("2020 KBO리그 포스트시즌 승부 예측 프로그램입니다.\n");
			System.out.println("시뮬레이션해보고 싶은 시리즈를 선택해주세요.\n");
			System.out.println("\t(1) 2020 KBO리그 한국시리즈\n");
			System.out.println("\t(2) 2020 KBO리그 플레이오프\n");
			System.out.println("\t(3) 2020 KBO리그 준플레이오프\n");
			System.out.println("\t(4) 2020 KBO리그 와일드카드\n");
		}
		else if (intro == "KoreanSeries")
		{
			System.out.println("한국시리즈를 선택하셨습니다.\n");
			System.out.println("NC와 상대하게 될 팀을 선택해주세요.\n");
			System.out.println("\t(1) KT 위즈\n");
			System.out.println("\t(2) 두산 베어스\n");
			System.out.println("\t(3) LG 트윈스\n");
			System.out.println("\t(4) 키움 히어로즈\n");
		}
		else if (intro == "Playoff")
		{
			System.out.println("플레이오프를 선택하셨습니다.\n");
			System.out.println("KT와 상대하게 될 팀을 선택해주세요.\n");
			System.out.println("\t(1) 두산 베어스\n");
			System.out.println("\t(2) LG 트윈스\n");
			System.out.println("\t(3) 키움 히어로즈\n");
		}
		else if (intro == "SemiPlayoff")
		{
			System.out.println("준플레이오프를 선택하셨습니다.\n");
			System.out.println("두산과 상대하게 될 팀을 선택해주세요.\n");
			System.out.println("\t(1) LG 트윈스\n");
			System.out.println("\t(2) 키움 히어로즈\n");
		}
		else if (intro == "Wildcard")
		{
			System.out.println("와일드카드를 선택하셨습니다.\n");
			System.out.println("LG와 키움이 붙게 됩니다.\n");
		}
	}
	
	public static int inputNumber(int aNumber) // 사용자에게 aNumber까지의 숫자 중 하나를 입력받는 함수.
	{
		int input; // 입력받은 숫자를 저장할 변수.
		Scanner in = new Scanner(System.in);
		
		while(true)
		{
			try
			{
				input = in.nextInt();
				
				if(input >= 1 && input <= aNumber)
				{
					break;
				}
				else
				{
					System.out.println("제시된 숫자 중에 하나를 선택해주세요.");
				}
			}
			catch (InputMismatchException ex)
			{
				System.out.println("제시된 숫자 중에 하나를 선택해주세요.");
				in.nextLine();
			}			
		};
		return input;
	}
	
	public static Team awayTeamSelect(int aSeriesKind, int aTeamKind) // 선택한 away팀을 출력하고 설정하는 함수.
	{
		if (aSeriesKind == 1) // 한국시리즈일 때.
		{
			switch(aTeamKind)
			{
				case 1:
					System.out.println("KT를 선택하셨습니다.\n");
					return new Team("KT");
				case 2:
					System.out.println("두산 베어스를 선택하셨습니다.\n");
					return new Team("두산");
				case 3:
					System.out.println("LG 트윈스를 선택하셨습니다.\n");
					return new Team("LG");
				case 4:
					System.out.println("키움 히어로즈를 선택하셨습니다.\n");
					return new Team("키움");
			}
		}
		else if (aSeriesKind == 2) // 플레이오프일 때.
		{
			switch(aTeamKind)
			{
				case 1:
					System.out.println("두산 베어스를 선택하셨습니다.\n");
					return new Team("두산");
				case 2:
					System.out.println("LG 트윈스를 선택하셨습니다.\n");
					return new Team("LG");
				case 3:
					System.out.println("키움 히어로즈를 선택하셨습니다.\n");
					return new Team("키움");
			}
		}
		else if (aSeriesKind == 3) // 준플레이오프일 때.
		{
			switch(aTeamKind)
			{
				case 1:
					System.out.println("LG 트윈스를 선택하셨습니다.\n");
					return new Team("LG");
				case 2:
					System.out.println("키움 히어로즈를 선택하셨습니다.\n");
					return new Team("키움");
			}
		}
		else if (aSeriesKind == 4) // 와일드카드일 때.
		{
			return new Team("키움");
		}
		
		return null;
	}
	
	public static void seriesSimulation(Series[] aSeries, Team aHome, Team aAway, int[] aStat, int aTowin) // 시리즈를 MATCH만큼 시뮬레이션을 돌리는 함수.
	{
		for (int i = 0; i < MATCH; i++) // MATCH만큼 시리즈를 진행.
		{
			aSeries[i] = new Series(aHome, aAway, aTowin); // 시리즈 생성 및 저장. (aTowin전제)
			aSeries[i].proceed(); // 시리즈 진행.
			switch(aSeries[i].getResult()) // 시리즈 결과에 따라 stat 배열에 저장. [0]이 4:0, [1]이 4:1... 이런 식.
			{
				case 0:
					aStat[0]++;
					break;
				case 1:
					aStat[1]++;
					break;
				case 2:
					aStat[2]++;
					break;
				case 3:
					aStat[3]++;
					break;
				case 4:
					aStat[4]++;
					break;
				case 5:
					aStat[5]++;
					break;
				case 6:
					aStat[6]++;
					break;
				case 7:
					aStat[7]++;
					break;
				default:
					break;
			}
		}
	}
	
	public static void resultPrint(Series[] aSeries, int[] aStat, int aTowin) // 시리즈 결과 출력 함수.
	{
		if (aTowin == 4)
		{
			System.out.println("\n\n [시뮬레이션 결과]\n");
			System.out.println(aSeries[0].getHome() + " 4 : 0 " + aSeries[0].getAway() + " - " + aStat[0] + "회\n");
			System.out.println(aSeries[0].getHome() + " 4 : 1 " + aSeries[0].getAway() + " - " + aStat[1] + "회\n");
			System.out.println(aSeries[0].getHome() + " 4 : 2 " + aSeries[0].getAway() + " - " + aStat[2] + "회\n");
			System.out.println(aSeries[0].getHome() + " 4 : 3 " + aSeries[0].getAway() + " - " + aStat[3] + "회\n");
			System.out.println(aSeries[0].getHome() + " 3 : 4 " + aSeries[0].getAway() + " - " + aStat[4] + "회\n");
			System.out.println(aSeries[0].getHome() + " 2 : 4 " + aSeries[0].getAway() + " - " + aStat[5] + "회\n");
			System.out.println(aSeries[0].getHome() + " 1 : 4 " + aSeries[0].getAway() + " - " + aStat[6] + "회\n");
			System.out.println(aSeries[0].getHome() + " 0 : 4 " + aSeries[0].getAway() + " - " + aStat[7] + "회\n");
		}
		else if (aTowin == 3)
		{
			System.out.println("\n\n [시뮬레이션 결과]\n");
			System.out.println(aSeries[0].getHome() + " 3 : 0 " + aSeries[0].getAway() + " - " + aStat[0] + "회\n");
			System.out.println(aSeries[0].getHome() + " 3 : 1 " + aSeries[0].getAway() + " - " + aStat[1] + "회\n");
			System.out.println(aSeries[0].getHome() + " 3 : 2 " + aSeries[0].getAway() + " - " + aStat[2] + "회\n");
			System.out.println(aSeries[0].getHome() + " 2 : 3 " + aSeries[0].getAway() + " - " + aStat[3] + "회\n");
			System.out.println(aSeries[0].getHome() + " 1 : 3 " + aSeries[0].getAway() + " - " + aStat[4] + "회\n");
			System.out.println(aSeries[0].getHome() + " 0 : 3 " + aSeries[0].getAway() + " - " + aStat[5] + "회\n");
		}
		else if (aTowin == 2)
		{
			System.out.println("\n\n [시뮬레이션 결과]\n");
			System.out.println(aSeries[0].getHome() + " 2 : 0 " + aSeries[0].getAway() + " - " + aStat[0] + "회\n");
			System.out.println(aSeries[0].getHome() + " 2 : 1 " + aSeries[0].getAway() + " - " + aStat[1] + "회\n");
			System.out.println(aSeries[0].getHome() + " 1 : 2 " + aSeries[0].getAway() + " - " + aStat[2] + "회\n");
			System.out.println(aSeries[0].getHome() + " 0 : 2 " + aSeries[0].getAway() + " - " + aStat[3] + "회\n");
		}
		else if (aTowin == 1)
		{
			System.out.println("\n\n [시뮬레이션 결과]\n");
			System.out.println(aSeries[0].getHome() + " 1 : 0 " + aSeries[0].getAway() + " - " + aStat[0] + "회\n");
			System.out.println(aSeries[0].getHome() + " 0 : 0 " + aSeries[0].getAway() + " - " + aStat[1] + "회\n");
			System.out.println(aSeries[0].getHome() + " 1 : 1 " + aSeries[0].getAway() + " - " + aStat[2] + "회\n");
			System.out.println(aSeries[0].getHome() + " 0 : 1 " + aSeries[0].getAway() + " - " + aStat[3] + "회\n");
			System.out.println(aSeries[0].getHome() + " 0 : 2 " + aSeries[0].getAway() + " - " + aStat[4] + "회\n");
		}
	}
	
	public static void seriesPredict(Series[] aSeries, int[] aStat, int aTowin) // 승부 예측 함수.
	{
		int best = 0; // 제일 자주 나온 스코어.
		int orCount = 0; // 제일 자주 나온 스코어가 한 개 이상일때 중복 체크용.
		if (aTowin == 4)
		{
			for (int i = 0; i < 8; i++)
			{
				if(best <= aStat[i]) // 모든 시리즈들 중 제일 자주 나온 스코어가 얼만큼 나왔는지 best에 저장.
				{
					best = aStat[i];
				}
			}
			
			System.out.print("AI 승부 예측 : ");
			
			for (int i = 0; i < 8; i++)
			{
				if(aStat[i] == best) // 제일 자주 나온 스코어면
				{
					if (orCount > 0) // 만약에 중복이면
					{
						System.out.print(", "); // 쉼표랑 같이 출력.
					}
					switch(i)
					{
					case 0:
						System.out.print(aSeries[0].getHome() + " 4 : 0 " + aSeries[0].getAway());
						break;
					case 1:
						System.out.print(aSeries[0].getHome() + " 4 : 1 " + aSeries[0].getAway());
						break;
					case 2:
						System.out.print(aSeries[0].getHome() + " 4 : 2 " + aSeries[0].getAway());
						break;
					case 3:
						System.out.print(aSeries[0].getHome() + " 4 : 3 " + aSeries[0].getAway());
						break;
					case 4:
						System.out.print(aSeries[0].getHome() + " 3 : 4 " + aSeries[0].getAway());
						break;
					case 5:
						System.out.print(aSeries[0].getHome() + " 2 : 4 " + aSeries[0].getAway());
						break;
					case 6:
						System.out.print(aSeries[0].getHome() + " 1 : 4 " + aSeries[0].getAway());
						break;
					case 7:
						System.out.print(aSeries[0].getHome() + " 0 : 4 " + aSeries[0].getAway());
						break;
					default:
						break;
					}
					orCount++; // 하나 나왔으니까 중복 카운트 추가.
				}
			}
		}
		else if (aTowin == 3)
		{
			for (int i = 0; i < 6; i++)
			{
				if(best <= aStat[i]) // 모든 시리즈들 중 제일 자주 나온 스코어가 얼만큼 나왔는지 best에 저장.
				{
					best = aStat[i];
				}
			}
			
			System.out.print("AI 승부 예측 : ");
			
			for (int i = 0; i < 6; i++)
			{
				if(aStat[i] == best) // 제일 자주 나온 스코어면
				{
					if (orCount > 0) // 만약에 중복이면
					{
						System.out.print(", "); // 쉼표랑 같이 출력.
					}
					switch(i)
					{
					case 0:
						System.out.print(aSeries[0].getHome() + " 3 : 0 " + aSeries[0].getAway());
						break;
					case 1:
						System.out.print(aSeries[0].getHome() + " 3 : 1 " + aSeries[0].getAway());
						break;
					case 2:
						System.out.print(aSeries[0].getHome() + " 3 : 2 " + aSeries[0].getAway());
						break;
					case 3:
						System.out.print(aSeries[0].getHome() + " 2 : 3 " + aSeries[0].getAway());
						break;
					case 4:
						System.out.print(aSeries[0].getHome() + " 1 : 3 " + aSeries[0].getAway());
						break;
					case 5:
						System.out.print(aSeries[0].getHome() + " 0 : 3 " + aSeries[0].getAway());
						break;
					default:
						break;
					}
					orCount++; // 하나 나왔으니까 중복 카운트 추가.
				}
			}
		}
		else if (aTowin == 2)
		{
			for (int i = 0; i < 4; i++)
			{
				if(best <= aStat[i]) // 모든 시리즈들 중 제일 자주 나온 스코어가 얼만큼 나왔는지 best에 저장.
				{
					best = aStat[i];
				}
			}
			
			System.out.print("AI 승부 예측 : ");
			
			for (int i = 0; i < 4; i++)
			{
				if(aStat[i] == best) // 제일 자주 나온 스코어면
				{
					if (orCount > 0) // 만약에 중복이면
					{
						System.out.print(", "); // 쉼표랑 같이 출력.
					}
					switch(i)
					{
					case 0:
						System.out.print(aSeries[0].getHome() + " 2 : 0 " + aSeries[0].getAway());
						break;
					case 1:
						System.out.print(aSeries[0].getHome() + " 2 : 1 " + aSeries[0].getAway());
						break;
					case 2:
						System.out.print(aSeries[0].getHome() + " 1 : 2 " + aSeries[0].getAway());
						break;
					case 3:
						System.out.print(aSeries[0].getHome() + " 0 : 2 " + aSeries[0].getAway());
						break;
					default:
						break;
					}
					orCount++; // 하나 나왔으니까 중복 카운트 추가.
				}
			}
		}
		else if (aTowin == 1)
		{
			for (int i = 0; i < 3; i++)
			{
				if(best <= aStat[i]) // 모든 시리즈들 중 제일 자주 나온 스코어가 얼만큼 나왔는지 best에 저장.
				{
					best = aStat[i];
				}
			}
			
			System.out.print("AI 승부 예측 : ");
			
			for (int i = 0; i < 3; i++)
			{
				if(aStat[i] == best) // 제일 자주 나온 스코어면
				{
					if (orCount > 0) // 만약에 중복이면
					{
						System.out.print(", "); // 쉼표랑 같이 출력.
					}
					switch(i)
					{
					case 0:
						System.out.print(aSeries[0].getHome() + " 1 : 0 " + aSeries[0].getAway());
						break;
					case 1:
						System.out.print(aSeries[0].getHome() + " 0 : 0 " + aSeries[0].getAway());
						break;
					case 2:
						System.out.print(aSeries[0].getHome() + " 1 : 1 " + aSeries[0].getAway());
						break;
					case 3:
						System.out.print(aSeries[0].getHome() + " 0 : 1 " + aSeries[0].getAway());
						break;
					case 4:
						System.out.print(aSeries[0].getHome() + " 0 : 2 " + aSeries[0].getAway());
						break;
					default:
						break;
					}
					orCount++; // 하나 나왔으니까 중복 카운트 추가.
				}
			}
		}
		System.out.println("\n");
	}
	
	public static void winPredict(Series[] aSeries) // 승률 예측 함수.
	{
		int homeWin = 0; // getHome()팀의 승리 합산.
		int awayWin = 0; // getAway()팀의 승리 합산.
		for (int i = 0; i < MATCH; i++)
		{
			homeWin += aSeries[i].getHomeWin(); // getHome()Win 변수에 getHome()팀의 모든 승리를 더함.
			awayWin += aSeries[i].getAwayWin(); // getAway()Win 변수에 getAway()팀의 모든 승리를 더함.
		}
		System.out.print("AI 승률 예측 : " + aSeries[0].getHome() + " - ");
		System.out.printf("%.1f%%, ",((double)homeWin / (double)(homeWin + awayWin)) * 100);
		System.out.print(aSeries[0].getAway() + " - ");
		System.out.printf("%.1f%%\n\n",((double)awayWin / (double)(homeWin + awayWin)) * 100);
	}
	
	public static void proceedPredict(Series[] aSeries, int[] aStat, int aTowin) // 진출 예측 함수.
	{
		int homeProceed = 0; // home팀의 진출 경우의 수 합산.
		int awayProceed = 0; // away팀의 진출 경우의 수 합산.

		if(aTowin == 4) // 7전 4전승제라면.
		{
			homeProceed += aStat[0];
			homeProceed += aStat[1];
			homeProceed += aStat[2];
			homeProceed += aStat[3];
			awayProceed += aStat[4];
			awayProceed += aStat[5];
			awayProceed += aStat[6];
			awayProceed += aStat[7];
			System.out.print("AI 우승 확률 예측 : " + aSeries[0].getHome() + " - ");
		}
		else if(aTowin == 3) // 5전 3전승제라면.
		{
			homeProceed += aStat[0];
			homeProceed += aStat[1];
			homeProceed += aStat[2];
			awayProceed += aStat[3];
			awayProceed += aStat[4];
			awayProceed += aStat[5];
			System.out.print("AI 진출 확률 예측 : " + aSeries[0].getHome() + " - ");
		}
		else if(aTowin == 2) // 3전 2전승제라면.
		{
			homeProceed += aStat[0];
			homeProceed += aStat[1];
			awayProceed += aStat[2];
			awayProceed += aStat[3];
			System.out.print("AI 진출 확률 예측 : " + aSeries[0].getHome() + " - ");
		}
		else if(aTowin == 1) // 3전 2전승제라면. (와일드카드)
		{
			homeProceed += aStat[0];
			homeProceed += aStat[1];
			homeProceed += aStat[2];
			homeProceed += aStat[3];
			awayProceed += aStat[4];
			System.out.print("AI 진출 확률 예측 : " + aSeries[0].getHome() + " - ");
		}	
		
		System.out.printf("%.1f%%, ",((double)homeProceed / (double)(homeProceed + awayProceed)) * 100);
		System.out.print(aSeries[0].getAway() + " - ");
		System.out.printf("%.1f%%\n\n",((double)awayProceed / (double)(homeProceed + awayProceed)) * 100);
	}
	
	public static void runPredict(Series[] aSeries) // 득점 예측 함수.
	{
		int homeScore = 0; // home팀의 득점 합산.
		int awayScore = 0; // away팀의 득점 합산.
		int totalMatch = 0; // 평균을 내는 데 필요한 총 경기 수.
		for (int i = 0; i < MATCH; i++)
		{
			homeScore += aSeries[i].getHomeScore(); // homeScore 변수에 home팀의 모든 득점을 더함.
			awayScore += aSeries[i].getAwayScore(); // awayScore 변수에 away팀의 모든 득점을 더함.
			totalMatch += aSeries[i].getHomeWin(); // totalMatch 변수에 home팀의 모든 승리를 더함.
			totalMatch += aSeries[i].getDraw(); // totalMatch 변수에 모든 무승부를 더함.
			totalMatch += aSeries[i].getAwayWin(); // totalMatch 변수에 away팀의 모든 패배를 더함.
		}
		
		System.out.print("AI 득점 예측 : " + aSeries[0].getHome() + " - ");
		System.out.printf("%.1f점, ",(double)(homeScore) / (double)(totalMatch));
		System.out.print(aSeries[0].getAway() + " - ");
		System.out.printf("%.1f점\n\n",(double)(awayScore) / (double)(totalMatch));
	}
	
	public static void statPrint(Series[] aSeries) // 개인 성적 예측 함수.
	{
		aSeries[0].getHome().lineupStatPrint();
		System.out.printf("\n");
		aSeries[0].getHome().rosterStatPrint();
		System.out.printf("\n");
		aSeries[0].getAway().lineupStatPrint();
		System.out.printf("\n");
		aSeries[0].getAway().rosterStatPrint();
	}
	
	public static void seriesProceed(Team aHome, Team aAway, int aTowin) // 위의 함수들을 이용해서 시리즈를 진행시키고 통계들을 출력하는 함수.
	{
		int[] stat = {0,0,0,0,0,0,0,0}; // 시리즈 결과를 저장할 배열.
		Series[] newSeries = new Series[MATCH]; // 시리즈가 진행되고 저장될 Series 배열.
		
		seriesSimulation(newSeries, aHome, aAway, stat, aTowin); // 시리즈 시뮬레이션 진행.
		resultPrint(newSeries, stat, aTowin); // 전체 시리즈 결과 출력.
		seriesPredict(newSeries, stat, aTowin); // 시리즈 결과를 바탕으로 승부 예측해서 출력.
		winPredict(newSeries); // 시리즈 결과를 바탕으로 승률 예측해서 출력.
		proceedPredict(newSeries, stat, aTowin); // 시리즈 결과를 바탕으로 진출확률 예측해서 출력.
		runPredict(newSeries); // 시리즈 결과를 바탕으로 득점 예측해서 출력.
		statPrint(newSeries); // 시리즈 동안의 선수들의 성적을 출력.
	}
	
	public static int retry() // 재시도 여부를 사용자에게 물어보는 함수.
	{
		System.out.println("\n\n >> 한 번 더 하시겠습니까?\n");
		System.out.println("\t(1) 한 번 더 시도\n");
		System.out.println("\t(2) 종료\n");
		int retry = inputNumber(2);
		
		switch(retry)
		{
			case 1 : System.out.println("\n\n\n\n\n"); return 1; // 1이면 그대로 유지.
			case 2 : System.out.println("감사합니다.\n"); return -1; // 2이면 프로그램 종료.
		}
		return -1;
	}
}
