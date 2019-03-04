//This is the class that represend a pro player participate in the competition.
//Each player have 2 field representing their name and id, and 5 field representing their skills: laning, team fight,
//  farming, decision making, cooperation/execution.
//Out of the 5 skills, 3 are basic skills, which are laning, team fight, and farming,
//                     1 is additional skill, which is decision making,
//                     1 is personal mutiplier skill, which is cooperation/execution.

public class Player {
  
//---------------------------------------------------------------------------------------------------------------------
//       Variables
//---------------------------------------------------------------------------------------------------------------------
  
  //Declare variables needed.
  private String name;
  private String id;
  private int laning;
  private int teamFight;
  private int farming;
  private int decisionMaking;
  private int cooperation_execution;
  private final int max = 100;
  
//---------------------------------------------------------------------------------------------------------------------
//       Constructor
//---------------------------------------------------------------------------------------------------------------------
  
  //Constructor method.
  //Setup the 5 skills of the player with the number given to the constructor method.
  public Player (String n, String i, int l, int tf, int f, int dm, int ce) {
    if (l > max) l = 100;  //Make sure no number is bigger than the max allowed value.
    if (tf > max) tf = 100;
    if (f > max) f = 100;
    if (dm > max) dm = 100;
    if (ce > max) ce = 100;
    name = n;
    id = i;
    laning = l;
    teamFight = tf;
    farming = f;
    decisionMaking = dm;
    cooperation_execution = ce;
  } //End of constructor method
  
//---------------------------------------------------------------------------------------------------------------------
//       Get the name or the id of the player
//---------------------------------------------------------------------------------------------------------------------
  
  //Return the name and the id of the player
  public String getNameId () {
    int index = name.indexOf(' ');
    if (index == -1) {
      return name + " '" + id + "' ";
    } //End of if statement
    else {
      return name.substring(0, index) + " '" + id + "' " + name.substring((index+1));
    } //End of else statement
  } //End of getNameId method
  
  //Return the name of the player
  public String getName () {
    return name;
  } //End of getName method
  
  //Return the id of the player
  public String getId () {
    return id;
  } //End of getId method
  
//---------------------------------------------------------------------------------------------------------------------
//       Get skill level
//---------------------------------------------------------------------------------------------------------------------
  
  //Return all the skill levels.
  public int[] getSkills () {
    int[] skill = {laning, teamFight, farming, decisionMaking, cooperation_execution};
    return skill;
  } //End of getSkills method
  
  //Return the specific skill user wants.
  public int getSkill (int option) {
    switch (option) {
      case 1: return laning;
      case 2: return teamFight;
      case 3: return farming;
      case 4: return decisionMaking;
      case 5: return cooperation_execution;
    } //End of switch statement
    return 0;
  } //End of getSkill method
  
//---------------------------------------------------------------------------------------------------------------------
//       Get training cost
//---------------------------------------------------------------------------------------------------------------------
  
  //Return the training cost of the specific skill the user want to check.
  //A return of 0 means the skill is already maxed out.
  public int getCost(int option) {
    switch (option) {
      case 1: return getLaningCost();
      case 2: return getTeamFightCost();
      case 3: return getPushingCost();
      case 4: return getDecisionMakingCost();
      case 5: return getCooperationExecutionCost();
    } //End of switch statement
    return 0;
  } //End of getCost method
  
//---------------------------------------------------------------------------------------------------------------------
  
  //Get the training cost of laning skill, 0 means skill already max out.
  private int getLaningCost () {
    if (laning == 100) {
      return 0;
    } //End of if statement
    return laning+1;
  } //End of getLaningCost method
  
  //Get the training cost of team fight skill, 0 means skill already max out.
  private int getTeamFightCost () {
    if (teamFight == 100) {
      return 0;
    } //End of if statement
    return teamFight+1;
  } //End of getTeamFightCost method
  
  //Get the training cost of farming skill, 0 means skill already max out.
  private int getPushingCost () {
    if (farming == 100) {
      return 0;
    } //End of if statement
    return farming+1;
  } //End of getLaningCost method
  
  //Get the training cost of decisionMaking skill, 0 means skill already max out.
  private int getDecisionMakingCost () {
    if (decisionMaking == 100) {
      return 0;
    } //End of if statement
    return decisionMaking+1;
  } //End of getDecisionMakingCost method
  
  //Get the training cost of cooperation/execution skill, 0 means skill already max out.
  private int getCooperationExecutionCost () {
    if (cooperation_execution == 100) {
      return 0;
    } //End of if statement
    return cooperation_execution+1;
  } //End of getCooperationExecutionCost method
  
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
      case 1: laning++;
              break;
      case 2: teamFight++;
              break;
      case 3: farming++;
              break;
      case 4: decisionMaking++;
              break;
      case 5: cooperation_execution++;
              break;
    } //End of switch statement
    return (money-cost);
  } //End of training method
  
//---------------------------------------------------------------------------------------------------------------------
//       Return the ability of the player (use to calculate the win rate of the team during a match)
//---------------------------------------------------------------------------------------------------------------------
  
  //Return the ability of the player.
  //The base ability is the total of the laning, team fight, farming and decision making skills.
  //The actual ability is the base ability multiply by a multiplier, which is the cooperation/execution skill.
  public int getAbility () {
    int ability = laning + teamFight + farming + decisionMaking;
    ability = (ability * cooperation_execution) / 100;
    return ability;
  } //End of getAbility method
  
//---------------------------------------------------------------------------------------------------------------------
//       Display all the information about this player
//---------------------------------------------------------------------------------------------------------------------
  
  //Return all the information about this player
  public String toString () {
    return getNameId() + ": \n" +
      "  -Laning: " + laning + "\n" +
      "  -Team Fight: " + teamFight + "\n" +
      "  -Farming: " + farming + "\n" +
      "  -Decision Making: " + decisionMaking + "\n" +
      "  -Cooperation/Execution: " + cooperation_execution;
  } //End of toString method
  
//---------------------------------------------------------------------------------------------------------------------
//       End of program
//---------------------------------------------------------------------------------------------------------------------
  
} //End of class