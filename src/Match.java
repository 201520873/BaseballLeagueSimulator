
public class Match extends Game{
	private static int inning = 1; // 현재 이닝.
	//private static boolean top = true; // 현재 이닝이 초/말인지 여부.
	private static int outcount = 0; // 현재 이닝의 아웃 카운트.
	private static boolean base1 = false; // 1루 베이스의 주자 유무.
	private static boolean base2 = false; // 2루 베이스의 주자 유무.
	private static boolean base3 = false; // 3루 베이스의 주자 유무.
	private static Batter[] Base = new Batter[4]; // 베이스에 출루해 있는 타자들의 배열.
	
	private Team home; // 홈팀.
	private Team away; // 원정팀.
	private int count;
	private int[] stat = new int[5]; // instance field.
	
	public Match(Team aHome, Team aAway, int aCount) // Match 생성자.
	{
		home = aHome; // home팀.
		away = aAway; // away팀.
		count = aCount; // count차전.
	}
	
	public void proceed(Team Home, Team Away, int aCount) // 야구 경기. (홈팀, 원정팀, count차전)
	{	
		matchCleaning(Home, Away, aCount); // 경기 시작전 초기화해주는 함수.

		do // <게임 진행>
		{
			//scoreboard(Home, Away, top); // 점수판 표기해주는 함수.
			
			do // <이닝 초 진행>
			{
				replacePitcher(Away, Home); // 투수 교체할지 말지 체크.
				//introduce(Home.getPitcher(), Away.getLineup(Away.getNum())); // 투수 vs 타자 및 아웃카운트-베이스 상황 알려주는 함수.
				matchup(Home.getPitcher(), Away.getLineup(Away.getNum())); // 투수 vs 타자.
				Away.addNum(); // 다음 타자 소환.
				
			} while(outcount < 3); // 아웃카운트 3개가 될 때까지 진행.
			
			if (inning >= 9) // 9회 이상인데
			{
				if (Home.getTotal() > Away.getTotal()) // 이미 홈팀이 이기고 있으면 경기 종료.
				{
					matchEnd(Home, Away, aCount);
					return;
				}
			}
			inningCleaning(); // 이닝 끝나고 초기화주는 함수.
			//top = false;
			//scoreboard(Home, Away, top); // 점수판 표기해주는 함수.
			
			do // <이닝 말 진행>
			{
				replacePitcher(Home, Away); // 투수 교체할지 말지 체크.
				//introduce(Away.getPitcher(), Home.getLineup(Home.getNum())); // 투수 vs 타자 및 아웃카운트-베이스 상황 알려주는 함수.
				matchup(Away.getPitcher(), Home.getLineup(Home.getNum())); // 투수 vs 타자.
				
				if (inning >= 9) // 9회 이상인데 (끝내기 상황)
				{
					if (Home.getTotal() > Away.getTotal()) // 이미 홈팀이 이기고 있으면 경기 종료.
					{
						matchEnd(Home, Away, aCount);
						return;
					}
				}
				Home.addNum(); // 다음 타자 소환.
			} while(outcount < 3); // 3아웃까지 진행.
			
			inningCleaning(); // 이닝 끝나고 초기화주는 함수.
			inning++; // 이닝 끝났으니까 이닝 추가.
			
			Home.resetOwn();
			Away.resetOwn();// 책임투수 제거. (이닝 끝났으니까)
			
			if (inning >= 10) // 9회가 끝났고
			{
				if (inning == 16 && (Home.getTotal() == Away.getTotal())) // 15회도 끝났는데 동점이면 경기 종료.
				{
					matchEnd(Home, Away, aCount);
					return;
				}
				if (Home.getTotal() > Away.getTotal()) // home팀이 이기고 있으면 경기 종료.
				{
					matchEnd(Home, Away, aCount);
					return;
				}
				else if (Home.getTotal() < Away.getTotal()) // away팀이 이기고 있으면 경기 종료.
				{
					matchEnd(Home, Away, aCount);
					return;
				}
			}
			//top = true;
		} while(inning <= 15); // 15이닝까지 진행.
	}
	
	public void replacePitcher(Team Attack, Team Defense) // 투수교체 결정해주는 함수.
	{
		int next; // 교체로 들어올 선수.
	
		if(inning >= 9 && (Defense.getTotal() - Attack.getTotal()) <= 3 && (Defense.getTotal() - Attack.getTotal()) > 0) // 마무리 상황이면.
		{
			if (Defense.getPitcher() != Defense.getRoster(13) && Defense.getRoster(13).getStamina() > 0) // 현재 투수가 마무리가 아니고 마무리가 미등판 상황이면.
			{
				//System.out.println(Defense + "팀의 " + Defense.getPitcher() + " 투수가 마무리 투수 " + Defense.getRoster(13) + "(으)로 교체되었습니다.");
				Defense.setPitcher(Defense.getPitcher(), Defense.getRoster(13));
			}
		}
		
		if (Defense.getPitcher().getStamina() < 0) // 투수 교체 타이밍이면.
		{
			next = Defense.nextPitcher(Defense.getTotal() - Attack.getTotal());
			
			if(next == 0) // 교체할 투수가 없으면
			{
				return;
			}
			//System.out.println(Defense + "팀의 " + Defense.getPitcher() + " 투수가 " + Defense.getRoster(next) + "투수로 교체되었습니다.");
			Defense.setPitcher(Defense.getPitcher(), Defense.getRoster(next));
		}	
	}
	
	public void matchEnd(Team Home, Team Away, int aCount) // 경기 끝났을 때 결과 정산해주는 함수.
	{
		if (Home.getTotal() > Away.getTotal()) // home팀 승리.
		{
			//System.out.println("\n[" + aCount + "차전 결과]" + Home + " 팀이 " + Away + " 팀을 " + Home.getTotal() + " : " + Away.getTotal() + " 으로 이겼습니다.");
			stat[0]++; // stat[0]에 home팀 승리 표시.
		}
		else if (Home.getTotal() == Away.getTotal()) // 무승부.
		{
			//System.out.println("\n[" + aCount + "차전 결과]" + Home + " 팀과 " + Away + " 팀이 " + Home.getTotal() + " : " + Away.getTotal() + " 으로 비겼습니다.");
			stat[1]++; // stat[1]에 무승부 표시.
			return;
		}
		else if (Home.getTotal() < Away.getTotal()) // away팀 승리.
		{
			//System.out.println("\n[" + aCount + "차전 결과]" + Away + " 팀이 " + Home + " 팀을 " + Away.getTotal() + " : " + Home.getTotal() + " 으로 이겼습니다.");
			stat[2]++; // stat[2]에 away팀 승리 표시.
		}
		stat[3] = Home.getTotal(); // stat[3]에 home팀 이번 경기 득점 저장.
		stat[4] = Away.getTotal(); // stat[4]에 away팀 이번 경기 득점 저장.
	}
	
	public void matchCleaning(Team Home, Team Away, int aCount) // 경기 시작전 초기화해주는 함수.
	{
		inning = 1; // 1이닝부터 시작.
		outcount = 0; // 아웃카운트는 0.
		Base[0] = null; // 베이스 초기화.
		Base[1] = null;
		Base[2] = null;
		Base[3] = null;
		base1 = false;
		base2 = false;
		base3 = false;
		Home.setTeam(aCount); // 팀 초기화.
		Away.setTeam(aCount);
	}
	
	public void inningCleaning() // 이닝 끝나고 초기화주는 함수.
	{
		//System.out.println("\n이닝 종료됩니다.\n");
		outcount = 0; // 아웃카운트는 0.
		Base[0] = null; // 베이스 초기화.
		Base[1] = null;
		Base[2] = null;
		Base[3] = null;
		base1 = false;
		base2 = false;
		base3 = false;
	}
	
	public void scoreboard(Team aHome, Team aAway, boolean aTop) // 점수판 표기해주는 함수. (테스트용으로 씀)
	{
		if(aTop) // 이닝 초면.
		{
			System.out.println("\n >> " + inning + "회 초 " + aAway + " " + aAway.getTotal() + " : " + aHome.getTotal() + " " + aHome + "\n");
		}
		else // 이닝 말이면.
		{
			System.out.println("\n >> " + inning + "회 말 " + aAway + " " + aAway.getTotal() + " : " + aHome.getTotal() + " " + aHome + "\n");
		}
		
		System.out.print("\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10\t11\t12\t13\t14\t15\tR"); // 점수판 이닝 표기.
		
		System.out.print("\n" + aAway); // away팀 이름 표기.
		
		for(int i = 1; i < 16 ; i++) // away팀 점수 표기.
		{
			System.out.print("\t" + aAway.getScore(i));
			
		}
		System.out.print("\t" + aAway.getTotal()); // away팀 총점 표기.
		
		System.out.print("\n" + aHome); // home팀 이름 표기.
		
		for(int i = 1; i < 16; i++) // home팀 점수 표기.
		{
			System.out.print("\t" + aHome.getScore(i));
		}
		System.out.println("\t" + aHome.getTotal()); // home팀 총점 표기.
	}
	
	public void introduce(Pitcher P, Batter B) // 투수 vs 타자 및 아웃카운트-베이스 상황 알려주는 함수. (테스트용으로 씀)
	{
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.print(P.getTeam() + " " + P + " 투수와 " + B.getTeam() + " " + B.getTeam().getNum() + "번 타자 " + B + " 선수의 맞대결."); // 간단한 선수 소개.
		System.out.print(" [ " + outcount + " OUT / ");
		
		if(!base1 && !base2 && !base3)
		{
			System.out.println("주자 없음 ]");
		}
		else if(base1 && !base2 && !base3)
		{
			System.out.println("주자 1루 ]");
		}
		else if(!base1 && base2 && !base3)
		{
			System.out.println("주자 2루 ]");
		}
		else if(!base1 && !base2 && base3)
		{
			System.out.println("주자 3루 ]");
		}
		else if(base1 && base2 && !base3)
		{
			System.out.println("주자 1,2루 ]");
		}
		else if(base1 && !base2 && base3)
		{
			System.out.println("주자 1,3루 ]");
		}
		else if(!base1 && base2 && base3)
		{
			System.out.println("주자 2,3루 ]");
		}
		else if(base1 && base2 && base3)
		{
			System.out.println("주자 만루 ]");
		}
		
		if(base1) System.out.println("1루 - " + Base[1]);
		if(base2) System.out.println("2루 - " + Base[2]);
		if(base3) System.out.println("3루 - " + Base[3]);
	}
	
	public void matchup(Pitcher P, Batter B) // 투수 vs 타자 맞대결.
	{
		double strike = (P.getStrike() + B.getStrike()) / 2; // 이번 대결에서 삼진이 나올 확률.
		double ball = strike + ((P.getBall() + B.getBall()) / 2); // 이번 대결에서 볼넷이 나올 확률.
		double hbp = ball + ((P.getHbp() + B.getHbp()) / 2); // 이번 대결에서 데드볼이 나올 확률.
		double infly = hbp + ((P.getInfly() + B.getInfly()) / 2); // 이번 대결에서 내야뜬공이 나올 확률.
		double outfly = infly + ((P.getOutfly() + B.getOutfly()) / 2); // 이번 대결에서 외야뜬공이 나올 확률.
		double ground = outfly + ((P.getGround() + B.getGround()) / 2); // 이번 대결에서 내야땅볼이 나올 확률.
		double hit = ground + ((P.getHit() + B.getHit()) / 2); // 이번 대결에서 안타가 나올 확률.
		double gap = hit + ((P.getGap() + B.getGap()) / 2); // 이번 대결에서 2루타가 나올 확률.
		double triple = gap + ((P.getTriple() + B.getTriple()) / 2); // 이번 대결에서 3구타가 나올 확률.
		double power = triple + ((P.getPower() + B.getPower()) / 2); // 이번 대결에서 홈런이 나올 확률.
		double pitch = Math.random() * power; // 이번 대결의 결과가 될 숫자. 랜덤으로 정해짐.
		
		if (pitch <= strike) // 삼진이면
		{
			B.addStrikeStat();
			P.addStrikeStat();
			P.addOutStat();
			P.minusStamina(3);
			//System.out.println(P + "선수가 " + B + " 선수를 상대로 삼진을 잡아냅니다.");
			outcount++;
			return;
		}
		else if (pitch <= ball) // 볼넷이면
		{
			B.addBallStat();
			B.setOwn(P);
			P.addBallStat();
			P.minusStamina(5);
			//System.out.println(B + "선수가 " + P + " 선수를 상대로 볼넷을 얻어냅니다.");
			fourball(B);
			return;
		}
		else if (pitch <= hbp) // 데드볼이면
		{
			B.addHbpStat();
			B.setOwn(P);
			P.addHbpStat();
			P.minusStamina(3);
			//System.out.println(B + "선수가 " + P + " 선수를 상대로 몸에 맞는 공을 얻어냅니다.");
			fourball(B);
			return;
		}
		else if (pitch <= infly) // 내야플라이볼이면
		{
			B.addOutStat();
			B.setOwn(P);
			P.addOutStat();
			P.minusStamina(3);
			//System.out.println(P + "선수가 " + B + " 선수를 내야 뜬공으로 잡아냅니다.");
			outcount++;
			return;
		}
		else if (pitch <= outfly) // 외야플라이볼이면
		{
			B.addOutStat();
			B.setOwn(P);
			P.addOutStat();
			P.minusStamina(3);
			outfly(P, B);
			return;
		}
		else if (pitch <= ground) // 땅볼이면
		{
			B.addOutStat();
			B.setOwn(P);
			P.addOutStat();
			P.minusStamina(3);
			ground(P, B);
			return;
		}
		else if (pitch <= hit) // 1루타면
		{
			B.addHitStat();
			B.setOwn(P);
			P.minusStamina(4);
			hit(P, B);
			
			return;
		}
		else if (pitch <= gap) // 2루타면
		{
			B.addGapStat();
			B.setOwn(P);
			P.minusStamina(5);
			gap(P,B);
			
			return;
		}
		else if (pitch <= triple) // 3루타면
		{
			B.addTripleStat();
			B.setOwn(P);
			P.minusStamina(5);
			triple(P,B);
			
			return;
		}
		else if (pitch <= power) // 홈런이면
		{
			B.addPowerStat();
			B.setOwn(P);
			P.minusStamina(6);
			power(P,B);
			
			return;
		}
	}
	
	public void fourball(Batter B) // 볼넷(데드볼) 관련 함수.
	{
		if(base1)
		{
			if(base2)
			{
				if(base3)
				{
					Base[3].getOwn().addScoreStat();
					B.addRbiStat();
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					//System.out.printf(B + " 선수가 밀어내기로 점수를 추가합니다.\n");
				}
				Base[3] = Base[2];
				Base[2] = Base[1];
				Base[1] = B;
				base3 = true;
			}
			else
			{
				Base[2] = Base[1];
				Base[1] = B;
				base2 = true;
				base1 = true;
			}
		}
		else
		{
			Base[1] = B;
			base1 = true;
		}
	}
	
	public void outfly(Pitcher P, Batter B)
	{
		//System.out.println(P + "선수가 " + B + " 선수를 외야 뜬공으로 잡아냅니다.");
		outcount++;
		if(outcount == 3) // 2아웃이라 그냥 잡고 이닝 종료
		{
			return;
		}
		else // 근데 그게 아니면
		{
			int go = (int) (Math.random()*2);
			int go2 = (int) (Math.random()*2);
			int out10 = (int) (Math.random()*10);
			
			if(base2 && !base3) // 주자 (1)2루 상황
			{
				if(go == 1) // 3루로 진루
				{
					if(out10 < 3) // 근데 아웃
					{
						//System.out.println("2루 주자 " + Base[2] + ", 3루로 태그업. 하지만 아웃당합니다.");
						outcount++;
						P.addOutStat();
						base2 = false;
						Base[2] = null;
						return;
					}
					else // 3루 태그업 성공
					{
						//System.out.println("2루 주자 " + Base[2] + ", 3루로 태그업. 3루 태그 성공합니다.");
						base2 = false;
						base3 = true;
						Base[3] = Base[2];
						Base[2] = null;
						return;
					}
				}
				else // 3루로 진루 안함
				{
					return;
				}
			}
			else if(!base2 && base3) // 주자 (1)3루 상황
			{
				if(go == 1) // 홈으로 태그업
				{
					if(out10 < 3) // 근데 아웃
					{
						//System.out.println("3루 주자 " + Base[3] + ", 홈으로 태그업. 하지만 아웃당합니다.");
						P.addOutStat();
						outcount++;
						base3 = false;
						Base[3] = null;
						return;
					}
					else // 태그업 성공
					{
						//System.out.println("3루 주자 " + Base[3] + ", 홈으로 태그업. 득점 성공합니다.");
						B.addRbiStat();
						Base[3].getOwn().addScoreStat();
						base3 = false;
						Base[3] = null;
						B.getTeam().addScore(inning, 1);
						B.getTeam().addTotal(1);
						return;
					}
				}
				else // 홈으로 태그업 안함
				{
					//System.out.println("3루 주자 " + Base[3] + ", 홈으로 태그업하지 못 합니다.");
				}
			}
			else if(base2 && base3) // 주자 (1)2, 3루 상황
			{
				if(go == 1) // 3루 주자 홈으로 태그업
				{
					if(go2 == 1) // 2루 주자도 3루로 태그업
					{
						if(out10 < 3) // 근데 아웃
						{
							//System.out.println("3루 주자 " + Base[3] + ", 홈으로 태그업. 하지만 아웃당합니다.");
							P.addOutStat();
							outcount++;
							base3 = false;
							Base[3] = null;
							if (outcount < 3)
							{
								//System.out.println("2루 주자 " + Base[2] + ", 3루로 진루합니다.");
								Base[3] = Base[2];
								Base[2] = null;
								base3 = true;
								base2 = false;
							}
							return;
						}
						else // 홈 태그업 성공
						{
							if(out10 < 3) // 근데 3루는 짤림
							{
								if (outcount == 2) // 득점 전에 3루가 먼저 짤림
								{
									//System.out.println("2루 주자 3루 주자 모두 태그업 합니다.");
									//System.out.println("아, 홈이 아니라 3루를 노립니다! 2루 주자 " + Base[2] + " 선수가 3루에서 아웃당합니다.");
									P.addOutStat();
									return;
								}
								else // 어차피 2아웃 아니라 상관없음
								{
									//System.out.println("2루 주자 3루 주자 모두 태그업 합니다.");
									//System.out.println("아, 홈이 아니라 3루를 노립니다! 2루 주자 " + Base[2] + " 선수가 3루에서 아웃당합니다.");
									//System.out.println("3루 주자 " + Base[3] + " 선수는 홈으로 태그업. 득점 성공합니다.");

									P.addOutStat();
									B.addRbiStat();
									Base[3].getOwn().addScoreStat(); 
									Base[3] = null;
									Base[2] = null;
									base3 = false;
									base2 = false;
									B.getTeam().addScore(inning, 1);
									B.getTeam().addTotal(1);
									return;
								}
							}
							else // 둘다 성공
							{
								//System.out.println("3루 주자 " + Base[3] + ", 홈으로 태그업. 득점 성공합니다.");
								//System.out.println("2루 주자 " + Base[2] + ", 3루로 진루합니다.");
								Base[3].getOwn().addScoreStat(); 
								B.addRbiStat();
								Base[3] = Base[2];
								Base[2] = null;
								base3 = true;
								base2 = false;
								B.getTeam().addScore(inning, 1);
								B.getTeam().addTotal(1);
								return;
							}
						}
					}
					else // 3루 주자만 태그업
					{
						if(out10 < 3) // 근데 아웃
						{
							//System.out.println("3루 주자 " + Base[3] + ", 홈으로 태그업. 하지만 아웃당합니다.");
							P.addOutStat();
							outcount++;
							base3 = false;
							Base[3] = null;
							return;
						}
						else // 태그업 성공
						{
							//System.out.println("3루 주자 " + Base[3] + ", 홈으로 태그업. 득점 성공합니다.");
							B.addRbiStat();
							Base[3].getOwn().addScoreStat();
							base3 = false;
							Base[3] = null;
							B.getTeam().addScore(inning, 1);
							B.getTeam().addTotal(1);
							return;
						}
					}
				}
				else // 2,3루 주자 가만히
				{
					//System.out.println("3루 주자 2루 주자 모두 움직이지 못 합니다.");
				}
			}
		}
	}
	
	public void ground(Pitcher P, Batter B)
	{
		if(!base1 && !base2 && !base3) // 주자 없는 상황에서 땅볼 아웃.
		{
			//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
			outcount++;
			return;
		}
		
		int go = (int) (Math.random()*2); // 주자 진루 가능성
		int go3 = (int) (Math.random()*2); // 추가주자 진루 가능성
		int out10 = (int) (Math.random()*10); // 아웃 가능성
		
		if(base1 && !base2 && !base3) // 주자 1루
		{
			if (outcount == 2) // 하지만 2아웃이라 이닝 종료
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				outcount++;
				return;
			}
			else if (out10 < 7) // 주자 2루로 진루타 될 수도 있는 상황
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				//System.out.println(Base[1] + " 선수가 2루로 진루합니다.");
				base2 = true;
				base1 = false;
				Base[2] = Base[1];
				Base[1] = null;
				outcount++;
				return;
			}
			else // 주자 1루에 병살이 될 수도 있는 상황
			{
				//System.out.println("땅볼 타구. 2루에서 포스 아웃. 1루 주자 아웃.");
				//System.out.println(P + " 선수가 위기 상황에서 병살타로 위기를 극복합니다.");
				P.addOutStat();
				base1 = false;
				Base[1] = null;
				outcount++;
				outcount++;
				return;
			}
		}
		
		if(!base1 && base2 && !base3) // 주자 2루
		{
			if (outcount == 2) // 하지만 2아웃이라 이닝 종료
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				outcount++;
				return;
			}
			else if (out10 < 7) // 주자 3루로 진루타 될 수도 있는 상황
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				//System.out.println("2루 주자 " + Base[2] + " 선수가 3루로 진루합니다.");
				base3 = true;
				base2 = false;
				Base[3] = Base[2];
				Base[2] = null;
				outcount++;
				return;
			}
			else // 주자 2루에 주자 움직이지 못하는 상황
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				//System.out.println("2루 주자 " + Base[2] + " 선수는 움직이지 못합니다.");
				outcount++;
				return;
			}
		}
		
		if(!base1 && base2 && !base3) // 주자 3루
		{
			if (outcount == 2) // 하지만 2아웃이라 이닝 종료
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				outcount++;
				return;
			}
			else if (out10 < 7) // 주자 홈으로 들어올 수도 있는 상황
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				//System.out.println("3루에 있던 " + Base[3] + " 선수가 홈으로 들어옵니다.");
				B.addRbiStat();
				Base[3].getOwn().addScoreStat();
				base3 = false;
				Base[3] = null;
				outcount++;
				B.getTeam().addScore(inning, 1);
				B.getTeam().addTotal(1);
				return;
			}
			else // 주자 2루에 주자 움직이지 못하는 상황
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				//System.out.println("2루 주자 " + Base[2] + " 선수는 움직이지 못합니다.");
				outcount++;
				return;
			}
		}
		
		if(base1 && base2 && !base3) // 주자 1,2루
		{
			if (outcount == 2) // 하지만 2아웃이라 이닝 종료
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				outcount++;
				return;
			}
			else if (out10 < 7) // 주자 2,3루로 진루할 수 있는 상황
			{
					//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
					//System.out.println("2루에 있던 " + Base[2] + " 선수가 3루로 진루합니다.");
					//System.out.println("1루에 있던 " + Base[1] + " 선수가 2루로 진루합니다.");
					base1 = false;
					base3 = true;
					Base[3] = Base[2];
					Base[2] = Base[1];
					Base[1] = null;
					outcount++;
					return;
			}
			else // 주자 1,2루에 주자 움직이지 못하는 상황
			{
				//System.out.println("땅볼 타구. 2루에서 포스 아웃. 1루 주자 아웃.");
				//System.out.println(P + " 선수가 위기 상황에서 병살타로 위기를 극복합니다.");
				base1 = false;
				base2 = false;
				base3 = true;
				Base[3] = Base[2];
				P.addOutStat();
				outcount++;
				outcount++;
				return;
			}
		}
		
		if(!base1 && base2 && base3) // 주자 2,3루
		{
			if (outcount == 2) // 하지만 2아웃이라 이닝 종료
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				outcount++;
				return;
			}
			else if (out10 < 7) // 3루 주자 홈으로 들어올 수 있는 상황
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				//System.out.println("3루에 있던 " + Base[3] + " 선수가 홈으로 들어옵니다.");
				//System.out.println("2루에 있던 " + Base[2] + " 선수가 3루로 진루합니다.");
				B.addRbiStat();
				Base[3].getOwn().addScoreStat(); 
				base2 = false;
				Base[3] = Base[2];
				Base[2] = null;
				B.getTeam().addScore(inning, 1);
				B.getTeam().addTotal(1);
				outcount++;
				return;
			}
			else // 주자 2,3루에 주자 움직이지 못하는 상황
			{	
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				//System.out.println("3루 주자 " + Base[3] + " 2루 주자 " + Base[2] + " 모두 움직이지 못합니다.");
				outcount++;
				return;
			}
		}
		
		if(base1 && base2 && base3) // 주자 만루
		{
			if (outcount == 2) // 어차피 2아웃이라 이닝 종료
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				outcount++;
				return;
			}
			else if (out10 < 7) // 3루 주자 홈으로 들어올 수 있는 상황
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				//System.out.println("3루에 있던 " + Base[3] + " 선수가 홈으로 들어옵니다.");
				//System.out.println("2루에 있던 " + Base[2] + " 선수가 3루로 진루합니다.");
				//System.out.println("1루에 있던 " + Base[1] + " 선수가 2루로 진루합니다.");
				Base[3].getOwn().addScoreStat(); 
				B.addRbiStat();
				base1 = false;
				Base[3] = Base[2];
				Base[2] = Base[1];
				Base[1] = B;
				B.getTeam().addScore(inning, 1);
				B.getTeam().addTotal(1);
				outcount++;
				return;
			}
			else // 3루 주자 홈에서 아웃당하는 상황
			{
				if (out10 < 7) // 다행히 홈병살은 면함
				{
					//System.out.println(B + " 선수의 내야 땅볼 타구.");
					//System.out.println("3루 주자 " + Base[3] + " 선수가 홈에서 아웃당합니다.");
					//System.out.println(B + " 선수는 1루에서 세잎입니다.");
					Base[3] = Base[2];
					Base[2] = Base[1];
					Base[1] = B;
					outcount++;
					return;
				}
				else // 홈병살
				{
					//System.out.println(B + " 선수의 내야 땅볼 타구.");
					//System.out.println("3루 주자 " + Base[3] + " 선수가 홈에서 아웃당합니다.");
					//System.out.println(B + " 선수도 1루에서 아웃당합니다.");
					P.addOutStat();
					base1 = false;
					Base[3] = Base[2];
					Base[2] = Base[1];
					Base[1] = null;
					outcount++;
					outcount++;
					return;
				}
			}
		}
		
		if(base1 && !base2 && base3) // 주자 1,3루
		{
			if (outcount == 2) // 하지만 2아웃이라 이닝 종료
			{
				//System.out.println(P + " 선수가 " + B + " 선수를 땅볼 타구로 잡아냅니다.");
				outcount++;
				return;
			}
			else if (go == 1) // 3루 주자 홈으로 들어올 수 있는 상황
			{
				if (out10 < 7) // 3루 주자 홈으로 들어옴
				{
					if (go3 == 1) // 홈에서 득점 성공 각인데 남은 주자는 병살각
					{
						if (outcount == 1) // 근데 1아웃이라 이닝 종료
						{
							//System.out.println("땅볼 타구. 2루에서 포스 아웃. 1루 주자 아웃.");
							//System.out.println(P + " 선수가 위기 상황에서 병살타로 위기를 극복합니다.");
							P.addOutStat();
							outcount++;
							outcount++;
							return;
						}
						else // 홈에서 득점하지만 남은 주자는 병살
						{
							//System.out.println(B + " 선수의 내야 땅볼 타구. 2루에서 포스 아웃. 1루 주자 아웃.");
							//System.out.println("3루 주자 " + Base[3] + " 선수는 홈에서 득점 성공합니다.");
							Base[3].getOwn().addScoreStat();
							P.addOutStat();
							Base[1] = null;
							Base[3] = null;
							base1 = false;
							base3 = false;
							B.getTeam().addScore(inning, 1);
							B.getTeam().addTotal(1);
							outcount++;
							outcount++;
							return;
						}
					}
					else // 홈에서 득점 성공하고 1루 주자는 진루 성공
					{
						//System.out.println(B + " 선수의 내야 땅볼 타구. 1루에서 아웃됩니다.");
						//System.out.println("3루 주자 " + Base[3] + " 선수는 홈에서 득점 성공합니다.");
						//System.out.println("1루 주자 " + Base[1] + " 선수는 2루로 진루합니다.");
						B.addRbiStat();
						Base[3].getOwn().addScoreStat();
						Base[3] = null;
						Base[2] = Base[1];
						Base[1] = null;
						base3 = false;
						base2 = true;
						base1 = false;
						B.getTeam().addScore(inning, 1);
						B.getTeam().addTotal(1);
						outcount++;
						return;
					}
				}
				else  // 3루 주자 홈에서 아웃
				{
					if (out10 < 3) // 1루 주자도 같이 아웃
					{
						if (outcount == 1) // 근데 1아웃이라 이닝 종료
						{
							//System.out.println("3루 주자 " + Base[3] + " 선수가 홈에서 아웃당합니다.");
							//System.out.println(B + " 선수도 1루에서 아웃됩니다.");
							//System.out.println(P + " 선수가 위기 상황에서 병살타로 위기를 극복합니다.");
							P.addOutStat();
							outcount++;
							outcount++;
							return;
						}
						else // 2사 2루
						{
							//System.out.println("3루 주자 " + Base[3] + " 선수가 홈에서 아웃당합니다.");
							//System.out.println(B + " 선수도 1루에서 아웃됩니다.");
							//System.out.println("1루 주자 " + Base[1] + " 선수는 진루에 성공합니다.");
							Base[3] = null;
							Base[2] = Base[1];
							Base[1] = null;
							base1 = false;
							base2 = true;
							base3 = false;
							P.addOutStat();
							outcount++;
							outcount++;
							return;
						}
					}
					else // 1사 12루
					{
						//System.out.println("3루 주자 " + Base[3] + " 선수가 홈에서 아웃당합니다.");
						//System.out.println(B + " 선수는 1루에서 세이프입니다.");
						Base[3] = null;
						base3 = false;
						Base[2] = Base[1];
						Base[1] = B;
						base2 = true;
						outcount++;
						return;
					}
				}
			}
			else // 3루 주자 움직이지 않는 상황
			{	
				if (out10 < 7) // 1루 주자는 2루로 진루
				{
					//System.out.println(B + " 선수의 내야 땅볼 타구.");
					//System.out.println(B + " 선수는 1루에서 아웃.");
					//System.out.println("1루 주자 " + Base[1] + " 선수 는 2루로 진루합니다.");
					//System.out.println("3루 주자 " + Base[3] + " 선수 움직이지 못합니다.");
					Base[2] = Base[1];
					Base[1] = B;
					base2 = true;
					base1 = false;
					outcount++;
					return;
				}
				else // 3루 주자 가만히 있고 병살각
				{
					if (outcount == 1) // 그대로 이닝 종료
					{
						//System.out.println("땅볼 타구. 2루에서 포스 아웃. 1루 주자 아웃.");
						//System.out.println(P + " 선수가 위기 상황에서 병살타로 위기를 극복합니다.");
						P.addOutStat();
						outcount++;
						outcount++;
						return;
					}
					else // 3루 주자 가만히 있고 병살
					{
						//System.out.println("땅볼 타구. 2루에서 포스 아웃. 1루 주자 아웃.");
						//System.out.println("3루 주자 " + Base[3] + " 선수 움직이지 못합니다.");
						P.addOutStat();
						Base[2] = null;
						Base[1] = null;
						base2 = false;
						base1 = false;
						outcount++;
						outcount++;
						return;
					}
				}
			}
		}
	}
	
	public void hit(Pitcher P, Batter B)
	{
		int go = (int) (Math.random()*2);
		int out10 = (int) (Math.random()*10);
		
		if(!base1 && !base2 && !base3) // 주자 없을 때
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 안타를 얻어냅니다.");
			Base[1] = B;
			base1 = true;
			return;
		}
		else if(base1 && !base2 && !base3) // 주자 1루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 안타를 얻어냅니다.");
			if (go == 1) // 1루 주자가 3루까지 간다
			{
				if (out10 < 3) // 3루에서 아웃
				{
					//System.out.println("1루 주자 " + Base[1] + " 선수, 3루까지 달립니다.");
					//System.out.println("3루에서 아웃되네요!");
					Base[1] = B;
					P.addOutStat();
					outcount++;
					return;
				}
				else // 3루에서 세잎
				{
					//System.out.println("1루 주자 " + Base[1] + " 선수, 3루까지 달립니다.");
					//System.out.println("3루에서 세잎입니다.");
					Base[3] = Base[1];
					Base[1] = B;
					base3 = true;
					return;
				}
			}
			else // 1루 주자가 2루까지만 간다
			{
				//System.out.println("1루 주자 " + Base[1] + " 선수, 2루까지만 진루합니다.");
				Base[2] = Base[1];
				Base[1] = B;
				base2 = true;
				return;
			}
		}
		else if(!base1 && base2 && !base3) // 주자 2루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 안타를 얻어냅니다.");
			if (go == 1) // 2루 주자가 홈까지 간다
			{
				if (out10 < 3) // 홈에서 아웃
				{
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 아웃되네요!");
					Base[1] = B;
					P.addOutStat();
					outcount++;
					return;
				}
				else // 홈에서 세잎
				{
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈까지 달립니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에서 세잎입니다.");
					B.addRbiStat();
					Base[2].getOwn().addScoreStat();
					Base[2] = null;
					Base[1] = B;
					base2 = false;
					base1 = true;
					
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					return;
				}
			}
			else // 2루 주자가 3루까지만 간다.
			{
				//System.out.println("2루 주자 " + Base[2] + " 선수, 3루까지만 진루합니다.");
				Base[3] = Base[2];
				Base[1] = B;
				base3 = true;
				base2 = false;
				base1 = true;
				return;
			}
		}
		else if(!base1 && !base2 && base3) // 주자 3루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 안타를 얻어냅니다.");
			//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
			B.addRbiStat();
			Base[3].getOwn().addScoreStat();
			Base[3] = null;
			Base[1] = B;
			base1 = true;
			base3 = false;
			B.getTeam().addScore(inning, 1);
			B.getTeam().addTotal(1);
			return;
		}
		else if(base1 && base2 && !base3) // 주자 1,2루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 안타를 얻어냅니다.");
			if (go == 1) // 2루 주자가 홈까지 진루한다.
			{
				if (out10 < 3) // 홈에서 아웃
				{
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 아웃되네요!");
					//System.out.println("1루 주자 " + Base[1] + " 선수는 2루까지 진루합니다.");
					((Pitcher)P).addOutStat();
					outcount++;
					Base[2] = Base[1];
					Base[1] = B;
					return;
				}
				else // 홈에서 세잎
				{
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈까지 달립니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에서 세잎입니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수는 2루까지 진루합니다.");
					Base[2].getOwn().addScoreStat(); 
					B.addRbiStat();
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					Base[2] = Base[1];
					Base[1] = B;
					return;
				}
			}
			else // 1,2루 주자 모두 한 베이스씩만 진루한다.
			{
				//System.out.println("2루 주자 " + Base[2] + " 선수, 3루까지만 진루합니다.");
				//System.out.println("1루 주자 " + Base[1] + " 선수, 2루까지만 진루합니다.");
				Base[3] = Base[2];
				Base[2] = Base[1];
				Base[1] = B;
				base3 = true;
				return;
			}
		}
		else if(!base1 && base2 && base3) // 주자 2,3루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 안타를 얻어냅니다.");
			if (go == 1) // 2루 주자가 홈까지 간다
			{
				if (out10 < 3) // 홈에서 아웃
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 아웃되네요!");
					B.addRbiStat();
					Base[3].getOwn().addScoreStat();
					P.addOutStat();
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					Base[3] = null;
					Base[2] = null;
					Base[1] = B;
					base3 = false;
					base2 = false;
					base1 = true;
					outcount++;
					return;
				}
				else // 홈에서 세잎
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈까지 달립니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에서 세잎입니다.");
					Base[3].getOwn().addScoreStat();
					Base[2].getOwn().addScoreStat();
					B.addRbiStat();
					B.addRbiStat();
					Base[3] = null;
					Base[2] = null;
					Base[1] = B;
					base3 = false;
					base2 = false;
					base1 = true;
					B.getTeam().addScore(inning, 2);
					B.getTeam().addTotal(2);
					return;
				}
			}
			else // 2루 주자가 3루까지만 간다.
			{
				//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
				//System.out.println("2루 주자 " + Base[2] + " 선수, 3루까지만 진루합니다.");
				Base[3].getOwn().addScoreStat();
				B.addRbiStat();
				B.getTeam().addScore(inning, 1);
				B.getTeam().addTotal(1);
				Base[3] = Base[2];
				Base[1] = B;
				base3 = true;
				base2 = false;
				base1 = true;
				return;
			}
		}
		else if(base1 && !base2 && base3) // 주자 1,3루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 안타를 얻어냅니다.");
			if (go == 1) // 1루 주자가 3루까지 간다
			{
				if (out10 < 3) // 3루에서 아웃
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수, 3루까지 달립니다.");
					//System.out.println("3루에서 아웃되네요!");
					B.addRbiStat();
					Base[3].getOwn().addScoreStat();
					P.addOutStat();
					Base[3] = null;
					Base[1] = B;
					base3 = false;
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					outcount++;
					return;
				}
				else // 3루에서 세잎
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수, 3루까지 달립니다.");
					//System.out.println("3루에서 세잎입니다.");
					Base[3].getOwn().addScoreStat(); 
					B.addRbiStat();
					Base[3] = Base[1];
					Base[1] = B;
					base3 = true;
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					return;
				}
			}
			else // 1루 주자가 2루까지만 간다
			{
				//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
				//System.out.println("1루 주자 " + Base[1] + " 선수, 2루까지만 진루합니다.");
				Base[3].getOwn().addScoreStat(); 
				B.addRbiStat();
				Base[3] = null;
				Base[2] = Base[1];
				Base[1] = B;
				base3 = false;
				base2 = true;
				B.getTeam().addScore(inning, 1);
				B.getTeam().addTotal(1);
				return;
			}
		}
		else if(base1 && base2 && base3) // 주자 만루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 안타를 얻어냅니다.");
			if (go == 1) // 2루 주자가 홈까지 진루한다.
			{
				if (out10 < 3) // 홈에서 아웃
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 아웃되네요!");
					//System.out.println("1루 주자 " + Base[1] + " 선수는 2루까지 진루합니다.");
					Base[3].getOwn().addScoreStat(); 
					P.addOutStat();
					B.addRbiStat();
					outcount++;
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					Base[3] = null;
					Base[2] = Base[1];
					Base[1] = B;
					base3 = false;
					return;
				}
				else // 홈에서 세잎
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈까지 달립니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에서 세잎입니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수는 2루까지 진루합니다.");
					Base[3].getOwn().addScoreStat();
					Base[2].getOwn().addScoreStat();
					B.addRbiStat();
					B.addRbiStat();
					B.getTeam().addScore(inning, 2);
					B.getTeam().addTotal(2);
					Base[3] = null;
					Base[2] = Base[1];
					Base[1] = B;
					base3 = false;
					return;
				}
			}
			else // 1,2루 주자 모두 한 베이스씩만 진루한다.
			{
				//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
				//System.out.println("2루 주자 " + Base[2] + " 선수, 3루까지만 진루합니다.");
				//System.out.println("1루 주자 " + Base[1] + " 선수, 2루까지만 진루합니다.");
				Base[3].getOwn().addScoreStat();
				B.addRbiStat();
				Base[3] = Base[2];
				Base[2] = Base[1];
				Base[1] = B;
				B.getTeam().addScore(inning, 1);
				B.getTeam().addTotal(1);
				return;
			}
		}	
	}
	
	public void gap(Pitcher P, Batter B)
	{
		int go = (int) (Math.random()*2);
		int out10 = (int) (Math.random()*10);
		
		if(!base1 && !base2 && !base3) // 주자 없을 때
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 2루타를 때려냅니다.");
			Base[2] = B;
			base2 = true;
			return;
		}
		else if(base1 && !base2 && !base3) // 주자 1루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 2루타를 때려냅니다.");
			
			if (go == 1) // 1루 주자 홈까지 달림
			{
				if (out10 < 3) // 홈에서 아웃
				{
					//System.out.println("1루 주자 " + Base[1] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 아웃되네요!");
					P.addOutStat();
					Base[2] = B;
					Base[1] = null;
					base2 = true;
					base1 = false;
					outcount++;
					return;
				}
				else // 홈에서 세잎
				{
					//System.out.println("1루 주자 " + Base[1] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 세잎입니다.");
					Base[1].getOwn().addScoreStat();
					B.addRbiStat();
					Base[2] = B;
					Base[1] = null;
					base2 = true;
					base1 = false;
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					return;
				}
			}
			else // 1루 주자 3루까지만 감
			{
				//System.out.println("1루 주자 " + Base[1] + " 선수, 3루까지만 진루합니다.");
				Base[3] = Base[1];
				Base[2] = B;
				Base[1] = null;
				base3 = true;
				base2 = true;
				base1 = false;
				return;
			}
		}
		else if(!base1 && base2 && !base3) // 주자 2루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 2루타를 때려냅니다.");
			//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
			Base[2].getOwn().addScoreStat(); 
			B.addRbiStat();
			Base[2] = B;
			B.getTeam().addScore(inning, 1);
			B.getTeam().addTotal(1);
			return;
		}
		else if(!base1 && !base2 && base3) // 주자 3루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 2루타를 때려냅니다.");
			//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
			Base[3].getOwn().addScoreStat(); 
			B.addRbiStat();
			Base[3] = null;
			Base[2] = B;
			base3 = false;
			base2 = true;
			B.getTeam().addScore(inning, 1);
			B.getTeam().addTotal(1);
			return;
		}
		else if(base1 && base2 && !base3) // 주자 1,2루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 2루타를 때려냅니다.");
			
			if (go == 1) // 1루 주자 홈까지 달림
			{
				if (out10 < 3) // 홈에서 아웃
				{
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 아웃되네요!");
					Base[2].getOwn().addScoreStat();
					B.addRbiStat();
					P.addOutStat();
					Base[2] = B;
					Base[1] = null;
					base1 = false;
					outcount++;
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					return;
				}
				else // 홈에서 세잎
				{
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 세잎입니다.");
					Base[2].getOwn().addScoreStat();
					Base[1].getOwn().addScoreStat();
					B.addRbiStat();
					B.addRbiStat();
					Base[2] = B;
					Base[1] = null;
					base1 = false;
					B.getTeam().addScore(inning, 2);
					B.getTeam().addTotal(2);
					return;
				}
			}
			else // 1루 주자 3루까지만 감
			{
				//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
				//System.out.println("1루 주자 " + Base[1] + " 선수, 3루까지만 진루합니다.");
				Base[2].getOwn().addScoreStat();
				B.addRbiStat();
				Base[3] = Base[1];
				Base[2] = B;
				Base[1] = null;
				base3 = true;
				base1 = false;
				B.getTeam().addScore(inning, 1);
				B.getTeam().addTotal(1);
				return;
			}
		}
		else if(!base1 && base2 && base3) // 주자 2,3루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 2루타를 때려냅니다.");
			//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
			//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
			Base[2].getOwn().addScoreStat();
			Base[3].getOwn().addScoreStat();
			B.addRbiStat();
			B.addRbiStat();
			Base[3] = null;
			Base[2] = B;
			base3 = false;
			base2 = true;
			B.getTeam().addScore(inning, 2);
			B.getTeam().addTotal(2);
			return;
		}
		else if(base1 && !base2 && base3) // 주자 1,3루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 2루타를 때려냅니다.");
			
			if (go == 1) // 1루 주자 홈까지 달림
			{
				if (out10 < 3) // 홈에서 아웃
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 아웃되네요!");
					Base[3].getOwn().addScoreStat();
					P.addOutStat();
					B.addRbiStat();
					Base[3] = null;
					Base[2] = B;
					Base[1] = null;
					base3 = false;
					base2 = true;
					base1 = false;
					outcount++;
					B.getTeam().addScore(inning, 1);
					B.getTeam().addTotal(1);
					return;
				}
				else // 홈에서 세잎
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 세잎입니다.");
					Base[3].getOwn().addScoreStat();
					Base[1].getOwn().addScoreStat();
					B.addRbiStat();
					B.addRbiStat();
					Base[3] = null;
					Base[2] = B;
					Base[1] = null;
					base3 = false;
					base2 = true;
					base1 = false;
					B.getTeam().addScore(inning, 2);
					B.getTeam().addTotal(2);
					return;
				}
			}
			else // 1루 주자 3루까지만 감
			{
				//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
				//System.out.println("1루 주자 " + Base[1] + " 선수, 3루까지만 진루합니다.");
				Base[3].getOwn().addScoreStat();
				B.addRbiStat();
				Base[3] = Base[1];
				Base[2] = B;
				Base[1] = null;
				base2 = true;
				base1 = false;
				B.getTeam().addScore(inning, 1);
				B.getTeam().addTotal(1);
				return;
			}
		}
		else if(base1 && base2 && base3) // 주자 만루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 2루타를 때려냅니다.");
			
			if (go == 1) // 1루 주자 홈까지 달림
			{
				if (out10 < 3) // 홈에서 아웃
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 아웃되네요!");
					Base[3].getOwn().addScoreStat();
					Base[2].getOwn().addScoreStat();
					B.addRbiStat();
					B.addRbiStat();
					P.addOutStat();
					Base[3] = null;
					Base[2] = B;
					Base[1] = null;
					base3 = false;
					base1 = false;
					outcount++;
					B.getTeam().addScore(inning, 2);
					B.getTeam().addTotal(2);
					return;
				}
				else // 홈에서 세잎
				{
					//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
					//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
					//System.out.println("1루 주자 " + Base[1] + " 선수, 홈까지 달립니다.");
					//System.out.println("홈에서 세잎입니다.");
					Base[3].getOwn().addScoreStat(); 
					Base[2].getOwn().addScoreStat(); 
					Base[1].getOwn().addScoreStat(); 
					B.addRbiStat();
					B.addRbiStat();
					B.addRbiStat();
					
					Base[3] = null;
					Base[2] = B;
					Base[1] = null;
					base3 = false;
					base1 = false;
					B.getTeam().addScore(inning, 3);
					B.getTeam().addTotal(3);
					return;
				}
			}
			else // 1루 주자 3루까지만 감
			{
				//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
				//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
				//System.out.println("1루 주자 " + Base[1] + " 선수, 3루까지만 진루합니다.");
				Base[3].getOwn().addScoreStat();
				Base[2].getOwn().addScoreStat();
				B.addRbiStat();
				B.addRbiStat();
				Base[3] = Base[2];
				Base[2] = B;
				Base[1] = null;
				base1 = false;
				B.getTeam().addScore(inning, 2);
				B.getTeam().addTotal(2);
				return;
			}
		}
	}
	
	public void triple(Pitcher P, Batter B)
	{
		if(!base1 && !base2 && !base3) // 주자 없을 때
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 3루타를 때려냅니다.");
			Base[3] = B;
			base3 = true;
			return;
		}
		else if(base1 && !base2 && !base3) // 주자 1루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 3루타를 때려냅니다.");
			//System.out.println("1루 주자 " + Base[1] + " 선수, 홈에 들어옵니다.");
			Base[1].getOwn().addScoreStat();
			B.addRbiStat();
			Base[3] = B;
			Base[1] = null;
			base3 = true;
			base1 = false;
			B.getTeam().addScore(inning, 1);
			B.getTeam().addTotal(1);
			return;
		}
		else if(!base1 && base2 && !base3) // 주자 2루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 3루타를 때려냅니다.");
			//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
			Base[2].getOwn().addScoreStat();
			B.addRbiStat();
			Base[3] = B;
			Base[2] = null;
			base3 = true;
			base2 = false;
			B.getTeam().addScore(inning, 1);
			B.getTeam().addTotal(1);
			return;
		}
		else if(!base1 && !base2 && base3) // 주자 3루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 3루타를 때려냅니다.");
			//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
			Base[3].getOwn().addScoreStat();
			B.addRbiStat();
			Base[3] = B;
			B.getTeam().addScore(inning, 1);
			B.getTeam().addTotal(1);
			return;
		}
		else if(base1 && base2 && !base3) // 주자 1,2루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 3루타를 때려냅니다.");
			//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
			//System.out.println("1루 주자 " + Base[1] + " 선수, 홈에 들어옵니다.");
			Base[2].getOwn().addScoreStat();
			Base[1].getOwn().addScoreStat();
			B.addRbiStat();
			B.addRbiStat();
			Base[3] = B;
			Base[2] = null;
			Base[1] = null;
			base3 = true;
			base2 = false;
			base1 = false;
			B.getTeam().addScore(inning, 2);
			B.getTeam().addTotal(2);
			return;
		}
		else if(!base1 && base2 && base3) // 주자 2,3루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 3루타를 때려냅니다.");
			//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
			//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
			Base[2].getOwn().addScoreStat();
			Base[3].getOwn().addScoreStat();
			B.addRbiStat();
			B.addRbiStat();
			Base[3] = B;
			Base[2] = null;
			base2 = false;
			B.getTeam().addScore(inning, 2);
			B.getTeam().addTotal(2);
			return;
		}
		else if(base1 && !base2 && base3) // 주자 1,3루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 3루타를 때려냅니다.");
			//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
			//System.out.println("1루 주자 " + Base[1] + " 선수, 홈에 들어옵니다.");
			Base[1].getOwn().addScoreStat();
			Base[3].getOwn().addScoreStat();
			B.addRbiStat();
			B.addRbiStat();
			Base[3] = B;
			Base[1] = null;
			base1 = false;
			B.getTeam().addScore(inning, 2);
			B.getTeam().addTotal(2);
			return;
		}
		else if(base1 && base2 && base3) // 주자 만루
		{
			//System.out.println(B + " 선수가 " + P + " 선수를 상대로 3루타를 때려냅니다.");
			//System.out.println("3루 주자 " + Base[3] + " 선수, 홈에 들어옵니다.");
			//System.out.println("2루 주자 " + Base[2] + " 선수, 홈에 들어옵니다.");
			//System.out.println("1루 주자 " + Base[1] + " 선수, 홈에 들어옵니다.");
			Base[3].getOwn().addScoreStat();
			Base[2].getOwn().addScoreStat();
			Base[1].getOwn().addScoreStat();
			B.addRbiStat();
			B.addRbiStat();
			B.addRbiStat();
			Base[3] = B;
			Base[2] = null;
			Base[1] = null;
			base2 = false;
			base1 = false;
			B.getTeam().addScore(inning, 3);
			B.getTeam().addTotal(3);
			return;
		}
	}
	
	public void power(Pitcher P, Batter B)
	{
		//System.out.println("쳤습니다! 이 타구!! 담장~~~~ 넘어갑니다!!!");
		
		if(!base1 && !base2 && !base3) // 주자 없을 때
		{
			//System.out.println(B + "선수가 " + P + " 선수를 상대로 솔로홈런을 만들어 냅니다.");
			B.getTeam().addScore(inning, 1);
			B.getTeam().addTotal(1);
			B.addRbiStat();
			B.getOwn().addScoreStat();
			return;
		}
		
		else if((base1 && !base2 && !base3)||(!base1 && base2 && !base3)||(!base1 && !base2 && base3)) // 주자 한 명
		{
			//System.out.println(B + "선수가 " + P + " 선수를 상대로 투런홈런을 만들어 냅니다.");
			if(base3) Base[3].getOwn().addScoreStat();
			if(base2) Base[2].getOwn().addScoreStat();
			if(base1) Base[1].getOwn().addScoreStat();
			B.getOwn().addScoreStat();
			B.addRbiStat();
			B.addRbiStat();
			B.getTeam().addScore(inning, 2);
			B.getTeam().addTotal(2);
		}
		
		else if((base1 && base2 && !base3)||(!base1 && base2 && base3)||(base1 && !base2 && base3)) // 주자 두 명
		{
			//System.out.println(B + "선수가 " + P + " 선수를 상대로 쓰리런홈런을 만들어 냅니다.");
			if(base3) Base[3].getOwn().addScoreStat();
			if(base2) Base[2].getOwn().addScoreStat();
			if(base1) Base[1].getOwn().addScoreStat();
			B.getOwn().addScoreStat();
			B.addRbiStat();
			B.addRbiStat();
			B.addRbiStat();
			B.getTeam().addScore(inning, 3);
			B.getTeam().addTotal(3);
		}
		
		else if(base1 && base2 && !base3) // 주자 만루
		{
			//System.out.println(B + "선수가 " + P + " 선수를 상대로 만루홈런을 만들어 냅니다.");
			if(base3) Base[3].getOwn().addScoreStat();
			if(base2) Base[2].getOwn().addScoreStat();
			if(base1) Base[1].getOwn().addScoreStat();
			B.addRbiStat();
			B.addRbiStat();
			B.addRbiStat();
			B.addRbiStat();
			B.getOwn().addScoreStat();
			B.getTeam().addScore(inning, 4);
			B.getTeam().addTotal(4);
		}
		
		Base[1] = null;
		Base[2] = null;
		Base[3] = null;
		base1 = false;
		base2 = false;
		base3 = false;
	}
	
	@Override
	public int getHomeWin() // home팀이 승리했으면 1, 졌으면 0.
	{
		return stat[0];
	}
	
	@Override
	public int getDraw() // 비겼으면 1, 아니면 0.
	{
		return stat[1];
	}
	
	@Override
	public int getAwayWin() // away팀이 승리했으면 1, 졌으면 0.
	{
		return stat[2];
	}
	
	@Override
	public int getHomeScore() // home팀의 점수를 불러옴.
	{
		return stat[3];
	}
	
	@Override
	public int getAwayScore() // away팀의 점수를 불러옴.
	{
		return stat[4];
	}
}













