
import java.util.*;


public class Tournament {
	
	public static Team Brazil = new Team("Brazil", 2110-100);
	public static Team Croatia = new Team("Croatia", 1779);
	public static Team Mexico = new Team("Mexico", 1784);
	public static Team Cameroon = new Team("Cameroon", 1593);
	public static Team Spain = new Team("Spain", 2082);
	public static Team Netherlands = new Team("Netherlands", 1979);
	public static Team Chile = new Team("Chile", 1890);
	public static Team Australia = new Team("Australia", 1711);		
	public static Team Colombia = new Team("Colombia", 1912);
	public static Team Greece = new Team("Greece", 1813);
	public static Team CotedIvoire = new Team("CotedIvoire", 1786);
	public static Team Japan = new Team("Japan", 1747);		
	public static Team Uruguay = new Team("Uruguay", 1898);
	public static Team CostaRica = new Team("CostaRica", 1717);
	public static Team England = new Team("England", 1906);
	public static Team Italy = new Team("Italy", 1887);		
	public static Team Switzerland = new Team("Switzerland", 1822);
	public static Team Ecuador = new Team("Ecuador", 1816);
	public static Team France = new Team("France", 1855);
	public static Team Honduras = new Team("Honduras", 1664);
	public static Team Argentina = new Team("Argentina", 1994);
	public static Team Bosnia = new Team("Bosnia", 1758);
	public static Team Iran = new Team("Iran", 1719);
	public static Team Nigeria = new Team("Nigeria", 1718);		
	public static Team Germany = new Team("Germany", 1912);
	public static Team Portugal = new Team("Portugal", 1813);
	public static Team Ghana = new Team("Ghana", 1786);
	public static Team USA = new Team("USA", 1747);		
	public static Team Belgium = new Team("Belgium", 1898);
	public static Team Algeria = new Team("Algeria", 1717);
	public static Team Russia = new Team("Russia", 1906);
	public static Team Korea = new Team("Korea", 1887);
	

	
	public static void main(String[] args) {	
		Team[] TeamList = {Brazil,Croatia,Mexico,Cameroon,Spain,Netherlands,Chile,Australia,Colombia,Greece,CotedIvoire,Japan,Uruguay,CostaRica,England,Italy,Switzerland,Ecuador,France,Honduras,Argentina,Bosnia,Iran,Nigeria,Germany,Portugal,Ghana,USA,Belgium,Algeria,Russia,Korea};
		
		int numIterations = 100000;
		for(int i = 0; i < numIterations; i++) {
			for(int k = 0; k < TeamList.length; k++) {
				TeamList[k].resetPoints();
				TeamList[k].resetGD();
				TeamList[k].resetGS();
			}
			runTournament().changeNumWins();
		}		
		System.out.println("\n\nTeam Name \t Num Wins \t Win Percentage");
		for(int i = 0; i < TeamList.length; i++) {
			System.out.println(TeamList[i].showName() + "\t"  + TeamList[i].showNumWins() + "\t" + 100 * ((double)(TeamList[i].showNumWins())/(double)(numIterations)));
		}
	}
	
	
	
	
	public static Team runTournament() {

		Group GroupA = new Group(Brazil, Croatia, Mexico, Cameroon);
		Group GroupB = new Group(Spain, Netherlands, Chile, Australia);
		Group GroupC = new Group(Colombia, Greece, CotedIvoire, Japan);		
		Group GroupD = new Group(Uruguay, CostaRica, England, Italy);		
		Group GroupE = new Group(Switzerland, Ecuador, France, Honduras);
		Group GroupF = new Group(Argentina, Bosnia, Iran, Nigeria);		
		Group GroupG = new Group(Germany, Portugal, Ghana, USA);
		Group GroupH = new Group(Belgium, Algeria, Russia, Korea);

		//System.out.println("------------------------------");
		GroupA.playGroup();
		//System.out.println("------------------------------");
		GroupA.printTable();
		
		//System.out.println("------------------------------");
		GroupB.playGroup();
		//System.out.println("------------------------------");
		GroupB.printTable();
		
		//System.out.println("------------------------------");
		GroupC.playGroup();
		//System.out.println("------------------------------");
		GroupC.printTable();
		
		//System.out.println("------------------------------");
		GroupD.playGroup();
		//System.out.println("------------------------------");
		GroupD.printTable();
		
		//System.out.println("------------------------------");
		GroupE.playGroup();
		//System.out.println("------------------------------");
		GroupE.printTable();
		
		//System.out.println("------------------------------");
		GroupF.playGroup();
		//System.out.println("------------------------------");
		GroupF.printTable();
		
		//System.out.println("------------------------------");
		GroupG.playGroup();
		//System.out.println("------------------------------");
		GroupG.printTable();
		
		//System.out.println("------------------------------");
		GroupH.playGroup();
		//System.out.println("------------------------------");
		GroupH.printTable();

		
		
		
		// Round of 16
		//System.out.println("\n------------------------------");
		//System.out.println("2nd Round Results:");
		Team R16W1 = matchWinner(GroupA.showWinner(), GroupB.showRunnerUp());
		Team R16W2 = matchWinner(GroupC.showWinner(), GroupD.showRunnerUp());
		Team R16W3 = matchWinner(GroupB.showWinner(), GroupA.showRunnerUp());
		Team R16W4 = matchWinner(GroupD.showWinner(), GroupC.showRunnerUp());
		Team R16W5 = matchWinner(GroupE.showWinner(), GroupF.showRunnerUp());
		Team R16W6 = matchWinner(GroupG.showWinner(), GroupH.showRunnerUp());
		Team R16W7 = matchWinner(GroupF.showWinner(), GroupE.showRunnerUp());
		Team R16W8 = matchWinner(GroupH.showWinner(), GroupG.showRunnerUp());

		
		// Quarters
		//System.out.println("\n------------------------------");
		//System.out.println("Quarter-Final Results:");
		Team QFW1 = matchWinner(R16W5, R16W6);
		Team QFW2 = matchWinner(R16W1, R16W2);
		Team QFW3 = matchWinner(R16W7, R16W8);
		Team QFW4 = matchWinner(R16W3, R16W4);

		
		// Semis
		//System.out.println("\n------------------------------");
		//System.out.println("Semi-Final Results:");

		Team SFW1 = matchWinner(QFW1, QFW2);
		Team SFW2 = matchWinner(QFW3, QFW4);


		// 3rd/4th play-oo
		
		//Team ThirdForthPO = matchWinner(SFW1, SFW2);

		
		// Final
		//System.out.println("\n------------------------------");
		//System.out.println("Final Results:");

		Team Champion = matchWinner(SFW1, SFW2);
		return Champion;
	}
	
	public static Team matchWinner(Team TeamA, Team TeamB) {
		int[] result = match(TeamA, TeamB); 
		if (result[0] > result[1]) {
			return TeamA;
		}
		if (result[0] < result[1]) {
			return TeamB;
		}
		else {
			return matchWinner(TeamA, TeamB);
		}
	}
	
	public static int[] match(Team TeamA, Team TeamB) {
		int[] result = new int[2];
		
		int dr = TeamA.showRating() - TeamB.showRating();
		result[0] = numGoals(dr);		//Team A (home team) goals.
		TeamA.addGoals(result[0]);
		
		
		result[1] = numGoals(-dr);		//Team B (away team) goals.
		TeamB.addGoals(result[1]);
		
		TeamA.changeGD(result[0] - result[1]);
		TeamB.changeGD(result[1] - result[0]);
		
		//System.out.println(TeamA.showName() + " " + result[0] + " : " + result[1] + " " + TeamB.showName());
		
		
		return result;
	}
	
	public static int numGoals(int rateDiff) {
		double expGoals = Math.pow(1.05 * 1.28, ((double)(rateDiff)) / 100);
		return poisson(expGoals);
	}
	
	public static int poisson(double expGoals) {
		double rand = Math.random();
		double pVal = (Math.pow(expGoals, 0) * Math.pow(Math.E, -expGoals)) / factorial(0);
		if (rand <= pVal) {
			return 0;
		}
		for(int k = 1; k <= 6; k++) {
			pVal += (Math.pow(expGoals, k) * Math.pow(Math.E, -expGoals)) / factorial(k);
			if (rand <= pVal) {
				return k;
			}
		}
		return 6;
	}
	
	public static int factorial(int n) {
		if (n == 0) {
			return 1;
		}
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}



}
