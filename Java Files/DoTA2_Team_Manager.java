import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

//This is the runner class of the game

public class DoTA2_Team_Manager {
  
//---------------------------------------------------------------------------------------------------------------------
//       Main method
//---------------------------------------------------------------------------------------------------------------------
  
  public static void main (String[] args) {
    
//---------------------------------------------------------------------------------------------------------------------
//  Declare variables needed
    
    //Declare variables needed
    String[] teamName = new String[15];
    StringTokenizer[][] teamData = new StringTokenizer[15][5];
    String name = "";
    String[] id = new String[4];
    String[] playerName = new String[4];
    String captainName = "";
    String captainId = "";
    int[][] skill = new int[4][5];
    int[] captainSkill = new int[6];
    
//---------------------------------------------------------------------------------------------------------------------
//  Get the data inputed from the file
    
    //Takes in the information from the files
    try {
      File txtFile = new File ("Team Data.bin"); //Declare variables needed
      Scanner txtInput = new Scanner(txtFile);
      for (int x = 0; x < teamName.length; x++) {
        teamName[x] = txtInput.nextLine();
        for (int y = 0; y < 5; y++) {
          teamData[x][y] = new StringTokenizer(txtInput.nextLine(), ",");
        } //End of for loop
      } //End of for loop
      txtInput.close();
    } //End of try block
    catch (IOException ioe){ //If something wrong with the file, end
      JOptionPane.showMessageDialog(null, "Cannot read the file, file might be corrupted, or data in the file is not" +
                                    " in the proper format, or the file cannot be found.");
      JOptionPane.showMessageDialog(null, "The program will be ended.");
      System.exit(0);
    } //End of catch block
    
    
//---------------------------------------------------------------------------------------------------------------------
//  Introduce user to the game
    
    JOptionPane.showMessageDialog(null, "Welcome to DoTA2 Team Manager");
    JOptionPane.showMessageDialog(null, "In this game, you are the manager of a newly established professional DoTA " + 
                                  "2 team.\n " + 
                                  "The team was lucky and strong enough to get through the regional qualifier " + 
                                  "to be able to compete in the biggest and best e-sport event for professional " +
                                  "DoTA 2 - The International.");
    JOptionPane.showMessageDialog(null, "Your mission is to lead your team through the competition and claim the " +
                                  "Aegis - an award only for the champion.\n" + 
                                  "If you have any question and anything not clear regarding the game play, " +
                                  "please refer to the user's manual provided along with the copy of the game.");
    
//---------------------------------------------------------------------------------------------------------------------
//  Initilize the user controlled team
    
    //Declare variables needed
    int option;
    Scanner input = new Scanner(System.in);
    
    //Ask user for option 1 or 2
    JOptionPane.showMessageDialog(null, "Now, you need to setup your team.");
    JOptionPane.showMessageDialog(null, "You have two options: \n" +
                                  "  1: Manully input information to create your own team. \n" + 
                                  "  2: Use the team computer created for you. \n\n" +
                                  "Hint: With option 2, the total skill of the team is slightly higher than option 1.");
    while (true) { //While loop to prevent user input wrong number
      option = Integer.parseInt(JOptionPane.showInputDialog(null, "Now, please enter the number representing the " + 
                                                            "option you like."));
      if (option == 1 || option == 2) {
        break;
      } //End of if statement
      else {
        JOptionPane.showMessageDialog(null, "Invalid input, please input a valid number.");
        JOptionPane.showMessageDialog(null, "The following are your two options: \n" +
                                      "  1: Manully input information to create your own team. \n" + 
                                      "  2: Use the team computer created for you. \n\n" +
                                      "Hint: With option 2, the total skill of the team is slightly higher than option 1.");
      } //End of else statement
    } //End of while loop
    
    //If user choose option 1
    if (option == 1) {
      int skillPoints = 600; //Declare variables needed
      JOptionPane.showMessageDialog(null, "You choose to manully input information to create your own team.");
      while (true) { //Get user input the team name
        name = JOptionPane.showInputDialog(null, "Now, please enter the name of your team: \n" +
                                           "Please keep it in 15 charaters. (Including space)");
        if (name.length() > 15) {
          JOptionPane.showMessageDialog(null, "The name you enter is too long");
        } //End if if statement
        else {
          break;
        } //End of else statement
      } //End of while loop
      JOptionPane.showMessageDialog(null, "Now, you need to manully input the skills of the player in the team.");
      JOptionPane.showMessageDialog(null, "The team consist of 5 players, a team captain and 4 other players.\n" +
                                    "Each player have 5 skills, they are: \n" + 
                                    "   - laning\n" + 
                                    "   - team fight\n" + 
                                    "   - farming\n" +
                                    "   - decision making\n" +
                                    "   - cooperation/execution\n" +
                                    "The team captain have one extra skill, which is commanding/leading." +
                                    "The maximum of any skill is 100.");
      JOptionPane.showMessageDialog(null, "Each skill of each player need to be a minimum of 50.. You can choose to" +
                                    "increase the skills with the free skill points provided to you right now, \n" +
                                    "or you can increase them later in the game will the 'money' you will get from " +
                                    "winning matches.");
      for (int x = 0; x < 4; x++) { //Get user to input the name of each player
        while (true) {
          playerName[x] = JOptionPane.showInputDialog(null, "Please enter the name of player " + (x+1) +
                                                      "\nPlease keep it in 30 letters. (including space)");
          id[x] = JOptionPane.showInputDialog(null, "Please enter the id of player " + (x+1) +
                                              "\nPlease keep it in 20 letters. (including space)");
          if (playerName[x].length() > 30 || id[x].length() > 20) {
            JOptionPane.showMessageDialog(null, "The name of the player or the id of the player is too long, " +
                                          "please keep them in 30/20 letters.");
          } //End of if statement
          else {
            break;
          } //End of else statement
        } //End of while loop
      } //End of for loop
      while (true) {
        captainName = JOptionPane.showInputDialog(null, "Please enter the name of team captain" + 
                                                  "\nPlease keep it in 30 letters. (including space)");
        captainId = JOptionPane.showInputDialog(null, "Please enter the id of team captain"+ 
                                                "\nPlease keep it in 20 letters. (including space)");
        if (captainName.length() > 30 || captainId.length() > 20) {
          JOptionPane.showMessageDialog(null, "The name of the player or the id of the player is too long, " +
                                        "please keep them in 30/20 letters.");
        } //End of if statement
        else {
          break;
        } //End of else statement
      } //End of while loop
      for (int x = 0; x < 4; x++) { //Get user to input each skill of each player
        while (true) {
          skill[x][0] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                     "laning skill of " + playerName[x] + " by?\n" + 
                                                                     "Current laning skill is 50, max is 100.\n" +
                                                                     "You have " + skillPoints + " free skill " + 
                                                                     "points left."));
          if (skill[x][0] > skillPoints) {
            JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                          "number.");
          } //End of if statement
          else if (skill[x][0] > 50) {
            JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else if (skill[x][0] < 0) {
            JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else {
            skillPoints -= skill[x][0];
            skill[x][0] += 50;
            break;
          } //End of else statement
        } //End of while loop
        while (true) {
          skill[x][1] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                     "team fight skill of " + playerName[x] + " by?\n" + 
                                                                     "Current team fight skill is 50, max is 100.\n" +
                                                                     "You have " + skillPoints + " free skill " + 
                                                                     "points left."));
          if (skill[x][1] > skillPoints) {
            JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                          "number.");
          } //End of if statement
          else if (skill[x][1] > 50) {
            JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else if (skill[x][1] < 0) {
            JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else {
            skillPoints -= skill[x][1];
            skill[x][1] += 50;
            break;
          } //End of else statement
        } //End of while loop
        while (true) {
          skill[x][2] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                     "farming skill of " + playerName[x] + " by?\n" + 
                                                                     "Current farming skill is 50, max is 100.\n" +
                                                                     "You have " + skillPoints + " free skill " + 
                                                                     "points left."));
          if (skill[x][2] > skillPoints) {
            JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                          "number.");
          } //End of if statement
          else if (skill[x][2] > 50) {
            JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else if (skill[x][2] < 0) {
            JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else {
            skillPoints -= skill[x][2];
            skill[x][2] += 50;
            break;
          } //End of else statement
        } //End of while loop
        while (true) {
          skill[x][3] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                     "decision making skill of " + playerName[x] +
                                                                     " by?\n" + 
                                                                     "Current decision making skill is 50, max " + 
                                                                     "is 100.\n" +
                                                                     "You have " + skillPoints + " free skill " + 
                                                                     "points left."));
          if (skill[x][3] > skillPoints) {
            JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                          "number.");
          } //End of if statement
          else if (skill[x][3] > 50) {
            JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else if (skill[x][3] < 0) {
            JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else {
            skillPoints -= skill[x][3];
            skill[x][3] += 50;
            break;
          } //End of else statement
        } //End of while loop
        while (true) {
          skill[x][4] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                     "cooperation/execution skill of " + 
                                                                     playerName[x] + " by?\n" + 
                                                                     "Current cooperation/execution making skill " + 
                                                                     "is 50, max is 100.\n" +
                                                                     "You have " + skillPoints + " free skill " + 
                                                                     "points left."));
          if (skill[x][4] > skillPoints) {
            JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                          "number.");
          } //End of if statement
          else if (skill[x][4] > 50) {
            JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else if (skill[x][4] < 0) {
            JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                          "Please enter a valid number.");
          } //End of else if statement
          else {
            skillPoints -= skill[x][4];
            skill[x][4] += 50;
            break;
          } //End of else statement
        } //End of while loop
      } //End of for loop
      while (true) {
        captainSkill[0] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                       "laning skill of " + captainName + " by?\n" + 
                                                                       "Current laning skill is 50, max is 100.\n" +
                                                                       "You have " + skillPoints + " free skill " + 
                                                                       "points left."));
        if (captainSkill[0] > skillPoints) {
          JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                        "number.");
        } //End of if statement
        else if (captainSkill[0] > 50) {
          JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else if (captainSkill[0] < 0) {
          JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else {
          skillPoints -= captainSkill[0];
          captainSkill[0] += 50;
          break;
        } //End of else statement
      } //End of while loop
      while (true) {
        captainSkill[1] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                       "team fight skill of " + captainName + " by?\n" + 
                                                                       "Current team fight skill is 50, max is 100.\n" +
                                                                       "You have " + skillPoints + " free skill " + 
                                                                       "points left."));
        if (captainSkill[1] > skillPoints) {
          JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                        "number.");
        } //End of if statement
        else if (captainSkill[1] > 50) {
          JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else if (captainSkill[1] < 0) {
          JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else {
          skillPoints -= captainSkill[1];
          captainSkill[1] += 50;
          break;
        } //End of else statement
      } //End of while loop
      while (true) {
        captainSkill[2] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                       "farming skill of " + captainName + " by?\n" + 
                                                                       "Current farming skill is 50, max is 100.\n" +
                                                                       "You have " + skillPoints + " free skill " + 
                                                                       "points left."));
        if (captainSkill[2] > skillPoints) {
          JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                        "number.");
        } //End of if statement
        else if (captainSkill[2] > 50) {
          JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else if (captainSkill[2] < 0) {
          JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else {
          skillPoints -= captainSkill[2];
          captainSkill[2] += 50;
          break;
        } //End of else statement
      } //End of while loop
      while (true) {
        captainSkill[3] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                       "decision making skill of " + captainName + 
                                                                       " by?\n" + 
                                                                       "Current decision making skill is 50, max " + 
                                                                       "is 100.\n" +
                                                                       "You have " + skillPoints + " free skill " + 
                                                                       "points left."));
        if (captainSkill[3] > skillPoints) {
          JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                        "number.");
        } //End of if statement
        else if (captainSkill[3] > 50) {
          JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else if (captainSkill[3] < 0) {
          JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else {
          skillPoints -= captainSkill[3];
          captainSkill[3] += 50;
          break;
        } //End of else statement
      } //End of while loop
      while (true) {
        captainSkill[4] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                       "cooperation/execution skill of " + 
                                                                       captainName + " by?\n" + 
                                                                       "Current cooperation/execution making skill " + 
                                                                       "is 50, max is 100.\n" +
                                                                       "You have " + skillPoints + " free skill " + 
                                                                       "points left."));
        if (captainSkill[4] > skillPoints) {
          JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                        "number.");
        } //End of if statement
        else if (captainSkill[4] > 50) {
          JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else if (captainSkill[4] < 0) {
          JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else {
          skillPoints -= captainSkill[4];
          captainSkill[4] += 50;
          break;
        } //End of else statement
      } //End of while loop
      while (true) {
        captainSkill[5] = Integer.parseInt(JOptionPane.showInputDialog(null, "How much do you want to upgrade the " +
                                                                       "commanding/leading skill of " + captainName +
                                                                       " by?\n" + 
                                                                       "Current commanding/leading making skill is " + 
                                                                       "50, max is 100.\n" +
                                                                       "You have " + skillPoints + " free skill " + 
                                                                       "points left."));
        if (captainSkill[5] > skillPoints) {
          JOptionPane.showMessageDialog(null, "You do not enough enough free skill points. Please enter a valid " +
                                        "number.");
        } //End of if statement
        else if (captainSkill[5] > 50) {
          JOptionPane.showMessageDialog(null, "The max of any skill is 100, you cannot upgrade it by that much. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else if (captainSkill[5] < 0) {
          JOptionPane.showMessageDialog(null, "You cannot upgrade the skill by a negative amount. " +
                                        "Please enter a valid number.");
        } //End of else if statement
        else {
          skillPoints -= captainSkill[5];
          captainSkill[5] += 50;
          break;
        } //End of else statement
      } //End of while loop
    } //End of if statement
    
    //If user choose option 2
    else {
      try {
        File txtFile = new File ("Team Data 2.bin"); //Declare variables needed
        Scanner txtInput = new Scanner(txtFile);
        name = txtInput.nextLine();
        for (int x = 0; x < 4; x++) { //Get the id of player
          id[x] = txtInput.nextLine();
        } //End of for loop
        for (int x = 0; x < 4; x++) { //Get the name of the player
          playerName[x] = txtInput.nextLine();
        } //End of for loop
        captainId = txtInput.nextLine(); //Get the name and id of the team captain
        captainName = txtInput.nextLine();
        for (int x = 0; x < 4; x++) {
          for (int y = 0; y < 5; y++) {
            skill[x][y] = txtInput.nextInt();
          } //End of for loop
        } //End of for loop
        for (int x = 0; x < 6; x++) {
          captainSkill[x] = txtInput.nextInt();
        } //End of for loop
        txtInput.close();
      } //End of try block
      catch (IOException ioe){ //If something wrong with the file, end
        JOptionPane.showMessageDialog(null, "Cannot read the file, file might be corrupted, or data in the file is" +
                                      " not in the proper format, or the file cannot be found.");
        JOptionPane.showMessageDialog(null, "The program will be ended.");
        System.exit(0);
      } //End of catch block
    } //End of else statement
    
    input.close();
    
//---------------------------------------------------------------------------------------------------------------------
//  Create the teams
    
    //Declare variables needed
    Team[] teams = new Team[16];
    
    //Create the user controlled team
    teams[0] = new Team(name, true);
    teams[0].setCaptain(captainName, captainId, captainSkill[0], captainSkill[1], captainSkill[2], captainSkill[3],
                        captainSkill[4], captainSkill[5]);
    for (int x = 0; x < 4; x++) {
      teams[0].setPlayer(playerName[x], id[x], x, skill[x][0], skill[x][1], skill[x][2], skill[x][3], skill[x][4]);
    } //End of for loop
    
    //Display the user controlled team's information to the user
    JOptionPane.showMessageDialog(null, "Following is the detail information of your team: \n" +
                                  "-------------------------------------------------\n" + teams[0]);
    
    //Create the other 15 teams
    for (int x = 1; x < teams.length; x++) {
      captainName = teamData[x-1][0].nextToken(); //Get the detail information of team captain from the string tokenzier
      captainId = teamData[x-1][0].nextToken();
      for (int y = 0; y < captainSkill.length; y++) {
        captainSkill[y] = Integer.parseInt(teamData[x-1][0].nextToken());
      } //End of for loop
      for (int y = 1; y < 5; y++) { //Get the detail information of players from the string tokenzier
        playerName[y-1] = teamData[x-1][y].nextToken();
        id[y-1] = teamData[x-1][y].nextToken();
        for (int z = 0; z < skill[y-1].length; z++) {
          skill[y-1][z] = Integer.parseInt(teamData[x-1][y].nextToken());
        } //End of for loop
      } //End of for loop
      teams[x] = new Team(teamName[x-1], false); //Setup the team
      teams[x].setCaptain(captainName, captainId, captainSkill[0], captainSkill[1], captainSkill[2], captainSkill[3],
                          captainSkill[4], captainSkill[5]);
      for (int y = 0; y < 4; y++) {
        teams[x].setPlayer(playerName[y], id[y], y, skill[y][0], skill[y][1], skill[y][2], skill[y][3], skill[y][4]);
      } //End of for loop
    } //End of for loop
    
//---------------------------------------------------------------------------------------------------------------------
//  Run the TI6 Competition
    
    //Run the TI6 Competition
    The_International_2016 ti6 = new The_International_2016(teams);
    ti6.runCompetition();
    
//---------------------------------------------------------------------------------------------------------------------
    
  } //End of main
  
//---------------------------------------------------------------------------------------------------------------------
//       Other methods
//---------------------------------------------------------------------------------------------------------------------
  
//---------------------------------------------------------------------------------------------------------------------
//       End of program
//---------------------------------------------------------------------------------------------------------------------
  
} //End of class