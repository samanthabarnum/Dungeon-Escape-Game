import java.util.*;

public class Dungeon {

     // constructor
     public Dungeon() {
     }

     // i was having trouble making them escape after they fight the monster on the
     // square, so i made this method separate to make sure. this method was
     // originally what was in the hasEscaped method.
     void hasReallyEscaped() throws InterruptedException {
          System.out.println("");
          Thread.sleep(500);
          System.out.println("Congratulations! You have escaped the dungeon. ");
          System.out.println("");
          Thread.sleep(1000);
          System.out.println("Enjoy your spoils that don't exist! ");
          System.out.println("");
          Thread.sleep(1000);
          System.out.println("Maybe next time... ");
          System.out.println("");
          Thread.sleep(1000);
          System.out.println("THE END");
          System.out.println("");
          Thread.sleep(1000);
          System.exit(0);
     }

     boolean hasEscaped(int limit, Player player, Monster monster, ArrayList<Monster> monsterList)
               throws InterruptedException {
          if ((player.getPlayerCol() == (limit - 1)) && (player.getPlayerRow() == (limit - 1))) {
               hasReallyEscaped();
          }
          return true;
     }

     boolean canMove(Player player, int row, String direction) {
          if (direction.equalsIgnoreCase("north")) {
               if (player.getPlayerCol() <= 0) {
                    return false;
               } else {
                    return true;
               }
          }
          if (direction.equalsIgnoreCase("south")) {
               if (player.getPlayerCol() >= row - 1) {
                    return false;
               } else {
                    return true;
               }
          }
          if (direction.equalsIgnoreCase("east")) {
               if (player.getPlayerRow() >= row - 1) {
                    return false;
               } else {
                    return true;
               }
          }
          if (direction.equalsIgnoreCase("west")) {
               if (player.getPlayerRow() <= 0) {
                    return false;
               } else {
                    return true;
               }
          }
          return false;
     }

     // this allows me to calculate how many monsters I should generate based off the
     // dungeon size.
     int getDungeonSize(int row, int col) {
          int numberOfRooms = row * col;
          return numberOfRooms;
     }
}