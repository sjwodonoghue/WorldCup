
public class Group {

	private Team Winner;
	private Team RunnerUp;
	
	private Team Team1;
	private Team Team2;
	private Team Team3;
	private Team Team4;

	public Group(Team Team1, Team Team2, Team Team3, Team Team4) {
		this.Team1 = Team1;
		this.Team2 = Team2;
		this.Team3 = Team3;
		this.Team4 = Team4;
	}
	
	public Team showWinner() {
		return Winner;
	}
	
	public Team showRunnerUp() {
		return RunnerUp;
	}
	
	public int[] match(Team TeamA, Team TeamB) {
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
	
	
	public int numGoals(int rateDiff) {
		double expGoals = Math.pow(1.05 * 1.28, ((double)(rateDiff)) / 100);
		return poisson(expGoals);
	}
	
	public int poisson(double expGoals) {
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
	
	public int factorial(int n) {
		if (n == 0) {
			return 1;
		}
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
	
	public void playGroup() {
		int[] match1 = match(Team1, Team2);
		int[] match2 = match(Team3, Team4);
		int[] match3 = match(Team1, Team3);
		int[] match4 = match(Team2, Team4);
		int[] match5 = match(Team1, Team4);
		int[] match6 = match(Team2, Team3);
		
		
		if (match1[0] > match1[1]) {
			Team1.points += 3;
		}
		else if (match1[0] < match1[1]){
			Team2.points += 3;
		}
		else {
			Team1.points += 1;
			Team2.points += 1;
		}
		
		
		if (match2[0] > match2[1]) {
			Team3.points += 3;
		}
		else if (match2[0] < match2[1]){
			Team4.points += 3;
		}
		else {
			Team3.points += 1;
			Team4.points += 1;
		}
		
		
		if (match3[0] > match3[1]) {
			Team1.points += 3;
		}
		else if (match3[0] < match3[1]){
			Team3.points += 3;
		}
		else {
			Team1.points += 1;
			Team3.points += 1;
		}
		
		
		if (match4[0] > match4[1]) {
			Team2.points += 3;
		}
		else if (match4[0] < match4[1]){
			Team4.points += 3;
		}
		else {
			Team2.points += 1;
			Team4.points += 1;
		}
		
		
		if (match5[0] > match5[1]) {
			Team1.points += 3;
		}
		else if (match5[0] < match5[1]){
			Team4.points += 3;
		}
		else {
			Team1.points += 1;
			Team4.points += 1;
		}
		
		
		if (match6[0] > match6[1]) {
			Team2.points += 3;
		}
		else if (match6[0] < match6[1]){
			Team3.points += 3;
		}
		else {
			Team2.points += 1;
			Team3.points += 1;
		}
		
	}
	
	public void printTable() {
		Team[] group = orderGroup();
		//System.out.println("Team \t P");
		for(int i = 0; i < group.length; i ++) {
			//System.out.println(group[i].showName() + "\t" + group[i].points);	
		}		
	}
	
	public Team[] orderGroup() {
		Team Team1 = this.Team1;
		Team Team2 = this.Team2;
		Team Team3 = this.Team3;
		Team Team4 = this.Team4;
		
		Team[] split1 = orderPair(Team1, Team2); 
		Team[] split2 = orderPair(Team3, Team4); 
		
		Team[] ret = new Team[4];
		
		if (split1[1].points > split2[0].points) {
			 ret[0] = split1[0];
			 ret[1] = split1[1];
			 ret[2] = split2[0];
			 ret[3] = split2[1];
			 //return ret;
		}
		else if (split2[1].points > split1[0].points) {
			 ret[0] = split2[0];
			 ret[1] = split2[1];
			 ret[2] = split1[0];
			 ret[3] = split1[1];
			 //return ret;
		}
		else if (split1[0].points < split2[0].points) {
			 ret[0] = split2[0];
			 ret[1] = split1[0];
			 ret[2] = split1[1];
			 ret[3] = split2[1];
			 //return ret;
		}
		else {//if (split2[0].points > split1[0].points) {
			 ret[0] = split1[0];
			 ret[1] = split2[0];
			 ret[2] = split2[1];
			 ret[3] = split1[1];
			 //return ret;
		}
		Winner = ret[0];
		RunnerUp = ret[1];
		return ret;
	}
	
	public Team[] orderPair(Team Team1, Team Team2) {
		Team[] split1 = new Team[2];
		if (Team1.points > Team2.points) {
			split1[0] = Team1;
			split1[1] = Team2;
		}
		else if (Team1.points < Team2.points) {
			split1[0] = Team2;
			split1[1] = Team1;

		}
		else if (Team1.showGD() > Team2.showGD()) {
			split1[0] = Team1;
			split1[1] = Team2;
		}
		else if (Team1.showGD() < Team2.showGD()) {
			split1[0] = Team2;
			split1[1] = Team1;
		}
		else if (Team1.showGS() > Team2.showGS()) {
			split1[0] = Team1;
			split1[1] = Team2;
		}
		else if (Team1.showGS() < Team2.showGS()) {
			split1[0] = Team2;
			split1[1] = Team1;
		}
		else if (Team1.showRating() < Team2.showRating()) {
			split1[0] = Team1;
			split1[1] = Team2;
		}
		else if (Team1.showRating() > Team2.showRating()) {
			split1[0] = Team2;
			split1[1] = Team1;
		}
		return split1;
	}
	
}
