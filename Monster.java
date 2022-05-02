//Samantha Barnum
//CS 1180L-07
//Project 4
//11/13/21

import java.util.*;

public class Monster {

     public Monster() {
     }

     public int name;
     public int monsterRow;
     public int monsterCol;
     public int monsterHealth;

     public Monster(int i, int randomRow, int randomCol) {
          this.name = i;
          this.monsterRow = randomRow;
          this.monsterCol = randomCol;
          this.monsterHealth = 25;
     }

     boolean inSameRoom(int getPlayerCol, int getPlayerRow, int getPlayerHealth, Player player,
               ArrayList<Monster> monsterList) {
          if ((getPlayerCol == monsterCol) && (getPlayerRow == monsterRow)) {
               return true;
          }
          return false;
     }

     boolean inAdjacentRoom(int getPlayerRow, int getPlayerCol) {
          if (getPlayerRow == (this.monsterRow + 1) && getPlayerCol == this.monsterCol) {
               return true;
          } else if (getPlayerRow == (this.monsterRow - 1) && getPlayerCol == this.monsterCol) {
               return true;
          } else if (getPlayerCol == (this.monsterCol + 1) && getPlayerRow == this.monsterRow) {
               return true;
          } else if (getPlayerCol == (this.monsterCol - 1) && getPlayerRow == this.monsterRow) {
               return true;
          } else {
               return false;
          }
     }

     void fight(int getPlayerHealth, Player player, ArrayList<Monster> monsterList, Scanner input) throws Exception {
          int doYouRunFromThisEncounter = 0;
          player.escaped = 0;
          int monsterMaxDamage = 5;
          int playerMaxDamage = 10;
          Random random = new Random();
          String yes = "yes";
          String no = "no";
          System.out.println("");
          do {
               int randomMonsterDamage = random.nextInt((monsterMaxDamage - 1) + 1) + 1;
               int randomPlayerDamage = random.nextInt((playerMaxDamage - 1) + 1) + 1;
               player.setPlayerHealth(player.getPlayerHealth() - randomMonsterDamage);
               while (doYouRunFromThisEncounter == 0) {
                    System.out.println(
                              "This... creature, whatever it is... looks tough. Do you want to try and run away? Enter yes or no. ");
                    String yesOrNo = input.nextLine();
                    yesOrNo = yesOrNo.replaceAll("\\s", "");
                    yesOrNo = yesOrNo.toLowerCase();
                    if (yesOrNo.equals(yes) || yesOrNo.equals(no)) {
                         if (yesOrNo.equals(yes)) {
                              player.hasRunAway(input);
                              doYouRunFromThisEncounter = 1;
                         } else {
                              System.out.println(
                                        "I can understand why you'd say no. Might as well duke it out with them then. ");
                              doYouRunFromThisEncounter = 1;
                              Thread.sleep(1000);

                         }

                    } else {
                         System.out.println("");
                         System.out.println("That's definitely not how you write yes or no.  Let's try again. ");
                         Thread.sleep(400);
                         System.out.println("");
                    }
               }

               // this makes running away actually run away from the fight
               if (player.escaped == 1) {
                    break;
               }
               // i plus one the monster name so that it displays not monster 0
               System.out.println("Monster " + (this.name + 1) + " does " + randomMonsterDamage + " damage to "
                         + player.name + ", who has " + player.getPlayerHealth() + " HP left.");
               player.isAlive(player.playerHealth, input); // this makes sure the player didn't die during the last
                                                           // monster's turn
               Thread.sleep(500);
               this.monsterHealth = (this.monsterHealth - randomPlayerDamage);
               System.out.println(player.name + " does " + randomPlayerDamage + " damage to Monster " + (this.name + 1)
                         + ", who has " + this.monsterHealth + " HP left.");
               player.isAlive(getPlayerHealth, input);
               Thread.sleep(500);
          } while ((getPlayerHealth > 0) && (monsterDead(this.monsterHealth)));

     }

     boolean monsterDead(int monsterHealth) {
          if (monsterHealth <= 0) {
               // nearbyFlag = 1;
               return false;
          }
          return true;
     }

     // getters and setters
     public int getName() {
          return name;
     }

     public void setName(int name) {
          this.name = name;
     }

     public int getMonsterRow() {
          return monsterRow;
     }

     public void setMonsterRow(int monsterRow) {
          this.monsterRow = monsterRow;
     }

     public int getMonsterCol() {
          return monsterCol;
     }

     public void setMonsterCol(int monsterCol) {
          this.monsterCol = monsterCol;
     }

     public int getMonsterHealth() {
          return monsterHealth;
     }

     public void setMonsterHealth(int monsterHealth) {
          this.monsterHealth = monsterHealth;
     }
}
