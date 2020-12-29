import java.util.ArrayList;

public class Team {
	
	private String name; // 팀 이름.
	private int num; // 타순.
	private int[] score = new int[16]; // 팀 점수.
	private int total; // 팀 총점.
	
	private Batter Lineup[] = new Batter[10]; // 현재 타자 라인업.
	private Pitcher Roster[] = new Pitcher[14]; // 투수 전체 로스터.
	
	public Team(String aName)
	{
		name = aName;
		num = 1;
		int[] score = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		total = 0;
		
		if (aName == "NC")
		{
			Roster[0] = null;
			Roster[1] = new Pitcher("루친스키", this, "1선발", 21.97, 7.50, 1.45, 8.42, 13.42, 24.47, 18.95, 1.58, 0.39, 1.84, 110);
			Roster[2] = new Pitcher("구창모", this, "2선발", 29.57, 5.22, 0.00, 9.57, 12.46, 26.38, 11.30, 3.48, 0.00, 2.03, 110);
			Roster[3] = new Pitcher("라이트", this, "3선발", 18.12, 9.13, 1.45, 8.70, 18.84, 20.00, 17.83, 3.91, 0.29, 1.74, 95);
			Roster[4] = new Pitcher("송명기", this, "4선발", 19.78, 10.71, 0.55, 9.07, 20.05, 18.41, 14.56, 3.57, 0.00, 3.30, 75);
			Roster[5] = new Pitcher("임창민", this, "필승조", 25.29, 13.22, 1.15, 8.62, 14.94, 14.37, 13.79, 5.75, 0.57, 2.30, 25);
			Roster[6] = new Pitcher("임정호", this, "필승조", 22.09, 11.63, 4.07, 6.98, 12.21, 24.42, 15.12, 1.74, 0.00, 1.74, 25);
			Roster[7] = new Pitcher("문경찬", this, "추격조", 15.65, 7.83, 0.00, 13.04, 27.83, 16.52, 10.43, 4.35, 0.00, 4.35, 25);
			Roster[8] = new Pitcher("홍성민", this, "추격조", 13.59, 5.83, 1.94, 8.74, 13.59, 36.89, 13.59, 3.88, 0.00, 1.94, 25);
			Roster[9] = new Pitcher("김영규", this, "추격조", 18.90, 4.81, 0.69, 7.56, 16.15, 23.37, 20.62, 3.09, 0.69, 4.12, 25);
			Roster[10] = new Pitcher("손정욱", this, "패전조", 8.93, 8.93, 1.79, 1.79, 25.00, 25.00, 23.21, 3.57, 0.00, 1.79, 25);
			Roster[11] = new Pitcher("김건태", this, "패전조", 24.34, 16.45, 1.32, 8.55, 20.39, 9.87, 12.50, 3.95, 0.00, 2.63, 25);
			Roster[12] = new Pitcher("김진성", this, "셋업", 29.95, 5.35, 0.00, 12.83, 18.18, 11.76, 14.44, 4.28, 1.07, 2.14, 30);
			Roster[13] = new Pitcher("원종현", this, "마무리", 17.23, 8.40, 0.84, 6.30, 11.34, 32.35, 19.33, 2.10, 0.42, 1.68, 30);
			
			Lineup[0] = null;
			Lineup[1] = new Batter("박민우", this, 9.27, 6.95, 2.90, 5.79, 20.27, 23.75, 23.36, 5.21, 0.97, 1.54);
			Lineup[2] = new Batter("이명기", this, 14.69, 9.42, 0.75, 5.65, 14.88, 27.12, 23.16, 3.39, 0.56, 0.38);
			Lineup[3] = new Batter("나성범", this, 25.39, 8.40, 1.54, 5.49, 12.52, 17.50, 16.64, 6.35, 0.34, 5.83);
			Lineup[4] = new Batter("양의지", this, 9.02, 8.83, 2.69, 12.67, 19.00, 18.81, 17.47, 4.99, 0.19, 6.33);
			Lineup[5] = new Batter("박석민", this, 14.06, 17.01, 2.27, 8.62, 11.56, 21.77, 18.14, 3.40, 0.00, 3.17);
			Lineup[6] = new Batter("노진혁", this, 21.77, 10.06, 1.03, 8.21, 15.81, 19.10, 14.78, 4.52, 0.62, 4.11);
			Lineup[7] = new Batter("강진성", this, 10.85, 4.72, 2.12, 8.02, 19.34, 26.18, 20.05, 5.90, 0.00, 2.83);
			Lineup[8] = new Batter("알테어", this, 27.59, 8.52, 2.22, 7.04, 14.44, 15.37, 14.07, 3.70, 1.30, 5.74);
			Lineup[9] = new Batter("권희동", this, 17.31, 13.94, 2.64, 7.93, 18.51, 18.03, 14.90, 3.85, 0.00, 2.88);
		}
		else if (aName == "KT")
		{
			Roster[0] = null;
			Roster[1] = new Pitcher("소형준", this, "1선발", 16.25, 7.95, 1.06, 5.83, 12.54, 31.45, 19.79, 4.06, 0.00, 1.06, 95);
			Roster[2] = new Pitcher("데스파이네", this, "2선발", 17.04, 7.62, 0.90, 6.17, 12.00, 30.16, 19.96, 4.04, 0.11, 2.02, 105);
			Roster[3] = new Pitcher("쿠에바스", this, "3선발", 16.79, 7.02, 1.37, 8.70, 15.88, 27.02, 16.64, 3.97, 0.15, 2.44, 100);
			Roster[4] = new Pitcher("배제성", this, "4선발", 13.54, 12.40, 0.65, 7.34, 18.27, 26.59, 14.19, 4.89, 0.16, 1.96, 80);
			Roster[5] = new Pitcher("조현우", this, "필승조", 15.00, 6.67, 1.11, 5.56, 22.22, 26.11, 19.44, 2.78, 0.00, 1.11, 25);
			Roster[6] = new Pitcher("유원상", this, "필승조", 13.81, 10.45, 0.37, 12.31, 19.03, 24.63, 11.94, 4.48, 0.00, 2.99, 25);
			Roster[7] = new Pitcher("전유수", this, "추격조", 13.99, 10.88, 1.55, 12.95, 17.10, 19.69, 18.13, 3.63, 0.52, 1.55, 25);
			Roster[8] = new Pitcher("이보근", this, "추격조", 16.58, 11.06, 0.50, 10.55, 17.59, 24.62, 14.07, 4.02, 0.50, 0.50, 25);
			Roster[9] = new Pitcher("하준호", this, "추격조", 13.83, 14.36, 1.06, 7.98, 21.28, 21.28, 16.49, 3.19, 0.00, 0.53, 25);
			Roster[10] = new Pitcher("이대은", this, "패전조", 10.79, 9.35, 3.60, 9.35, 14.39, 26.62, 18.71, 4.32, 0.00, 2.88, 25);
			Roster[11] = new Pitcher("김민수", this, "패전조", 13.18, 6.28, 0.84, 6.90, 17.15, 24.06, 23.85, 4.60, 0.21, 2.93, 25);
			Roster[12] = new Pitcher("주권", this, "셋업", 12.89, 11.15, 0.35, 8.71, 18.82, 29.27, 12.89, 3.83, 0.00, 2.09, 30);
			Roster[13] = new Pitcher("김재윤", this, "마무리", 23.20, 6.00, 0.00, 10.00, 20.00, 16.00, 19.60, 1.60, 0.80, 2.80, 30);
			
			Lineup[0] = null;
			Lineup[1] = new Batter("조용호", this, 17.47, 13.47, 0.42, 2.32, 15.58, 25.26, 22.32, 3.16, 0.00, 0.00);
			Lineup[2] = new Batter("황재균", this, 16.53, 7.93, 0.84, 9.78, 17.54, 18.89, 18.21, 5.90, 0.84, 3.54);
			Lineup[3] = new Batter("로하스", this, 21.29, 10.48, 0.81, 3.87, 17.42, 15.16, 16.94, 6.29, 0.16, 7.58);
			Lineup[4] = new Batter("유한준", this, 11.97, 9.39, 0.00, 9.86, 16.67, 26.76, 18.78, 3.99, 0.00, 2.58);
			Lineup[5] = new Batter("강백호", this, 16.29, 11.56, 0.88, 6.65, 14.01, 21.72, 18.39, 6.30, 0.18, 4.03);
			Lineup[6] = new Batter("장성우", this, 14.58, 8.66, 0.23, 7.97, 19.13, 24.15, 18.91, 3.42, 0.00, 2.96);
			Lineup[7] = new Batter("박경수", this, 25.20, 12.60, 2.36, 5.77, 13.12, 17.06, 16.01, 4.46, 0.00, 3.41);
			Lineup[8] = new Batter("배정대", this, 23.39, 11.20, 0.82, 6.92, 12.85, 19.44, 18.62, 4.12, 0.49, 2.14);
			Lineup[9] = new Batter("심우준", this, 19.03, 7.18, 0.39, 11.65, 13.20, 26.80, 17.48, 3.11, 0.58, 0.58);
		}
		else if (aName == "두산")
		{
			Roster[0] = null;
			Roster[1] = new Pitcher("플렉센", this, "1선발", 28.57, 6.49, 0.43, 8.87, 13.85, 20.78, 16.67, 3.03, 0.00, 1.30, 110);
			Roster[2] = new Pitcher("알칸타라", this, "2선발", 23.07, 3.80, 1.14, 9.25, 17.11, 23.57, 15.34, 4.82, 0.38, 1.52, 110);
			Roster[3] = new Pitcher("최원준", this, "3선발", 17.87, 6.65, 1.33, 9.89, 21.48, 17.30, 17.68, 4.37, 0.57, 2.85, 80);
			Roster[4] = new Pitcher("유희관", this, "4선발", 9.17, 6.38, 0.49, 8.51, 19.64, 24.71, 21.93, 6.06, 0.82, 2.29, 60);
			Roster[5] = new Pitcher("김민규", this, "필승조", 23.58, 9.61, 1.31, 12.66, 16.16, 16.16, 13.10, 6.11, 0.00, 1.31, 25);
			Roster[6] = new Pitcher("이승진", this, "필승조", 24.11, 9.82, 0.89, 7.59, 13.39, 19.64, 16.96, 5.36, 0.89, 1.34, 25);
			Roster[7] = new Pitcher("함덕주", this, "추격조", 23.21, 8.86, 0.00, 8.86, 13.92, 21.94, 19.41, 2.95, 0.00, 0.84, 25);
			Roster[8] = new Pitcher("김강률", this, "추격조", 20.00, 14.29, 2.86, 5.71, 10.00, 21.43, 19.29, 4.29, 1.43, 0.71, 25);
			Roster[9] = new Pitcher("홍건희", this, "추격조", 20.56, 8.06, 2.02, 9.68, 14.52, 19.76, 18.15, 4.84, 0.40, 2.02, 25);
			Roster[10] = new Pitcher("이현승", this, "패전조", 21.99, 10.99, 1.05, 10.99, 14.14, 15.71, 18.32, 4.71, 1.05, 1.05, 25);
			Roster[11] = new Pitcher("윤명준", this, "패전조", 11.70, 7.02, 0.58, 4.09, 23.39, 24.56, 22.22, 3.51, 0.58, 2.34, 25);
			Roster[12] = new Pitcher("박치국", this, "셋업", 21.09, 10.86, 1.60, 6.39, 13.10, 26.20, 14.70, 3.83, 0.64, 1.60, 30);
			Roster[13] = new Pitcher("이영하", this, "마무리", 14.36, 11.15, 0.84, 7.43, 17.91, 23.31, 18.92, 4.39, 0.17, 1.52, 30);
			
			Lineup[0] = null;
			Lineup[1] = new Batter("허경민", this, 5.87, 7.34, 1.05, 9.43, 19.08, 26.83, 23.48, 5.24, 0.21, 1.47);
			Lineup[2] = new Batter("정수빈", this, 10.18, 10.00, 0.36, 6.18, 16.36, 30.36, 21.09, 3.09, 1.45, 0.91);
			Lineup[3] = new Batter("최주환", this, 11.74, 8.54, 1.07, 11.74, 21.00, 18.33, 18.86, 5.16, 0.71, 2.85);
			Lineup[4] = new Batter("김재환", this, 25.37, 14.99, 0.16, 4.12, 12.03, 20.76, 13.18, 4.28, 0.16, 4.94);
			Lineup[5] = new Batter("페르난데스", this, 6.39, 8.83, 1.98, 5.78, 15.37, 31.35, 22.68, 4.41, 0.00, 3.20);
			Lineup[6] = new Batter("김재호", this, 10.00, 10.00, 0.89, 8.67, 18.00, 26.67, 21.78, 3.33, 0.22, 0.44);
			Lineup[7] = new Batter("오재일", this, 17.29, 11.47, 0.00, 6.02, 17.67, 19.92, 18.61, 6.02, 0.00, 3.01);
			Lineup[8] = new Batter("박세혁", this, 11.06, 8.60, 2.95, 10.07, 17.69, 25.80, 17.94, 4.42, 0.49, 0.98);
			Lineup[9] = new Batter("박건우", this, 12.01, 7.76, 2.22, 4.81, 24.77, 21.07, 17.38, 7.39, 0.00, 2.59);
		}
		else if (aName == "LG")
		{
			Roster[0] = null;
			Roster[1] = new Pitcher("켈리", this, "1선발", 19.20, 5.73, 1.58, 7.59, 15.76, 27.22, 16.33, 4.15, 0.14, 2.29, 100);
			Roster[2] = new Pitcher("윌슨", this, "2선발", 23.64, 8.46, 1.74, 4.34, 12.80, 14.10, 26.68, 4.99, 0.43, 2.82, 90);
			Roster[3] = new Pitcher("이민호", this, "3선발", 13.01, 8.54, 1.94, 6.99, 17.86, 33.40, 13.59, 3.69, 0.00, 0.97, 75);
			Roster[4] = new Pitcher("임찬규", this, "4선발", 23.83, 11.23, 0.86, 5.18, 12.61, 21.59, 16.93, 5.01, 0.35, 2.42, 75);
			Roster[5] = new Pitcher("진해수", this, "필승조", 27.96, 10.90, 1.90, 4.27, 10.90, 18.48, 21.33, 2.84, 0.00, 1.42, 25);
			Roster[6] = new Pitcher("송은범", this, "필승조", 11.48, 7.79, 1.23, 6.15, 22.95, 23.36, 21.72, 3.28, 0.41, 1.64, 25);
			Roster[7] = new Pitcher("정찬헌", this, "추격조", 18.76, 6.84, 0.88, 11.04, 19.65, 17.22, 18.32, 4.86, 0.22, 2.21, 25);
			Roster[8] = new Pitcher("최동환", this, "추격조", 15.97, 4.56, 0.76, 3.04, 9.89, 45.25, 14.07, 3.80, 0.38, 2.28, 25);
			Roster[9] = new Pitcher("김윤식", this, "추격조", 12.71, 8.03, 2.01, 6.69, 19.73, 21.74, 21.07, 5.35, 0.33, 2.34, 25);
			Roster[10] = new Pitcher("이정용", this, "패전조", 15.94, 6.76, 0.97, 5.80, 12.08, 43.00, 10.14, 2.42, 0.00, 2.90, 25);
			Roster[11] = new Pitcher("최성훈", this, "패전조", 12.77, 8.51, 0.53, 11.70, 28.19, 19.15, 15.96, 2.13, 0.53, 0.53, 25);
			Roster[12] = new Pitcher("정우영", this, "셋업", 13.66, 6.71, 2.55, 9.72, 23.61, 32.64, 9.26, 1.16, 0.00, 0.69, 30);
			Roster[13] = new Pitcher("고우석", this, "마무리", 26.15, 9.74, 1.03, 4.62, 13.33, 26.15, 13.33, 4.62, 0.00, 1.03, 30);
			
			Lineup[0] = null;
			Lineup[1] = new Batter("홍창기", this, 17.33, 16.53, 1.99, 4.38, 14.14, 22.91, 14.74, 5.78, 1.20, 1.00);
			Lineup[2] = new Batter("오지환", this, 19.97, 7.75, 1.55, 7.23, 15.15, 21.17, 17.21, 7.06, 1.20, 1.72);
			Lineup[3] = new Batter("김현수", this, 8.66, 10.29, 0.33, 7.19, 18.95, 25.00, 19.93, 5.72, 0.33, 3.59);
			Lineup[4] = new Batter("라모스", this, 27.76, 11.22, 0.82, 5.51, 15.71, 14.49, 12.86, 3.47, 0.41, 7.76);
			Lineup[5] = new Batter("채은성", this, 15.10, 7.00, 1.97, 7.00, 17.29, 24.95, 19.26, 3.72, 0.44, 3.28);
			Lineup[6] = new Batter("유강남", this, 17.72, 6.75, 2.74, 9.28, 15.19, 24.68, 16.46, 3.80, 0.00, 3.38);
			Lineup[7] = new Batter("이형종", this, 19.00, 8.41, 2.18, 11.84, 15.26, 16.82, 15.26, 5.30, 0.62, 5.30);
			Lineup[8] = new Batter("김민성", this, 20.31, 7.38, 1.23, 8.31, 20.31, 18.15, 16.62, 6.15, 0.00, 1.54);
			Lineup[9] = new Batter("정주현", this, 17.40, 8.01, 0.83, 7.46, 18.78, 25.14, 17.40, 2.76, 1.10, 1.10);
		}
		else if (aName == "키움")
		{
			Roster[0] = null;
			Roster[1] = new Pitcher("요키시", this, "1선발", 18.00, 3.91, 0.94, 6.10, 14.08, 34.43, 17.06, 4.07, 0.47, 0.94, 105);
			Roster[2] = new Pitcher("브리검", this, "2선발", 23.60, 9.44, 1.12, 6.52, 11.46, 25.84, 18.65, 1.57, 0.45, 1.35, 90);
			Roster[3] = new Pitcher("최원태", this, "3선발", 14.19, 7.84, 1.48, 4.24, 17.80, 30.08, 17.37, 3.39, 0.21, 3.39, 100);
			Roster[4] = new Pitcher("한현희", this, "4선발", 18.73, 6.36, 2.41, 7.04, 17.18, 24.05, 18.90, 3.44, 0.17, 1.72, 75);
			Roster[5] = new Pitcher("양현", this, "필승조", 13.81, 7.11, 1.26, 5.44, 17.57, 30.54, 18.41, 3.77, 0.42, 1.67, 25);
			Roster[6] = new Pitcher("이승호", this, "필승조", 14.34, 7.86, 0.98, 6.48, 17.49, 26.92, 18.86, 3.93, 0.20, 2.95, 25);
			Roster[7] = new Pitcher("김태훈", this, "추격조", 14.55, 8.00, 1.82, 6.91, 14.18, 29.09, 19.64, 5.09, 0.36, 0.36, 25);
			Roster[8] = new Pitcher("오주원", this, "추격조", 17.57, 0.00, 0.00, 6.76, 14.86, 31.08, 18.92, 2.70, 0.00, 8.11, 25);
			Roster[9] = new Pitcher("김상수", this, "추격조", 21.82, 9.55, 1.36, 6.36, 18.64, 18.64, 16.82, 5.45, 0.00, 1.36, 25);
			Roster[10] = new Pitcher("김성민", this, "패전조", 19.63, 11.21, 2.80, 3.74, 12.15, 24.30, 17.76, 5.61, 0.00, 2.80, 25);
			Roster[11] = new Pitcher("김선기", this, "패전조", 12.22, 8.89, 4.44, 12.22, 15.56, 27.78, 14.44, 3.33, 0.00, 1.11, 25);
			Roster[12] = new Pitcher("안우진", this, "셋업", 28.87, 11.97, 0.00, 8.45, 14.79, 21.83, 10.56, 1.41, 0.00, 2.11, 30);
			Roster[13] = new Pitcher("조상우", this, "마무리", 27.83, 7.83, 0.87, 9.57, 16.52, 15.65, 18.26, 2.17, 0.00, 1.30, 30);
			
			Lineup[0] = null;
			Lineup[1] = new Batter("박준태", this, 27.84, 15.08, 4.41, 4.64, 11.14, 17.17, 15.31, 3.02, 0.23, 1.16);
			Lineup[2] = new Batter("서건창", this, 10.00, 15.69, 0.86, 7.59, 18.28, 24.48, 16.55, 4.83, 0.86, 0.86);
			Lineup[3] = new Batter("이정후", this, 7.74, 9.72, 0.66, 7.91, 23.72, 20.43, 18.45, 8.07, 0.82, 2.47);
			Lineup[4] = new Batter("박병호", this, 30.40, 15.20, 2.40, 5.87, 13.60, 14.13, 10.93, 1.87, 0.00, 5.60);
			Lineup[5] = new Batter("김하성", this, 11.04, 12.18, 1.30, 11.36, 20.62, 17.05, 17.53, 3.90, 0.16, 4.87);
			Lineup[6] = new Batter("김혜성", this, 17.18, 8.41, 0.37, 6.03, 16.45, 25.59, 19.20, 4.39, 1.10, 1.28);
			Lineup[7] = new Batter("이지영", this, 9.82, 6.67, 1.40, 3.51, 15.79, 34.39, 24.21, 3.51, 0.70, 0.00);
			Lineup[8] = new Batter("허정협", this, 20.51, 11.24, 0.56, 7.58, 13.20, 23.31, 17.42, 3.37, 0.00, 2.81);
			Lineup[9] = new Batter("전병우", this, 25.13, 9.30, 0.50, 8.04, 18.34, 17.34, 15.33, 3.27, 0.75, 2.01);
		}
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	/*
	 * 경기 시작 전, 이닝 전에 쓰는 함수.
	 */
	
	public void setTeam(int aCount) // 경기 시작 전에 팀을 초기화 시킴.
	{
		for (int i = 0; i < 16; i++)
		{
			score[i] = 0; // 점수 초기화.
		}
		
		for (int i = 1; i < 14; i++)
		{
			getRoster(i).setStamina(); // 투수 체력 초기화.
		}
		
		switch(aCount) // 몇차전이냐에 따라 선발 설정.
		{
			case 1 : Roster[0] = getRoster(1); break;
			case 2 : Roster[0] = getRoster(2); break;
			case 3 : Roster[0] = getRoster(3); break;
			case 4 : Roster[0] = getRoster(4); break;
			case 5 : Roster[0] = getRoster(1); break;
			case 6 : Roster[0] = getRoster(2); break;
			case 7 : Roster[0] = getRoster(3); break;
			
			case 8 : Roster[0] = getRoster(4); break; // 무승부로 인한 추가 경기시.
			case 9 : Roster[0] = getRoster(1); break;
			case 10 : Roster[0] = getRoster(2); break;
		}
		
		getPitcher().addGameStat(); // 선발투수의 게임 출장 스탯 추가.
		
		for (int i = 1; i < 10; i++)
		{
			getLineup(i).addGameStat(); // 라인업에 있는 타자들의 게임 출장 스탯 추가.
		}
		
		total = 0; // 팀 총점 초기화.
		num = 1; // 팀 타선 초기화.
	}
	
	public void resetOwn() // 이닝이 끝나고 타자들의 책임투수를 리셋시킴.
	{
		for (int i = 1; i < 10; i++)
		{
			Lineup[i].setOwn(null);
		}
	}
	
	/*
	 * 득점 관련 함수들.
	 */
	
	public int getScore(int aInning) // 해당 이닝의 스코어를 반환.
	{
		return score[aInning];
	}
	
	public int getTotal() // 팀의 총점을 반환.
	{
		return total;
	}
	
	public void addScore(int aInning, int aScore) // 해당 이닝에 해당 점수만큼 스코어를 추가.
	{
		score[aInning] += aScore;
	}
	
	public void addTotal(int aTotal) // 팀의 총점에 점수를 추가.
	{
		total += aTotal;
	}
	
	/*
	 * 라인업 - 로스터 관련 함수들.
	 */
	
	public Pitcher getRoster(int n) // 로스터의 n번째 투수를 반환.
	{
		return Roster[n];
	}
	
	public Batter getLineup(int n) // 라인업의 n번 타자를 반환.
	{
		return Lineup[n];
	}

	public Pitcher getPitcher() // 현재 등판중인 투수를 반환.
	{
		return Roster[0];
	}
	
	public int getNum() // 현재 타순을 반환.
	{
		return num;
	}
	
	public void addNum() // 타순을 다음 타자로 넘김.
	{
		if(num == 9) // 지금 9번타자면 1번 타자로.
		{
			num = 1;
		}
		else
		{
			num++;
		}
	}

	/*
	 * 투수 교체 관련 함수들.
	 */
	
	public void setPitcher(Pitcher aOldP, Pitcher aNewP) // 투수를 aOldP에서 aNewP로 교체.
	{
		aOldP.minusStamina(200); // 이전 투수 내리고
		Roster[0] = aNewP; // 새 투수 등판.
		aNewP.addGameStat(); // 새 투수 출장 횟수 추가.
	}
	
	public int nextPitcher(int score) // 투수 교체를 해주는 함수. 교체할 투수가 없으면 0을 반환.
	{	
		int next; // 교체 투입될 선수.
		
		// 체력 고갈.
		if (score >= 5) // 5점차 이상 리드중일때.
		{
			next = pitcherCheck(7, 8, 9); // 추격조 먼저 투입.
			if (next != 0)
			{
				return next;
			}
			else
			{
				next = pitcherCheck(5, 6, 12); // 다 썼으면 필승조 투입.
				if (next != 0)
				{
					return next;
				}
				else
				{
					next = pitcherCheck(10, 11); // 다 썼으면 패전조 투입.
					if (next != 0)
					{
						return next;
					}
					else
					{
						next = pitcherCheck(13); // 다 썼으면 마무리 투입.
						if (next != 0)
						{
							return next;
						}
						else // 정말 다 썼으면 0 변환.
						{
							return 0;
						}
					}
				}
			}
		}
		else if (score >= 0) // 0-4점차 리드중일때.
		{
			next = pitcherCheck(5, 6, 12); // 필승조 먼저 투입.
			if (next != 0)
			{
				return next;
			}
			else
			{
				next = pitcherCheck(13); // 다 썼으면 마무리 투입.
				if (next != 0)
				{
					return next;
				}
				else
				{
					next = pitcherCheck(7, 8, 9); // 다 썼으면 추격조 투입.
					if (next != 0)
					{
						return next;
					}
					else
					{
						next = pitcherCheck(10, 11); // 다 썼으면 패전조 투입.
						if (next != 0)
						{
							return next;
						}
						else // 정말 다 썼으면 0 변환.
						{
							return 0;
						}
					}
				}
			}
		}
		else if (score <= -5) // 5점차 이상 패배중일때.
		{
			next = pitcherCheck(10, 11); // 패전조 먼저 투입.
			if (next != 0)
			{
				return next;
			}
			else
			{
				next = pitcherCheck(7, 8, 9); // 다 썼으면 추격조 투입.
				if (next != 0)
				{
					return next;
				}
				else
				{
					next = pitcherCheck(5, 6, 12); // 다 썼으면 필승조 투입.
					if (next != 0)
					{
						return next;
					}
					else
					{
						next = pitcherCheck(13); // 다 썼으면 마무리 투입.
						if (next != 0)
						{
							return next;
						}
						else // 정말 다 썼으면 0 변환.
						{
							return 0;
						}
					}
				}
			}
		}
		else // 0-4점차 패배중일때.
		{
			next = pitcherCheck(7, 8, 9); // 추격조 먼저 투입.
			if (next != 0)
			{
				return next;
			}
			else
			{
				next = pitcherCheck(10, 11); // 다 썼으면 패전조 투입.
				if (next != 0)
				{
					return next;
				}
				else
				{
					next = pitcherCheck(5, 6, 12); // 다 썼으면 필승조 투입.
					if (next != 0)
					{
						return next;
					}
					else
					{
						next = pitcherCheck(13); // 다 썼으면 마무리 투입.
						if (next != 0)
						{
							return next;
						}
						else // 정말 다 썼으면 0 변환.
						{
							return 0;
						}
					}
				}
			}
		}
	}
	
	public int pitcherCheck (int i1, int i2, int i3) // 투수 교체 후보군 세명 중에서 적당한 투수를 선택해주는 함수.
	{
		ArrayList<Integer> pc = new ArrayList<>(); // 교체 후보군 리스트.
		
		if (Roster[i1].getStamina() > 0) // 미출전 선수면 리스트에 추가.
		{
			pc.add(i1);
		}
		
		if (Roster[i2].getStamina() > 0) // 미출전 선수면 리스트에 추가.
		{
			pc.add(i2);
		}
		
		if (Roster[i3].getStamina() > 0) // 미출전 선수면 리스트에 추가.
		{
			pc.add(i3);
		}
		
		if (pc.size() == 0) // 출전할 선수가 없으면 0 반환.
		{
			return 0;
		}
		
		int go = (int) (Math.random()*pc.size()); // 리스트에 있는 선수들 중 랜덤하게 한 명을 선택.
		
		return pc.get(go);
	}

	public int pitcherCheck (int i1, int i2) // 투수 교체 후보군 두명 중에서 적당한 투수를 선택해주는 함수.
	{
		ArrayList<Integer> pc = new ArrayList<>();  // 교체 후보군 리스트.
		
		if (Roster[i1].getStamina() > 0) // 미출전 선수면 리스트에 추가.
		{
			pc.add(i1);
		}
		
		if (Roster[i2].getStamina() > 0) // 미출전 선수면 리스트에 추가.
		{
			pc.add(i2);
		}
		
		if (pc.size() == 0) // 출전할 선수가 없으면 0 반환.
		{
			return 0;
		}
		
		int go = (int) (Math.random()*pc.size()); // 리스트에 있는 선수들 중 랜덤하게 한 명을 선택.
		
		return pc.get(go);
	}
	
	public int pitcherCheck (int i1) // 투수 교체 후보군 한명 중에서 적당한 투수를 선택해주는 함수.
	{
		if (Roster[i1].getStamina() > 0) // 미출전 선수면 선택.
		{
			return i1;
		}
		
		else return 0;
	}
	
	/*
	 * 팀 내 선수들 성적 출력 함수들.
	 */
	
	public void lineupStatPrint() // 팀 타자들의 성적을 출력해주는 함수.
	{
		System.out.println("<< " + name + "타자 성적 >>\n");
		for (int i = 1; i < 10 ; i++)
		{
			System.out.print(i + "번 타자 ) " + Lineup[i] + " ");
			System.out.printf("타율 - %.3f / ", Lineup[i].getAvgStat());
			System.out.printf("출루율 - %.3f / ", Lineup[i].getObpStat());
			System.out.printf("장타율 - %.3f / ", Lineup[i].getSlgStat());
			System.out.printf("OPS - %.3f  // ", Lineup[i].getObpStat() + Lineup[i].getSlgStat());
			System.out.printf("%d경기 ", Lineup[i].getGameStat());
			System.out.printf("%d홈런 ", Lineup[i].getPowerStat());
			System.out.printf("%d타점", Lineup[i].getRbiStat());
			System.out.printf("\n");
		}
	}
	
	public void rosterStatPrint() // 팀 투수들의 성적을 출력해주는 함수.
	{
		System.out.println("<< " + name + "투수 성적 >>\n");
		for (int i = 1; i < 14 ; i++)
		{
			System.out.print(Roster[i].getPosition() + ") " + Roster[i] + " ");
			System.out.printf("%d경기 ", Roster[i].getGameStat());
			System.out.printf("%.1f이닝 ", Roster[i].getInningStat());
			System.out.printf("ERA %.2f ", Roster[i].getEraStat());
			System.out.printf("%dK ", Roster[i].getStrikeStat());
			System.out.printf("%dBB", Roster[i].getBallStat());
			System.out.printf("\n");
		}
	}
}
