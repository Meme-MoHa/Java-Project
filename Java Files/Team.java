import javax.swing.JOptionPane;

//This is the class representing teams in the competition.
//Each team have 5 players, 4 regular player and a team captain.
//The reason why not making a child class of this class to be the player controlled team is that so I can put all
//  teams in an array, not an arraylist, which makes some other parts easier to code.
//3154 - The secret code

public class Team {
  
//---------------------------------------------------------------------------------------------------------------------
//       Variables
//---------------------------------------------------------------------------------------------------------------------
  
  //Declare variables needed.
  private TeamCaptain captain;
  private Player[] team;
  private String name;
  private int money; //Only used for the player controlled team
  public boolean user; //If it is the user controlled team or not
  
//---------------------------------------------------------------------------------------------------------------------
//       Constructor
//---------------------------------------------------------------------------------------------------------------------
  
  //Constructor method.
  //Setup the array with and other two variables
  public Team (String n, boolean u) {
    name = n;
    team = new Player[4];
    money = 0;
    user = u;
  } //End of constructor method
  
//---------------------------------------------------------------------------------------------------------------------
//       Initilizer method
//---------------------------------------------------------------------------------------------------------------------
  
  //Initialize the captain with the variables given.
  public void setCaptain (String n, String i, int l, int tf, int f, int dm, int ce, int cl) {
    captain = new TeamCaptain(n, i, l, tf, f, dm, ce, cl);
  } //End of setCaptain method
  
  //Initialize the captain with the variables given.
  public void setPlayer (String n, String i, int index, int l, int tf, int f, int dm, int ce) {
    team[index] = new Player(n, i, l, tf, f, dm, ce);
  } //End of setPlayer method
  
//---------------------------------------------------------------------------------------------------------------------
//       Get the name of the team
//---------------------------------------------------------------------------------------------------------------------
  
  //Return the name of the team
  public String getName () {
    return name;
  } //End of getName method
  
//---------------------------------------------------------------------------------------------------------------------
//       Get the ability/skill of the team
//---------------------------------------------------------------------------------------------------------------------
  
  //Return the ability of the whole team
  //Use to calculate the win rate of the team during a match
  public int getAbility () {
    int ability = 0;
    int multiplier = captain.getSkill(6);
    for (int x = 0; x < 4; x++) {
      ability += team[x].getAbility();
    } //End of for loop
    ability += captain.getAbility();
    ability = (ability * multiplier) / 100;
    return ability;
  } //End of getAbility method
  
  //Return the total skills of the five player on one of the base abilities.
  public int getTotalSkills (int option) {
    int skill = 0;
    for (int x = 0; x < 4; x++) {
      skill += team[x].getSkill(option);
    } //End of for loop
    skill += captain.getSkill(option);
    return skill;
  } //End of getTotalSkills method
  
//---------------------------------------------------------------------------------------------------------------------
//       Player controlled team only methods
//---------------------------------------------------------------------------------------------------------------------
  
  //Increase the money by a set amount
  public void increaseMoney (int amount) {
    money += amount;
  } //End of increaseMoney
  
  //Method that allow the user to train the players in the team
  public void trainTeam (Team[] teams) {
    while (true) {
      int player;
      String code;
      while (true) {//Ask user which player they want to train?
        code = JOptionPane.showInputDialog(null, toString() + "\n\n" + 
                                           "Which player do you want to train: \n" +
                                           "  1: " + captain.getNameId() + "\n" +
                                           "  2: " + team[0].getNameId() + "\n" + 
                                           "  3: " + team[1].getNameId() + "\n" +
                                           "  4: " + team[2].getNameId() + "\n" +
                                           "  5: " + team[3].getNameId() + "\n" +
                                           "  0: Quit");
        if (code.equals("danche")) { //Secret code 1, allow user to see the stat of all other 15 teams
          int team;
          while (true) {
            team = Integer.parseInt(JOptionPane.showInputDialog(null, "Which team do you want to see the stat of:\n" +
                                                                "   1: Wings Gaming\n" +
                                                                "   2: Digital Chaos\n" +
                                                                "   3: Evil Geniuses\n" +
                                                                "   4: Fnatic\n" +
                                                                "   5: EHOME\n" +
                                                                "   6: MVP Pheonix\n" +
                                                                "   7: TNC Gaming\n" +
                                                                "   8: Team Liquid\n" +
                                                                "   9: Newbee\n" +
                                                                "  10: LGD Gaming\n" +
                                                                "  11: OG\n" +
                                                                "  12: Alliance\n" +
                                                                "  13: Team Secret\n" +
                                                                "  14: Natus Vincere\n" +
                                                                "  15: Escape Gaming"));
            if (team > 0 || team < 16) {
              JOptionPane.showMessageDialog(null, teams[team]);
              break;
            } //End of if statement
            else {
              JOptionPane.showMessageDialog(null, "Invalid option. \n" + 
                                            "Please enter a valid number.");
            } //End of else statement
          } //End of while loop
          player = 3154;
        } //End of if statement
        else {
          player = Integer.parseInt(code);
          if (player == 3154) { //Secret code 2, add 1000 money
            money += 1000;
          } //End of else if statement
          else if (player < 0 || player > 5) {
            JOptionPane.showMessageDialog(null, "Invalid option. \n" + 
                                          "Please enter a valid number.");
          } //End of else if statement
          else {
            break;
          } //End of else statement
        } //End of else statement
      } //End of while loop
      if (player == 0) { //If user choose to quit
        break;
      } //End of if statement
      else if (player == 1) { //If user choose the team captain
        int skill;
        int option;
        int answer;
        while (true) { //Ask user which skill they want to train
          skill = Integer.parseInt(JOptionPane.showInputDialog(null, "Money " + money + "\n\n" +
                                                               captain + "\n\n" + 
                                                               "Which skill do you want to train: \n" +
                                                               "  1: Laning   cost-" + captain.getCost(1) + "\n" +
                                                               "  2: Team Fight   cost-" + captain.getCost(2) + "\n" +
                                                               "  3: Farming   cost-" + captain.getCost(3) + "\n" +
                                                               "  4: Decision Making   cost-" + captain.getCost(4) + 
                                                               "\n" +
                                                               "  5: Cooperation/Execution   cost-" + 
                                                               captain.getCost(5) + "\n" +
                                                               "  6: Commanding/Leading   cost-" + captain.getCost(6)));
          if (skill < 1 || skill > 6) {
            JOptionPane.showMessageDialog(null, "Invalid option. \n" + 
                                          "Please enter a valid number.");
          } //End of if statement
          else {
            break;
          } //End of else statement
        } //End of while loop
        if (captain.getSkill(skill) == 100) { //Check if the skill is maxed out
          JOptionPane.showMessageDialog(null, "The skill is already maxed out.");
        } //End of if statement
        else { //Confirm with user if they want to train that skill
          option = Integer.parseInt(JOptionPane.showInputDialog(null, "Money " + money + "\n\n" +
                                                                "It cost " + captain.getCost(skill) + " to train that" +
                                                                " skill, do you want to continue?\n" +
                                                                " Enter 1 to continue or any other number to quit."));
          if (option == 1) {
            answer = captain.training(skill, money);
            if (answer == -1) {
              JOptionPane.showMessageDialog(null, "You do not have enough money.");
            } //End of if statement
            else {
              JOptionPane.showMessageDialog(null, "Training success.");
              money = answer;
            } //End of else statement
          } //End of if statement
        } //End of else statement
      } //End of else if statement
      else if (player != 3154) { //If user choose a regular player in the team
        int skill;
        int option;
        int answer;
        while (true) { //Ask user which skill they want to train
          skill = Integer.parseInt(JOptionPane.showInputDialog(null, "Money " + money + "\n\n" +
                                                               team[player-2] + "\n\n" + 
                                                               "Which skill do you want to train: \n" +
                                                               "  1: Laning   cost-" + team[player-2].getCost(1) + 
                                                               "\n" +
                                                               "  2: Team Fight   cost-" + team[player-2].getCost(2) +
                                                               "\n" +
                                                               "  3: Farming   cost-" + team[player-2].getCost(3) +
                                                               "\n" +
                                                               "  4: Decision Making   cost-" + 
                                                               team[player-2].getCost(4) + "\n" +
                                                               "  5: Cooperation/Execution   cost-" + 
                                                               team[player-2].getCost(5)));
          if (skill < 1 || skill > 5) {
            JOptionPane.showMessageDialog(null, "Invalid option. \n" + 
                                          "Please enter a valid number.");
          } //End of if statement
          else {
            break;
          } //End of else statement
        } //End of while loop
        if (team[player-2].getSkill(skill) == 100) { //Check if the skill is maxed out
          JOptionPane.showMessageDialog(null, "The skill is already maxed out.");
        } //End of if statement
        else { //Confirm with user if they want to train that skill
          option = Integer.parseInt(JOptionPane.showInputDialog(null, "Money " + money + "\n\n" +
                                                                "It cost " + team[player-2].getCost(skill) + 
                                                                " to train that" +
                                                                " skill, do you want to continue?\n" +
                                                                " Enter 1 to continue or any other number to quit."));
          if (option == 1) {
            answer = team[player-2].training(skill, money);
            if (answer == -1) {
              JOptionPane.showMessageDialog(null, "You do not have enough money.");
            } //End of if statement
            else {
              JOptionPane.showMessageDialog(null, "Training success.");
              money = answer;
            } //End of else statement
          } //End of if statement
        } //End of else statement
      } //End of else if statement
    } //End of while loop
  } //End of train team method
  
//---------------------------------------------------------------------------------------------------------------------
//       Display all the information about this team
//---------------------------------------------------------------------------------------------------------------------
  
  //Display all the information about this team
  public String toString () {
    String tempString = "   " + name + "   \n" +
      "Money: " + money + "\n\n" +
      captain + "\n";
    for (int x = 0; x < team.length; x++) {
      tempString += team[x] + "\n";
    } //End of for loop
    return tempString;
  } //End of toString method
  
//---------------------------------------------------------------------------------------------------------------------
//       End of program
//---------------------------------------------------------------------------------------------------------------------
  
} //End of class

//3154 - +1000 money