//This is the class that represend a pro player who is the team captain/commender that participat in the competition.
//Each team captain have 2 field representing their name and id, and 6 field representing their skills: laning,
//  team fight, farming, decision making, cooperation/execution, commanding/leading.
//The difference is the decision making skill of the captain is 2 times more important than other players, and a unique
//  skill of commanding/leading.
//Out of the 6 skills, 3 are basic skills, which are laning, team fight, and farming,
//                     1 is additional skill, which is decision making,
//                     1 is personal mutiplier skill, which is cooperation/execution,
//                     1 is team mutiplier skill, which is commanding/leading.

public class TeamCaptain extends Player {
  
//---------------------------------------------------------------------------------------------------------------------
//       Variables
//--------------------------------------------------------------------------------------------------------------------- 
  
  //Declare variables needed.
  private int commanding_leading;
  private final int max = 100;
  
//---------------------------------------------------------------------------------------------------------------------
//       Constructor
//---------------------------------------------------------------------------------------------------------------------
  
  //Constructor method.
  //Setup the 6 skills of the player with the number given to the constructor method.
  public TeamCaptain (String n, String i, int l, int tf, int f, int dm, int ce, int cl) {
    super(n, i, l, tf, f, dm, ce);  //Pass the variables to the superclass.
    if (cl > max) cl = 100;  //Make sure no number is bigger than the max allowed value.
    commanding_leading = cl;  //Set up the value.
  } //End of constructor method
  
//---------------------------------------------------------------------------------------------------------------------
//       Get the name or the id of the player
//---------------------------------------------------------------------------------------------------------------------
  
  //It is the same as the one in the Player class, so it will not override the one in the Player class
  
//---------------------------------------------------------------------------------------------------------------------
//       Get skill level
//---------------------------------------------------------------------------------------------------------------------
  
  //Return all the skill levels.
  public int[] getSkills () {
    int[] skill = new int[6];
    int[] temp = super.getSkills();
    for (int x = 0; x < 5; x++) {
      skill[x] = temp[x];
    } //End of for loop
    skill[5] = commanding_leading;
    return skill;
  } //End of getSkills method
  
  //Return the specific skill user wants.
  public int getSkill (int option) {
    switch (option) {
      case 6: return commanding_leading;
      default: return super.getSkill(option);
    } //End of switch statement
  } //End of getSkill method
  
//---------------------------------------------------------------------------------------------------------------------
//       Get training cost
//---------------------------------------------------------------------------------------------------------------------
  
  //Return the training cost of the specific skill the user want to check.
  //A return of 0 means the skill is already maxed out.
  public int getCost(int option) {
    switch (option) {
      case 6: return getCommandingLeadingCost();
      default: return super.getCost(option);
    } //End of switch statement
  } //End of getCost method
  
//---------------------------------------------------------------------------------------------------------------------
  
  //Get the training cost of commanding/leading skill, 0 means skill already max out.
  private int getCommandingLeadingCost () {
    if (commanding_leading == 100) {
      return 0;
    } //End of if statement
    return commanding_leading+1;
  } //End of getCommandingLeadingCost method
  
//---------------------------------------------------------------------------------------------------------------------
//       Train the player
//---------------------------------------------------------------------------------------------------------------------
  
  //Train the player with the specific skill the user want to train.
  //Return the money left after the training is complete. -1 means the user doesn't have enough money.
  public int training (int option, int money) {
    int cost = getCost(option); //Get the cost of the training.
    if (money < cost) { //If the user doesn't have enough money, return -1.
      return -1;
    } //End of if statement
    switch (option) {
      case 6: commanding_leading++;
      return (money-cost);
      default: return super.training(option, money);
    } //End of switch statement
  } //End of training method
  
//---------------------------------------------------------------------------------------------------------------------
//       Return the ability of the player (use to calculate the win rate of the team during a match)
//---------------------------------------------------------------------------------------------------------------------
  
  //Return the ability of the player.
  //The base ability is the total of the laning, team fight, farming and decision making skills. The decision making is
  //  twice as effective as the regular player
  //The actual ability is the base ability multiply by a multiplier, which is the cooperation/execution skill.
  public int getAbility () {
    int ability = 0;
    int[] skill = getSkills(); //Get all the skills
    for (int x = 0; x < 4; x++) { //Add up all the skills
      ability += skill[x];
    } //End of for loop
    ability += skill[4]; //Double the 4th skill's effective
    ability = (ability * skill[5]) / 100; //Mutiply by the multiplier
    return ability;
  } //End of getAbility method
  
//---------------------------------------------------------------------------------------------------------------------
//       Display all the information about this player
//---------------------------------------------------------------------------------------------------------------------
  
  //Return all the information about this player
  public String toString () {
    return super.toString() + "\n" +
      "  -Commanding/Leading: " + commanding_leading;
  } //End of toString method
  
//---------------------------------------------------------------------------------------------------------------------
//       End of program
//---------------------------------------------------------------------------------------------------------------------
  
} //End of class