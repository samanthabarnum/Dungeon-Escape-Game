//Samantha Barnum
//CS 1180L-07
//Project 4
//11/13/21

import java.util.*;

public class Player {

     public String name;
     public int playerHealth;
     public int playerRow;
     public int playerCol;
     int escaped = 0;

     // constructor
     public Player() {
          name = "";
          playerHealth = 100;
          playerRow = 0;
          playerCol = 0;
     }

     boolean isAlive(int playerHealth, Scanner input) throws Exception {
          if (playerHealth >= 0) {
               return true;
          } else {
               System.out.println("I'm sorry, you didn't escape.  You died.  RIP. ");
               System.out.println("Better luck next time! ");
               System.out.println("");
               System.out.println("Press F to pay respects."); // tbh i tried to make the next input restart the game
                                                               // for fun, but i couldn't get it to work and i was like
                                                               // whatever lol
               input.nextLine();
               System.exit(playerHealth);
               return false;
          }
     }

     public void move(String direction, Dungeon room, Player player, int row, ArrayList<Monster> monsterList,
               Monster monster, Scanner input) throws Exception {
          String north = "north";
          String south = "south";
          String east = "east";
          String west = "west";
          if (direction.equals(north) || direction.equals(south) || direction.equals(east) || direction.equals(west)) {
               // north
               if (direction.equals(north)) {
                    if (room.canMove(player, row, direction)) { // canMove is a bool that lets you know if you're up
                                                                // against a wall or not
                         playerCol = playerCol - 1;
                         this.playerHealth = (this.playerHealth - 2);
                    } else {
                         System.out.println("");
                         System.out.println("You can't move that way, there's a wall there! ");
                         System.out.println("Try again. ");
                         System.out.println("");
                         Thread.sleep(400);
                    }
                    // south
               } else if (direction.equals(south)) {
                    if (room.canMove(player, row, direction)) {
                         playerCol = playerCol + 1;
                         // this whole section is really just to make sure that if a mob spawns on the
                         // exit, then you fight it, and then you escape.
                         if (monster.inSameRoom(player.getPlayerCol(), player.getPlayerRow(), player.getPlayerHealth(),
                                   player, monsterList)) {
                              Thread.sleep(800);
                              System.out.println("A wild Monster " + (monster.getName() + 1) + " appears! It has "
                                        + monster.getMonsterHealth() + " HP. ");
                              System.out.println("");
                              Thread.sleep(1000);
                              monster.fight(player.getPlayerHealth(), player, monsterList, input);
                              room.hasEscaped(row, player, monster, monsterList); // i only have to check if you have
                                                                                  // escaped the dungeon if you have
                                                                                  // moved to the south or east
                              if (!monster.monsterDead(monster.getMonsterHealth())) {
                                   System.out.println("");
                                   System.out.println("You have vanquished the monster! Huzzah! ");
                                   System.out.println("");
                              }
                         }
                         this.playerHealth = (this.playerHealth - 2);
                    } else {
                         System.out.println("");
                         System.out.println("You can't move that way, there's a wall there! ");
                         System.out.println("Try again. ");
                         System.out.println("");
                         Thread.sleep(400);
                    }
                    // east
               } else if (direction.equals(east)) {
                    if (room.canMove(player, row, direction)) {
                         playerRow = playerRow + 1;
                         if (monster.inSameRoom(player.getPlayerCol(), player.getPlayerRow(), player.getPlayerHealth(),
                                   player, monsterList)) {
                              Thread.sleep(800);
                              System.out.println("A wild Monster " + (monster.getName() + 1) + " appears! It has "
                                        + monster.getMonsterHealth() + " HP. ");
                              System.out.println("");
                              Thread.sleep(1000);
                              monster.fight(player.getPlayerHealth(), player, monsterList, input);
                              room.hasEscaped(row, player, monster, monsterList);
                              if (!monster.monsterDead(monster.getMonsterHealth())) {
                                   System.out.println("");
                                   System.out.println("You have vanquished the monster! Huzzah! ");
                                   System.out.println("");
                              }
                         }
                         this.playerHealth = (this.playerHealth - 2);
                    } else {
                         System.out.println("");
                         System.out.println("You can't move that way, there's a wall there! ");
                         System.out.println("Try again. ");
                         System.out.println("");
                         Thread.sleep(400);
                    }
                    // west
               } else if (direction.equals(west)) {
                    if (room.canMove(player, row, direction)) {
                         playerRow = playerRow - 1;
                         this.playerHealth = (this.playerHealth - 2);
                    } else {
                         System.out.println("");
                         System.out.println("You can't move that way, there's a wall there! ");
                         System.out.println("Try again. ");
                         System.out.println("");
                         Thread.sleep(400);
                    }
               }
          } else {
               System.out.println("");
               System.out.println("That's not even a valid direction! Try again. ");
               System.out.println("");
               Thread.sleep(400);
          }
     }

     // this is for the extra credit, a check to see if they were successful running
     // away with an RNG chance
     boolean hasRunAway(Scanner input) throws Exception {
          Random random = new Random();
          int randomNumber = random.nextInt((10 - 0) + 1) + 0;
          if (randomNumber <= 3) {
               System.out.println("");
               System.out.println("Luck wasn't on your side today.");
               Thread.sleep(400);
               System.out.println("You tried to run away, but they grabbed your leg on your way out... ");
               Thread.sleep(400);
               System.out.println("You lose 5 HP for your efforts. They hurt your leg! ");
               setPlayerHealth(playerHealth - 5);
               isAlive(playerHealth, input);
               System.out.println("");
               System.out.println("Press enter to continue on with the harrowing battle... ");
               input.nextLine();
               return false;
          } else {
               System.out.println("");
               System.out.println("You manage to throw a rock away from you, distracting the foul beast.");
               Thread.sleep(400);
               System.out.println("You got away!");
               Thread.sleep(400);
               System.out.println("");
               System.out.println("Hurry up and leave before it notices you're still in the room!!! ");
               System.out.println("");
               System.out.println("They will still be in there, so try and avoid that room. ");
               System.out.println("I mean unless you want to fight it again. ");
               Thread.sleep(400);
               System.out.println("");
               System.out.println("Press enter to continue on your quest for the exit of the dungeon. ");
               input.nextLine();
               escaped = 1;
               return true;
          }
     }

     // getters and setters
     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public int getPlayerHealth() {
          return playerHealth;
     }

     public void setPlayerHealth(int playerHealth) {
          this.playerHealth = playerHealth;
     }

     public int getPlayerRow() {
          return playerRow;
     }

     public void setPlayerRow(int playerRow) {
          this.playerRow = playerRow;
     }

     public int getPlayerCol() {
          return playerCol;
     }

     public void setPlayerCol(int playerCol) {
          this.playerCol = playerCol;
     }
}
