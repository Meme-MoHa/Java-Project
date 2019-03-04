import javax.swing.JOptionPane;
import java.util.ArrayList;

//This is the class representing the TI6 Competition
//It has two stages: group stage and main event.
//Group stage: 2 groups, each has 8 teams, played in the round-robin format.
//             All games in group stage is BO2, the winner get 3 points, loser get 0 points. If there is a tie,
//               both team get 1 points.
//Main Event: Double elimination bracket, no bracket reset for the finals.
//            The first round of loser bracket is BO1, the grand final is BO5, all other games are BO3.
//            The top 4 teams of each group advance to winner bracket, the bottom 4 teams of each group advance to
//              loser bracket.
//The detail explanation of the competition (in real life, which is the same format) can be found on the group stage
//  and main event section on the following website: http://wiki.teamliquid.net/dota2/The_International/2016
//The days will be off in this game compare to the actual tournament.
//The difference is, for a tie in the top 4 or bottom 4 teams, it will be determined purely on a random base. And
//  tie breaker matches are played in a different format.
//For the tie breaker matches, teams with the same score will play all other teams. The team with the highest score
//  will be put in the first position and lowest score in the last position of the tied teams. If there is a tie in
//  highest or lowest scores, then they will have a tie breaker between them. It will be done until no team or 1 team 
//  left.
//All tie breaker matches will be played in BO2 and BO3 (only for 2 team tiebreaker) format.
//If the team win a BO2, they get 3 points, if they tie, they both get 1 point, if the team lose, they get no point.

//For all group stage matches, if the user win, they will get 5000 money to train their player. If it is a tie, user
//  get 3000 money. If the user lose, they will get 1000.
//For all main even matches, user will get 7000 money for every win, or 5000 money for lost (only for upper bracket to
//  lower bracket lost, if they lost in lower, it is game over).

public class The_International_2016 {
  
//---------------------------------------------------------------------------------------------------------------------
//       Variables
//---------------------------------------------------------------------------------------------------------------------
  
  //Declare variables needed
  private Team[] team;
  private ArrayList<Team> teams;
  private ArrayList<Match> upperBracketMatches = new ArrayList<Match>();
  private ArrayList<Match> lowerBracketMatches = new ArrayList<Match>();
  private ArrayList<Team> upperBracketTeams = new ArrayList<Team>();
  private ArrayList<Team> lowerBracketTeams = new ArrayList<Team>();
  private Team userTeam;
  
//---------------------------------------------------------------------------------------------------------------------
//       Constructor
//---------------------------------------------------------------------------------------------------------------------
  
  //Constructor method
  //Initialize the 16 teams with the teams provided
  //Initialize the userTeam with user's team
  public The_International_2016 (Team[] team) {
    this.team = team;
    teams = new ArrayList<Team>();
    for (int x = 0; x < 16; x++) {
      teams.add(team[x]);
    } //End of for loop
    userTeam = team[0];
  } //End of constructor method
  
//---------------------------------------------------------------------------------------------------------------------
//       Run the competition
//---------------------------------------------------------------------------------------------------------------------
  
  //Run the competition, basically call the two methods
  public void runCompetition () {
    runGroupStage();
    runMainEvent();
  } //End of runCompetition method
  
//---------------------------------------------------------------------------------------------------------------------
//       Run the group stage of the competition
//---------------------------------------------------------------------------------------------------------------------
  
  //Run the group stage of the competition
  private void runGroupStage () {
    
    //Declare major variables needed
    Team[] group1 = new Team[8];
    Team[] group2 = new Team[8];
    int[] group1Points = new int[8];
    int[] group2Points = new int[8];
    
    //Declare minar variables needed
    int[] group1MatchPlayed = new int[8];
    int group1TotalMatchPlayed;
    int[] group1Order = new int[8];
    int[] group2Order = new int[8];
    
    //Declare other variables
    String tempString = "";
    ArrayList<Integer> team1Matches = new ArrayList<Integer>();
    ArrayList<Integer> team2Matches = new ArrayList<Integer>();
    ArrayList<Integer> team3Matches = new ArrayList<Integer>();
    ArrayList<Integer> team4Matches = new ArrayList<Integer>();
    ArrayList<Integer> team5Matches = new ArrayList<Integer>();
    ArrayList<Integer> team6Matches = new ArrayList<Integer>();
    ArrayList<Integer> team7Matches = new ArrayList<Integer>();
    int round = 1;
    int[] displayOrder;
    
//---------------------------------------------------------------------------------------------------------------------
//  Introduce the competition
    
    JOptionPane.showMessageDialog(null, "Welcome to The 2016 Internation DoTA2 Championships.");
    JOptionPane.showMessageDialog(null, "This year, we have 16 teams across the world to compete for the highest " +
                                  "glory in DoTA2 - The Aegis, and take a share in the highest price pool in e-sport" +
                                  " history - 20 million dollars.");
    for (int x = 0; x < 16; x++) {
      tempString += "  " + (x+1) + ": " + teams.get(x).getName() + "\n";
    } //End of for loop
    JOptionPane.showMessageDialog(null, "The 16 teams participating this years are:\n" +
                                  tempString);
    tempString = "";
    
//---------------------------------------------------------------------------------------------------------------------
//  Group Stage Preparation
    
    JOptionPane.showMessageDialog(null, "Welcome to the group stage.\n" + 
                                  "16 teams will be seperated into 2 groups of 8 teams each.");
    JOptionPane.showMessageDialog(null, "The group stage will be played in a round-robin format.\n" + 
                                  "The top 4 teams in each group advance to upper bracket, the bottom 4 teams in " +
                                  "each group advance to lower bracket.");
    JOptionPane.showMessageDialog(null, "Good Luck and Have Fun.");
    
    //Randomize the teams in the two groups.
    //The user's team will always be in group 1.
    group1[0] = teams.get(0); //Add user's team to group 1
    teams.remove(0);
    for (int x = 1; x < 8; x++) { //Initialize group 1 with 8 random teams
      int randomTeam = (int)(Math.random()*teams.size());
      group1[x] = teams.get(randomTeam);
      teams.remove(randomTeam);
    } //End of for loop
    for (int x = 0; x < 8; x++) { //Initialize group 2 with the rest 8 teams
      group2[x] = teams.get(x);
    } //End of for loop
    
    //Output the teams in each group
    tempString += "Group 1 Teams:\n"; //Display group 1 teams
    for (int x = 0; x < 8; x++) {
      tempString += "  " + group1[x].getName() + "\n";
    } //End of for loop
    JOptionPane.showMessageDialog(null, tempString);
    tempString = "Group 2 Teams:\n"; //Display group 2 teams
    for (int x = 0; x < 8; x++) {
      tempString += "  " + group2[x].getName() + "\n";
    } //End of for loop
    JOptionPane.showMessageDialog(null, tempString);
    
    //Initilize the two array holding the result of the group stage
    for (int x = 0; x < 8; x++) {
      group1Points[x] = 0;
      group2Points[x] = 0;
    } //End of for loop
    
    //Initilize the array hold the matches played by each team
    for (int x = 0; x < 8; x++) {
      group1MatchPlayed[x] = 0;
    } //End of for loop
    
    //Initialize the arraylists that hold the matches each team need to play
    for (int x = 1; x < 8; x++) {
      team1Matches.add(x);
    } //End of for loop
    for (int x = 2; x < 8; x++) {
      team2Matches.add(x);
    } //End of for loop
    for (int x = 3; x < 8; x++) {
      team3Matches.add(x);
    } //End of for loop
    for (int x = 4; x < 8; x++) {
      team4Matches.add(x);
    } //End of for loop
    for (int x = 5; x < 8; x++) {
      team5Matches.add(x);
    } //End of for loop
    for (int x = 6; x < 8; x++) {
      team6Matches.add(x);
    } //End of for loop
    team7Matches.add(7);
    
//---------------------------------------------------------------------------------------------------------------------
//  Group Stage Gameplay
    
    //Play the game in the group 1.
    //Group two will be auto played and the result will be showed after group 1 is done.
    while (true) {
      
      //Check if all matches are played in the group stage, if so, break.
      group1TotalMatchPlayed = 0;
      for (int x = 0; x < 8; x++) {
        group1TotalMatchPlayed += group1MatchPlayed[x];
      } //End of for loop
      if (group1TotalMatchPlayed == 56) {
        break;
      } //End of if statement
      
      //Make sure that each team plays a certain number of matches in total ("round" number of times).
      if (group1MatchPlayed[0] < round) { //If team 1 have not play enough match and there is "more" matches for it to
        if (team1Matches.size() > 0) {    //  play, then pick a random team haven't play against yet and play it.
          int randomNumber = (int)(Math.random()*team1Matches.size());
          int opponent = team1Matches.get(randomNumber);
          team1Matches.remove(randomNumber);
          Match match = new Match(group1[0], group1[opponent], 2, "Group Stage Regular Match", true);
          JOptionPane.showMessageDialog(null, match);
          int result = match.playMatch(); //Get the result of the match
          if (result == 1) { //If user win
            group1MatchPlayed[0]++;
            group1MatchPlayed[opponent]++;
            group1Points[0] += 3;
            JOptionPane.showMessageDialog(null, "You get 3 points for winning this group stage match.\n" +
                                          "Since you win the match, you will get 5000 money to train your" +
                                          " players");
            group1[0].increaseMoney(5000);
          } //End of if statement
          else if (result == 0) { //If it is a tie
            group1MatchPlayed[0]++;
            group1MatchPlayed[opponent]++;
            group1Points[0]++;
            group1Points[opponent]++;
            JOptionPane.showMessageDialog(null,  "You get 1 points for tieing this group stage match.\n" +
                                          "Since it is a tie match, you will get 3000 money to train your" +
                                          " players");
            group1[0].increaseMoney(3000);
          } //End of else if statement
          else { //If user lost
            group1MatchPlayed[0]++;
            group1MatchPlayed[opponent]++;
            group1Points[opponent] += 3;
            JOptionPane.showMessageDialog(null,  "You get 0 points for losing this group stage match.\n" +
                                          "Since you lose the match, you will only get 1000 money that can" +
                                          " be used to train your players");
            group1[0].increaseMoney(1000);
          } //End of else statement
        } //End of if statement
      } //End of if statement
      if (group1MatchPlayed[1] < round) { //If team 2 have not play enough match and there is "more" matches for it to
        if (team2Matches.size() > 0) {    //  play, then pick a random team haven't play against yet and play it.
          int randomNumber = (int)(Math.random()*team2Matches.size());
          int opponent = team2Matches.get(randomNumber);
          team2Matches.remove(randomNumber);
          Match match = new Match(group1[1], group1[opponent], 2, "Group Stage Regular Match", false);
          int result = match.playMatch(); //Get the result of the match
          if (result == 1) { //If team 2 win
            group1MatchPlayed[1]++;
            group1MatchPlayed[opponent]++;
            group1Points[1] += 3;
          } //End of if statement
          else if (result == 0) { //If it is a tie
            group1MatchPlayed[1]++;
            group1MatchPlayed[opponent]++;
            group1Points[1]++;
            group1Points[opponent]++;
          } //End of else if statement
          else { //If team 2 lost
            group1MatchPlayed[1]++;
            group1MatchPlayed[opponent]++;
            group1Points[opponent] += 3;
          } //End of else statement
        } //End of if statement
      } //End of if statement
      if (group1MatchPlayed[2] < round) { //If team 3 have not play enough match and there is "more" matches for it to
        if (team3Matches.size() > 0) {    //  play, then pick a random team haven't play against yet and play it.
          int randomNumber = (int)(Math.random()*team3Matches.size());
          int opponent = team3Matches.get(randomNumber);
          team3Matches.remove(randomNumber);
          Match match = new Match(group1[2], group1[opponent], 2, "Group Stage Regular Match", false);
          int result = match.playMatch(); //Get the result of the match
          if (result == 1) { //If team 3 win
            group1MatchPlayed[2]++;
            group1MatchPlayed[opponent]++;
            group1Points[2] += 3;
          } //End of if statement
          else if (result == 0) { //If it is a tie
            group1MatchPlayed[2]++;
            group1MatchPlayed[opponent]++;
            group1Points[2]++;
            group1Points[opponent]++;
          } //End of else if statement
          else { //If team 3 lost
            group1MatchPlayed[2]++;
            group1MatchPlayed[opponent]++;
            group1Points[opponent] += 3;
          } //End of else statement
        } //End of if statement
      } //End of if statement
      if (group1MatchPlayed[3] < round) { //If team 4 have not play enough match and there is "more" matches for it to
        if (team4Matches.size() > 0) {    //  play, then pick a random team haven't play against yet and play it.
          int randomNumber = (int)(Math.random()*team4Matches.size());
          int opponent = team4Matches.get(randomNumber);
          team4Matches.remove(randomNumber);
          Match match = new Match(group1[3], group1[opponent], 2, "Group Stage Regular Match", false);
          int result = match.playMatch(); //Get the result of the match
          if (result == 1) { //If team 4 lost
            group1MatchPlayed[3]++;
            group1MatchPlayed[opponent]++;
            group1Points[3] += 3;
          } //End of if statement
          else if (result == 0) { //If it is a tie
            group1MatchPlayed[3]++;
            group1MatchPlayed[opponent]++;
            group1Points[3]++;
            group1Points[opponent]++;
          } //End of else if statement
          else { //If team 4 lost
            group1MatchPlayed[3]++;
            group1MatchPlayed[opponent]++;
            group1Points[opponent] += 3;
          } //End of else statement
        } //End of if statement
      } //End of if statement
      if (group1MatchPlayed[4] < round) { //If team 5 have not play enough match and there is "more" matches for it to
        if (team5Matches.size() > 0) {    //  play, then pick a random team haven't play against yet and play it.
          int randomNumber = (int)(Math.random()*team5Matches.size());
          int opponent = team5Matches.get(randomNumber);
          team5Matches.remove(randomNumber);
          Match match = new Match(group1[4], group1[opponent], 2, "Group Stage Regular Match", false);
          int result = match.playMatch(); //Get the result of the match
          if (result == 1) { //If team 5 lost
            group1MatchPlayed[4]++;
            group1MatchPlayed[opponent]++;
            group1Points[4] += 3;
          } //End of if statement
          else if (result == 0) { //If it is a tie
            group1MatchPlayed[4]++;
            group1MatchPlayed[opponent]++;
            group1Points[4]++;
            group1Points[opponent]++;
          } //End of else if statement
          else { //If team 5 lost
            group1MatchPlayed[4]++;
            group1MatchPlayed[opponent]++;
            group1Points[opponent] += 3;
          } //End of else statement
        } //End of if statement
      } //End of if statement
      if (group1MatchPlayed[5] < round) { //If team 6 have not play enough match and there is "more" matches for it to
        if (team5Matches.size() > 0) {    //  play, then pick a random team haven't play against yet and play it.
          int randomNumber = (int)(Math.random()*team6Matches.size());
          int opponent = team6Matches.get(randomNumber);
          team6Matches.remove(randomNumber);
          Match match = new Match(group1[5], group1[opponent], 2, "Group Stage Regular Match", false);
          int result = match.playMatch(); //Get the result of the match
          if (result == 1) { //If team 6 lost
            group1MatchPlayed[5]++;
            group1MatchPlayed[opponent]++;
            group1Points[5] += 3;
          } //End of if statement
          else if (result == 0) { //If it is a tie
            group1MatchPlayed[5]++;
            group1MatchPlayed[opponent]++;
            group1Points[5]++;
            group1Points[opponent]++;
          } //End of else if statement
          else { //If team 6 lost
            group1MatchPlayed[5]++;
            group1MatchPlayed[opponent]++;
            group1Points[opponent] += 3;
          } //End of else statement
        } //End of if statement
      } //End of if statement
      if (group1MatchPlayed[6] < round) { //If team 7 have not play enough match and there is "more" matches for it to
        if (team7Matches.size() > 0) {    //  play, then pick a random team haven't play against yet and play it.
          int randomNumber = (int)(Math.random()*team7Matches.size());
          int opponent = team7Matches.get(randomNumber);
          team7Matches.remove(randomNumber);
          Match match = new Match(group1[6], group1[opponent], 2, "Group Stage Regular Match", false);
          int result = match.playMatch(); //Get the result of the match
          if (result == 1) { //If team 7 lost
            group1MatchPlayed[6]++;
            group1MatchPlayed[opponent]++;
            group1Points[6] += 3;
          } //End of if statement
          else if (result == 0) { //If it is a tie
            group1MatchPlayed[6]++;
            group1MatchPlayed[opponent]++;
            group1Points[6]++;
            group1Points[opponent]++;
          } //End of else if statement
          else { //If team 7 lost
            group1MatchPlayed[6]++;
            group1MatchPlayed[opponent]++;
            group1Points[opponent] += 3;
          } //End of else statement
        } //End of if statement
      } //End of if statement
      
      //Display the result
      displayOrder = groupStageSortByPoints(group1Points);
      displayGroupStageResult(group1, group1Points, group1MatchPlayed, displayOrder, round, 1);
      
      //Allow user to train the team
      JOptionPane.showMessageDialog(null, "Now, you can use the money you get to train your players.");
      group1[0].trainTeam(team);
      
      round++; //Increase round by 1
      
    } //End of while loop
    
    //After group 1 is done, group 2 will be auto played
    for (int x = 0; x < 7; x++) {
      for (int y = (x+1); y < 8; y++) {
        Match match = new Match(group2[x], group2[y], 2, "Group Stage Regular Match", false);
        int result = match.playMatch();
        if (result == 1) {
          group2Points[x] += 3;
        } //End of if statement
        else if (result == 0) {
          group2Points[x]++;
          group2Points[y]++;
        } //End of else if statement
        else {
          group2Points[y] += 3;
        } //End of else statement
      } //End of for loop
    } //End of for loop
    
//---------------------------------------------------------------------------------------------------------------------
//  Main Event Preparation
    
    //Order the teams in each group by points
    group1Order = groupStageSortByPoints(group1Points);
    group2Order = groupStageSortByPoints(group2Points);
    
    //Sort out the order of teams with a tie along the Upper and Lower Bracket divider
    //Check for group 1
    if (group1Points[group1Order[3]] == group1Points[group1Order[4]]) {
      ArrayList<Integer> tieTeams = new ArrayList<Integer>(); //Declare variables needed
      ArrayList<Integer> tiePositions = new ArrayList<Integer>();
      tieTeams.add(group1Order[3]);
      tieTeams.add(group1Order[4]);
      for (int x = 0; x < 3; x++) { //Check for ties (already checked for 4th and 5th)
        if (group1Points[group1Order[3]] == group1Points[group1Order[x]]) {
          tieTeams.add(group1Order[x]); tiePositions.add(x);
        } //End of if statement
      } //End of for loop
      tiePositions.add(3);
      tiePositions.add(4);
      for (int x = 5; x < 8; x++) {
        if (group1Points[group1Order[3]] == group1Points[group1Order[x]]) {
          tieTeams.add(group1Order[x]); tiePositions.add(x);
        } //End of if statement
      } //End of for loop
      group1Order = tieBreaker(group1Order, tieTeams, tiePositions, group1);
    } //End of if statement
    //Check for group 2
    if (group2Points[group2Order[3]] == group2Points[group2Order[4]]) {
      ArrayList<Integer> tieTeams = new ArrayList<Integer>(); //Declare variables needed
      ArrayList<Integer> tiePositions = new ArrayList<Integer>();
      tieTeams.add(group2Order[3]);
      tieTeams.add(group2Order[4]);
      for (int x = 0; x < 3; x++) { //Check for ties (already checked for 4th and 5th)
        if (group2Points[group2Order[3]] == group2Points[group2Order[x]]) {
          tieTeams.add(group2Order[x]); tiePositions.add(x);
        } //End of if statement
      } //End of for loop
      tiePositions.add(3);
      tiePositions.add(4);
      for (int x = 5; x < 8; x++) {
        if (group2Points[group2Order[3]] == group2Points[group2Order[x]]) {
          tieTeams.add(group2Order[x]); tiePositions.add(x);
        } //End of if statement
      } //End of for loop
      group2Order = tieBreaker(group2Order, tieTeams, tiePositions, group2);
    } //End of if statement
    
    //Display the final result of each group
    int[] group2MatchPlayed = new int[8];
    for (int x = 0; x < group2MatchPlayed.length; x++) {
      group2MatchPlayed[x] = 7;
    } //End of for loop
    JOptionPane.showMessageDialog(null, "The followings are the final result of each group during the group stage.");
    displayGroupStageResult(group1, group1Points, group1MatchPlayed, group1Order, 8, 1);
    displayGroupStageResult(group2, group2Points, group2MatchPlayed, group2Order, 8, 2);
    
    //Determine the matches in each group
    JOptionPane.showMessageDialog(null, "Now, the competition will move from group stage to main event.\n" +
                                  "The top four teams of each group will get into upper bracket, the lower four " +
                                  "teams of each group will get into lower bracket.\n" +
                                  "If you lose in the upper bracket, you will get down to lower bracket, if you " +
                                  "lose in lower bracket, you lost the competition.\n\n" +
                                  "There is no bracket reset for the lower bracket team for grand final.");
    JOptionPane.showMessageDialog(null, "The 1st place of each group can choose their opponent, either 3rd place " +
                                  "or 4th place of another group. The 2nd place of each group will take the team" +
                                  " first place does not pick.\n" +
                                  "The 5th place of each group can choose their opponent, either 7th place " +
                                  "or 8th place of another group. The 6th place of each group will take the team" +
                                  " first place does not pick.");
    //Add the upper bracket matches
    if (group1[group1Order[0]].user) { //If the user is the first place of group 1
      while (true) {
        int opponent = Integer.parseInt(JOptionPane.showInputDialog(null, "Which opponent do you want to face? \n" +
                                                                    "  1: " + group2[group2Order[2]].getName() + 
                                                                    " - 3rd place\n" +
                                                                    "  2: " + group2[group2Order[3]].getName() + 
                                                                    " - 4th place"));
        if (opponent == 1) {
          upperBracketMatches.add(new Match(group1[group1Order[0]], group2[group2Order[2]], 3, 
                                            "Upper Bracket Round 1", true));
          upperBracketMatches.add(new Match(group1[group1Order[1]], group2[group2Order[3]], 3, 
                                            "Upper Bracket Round 1", false));
          break;
        } //End of if statement
        else if (opponent == 2) {
          upperBracketMatches.add(new Match(group1[group1Order[0]], group2[group2Order[3]], 3, 
                                            "Upper Bracket Round 1", true));
          upperBracketMatches.add(new Match(group1[group1Order[1]], group2[group2Order[2]], 3, 
                                            "Upper Bracket Round 1", false));
          break;
        } //End of else if statement
        else {
          JOptionPane.showMessageDialog(null, "Invalid input, please input a valid number.");
        } //End of else statement
      } //End of while loop
    } //End of if statement
    else { //If the user is not the first place of group 1
      upperBracketMatches.add(new Match(group1[group1Order[0]], group2[group2Order[3]], 3, "Upper Bracket Round 1", 
                                        false));
      upperBracketMatches.add(new Match(group1[group1Order[1]], group2[group2Order[2]], 3, "Upper Bracket Round 1", 
                                        group1[group1Order[1]].user));
    } //End of else statement
    upperBracketMatches.add(new Match(group2[group2Order[0]], group1[group1Order[3]], 3, "Upper Bracket Round 1",
                                      group1[group1Order[3]].user));
    upperBracketMatches.add(1, new Match(group2[group2Order[1]], group1[group1Order[2]], 3, "Upper Bracket Round 1",
                                         group1[group1Order[2]].user));
    //Add the lower bracket matches
    if (group1[group1Order[4]].user) { //If the user is the 5th place of group 1
      while (true) {
        int opponent = Integer.parseInt(JOptionPane.showInputDialog(null, "Which opponent do you want to face? \n" +
                                                                    "  1: " + group2[group2Order[6]].getName() + 
                                                                    " - 7th place\n" +
                                                                    "  2: " + group2[group2Order[7]].getName() + 
                                                                    " - 8th place"));
        if (opponent == 1) {
          lowerBracketMatches.add(new Match(group1[group1Order[4]], group2[group2Order[6]], 1, 
                                            "Lower Bracket Round 1", true));
          lowerBracketMatches.add(new Match(group1[group1Order[5]], group2[group2Order[7]], 1, 
                                            "Lower Bracket Round 1", false));
          break;
        } //End of if statement
        else if (opponent == 2) {
          lowerBracketMatches.add(new Match(group1[group1Order[4]], group2[group2Order[7]], 1, 
                                            "Lower Bracket Round 1", true));
          lowerBracketMatches.add(new Match(group1[group1Order[5]], group2[group2Order[6]], 1, 
                                            "Lower Bracket Round 1", false));
          break;
        } //End of else if statement
        else {
          JOptionPane.showMessageDialog(null, "Invalid input, please input a valid number.");
        } //End of else statement
      } //End of while loop
    } //End of if statement
    else { //If the user is not the 5th place of group 1
      lowerBracketMatches.add(new Match(group1[group1Order[4]], group2[group2Order[7]], 1, "Lower Bracket Round 1", 
                                        false));
      lowerBracketMatches.add(new Match(group1[group1Order[5]], group2[group2Order[6]], 1, "Lower Bracket Round 1", 
                                        group1[group1Order[5]].user));
    } //End of else statement
    lowerBracketMatches.add(new Match(group2[group2Order[4]], group1[group1Order[7]], 1, "Lower Bracket Round 1",
                                      group1[group1Order[7]].user));
    lowerBracketMatches.add(1, new Match(group2[group2Order[5]], group1[group1Order[6]], 1, "Lower Bracket Round 1",
                                         group1[group1Order[6]].user));
    
    //Display all the matches
    JOptionPane.showMessageDialog(null, "   Main Event Matches\n\n" + 
                                  "Lower Bracket Round 1: (day 1)\n" +
                                  lowerBracketMatches.get(0) + "\n" +
                                  lowerBracketMatches.get(1) + "\n" +
                                  lowerBracketMatches.get(2) + "\n" +
                                  lowerBracketMatches.get(3) + "\n\n" +
                                  "Upper Bracket Round 1: (day 2)\n" + 
                                  upperBracketMatches.get(0) + "\n" +
                                  upperBracketMatches.get(1) + "\n" +
                                  upperBracketMatches.get(2) + "\n" +
                                  upperBracketMatches.get(3));
  } //End of runGroupStage method
  
//---------------------------------------------------------------------------------------------------------------------
//       Run the main event of the competition
//---------------------------------------------------------------------------------------------------------------------
  
  //Run the main event of the competition
  private void runMainEvent() {
    
    //Declare variables needed
    int place;
    int result;
    
    //Intro to main event
    JOptionPane.showMessageDialog(null, "Welcome to the main event of the competition. If you are in upper bracket " +
                                  "and you lose, you will get to lower bracket. If you are in lower bracket and you" +
                                  "lose, you lost in the competition.");
    JOptionPane.showMessageDialog(null, "Remember, use the money you get to train the players in your team to be " +
                                  "better prepared for the upcoming matches.\n" +
                                  "You will only get chances to train the players in the end of each day.");
    
    //Day 1, play the four match of Lower Bracket Round 1
    JOptionPane.showMessageDialog(null, "Day 1: the four matches in the Lower Bracket Round 1 will be played.\n" + 
                                  "They are:\n\n" + 
                                  lowerBracketMatches.get(0) + "\n" +
                                  lowerBracketMatches.get(1) + "\n" +
                                  lowerBracketMatches.get(2) + "\n" +
                                  lowerBracketMatches.get(3));
    for (int x = 0; x < 4; x++) { //Play the matches
      result = lowerBracketMatches.get(0).playMatch();
      if (lowerBracketMatches.get(0).userMatch()) { //If user's team is in it
        if (result == 1) { //If team 1 win
          if (lowerBracketMatches.get(0).getTeam(1).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                          "Bracket round 2. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
            teams.add(0, lowerBracketMatches.get(0).getTeam(2));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
            place = 13 + x;
            displayCompetitionResult(place);
            JOptionPane.showMessageDialog(null, "Your team got 13-16th place in the competition. Congradulations");
            JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
            JOptionPane.showMessageDialog(null, "Game Over");
            return;
          } //End of else statement
        } //End of if statement
        else { //If team 2 win
          if (lowerBracketMatches.get(0).getTeam(2).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                          "Bracket round 2. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
            teams.add(0, lowerBracketMatches.get(0).getTeam(1));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
            place = 13 + x;
            displayCompetitionResult(place);
            JOptionPane.showMessageDialog(null, "Your team got 13-16th place in the competition. Congradulations");
            JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
            JOptionPane.showMessageDialog(null, "Game Over");
            return;
          } //End of else statement
        } //End of else statement
      } //End of if statement
      else { //If user's team is not in it
        if (result == 1) { //If team 1 win
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
          teams.add(0, lowerBracketMatches.get(0).getTeam(2));
        } //End of if statement
        else { //If team 2 win
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
          teams.add(0, lowerBracketMatches.get(0).getTeam(1));
        } //End of else statement
        JOptionPane.showMessageDialog(null, "Match " + x + ": " + lowerBracketMatches.get(0).getTeam(1).getName() +
                                      " vs " + lowerBracketMatches.get(0).getTeam(2).getName() + "\n" +
                                      lowerBracketMatches.get(0).getTeam(result).getName() + " wins");
      } //End of else statement
      lowerBracketMatches.remove(0);
    } //End of for loop
    
    
    userTeam.trainTeam(team); //End of day 1, let the user train his team
    
    //Day 2, play the four match of Upper Bracket Round 1
    JOptionPane.showMessageDialog(null, "Day 2: the four matches in the Upper Bracket Round 1 will be played.\n" + 
                                  "They are:\n\n" + 
                                  upperBracketMatches.get(0) + "\n" +
                                  upperBracketMatches.get(1) + "\n" +
                                  upperBracketMatches.get(2) + "\n" +
                                  upperBracketMatches.get(3));
    for (int x = 0; x < 4; x++) { //Play the matches
      result = upperBracketMatches.get(0).playMatch();
      if (upperBracketMatches.get(0).userMatch()) { //If user's team is in it
        if (result == 1) { //If team 1 win
          if (upperBracketMatches.get(0).getTeam(1).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Upper " +
                                          "Bracket round 2. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            upperBracketTeams.add(upperBracketMatches.get(0).getTeam(1));
            lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(2));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in upper bracket, you will get down to lower bracket.\n" +
                                          "You will proceed to Lower Bracket Round 2. Your opponent will be " +
                                          "determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(5000);
            upperBracketTeams.add(upperBracketMatches.get(0).getTeam(2));
            lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(1));
          } //End of else statement
        } //End of if statement
        else { //If team 2 win
          if (lowerBracketMatches.get(0).getTeam(2).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Upper " +
                                          "Bracket round 2. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(2).increaseMoney(7000);
            upperBracketTeams.add(upperBracketMatches.get(0).getTeam(2));
            lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(1));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in upper bracket, you will get down to lower bracket.\n" +
                                          "You will proceed to Lower Bracket Round 2. Your opponent will be " +
                                          "determined later.");
            lowerBracketMatches.get(0).getTeam(2).increaseMoney(5000);
            upperBracketTeams.add(upperBracketMatches.get(0).getTeam(1));
            lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(2));
          } //End of else statement
        } //End of else statement
      } //End of if statement
      else { //If user's team is not in it
        if (result == 1) { //If team 1 win
          upperBracketTeams.add(upperBracketMatches.get(0).getTeam(1));
          lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(2));
        } //End of if statement
        else { //If team 2 win
          upperBracketTeams.add(upperBracketMatches.get(0).getTeam(2));
          lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(1));
        } //End of else statement
        JOptionPane.showMessageDialog(null, "Match " + x + ": " + upperBracketMatches.get(0).getTeam(1).getName() +
                                      " vs " + upperBracketMatches.get(0).getTeam(2).getName() + "\n" +
                                      upperBracketMatches.get(0).getTeam(result).getName() + " wins");
      } //End of else statement
      upperBracketMatches.remove(0);
    } //End of for loop
    
    userTeam.trainTeam(team); //End of day 2, let the user train his team
    
    //Matchmaking for Lower Bracket Round 2
    for (int x = 0; x < 4; x++) {
      if (lowerBracketTeams.get(0).user || lowerBracketTeams.get(1).user) { //If user's team is in it
        lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                          "Lower Bracket Round 2", true));
      } //End of if statement
      else { //If user's team is not in it
        lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                          "Lower Bracket Round 2", false));
      } //End of else statement
      lowerBracketTeams.remove(0); lowerBracketTeams.remove(0);
    } //End of for loop
    
    //Display all the matches after the match making
    JOptionPane.showMessageDialog(null, "   Main Event Matches\n\n" + 
                                  "Lower Bracket Round 2: (day 3)\n" +
                                  lowerBracketMatches.get(0) + "\n" +
                                  lowerBracketMatches.get(1) + "\n" +
                                  lowerBracketMatches.get(2) + "\n" +
                                  lowerBracketMatches.get(3));
    
    //Day 3, play the four match of Lower Bracket Round 2
    JOptionPane.showMessageDialog(null, "Day 3: the four matches in the Lower Bracket Round 2 will be played.\n" + 
                                  "They are:\n\n" + 
                                  lowerBracketMatches.get(0) + "\n" +
                                  lowerBracketMatches.get(1) + "\n" +
                                  lowerBracketMatches.get(2) + "\n" +
                                  lowerBracketMatches.get(3));
    for (int x = 0; x < 4; x++) { //Play the matches
      result = lowerBracketMatches.get(0).playMatch();
      if (lowerBracketMatches.get(0).userMatch()) { //If user's team is in it
        if (result == 1) { //If team 1 win
          if (lowerBracketMatches.get(0).getTeam(1).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                          "Bracket round 3. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
            teams.add(0, lowerBracketMatches.get(0).getTeam(2));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
            place = 9 + x;
            displayCompetitionResult(place);
            JOptionPane.showMessageDialog(null, "Your team got 9-12th place in the competition. Congradulations");
            JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
            JOptionPane.showMessageDialog(null, "Game Over");
            return;
          } //End of else statement
        } //End of if statement
        else { //If team 2 win
          if (lowerBracketMatches.get(0).getTeam(2).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                          "Bracket round 3. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
            teams.add(0, lowerBracketMatches.get(0).getTeam(1));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
            place = 9 + x;
            displayCompetitionResult(place);
            JOptionPane.showMessageDialog(null, "Your team got 9-12th place in the competition. Congradulations");
            JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
            JOptionPane.showMessageDialog(null, "Game Over");
            return;
          } //End of else statement
        } //End of else statement
      } //End of if statement
      else { //If user's team is not in it
        if (result == 1) { //If team 1 win
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
          teams.add(0, lowerBracketMatches.get(0).getTeam(2));
        } //End of if statement
        else { //If team 2 win
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
          teams.add(0, lowerBracketMatches.get(0).getTeam(1));
        } //End of else statement
        JOptionPane.showMessageDialog(null, "Match " + x + ": " + lowerBracketMatches.get(0).getTeam(1).getName() +
                                      " vs " + lowerBracketMatches.get(0).getTeam(2).getName() + "\n" +
                                      lowerBracketMatches.get(0).getTeam(result).getName() + " wins");
      } //End of else statement
      lowerBracketMatches.remove(0);
    } //End of for loop
    
    userTeam.trainTeam(team); //End of day 3, let the user train his team
    
    //Matchmaking for Upper Bracket Round 2  and Lower Bracket Round 3
    for (int x = 0; x < 2; x++) { //Matchingmaking for Lower Bracket Round 3
      if (lowerBracketTeams.get(0).user || lowerBracketTeams.get(1).user) { //If user's team is in it
        lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                          "Lower Bracket Round 3", true));
      } //End of if statement
      else { //If user's team is not in it
        lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                          "Lower Bracket Round 3", false));
      } //End of else statement
      lowerBracketTeams.remove(0); lowerBracketTeams.remove(0);
    } //End of for loop
    for (int x = 0; x < 2; x++) { //Matchingmaking for Upper Bracket Round 2
      if (upperBracketTeams.get(0).user || upperBracketTeams.get(1).user) { //If user's team is in it
        upperBracketMatches.add(new Match(upperBracketTeams.get(0), upperBracketTeams.get(1), 3, 
                                          "Upper Bracket Round 2", true));
      } //End of if statement
      else { //If user's team is not in it
        upperBracketMatches.add(new Match(upperBracketTeams.get(0), upperBracketTeams.get(1), 3, 
                                          "Upper Bracket Round 2", false));
      } //End of else statement
      upperBracketTeams.remove(0); upperBracketTeams.remove(0);
    } //End of for loop
    
    //Display all the matches after the match making
    JOptionPane.showMessageDialog(null, "   Main Event Matches\n\n" + 
                                  "Lower Bracket Round 3: (day 4)\n" +
                                  lowerBracketMatches.get(0) + "\n" +
                                  lowerBracketMatches.get(1) + "\n" +
                                  "Upper Bracket Round 2: (day 5)\n" + 
                                  upperBracketMatches.get(0) + "\n" +
                                  upperBracketMatches.get(1));
    
    //Day 4, play the two match of Lower Bracket Round 3
    JOptionPane.showMessageDialog(null, "Day 4: the two matches in the Lower Bracket Round 3 will be played.\n" + 
                                  "They are:\n\n" + 
                                  lowerBracketMatches.get(0) + "\n" +
                                  lowerBracketMatches.get(1));
    for (int x = 0; x < 2; x++) { //Play the matches
      result = lowerBracketMatches.get(0).playMatch();
      if (lowerBracketMatches.get(0).userMatch()) { //If user's team is in it
        if (result == 1) { //If team 1 win
          if (lowerBracketMatches.get(0).getTeam(1).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                          "Bracket round 4. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
            teams.add(0, lowerBracketMatches.get(0).getTeam(2));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
            place = 7 + x;
            displayCompetitionResult(place);
            JOptionPane.showMessageDialog(null, "Your team got 7-8th place in the competition. Congradulations");
            JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
            JOptionPane.showMessageDialog(null, "Game Over");
            return;
          } //End of else statement
        } //End of if statement
        else { //If team 2 win
          if (lowerBracketMatches.get(0).getTeam(2).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                          "Bracket round 4. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
            teams.add(0, lowerBracketMatches.get(0).getTeam(1));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
            place = 7 + x;
            displayCompetitionResult(place);
            JOptionPane.showMessageDialog(null, "Your team got 7-8th place in the competition. Congradulations");
            JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
            JOptionPane.showMessageDialog(null, "Game Over");
            return;
          } //End of else statement
        } //End of else statement
      } //End of if statement
      else { //If user's team is not in it
        if (result == 1) { //If team 1 win
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
          teams.add(0, lowerBracketMatches.get(0).getTeam(2));
        } //End of if statement
        else { //If team 2 win
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
          teams.add(0, lowerBracketMatches.get(0).getTeam(1));
        } //End of else statement
        JOptionPane.showMessageDialog(null, "Match " + x + ": " + lowerBracketMatches.get(0).getTeam(1).getName() +
                                      " vs " + lowerBracketMatches.get(0).getTeam(2).getName() + "\n" +
                                      lowerBracketMatches.get(0).getTeam(result).getName() + " wins");
      } //End of else statement
      lowerBracketMatches.remove(0);
    } //End of for loop
    
    userTeam.trainTeam(team); //End of day 4, let the user train his team
    
    //Day 5, play the two match of Upper Bracket Round 2
    JOptionPane.showMessageDialog(null, "Day 5: the two matches in the Upper Bracket Round 2 will be played.\n" + 
                                  "They are:\n\n" + 
                                  upperBracketMatches.get(0) + "\n" +
                                  upperBracketMatches.get(1));
    for (int x = 0; x < 2; x++) { //Play the matches
      result = upperBracketMatches.get(0).playMatch();
      if (upperBracketMatches.get(0).userMatch()) { //If user's team is in it
        if (result == 1) { //If team 1 win
          if (upperBracketMatches.get(0).getTeam(1).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Upper " +
                                          "Bracket Final. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            upperBracketTeams.add(upperBracketMatches.get(0).getTeam(1));
            lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(2));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in upper bracket, you will get down to lower bracket.\n" +
                                          "You will proceed to Lower Bracket Round 4. Your opponent will be " +
                                          "determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(5000);
            upperBracketTeams.add(upperBracketMatches.get(0).getTeam(2));
            lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(1));
          } //End of else statement
        } //End of if statement
        else { //If team 2 win
          if (lowerBracketMatches.get(0).getTeam(2).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Upper " +
                                          "Bracket Final. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(2).increaseMoney(7000);
            upperBracketTeams.add(upperBracketMatches.get(0).getTeam(2));
            lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(1));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in upper bracket, you will get down to lower bracket.\n" +
                                          "You will proceed to Lower Bracket Round 4. Your opponent will be " +
                                          "determined later.");
            lowerBracketMatches.get(0).getTeam(2).increaseMoney(5000);
            upperBracketTeams.add(upperBracketMatches.get(0).getTeam(1));
            lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(2));
          } //End of else statement
        } //End of else statement
      } //End of if statement
      else { //If user's team is not in it
        if (result == 1) { //If team 1 win
          upperBracketTeams.add(upperBracketMatches.get(0).getTeam(1));
          lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(2));
        } //End of if statement
        else { //If team 2 win
          upperBracketTeams.add(upperBracketMatches.get(0).getTeam(2));
          lowerBracketTeams.add(((x*2)+1), upperBracketMatches.get(0).getTeam(1));
        } //End of else statement
        JOptionPane.showMessageDialog(null, "Match " + x + ": " + upperBracketMatches.get(0).getTeam(1).getName() +
                                      " vs " + upperBracketMatches.get(0).getTeam(2).getName() + "\n" +
                                      upperBracketMatches.get(0).getTeam(result).getName() + " wins");
      } //End of else statement
      upperBracketMatches.remove(0);
    } //End of for loop
    
    userTeam.trainTeam(team); //End of day 5, let the user train his team
    
    //Matchmaking for Lower Bracket Round 4
    for (int x = 0; x < 2; x++) {
      if (lowerBracketTeams.get(0).user || lowerBracketTeams.get(1).user) { //If user's team is in it
        lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                          "Lower Bracket Round 4", true));
      } //End of if statement
      else { //If user's team is not in it
        lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                          "Lower Bracket Round 4", false));
      } //End of else statement
      lowerBracketTeams.remove(0); lowerBracketTeams.remove(0);
    } //End of for loop
    
    //Display all the matches after the match making
    JOptionPane.showMessageDialog(null, "   Main Event Matches\n\n" + 
                                  "Lower Bracket Round 4: (day 6)\n" +
                                  lowerBracketMatches.get(0) + "\n" +
                                  lowerBracketMatches.get(1));
    
    //Day 6, play the two match of Lower Bracket Round 4
    JOptionPane.showMessageDialog(null, "Day 6: the two matches in the Lower Bracket Round 4 will be played.\n" + 
                                  "They are:\n\n" + 
                                  lowerBracketMatches.get(0) + "\n" +
                                  lowerBracketMatches.get(1));
    for (int x = 0; x < 2; x++) { //Play the matches
      result = lowerBracketMatches.get(0).playMatch();
      if (lowerBracketMatches.get(0).userMatch()) { //If user's team is in it
        if (result == 1) { //If team 1 win
          if (lowerBracketMatches.get(0).getTeam(1).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                          "Bracket round 5. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
            teams.add(0, lowerBracketMatches.get(0).getTeam(2));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
            place = 5 + x;
            displayCompetitionResult(place);
            JOptionPane.showMessageDialog(null, "Your team got 5-6th place in the competition. Congradulations");
            JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
            JOptionPane.showMessageDialog(null, "Game Over");
            return;
          } //End of else statement
        } //End of if statement
        else { //If team 2 win
          if (lowerBracketMatches.get(0).getTeam(2).user) { //If user won
            JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                          "Bracket round 5. Your opponent will be determined later.");
            lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
            lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
            teams.add(0, lowerBracketMatches.get(0).getTeam(1));
          } //End of if statement
          else { //If user lost
            JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
            place = 5 + x;
            displayCompetitionResult(place);
            JOptionPane.showMessageDialog(null, "Your team got 5-6th place in the competition. Congradulations");
            JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
            JOptionPane.showMessageDialog(null, "Game Over");
            return;
          } //End of else statement
        } //End of else statement
      } //End of if statement
      else { //If user's team is not in it
        if (result == 1) { //If team 1 win
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
          teams.add(0, lowerBracketMatches.get(0).getTeam(2));
        } //End of if statement
        else { //If team 2 win
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
          teams.add(0, lowerBracketMatches.get(0).getTeam(1));
        } //End of else statement
        JOptionPane.showMessageDialog(null, "Match " + x + ": " + lowerBracketMatches.get(0).getTeam(1).getName() +
                                      " vs " + lowerBracketMatches.get(0).getTeam(2).getName() + "\n" +
                                      lowerBracketMatches.get(0).getTeam(result).getName() + " wins");
      } //End of else statement
      lowerBracketMatches.remove(0);
    } //End of for loop
    
    userTeam.trainTeam(team); //End of day 6, let the user train his team
    
    //Matchmaking for Upper Bracket Final and Lower Bracket Round 5
    //Matchingmaking for Lower Bracket Round 5
    if (lowerBracketTeams.get(0).user || lowerBracketTeams.get(1).user) { //If user's team is in it
      lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                        "Lower Bracket Round 5", true));
    } //End of if statement
    else { //If user's team is not in it
      lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                        "Lower Bracket Round 5", false));
    } //End of else statement
    lowerBracketTeams.remove(0); lowerBracketTeams.remove(0);
    //Matchingmaking for Upper Bracket Final
    if (upperBracketTeams.get(0).user || upperBracketTeams.get(1).user) { //If user's team is in it
      upperBracketMatches.add(new Match(upperBracketTeams.get(0), upperBracketTeams.get(1), 3, 
                                        "Upper Bracket Final", true));
    } //End of if statement
    else { //If user's team is not in it
      upperBracketMatches.add(new Match(upperBracketTeams.get(0), upperBracketTeams.get(1), 3, 
                                        "Upper Bracket Final", false));
    } //End of else statement
    upperBracketTeams.remove(0); upperBracketTeams.remove(0);
    
    //Display all the matches after the match making
    JOptionPane.showMessageDialog(null, "   Main Event Matches\n\n" + 
                                  "Lower Bracket Round 5: (day 7)\n" +
                                  lowerBracketMatches.get(0) + "\n" +
                                  "Upper Bracket Final: (day 8)\n" + 
                                  upperBracketMatches.get(0));
    
    //Day 7, play the one match of Lower Bracket Round 5
    JOptionPane.showMessageDialog(null, "Day 7: the one match in the Lower Bracket Round 5 will be played.\n" + 
                                  "It is:\n\n" + 
                                  lowerBracketMatches.get(0));
    result = lowerBracketMatches.get(0).playMatch(); //Play the matches
    if (lowerBracketMatches.get(0).userMatch()) { //If user's team is in it
      if (result == 1) { //If team 1 win
        if (lowerBracketMatches.get(0).getTeam(1).user) { //If user won
          JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                        "Bracket Final. Your opponent will be determined later.");
          lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
          teams.add(0, lowerBracketMatches.get(0).getTeam(2));
        } //End of if statement
        else { //If user lost
          JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
          place = 4;
          displayCompetitionResult(place);
          JOptionPane.showMessageDialog(null, "Your team got 4th place in the competition. Congradulations");
          JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
          JOptionPane.showMessageDialog(null, "Game Over");
          return;
        } //End of else statement
      } //End of if statement
      else { //If team 2 win
        if (lowerBracketMatches.get(0).getTeam(2).user) { //If user won
          JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Lower " +
                                        "Bracket Final. Your opponent will be determined later.");
          lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
          lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
          teams.add(0, lowerBracketMatches.get(0).getTeam(1));
        } //End of if statement
        else { //If user lost
          JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
          place = 4;
          displayCompetitionResult(place);
          JOptionPane.showMessageDialog(null, "Your team got 4th place in the competition. Congradulations");
          JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
          JOptionPane.showMessageDialog(null, "Game Over");
          return;
        } //End of else statement
      } //End of else statement
    } //End of if statement
    else { //If user's team is not in it
      if (result == 1) { //If team 1 win
        lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
        teams.add(0, lowerBracketMatches.get(0).getTeam(2));
      } //End of if statement
      else { //If team 2 win
        lowerBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
        teams.add(0, lowerBracketMatches.get(0).getTeam(1));
      } //End of else statement
      JOptionPane.showMessageDialog(null, "Match " + 1 + ": " + lowerBracketMatches.get(0).getTeam(1).getName() +
                                    " vs " + lowerBracketMatches.get(0).getTeam(2).getName() + "\n" +
                                    lowerBracketMatches.get(0).getTeam(result).getName() + " wins");
    } //End of else statement
    lowerBracketMatches.remove(0);
    
    userTeam.trainTeam(team); //End of day 7, let the user train his team
    
    //Day 8, play the one match of Upper Bracket Final
    JOptionPane.showMessageDialog(null, "Day 8: the one matche in the Upper Bracket Final will be played.\n" + 
                                  "It is:\n\n" + 
                                  upperBracketMatches.get(0));
    result = upperBracketMatches.get(0).playMatch();
    if (upperBracketMatches.get(0).userMatch()) { //If user's team is in it
      if (result == 1) { //If team 1 win
        if (upperBracketMatches.get(0).getTeam(1).user) { //If user won
          JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Grand " +
                                        "Final. Your opponent will be determined later.");
          lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
          upperBracketTeams.add(upperBracketMatches.get(0).getTeam(1));
          lowerBracketTeams.add(1, upperBracketMatches.get(0).getTeam(2));
        } //End of if statement
        else { //If user lost
          JOptionPane.showMessageDialog(null, "You lost in upper bracket, you will get down to lower bracket.\n" +
                                        "You will proceed to Lower Bracket Final. Your opponent will be " +
                                        "determined later.");
          lowerBracketMatches.get(0).getTeam(1).increaseMoney(5000);
          upperBracketTeams.add(upperBracketMatches.get(0).getTeam(2));
          lowerBracketTeams.add(1, upperBracketMatches.get(0).getTeam(1));
        } //End of else statement
      } //End of if statement
      else { //If team 2 win
        if (lowerBracketMatches.get(0).getTeam(2).user) { //If user won
          JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Grand " +
                                        "Final. Your opponent will be determined later.");
          lowerBracketMatches.get(0).getTeam(2).increaseMoney(7000);
          upperBracketTeams.add(upperBracketMatches.get(0).getTeam(2));
          lowerBracketTeams.add(1, upperBracketMatches.get(0).getTeam(1));
        } //End of if statement
        else { //If user lost
          JOptionPane.showMessageDialog(null, "You lost in upper bracket, you will get down to lower bracket.\n" +
                                        "You will proceed to Lower Bracket Final. Your opponent will be " +
                                        "determined later.");
          lowerBracketMatches.get(0).getTeam(2).increaseMoney(5000);
          upperBracketTeams.add(upperBracketMatches.get(0).getTeam(1));
          lowerBracketTeams.add(1, upperBracketMatches.get(0).getTeam(2));
        } //End of else statement
      } //End of else statement
    } //End of if statement
    else { //If user's team is not in it
      if (result == 1) { //If team 1 win
        upperBracketTeams.add(upperBracketMatches.get(0).getTeam(1));
        lowerBracketTeams.add(1, upperBracketMatches.get(0).getTeam(2));
      } //End of if statement
      else { //If team 2 win
        upperBracketTeams.add(upperBracketMatches.get(0).getTeam(2));
        lowerBracketTeams.add(1, upperBracketMatches.get(0).getTeam(1));
      } //End of else statement
      JOptionPane.showMessageDialog(null, "Match " + 1 + ": " + upperBracketMatches.get(0).getTeam(1).getName() +
                                    " vs " + upperBracketMatches.get(0).getTeam(2).getName() + "\n" +
                                    upperBracketMatches.get(0).getTeam(result).getName() + " wins");
    } //End of else statement
    upperBracketMatches.remove(0);
    
    userTeam.trainTeam(team); //End of day 8, let the user train his team
    
    //Matchmaking for Lower Bracket Final
    if (lowerBracketTeams.get(0).user || lowerBracketTeams.get(1).user) { //If user's team is in it
      lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                        "Lower Bracket Final", true));
    } //End of if statement
    else { //If user's team is not in it
      lowerBracketMatches.add(new Match(lowerBracketTeams.get(0), lowerBracketTeams.get(1), 3, 
                                        "Lower Bracket Final", false));
    } //End of else statement
    lowerBracketTeams.remove(0); lowerBracketTeams.remove(0);
    
    //Display all the matches after the match making
    JOptionPane.showMessageDialog(null, "   Main Event Matches\n\n" + 
                                  "Lower Bracket Final: (day 9)\n" +
                                  lowerBracketMatches.get(0));
    
    //Day 9, play the one match of Lower Bracket Final
    JOptionPane.showMessageDialog(null, "Day 9: the one match in the Lower Bracket Final will be played.\n" + 
                                  "It is:\n\n" + 
                                  lowerBracketMatches.get(0));
    result = lowerBracketMatches.get(0).playMatch(); //Play the matches
    if (lowerBracketMatches.get(0).userMatch()) { //If user's team is in it
      if (result == 1) { //If team 1 win
        if (lowerBracketMatches.get(0).getTeam(1).user) { //If user won
          JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Grand " +
                                        "Final. Your opponent will be determined later.");
          lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
          upperBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
          teams.add(0, lowerBracketMatches.get(0).getTeam(2));
        } //End of if statement
        else { //If user lost
          JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
          place = 3;
          displayCompetitionResult(place);
          JOptionPane.showMessageDialog(null, "Your team got 3rd place in the competition. Congradulations");
          JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
          JOptionPane.showMessageDialog(null, "Game Over");
          return;
        } //End of else statement
      } //End of if statement
      else { //If team 2 win
        if (lowerBracketMatches.get(0).getTeam(2).user) { //If user won
          JOptionPane.showMessageDialog(null, "Congradulation, you won the match. You will advance to Grand " +
                                        "Final. Your opponent will be determined later.");
          lowerBracketMatches.get(0).getTeam(1).increaseMoney(7000);
          upperBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
          teams.add(0, lowerBracketMatches.get(0).getTeam(1));
        } //End of if statement
        else { //If user lost
          JOptionPane.showMessageDialog(null, "You lost in lower bracket, you lost the competition.");
          place = 3;
          displayCompetitionResult(place);
          JOptionPane.showMessageDialog(null, "Your team got 3rd place in the competition. Congradulations");
          JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
          JOptionPane.showMessageDialog(null, "Game Over");
          return;
        } //End of else statement
      } //End of else statement
    } //End of if statement
    else { //If user's team is not in it
      if (result == 1) { //If team 1 win
        upperBracketTeams.add(lowerBracketMatches.get(0).getTeam(1));
        teams.add(0, lowerBracketMatches.get(0).getTeam(2));
      } //End of if statement
      else { //If team 2 win
        upperBracketTeams.add(lowerBracketMatches.get(0).getTeam(2));
        teams.add(0, lowerBracketMatches.get(0).getTeam(1));
      } //End of else statement
      JOptionPane.showMessageDialog(null, "Match " + 1 + ": " + lowerBracketMatches.get(0).getTeam(1).getName() +
                                    " vs " + lowerBracketMatches.get(0).getTeam(2).getName() + "\n" +
                                    lowerBracketMatches.get(0).getTeam(result).getName() + " wins");
    } //End of else statement
    lowerBracketMatches.remove(0);
    
    userTeam.trainTeam(team); //End of day 9, let the user train his team
    
    //Matchmaking for Grand Final
    upperBracketMatches.add(new Match(upperBracketTeams.get(0), upperBracketTeams.get(1), 5, 
                                      "Grand Final", true));
    upperBracketTeams.remove(0); upperBracketTeams.remove(0);
    
    //Display all the matches after the match making
    JOptionPane.showMessageDialog(null, "   Main Event Matches\n\n" + 
                                  "Grand Final: (day 10)\n" +
                                  upperBracketMatches.get(0));
    
    //Day 10, play the Grand Final Match
    JOptionPane.showMessageDialog(null, "Day 10: the Grand Final match will be played.\n" + 
                                  "It is:\n\n" + 
                                  upperBracketMatches.get(0));
    JOptionPane.showMessageDialog(null, "Welcome to the Grand Final match. You have fight all the way against " +
                                  "other 15 top teams through the group stage and main event and comes to the " +
                                  "Grand Final.\n" + 
                                  "Will you beat your opponent just like how you did before and claim the Aegis " +
                                  "to be yours?");
    JOptionPane.showMessageDialog(null, "GL and HF on your final match.");
    result = upperBracketMatches.get(0).playMatch();
    if (upperBracketMatches.get(0).getTeam(1).user) { //If user's team is team 1
      if (result == 1) { //If user win
        JOptionPane.showMessageDialog(null, "Congradulations, you won the Grand Final match. The Aegis and the " + 
                                      "biggest price in e-sport history - 9 million dollars, belongs to your team, " + 
                                      "which is the champion of The 2016 International DoTA2 Championships.");
        teams.add(0, upperBracketMatches.get(0).getTeam(2));
        teams.add(0, upperBracketMatches.get(0).getTeam(1));
        displayCompetitionResult(1);
        JOptionPane.showMessageDialog(null, "Your team got 1st place in the competition. Congradulations");
        JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
        JOptionPane.showMessageDialog(null, "The End");
        return;
      } //End of if statement
      else { //If user lose
        JOptionPane.showMessageDialog(null, "You lost the Grand Final match. You cannot beat the opponent like how " +
                                      "you did before. You took the second place in the competition, which is " +
                                      "still great.");
        teams.add(0, upperBracketMatches.get(0).getTeam(1));
        teams.add(0, upperBracketMatches.get(0).getTeam(2));
        displayCompetitionResult(1);
        JOptionPane.showMessageDialog(null, "Your team got 2nd place in the competition. Congradulations");
        JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
        JOptionPane.showMessageDialog(null, "Game Over");
        return;
      } //End of else statement
    } //End of if statement
    else { //If user's team is team 2
      if (result == 2) { //If user win
        JOptionPane.showMessageDialog(null, "Congradulations, you won the Grand Final match. The Aegis and the " + 
                                      "biggest price in e-sport history - 9 million dollars, belongs to your team, " + 
                                      "which is the champion of The 2016 International DoTA2 Championships.");
        teams.add(0, upperBracketMatches.get(0).getTeam(1));
        teams.add(0, upperBracketMatches.get(0).getTeam(2));
        displayCompetitionResult(1);
        JOptionPane.showMessageDialog(null, "Your team got 1st place in the competition. Congradulations");
        JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
        JOptionPane.showMessageDialog(null, "The End");
        return;
      } //End of if statement
      else { //If user lose
        JOptionPane.showMessageDialog(null, "You lost the Grand Final match. You cannot beat the opponent like how " +
                                      "you did before. You took the second place in the competition, which is " +
                                      "still great.");
        teams.add(0, upperBracketMatches.get(0).getTeam(2));
        teams.add(0, upperBracketMatches.get(0).getTeam(1));
        displayCompetitionResult(1);
        JOptionPane.showMessageDialog(null, "Your team got 2nd place in the competition. Congradulations");
        JOptionPane.showMessageDialog(null, "This is your team statistics: \n\n" + teams.get(0));
        JOptionPane.showMessageDialog(null, "Game Over");
        return;
      } //End of else statement
    } //End of else statement
    
  } //End of runMainEvent method
  
//---------------------------------------------------------------------------------------------------------------------
//       Other methods
//---------------------------------------------------------------------------------------------------------------------
  
//---------------------------------------------------------------------------------------------------------------------
//  Method used in Group Stage Gameplay
  
  //Return an array of integer that hold the position number of the teams.
  //The array is sorted in descending order of points of each team.
  //If two team have the same score, then it will be determined on a random base
  private int[] groupStageSortByPoints (int[] points) {
    int[] order = new int[8]; //Declare variables needed
    int[] tempPoints = new int[8];
    for (int x = 0; x < 8; x++) { //Add the number into the temp array
      tempPoints[x] = points[x];
    } //End of for loop
    for (int x = 0; x < 8; x++) { //Get the position number of the biggest integers, the the second biggest, etc.
      int index = 0;
      for (int y = 1; y < tempPoints.length; y++) {
        if (tempPoints[y] > tempPoints[index]) {
          index = y;
        } //End of if statement
      } //End of for loop
      order[x] = index;
      tempPoints[index] = -1;
    } //End of for loop
    for (int x = 7; x > 0; x--) { //If two team have the same score, then it will be determined on a random base
      boolean firstTime = true;
      for (int y = x; y > 0; y--) {
        if (points[order[y]] == points[order[y-1]]) {
          int randomNumber = (int)(Math.random()*100);
          if (randomNumber < 50) {
            int tempInt = order[y-1];
            order[y-1] = order[y];
            order[y] = tempInt;
            if (firstTime) {
              firstTime = false;
              x++;
            } //End of if statment
          } //End of if statement
        } //End of if statement
        else {
          break;
        } //End of else statement
      } //End of for loop
    } //End of for loop
    return order;
  } //End of groupStageSortByPoints method
  
  //Display the result of group stage in proper format
  private void displayGroupStageResult (Team[] team, int[] points, int[] matchPlayed, int[] order, int day, int group) {
    int nameLength = 20; //Declare variables needed
    String tempName;
    int tempScore;
    String output = "          Day " + day + "\n" + "          Group " + group + "\n" +
      "Teams:                            Points:       Game Played:" + "\n";
    for (int x = 0; x < 8; x++) { //Put the information in proper format
      output += "\n";
      tempName = team[order[x]].getName(); //Name in proper format
      for (int y = tempName.length(); y <= nameLength; y++) {
        tempName += "  ";
      } //End of for loop
      output += tempName;
      tempScore = points[order[x]]; //Score in proper format
      if (tempScore < 10) {
        output += tempScore;
        output += "                 ";
      } //End of if statement
      else {
        output += tempScore;
        output += "               ";
      } //End of else statement
      output += matchPlayed[order[x]];
    } //End of for loop
    JOptionPane.showMessageDialog(null, output);
  } //End of displayGroupStageResult
  
//---------------------------------------------------------------------------------------------------------------------
//  Method used in Main Event Preparation
  
  //It will determine the order of the teams that have a tied score in group stage.
  //It is used only when there is a tie along the Upper and Lower Bracket divider.
  //If there are two team left, then they will play a BO3 match. The winner take the higher position while the loser
  //  take the lower position
  //If there are more than 2 team left, each team will play all other teams in BO2 format. The team with the highest 
  //  score will be put in the first position and lowest score in the last position of the tied teams. If there is a 
  //  tie in highest or lowest scores, then they will have a tie breaker between them. It will be done until less than 
  //  3 team left.
  //If the team win, they will get 3 points, if the teams tied, both team get 1 points, if the team lose, they will 
  //  get 0 points
  //Recursive method
  public int[] tieBreaker (int[] order, ArrayList<Integer> teamIndex, ArrayList<Integer> positionIndex, Team[] team) {
    ArrayList<Integer> points = new ArrayList<Integer>(); //Declare variables needed
    int tempInt;
    for (int x = 0; x < teamIndex.size(); x++) {
      points.add(0);
    } //End of for loop
    if (teamIndex.size() == 0) { //If no team left
      return order;
    } //End of if statement
    else if (teamIndex.size() == 1) { //If one team left
      order[positionIndex.get(0)] = teamIndex.get(0);
      return order;
    } //End of else if statement
    else if (teamIndex.size() == 2) { //If two team left
      Match match = new Match(team[teamIndex.get(0)], team[teamIndex.get(1)], 3, "Group Stage Tie Breaker Match",
                              false);
      int result = match.playMatch();
      if (result == 1) { //If first team win
        order[positionIndex.get(0)] = teamIndex.get(0);
        order[positionIndex.get(1)] = teamIndex.get(1);
      } //End of if statement
      else { //If second team win
        order[positionIndex.get(1)] = teamIndex.get(0);
        order[positionIndex.get(0)] = teamIndex.get(1);
      } //End of if statement
      return order;
    } //End of else if statement
    else { //If more than two team left
      for (int x = 0; x < (points.size()-1); x++) { //Play the matches
        for (int y = (x+1); y < points.size(); y++) {
          Match match = new Match(team[teamIndex.get(x)], team[teamIndex.get(y)], 2, "Group Stage Tie Breaker Match",
                                  false);
          int result = match.playMatch();
          if (result == 1) { //If first team win
            tempInt = points.get(x);
            tempInt += 3;
            points.remove(x);
            points.add(x, tempInt);
          } //End of if statement
          else if (result == 2){ //If the second team win
            tempInt = points.get(y);
            tempInt += 3;
            points.remove(y);
            points.add(y, tempInt);
          } //End of else statement
          else {
            tempInt = points.get(x);
            tempInt++;
            points.remove(x);
            points.add(x, tempInt);
            tempInt = points.get(y);
            tempInt++;
            points.remove(y);
            points.add(y, tempInt);
          } //End of else statement
        } //End of for loop
      } //End of for loop
      int highest = findHighestPointIndex(points); //Find the highest score
      if (highest > -1) { //If there is no tie, then place it in the first position availble
        order[positionIndex.get(0)] = teamIndex.get(highest);
        positionIndex.remove(0);
        teamIndex.remove(highest);
        points.remove(highest);
      } //End of if statement
      int lowest = findLowestPointIndex(points); //Find the lowest score
      if (highest > -1) { //If there is no tie, then place it in the last position availble
        order[positionIndex.get((positionIndex.size()-1))] = teamIndex.get(lowest);
        positionIndex.remove((positionIndex.size()-1));
        teamIndex.remove(lowest);
        points.remove(lowest);
      } //End of if statement
      return tieBreaker(order, teamIndex, positionIndex, team);
    } //End of else statement
  } //End of tieBreaker method
  
  //Find the highest point index for the previous method
  //Return of -1 means there is a tie
  private int findHighestPointIndex (ArrayList<Integer> point) {
    int index = 0;
    for (int x = 1; x < point.size(); x++) { //Check for the highest number
      if (point.get(index) < point.get(x)) {
        index = x;
      } //End of if statement
    } //End of for loop
    for (int x = (index+1); x < point.size(); x++) { //Check for tie
      if (point.get(index) == point.get(x)) {
        return -1;
      } //End of if statement
    } //End of for loop
    return index;
  } //End of findHighestPointIndex method
  
  //Find the lowest point index for the previous method
  //Return of -1 means there is a tie
  private int findLowestPointIndex (ArrayList<Integer> point) {
    int index = 0;
    for (int x = 1; x < point.size(); x++) { //Check for the lowest number
      if (point.get(index) > point.get(x)) {
        index = x;
      } //End of if statement
    } //End of for loop
    for (int x = (index+1); x < point.size(); x++) { //Check for tie
      if (point.get(index) == point.get(x)) {
        return -1;
      } //End of if statement
    } //End of for loop
    return index;
  } //End of findLowestPointIndex method
  
//---------------------------------------------------------------------------------------------------------------------
//  Method used in Main Event Gameplay
  
  private void displayCompetitionResult (int index) {
    index--;
    String tempString = ""; //Declare variables needed
    int count = 0;
    tempString += "     The Internation 2016 Final Result\n" +
      "Place:      " + "Price:       " + "Team:\n";
    for (int x = index; x < 16; x++) { //Put the information in proper format
      if (x == 1) { //1st place
        tempString += "1st:        " + "$9,139,002   " + teams.get(count).getName();
        count++;
      } //End of if statement
      else if (x == 2) { //2nd place
        tempString += "2nd:        " + "$3,427,126   " + teams.get(count).getName();
        count++;
      } //End of else if statement
      else if (x == 3) { //3rd place
        tempString += "3rd:        " + "$2,180,898   " + teams.get(count).getName();
        count++;
      } //End of else if statement
      else if (x == 4) { //4th place
        tempString += "4th:        " + "$1,453,932   " + teams.get(count).getName();
        count++;
      } //End of else if statement
      else if (x == 5 || x == 6) { //5th-6th place
        tempString += "5th-6th:    " + "$934,671     " + teams.get(count).getName();
        count++;
      } //End of else if statement
      else if (x == 7 || x == 8) { //7th-8th place
        tempString += "7th-8th:    " + "$519,262     " + teams.get(count).getName();
        count++;
      } //End of else if statement
      else if (x >= 9 || x <= 12) { //9th-12th place
        tempString += "9th-12th:   " + "$311,557     " + teams.get(count).getName();
        count++;
      } //End of else if statement
      else { //13th-16th place
        tempString += "13th-16th:  " + "$103,852     " + teams.get(count).getName();
        count++;
      } //End of else if statement
    } //End of for loop
    JOptionPane.showMessageDialog(null, tempString);
  } //End of displayCompetitionResult
  
//---------------------------------------------------------------------------------------------------------------------
//       End of program
//---------------------------------------------------------------------------------------------------------------------
  
} //End of class