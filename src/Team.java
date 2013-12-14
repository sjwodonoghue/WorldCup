
public class Team {
	
	private String Name;
	private int ELORating;
	int points;
	private int goalDiff;
	private int goalsScored;
	private int numWins;
	
	
	public Team(String Name, int rating) {
		this.Name = Name;
		this.ELORating = rating;
	}
	
	public String showName() {
		return this.Name;
	}
	
	public int showRating() {
		return this.ELORating;
	}
	
	public int showGS() {			//Goals scored
		return this.goalsScored;
	}
	
	public void addGoals(int n) {
		this.goalsScored += n;
	}

	public int showGD() {			//Goal difference
		return this.goalDiff;
	}
	
	public void changeGD(int n) {
		this.goalDiff += n;
	}

	public int showNumWins() {
		return this.numWins;
	}
	
	public void changeNumWins(){
		this.numWins += 1;
	}
	
	public void resetPoints() {
		this.points = 0;
	}

	public void resetGD() {
		this.goalDiff = 0;
	}

	public void resetGS() {
		this.goalsScored = 0;
	}

	
}
