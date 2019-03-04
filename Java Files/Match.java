import javax.swing.JOptionPane;

//This is the class that represent a match in the competition.
//In each match there are two teams that will play against each another.
//The matches are either BO1, BO3 or BO5 (Best out of X).
//  There is a special type of match - BO2. Those match is usually found in the group stage of the competition
//Each team have a win rate depend of their team abilities.

public class Match {
  
//---------------------------------------------------------------------------------------------------------------------
//       Variables
//---------------------------------------------------------------------------------------------------------------------
  
  //Declare variables needed.
  private Team team1;
  private Team team2;
  private int format; //BO1, BO2, BO3 or BO5
  private String round; //What round is it, a regular round (ie. Upper bracket round 1) or Finals (Upper, lower or grand)
  private boolean showMessage; //Show the message of who win the match or the rounds
  
//---------------------------------------------------------------------------------------------------------------------
//       Constructor
//---------------------------------------------------------------------------------------------------------------------
  
  //Constructor method.
  //Setup the match with the variables given.
  public Match (Team t1, Team t2, int f, String r, boolean sm) {
    team1 = t1;
    team2 = t2;
    format = f;
    round = r;
    showMessage = sm;
  } //End of constructor method
  
//---------------------------------------------------------------------------------------------------------------------
//       Retrieve Info about this match
//---------------------------------------------------------------------------------------------------------------------
  
  //Return the information about this match
  public String toString () {
    return round + ": BO" + format +
      "\n" + team1.getName() + " vs " + team2.getName();
  } //End of toString method
  
//---------------------------------------------------------------------------------------------------------------------
//       Play the match
//---------------------------------------------------------------------------------------------------------------------
  
  //Play the match, depends on the format, it will calls playRegularMatch or playBO2Match.
  public int playMatch () {
    if (format == 2) {
      return playBO2Match();
    } //End of if statement
    else {
      return playRegularMatch();
    } //End of else statement
  } //End of playMatch method
  
//---------------------------------------------------------------------------------------------------------------------
  
  //For all matches that is not BO2.
  //It will automatically play the match.
  //It will randomly generate a number between 1 and 100. If the number is smaller to equal to the win rate of the 1st
  //  team, the team 1 wins that round. If the number is bigger, then team 2 wins that round. The team won the majority
  //  of the rounds in that match wins.
  //A return of 1 means team 1 win, a return of 2 means team 2 win.
  private int playRegularMatch () {
    int roundNeeded = format / 2 + 1; //Declare variables needed
    int winrate = winrate();
    int team1Win = 0;
    int team2Win = 0;
    int number;
    for (int x = 0; x < format; x++) { //Play the match
      number = ((int)(Math.random() * 100)) + 1; //Play the round
      if (number <= winrate) {
        team1Win++;
        if (showMessage) { //Show message or not
          JOptionPane.showMessageDialog(null, team1.getName() + " wins the round\n" + 
                                        team1.getName() + " " + team1Win + "-" + team2Win + " " + team2.getName());
        } //End of if statement
      } //End of if statement
      else {
        team2Win++;
        if (showMessage) { //Show message or not
          JOptionPane.showMessageDialog(null, team2.getName() + " wins the round\n" + 
                                        team1.getName() + " " + team1Win + "-" + team2Win + " " + team2.getName());
        } //End of if statement
      } //End of else statement
      if (team1Win >= roundNeeded) { //Check if a team got the majority of the rounds
        if (showMessage) { //Show message or not
          JOptionPane.showMessageDialog(null, round + ":\n\n" + 
                                        team1.getName() + " Win");
        } //End of if statement
        return 1;
      } //End of if statement
      else if (team2Win >= roundNeeded) {
        if (showMessage) { //Show message or not
          JOptionPane.showMessageDialog(null, round + ":\n\n" + 
                                        team2.getName() + " Win");
        } //End of if statement
        return 2;
      } //End of else if statement
    } //End of for loop
    return 1;
  } //End of playRegularMatch method
  
  //For BO2 matches only.
  //It will automatically play the match.
  //It will randomly generate a number between 1 and 100. If the number is smaller to equal to the win rate of the 1st
  //  team, the team 1 wins that round. If the number is bigger, then team 2 wins that round.
  //If a team wins 2 round, that team wins. If both team wins 1 round, then there is a tie.
  //A return of 1 means team 1 win, a return of 2 means team 2 win, a return of 0 means there is a tie.
  private int playBO2Match () {
    int winrate = winrate();
    int team1Win = 0;
    int team2Win = 0;
    int number;
    for (int x = 0; x < format; x++) { //Play the match
      number = ((int)(Math.random() * 100)) + 1; //Play the round
      if (number <= winrate) {
        team1Win++;
        if (showMessage) { //Show message or not
          JOptionPane.showMessageDialog(null, team1.getName() + " wins the round\n" + 
                                        team1.getName() + " " + team1Win + "-" + team2Win + " " + team2.getName());
        } //End of if statement
      } //End of if statement
      else {
        team2Win++;
        if (showMessage) { //Show message or not
          JOptionPane.showMessageDialog(null, team2.getName() + " wins the round\n" + 
                                        team1.getName() + " " + team1Win + "-" + team2Win + " " + team2.getName());
        } //End of if statement
      } //End of else statement
    } //End of for loop
    if (team1Win == 2) { //Check for which team won, or if there is a tie.
      if (showMessage) { //Show message or not
          JOptionPane.showMessageDialog(null, round + ":\n\n" + 
                                        team1.getName() + " Win");
        } //End of if statement
      return 1;
    } //End of if statement
    else if (team2Win == 2) {
      if (showMessage) { //Show message or not
          JOptionPane.showMessageDialog(null, round + ":\n\n" + 
                                        team2.getName() + " Win");
        } //End of if statement
      return 2;
    } //End of else if statement
    if (showMessage) { //Show message or not
          JOptionPane.showMessageDialog(null, round + ":\n\n" + 
                                        "There is a tie between " + team1.getName() + " and " + team2.getName());
        } //End of if statement
    return 0;
  } //End of playBO2Match method
  
//---------------------------------------------------------------------------------------------------------------------
  
  //Calculate the win rate of the two teams
  //Return the win rate of the first team.The win rate of the two team add up to 100.
  //The detail of how to calculate win rate is in the "extra" section in the botton of this class, after the "end of 
  //  program" section.
  private int winrate () {
    int winrate; //Declare variables needed
    int winrate2;
    int totalWinrate;
    int team1Ability;
    int team2Ability;
    int difference;
    winrate = team1.getAbility(); //Get the ability of the two teams.
    winrate2 = team2.getAbility();
    for (int x = 0; x < 3; x++) { //Check for suppression
      team1Ability = team1.getTotalSkills(x);
      team2Ability = team2.getTotalSkills(x);
      difference = team1Ability - team2Ability;
      if (difference >= 60) {
        winrate += 100;
      } //End of if statement
      else if (difference <= -60) {
        winrate2 += 100;
      } //End of else if statement
    } //End of for loop
    totalWinrate = winrate + winrate2; //Get the total points
    winrate = (int)Math.round(winrate * 100.0 / totalWinrate); //Get the winrate of the first team
    if (winrate >= 65) { //Check for an 100% win or lose
      return 100;
    } //End of if statement
    if (winrate <= 35) {
      return 0;
    } //End of if statement
    return winrate;
  } //End of winrate method
  
//---------------------------------------------------------------------------------------------------------------------
//       Get methods
//---------------------------------------------------------------------------------------------------------------------
  
  //Return team 1 or team 2.
  public Team getTeam (int whichTeam) {
    if (whichTeam == 1) {
      return team1;
    } //End of if statement
    else {
      return team2;
    } //End of else statement
  } //End of getTeam method
  
  //Basically return the showMessage variable, because it is only true if one of the two team is a user controlled
  //  team.
  public boolean userMatch () {
    return showMessage;
  } //End of userMatch method
  
//---------------------------------------------------------------------------------------------------------------------
//       End of program
//---------------------------------------------------------------------------------------------------------------------
  
} //End of class

//---------------------------------------------------------------------------------------------------------------------
//       Extra
//---------------------------------------------------------------------------------------------------------------------

//How to calculate win rate:
//  -The win rate is out of 100.
//  -If the difference of the win rate (when transfer to out of 100) is more than 30, the team with the higher win rate
//     will always win. (So if the win rate of the team is more than 65(including), they will always win, if the 
//     win rate of the team is lower than 35(including), they will always lose.
//  -First, get the ability of the two team, that is the base points.
//  -Second, suppression - if the total ability of the team on one of the base abilities is lower than the opposite
//     team by 60, theteam with the higher total ability will get additional 100 points to their win rate.
//  -Third, get the total points of the two team.
//  -Fourth, divide team 1's points by the total points, then mutiply it by 100, that is the final win rate of the 1st
//     team. Team 2's win rate will be 100 - team 1's win rate.