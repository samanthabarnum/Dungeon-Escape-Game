//Samantha Barnum
//CS 1180L-07
//Project 4
//11/13/21
//I had entirely too much fun making this.

import java.util.*;

public class MainGame {
    public static void main(String[] args) throws Exception {
        int totalTimesRan = 0; // this lets me see if i need to show the smell-o-vision or not
        Player player = new Player();
        Monster monster = new Monster();
        Dungeon room = new Dungeon();
        int row = 0;
        int col = 0;
        boolean sizeCorrect = false;
        Scanner input = new Scanner(System.in);
        System.out.println("What is your name, heroic adventurer? ");
        String name = input.nextLine();
        player.setName(name);
        System.out.println("Welcome to the dungeon, " + player.getName() + "! ");
        Thread.sleep(400);
        System.out.println("Good luck... muahahaha... ");
        Thread.sleep(400);
        System.out.println("");
        Thread.sleep(200);
        System.out.println("__________________________________________________________");
        Thread.sleep(200);
        System.out.println("");
        Thread.sleep(200);
        System.out.println("|        You choose how big of a grid you want           |");
        Thread.sleep(200);
        System.out.println("|        The bigger the grid, the harder it is           |");
        Thread.sleep(200);
        System.out.println("|          Each step you take lowers your HP.            |");
        Thread.sleep(200);
        System.out.println("|          You start in the northwest corner.            |");
        Thread.sleep(200);
        System.out.println("|      Your goal, should you choose to accept it...      |");
        Thread.sleep(200);
        System.out.println("|    Make it to the southeast corner without dying.      |");
        Thread.sleep(200);
        System.out.println("|           You're not alone in this dungeon.            |");
        Thread.sleep(200);
        System.out.println("|                 ~~~ Good luck! ~~~                     |");
        Thread.sleep(200);
        System.out.println("__________________________________________________________");
        Thread.sleep(200);
        System.out.println("");
        System.out.println("");
        while (sizeCorrect == false) {
            System.out.println("How large of a dungeon do you want to face? This value can be between 5 and 10. ");
            row = input.nextInt();
            input.nextLine();
            if (row >= 5 && row <= 10) {
                col = row;
                sizeCorrect = true;
                break;
            } else {
                System.out.println("This is outside of the given range of values! ");
                Thread.sleep(400);
            }
        }
        // this spawns monsters based off the size of the dungeon and in random
        // positions
        int numberOfRooms = room.getDungeonSize(row, col);
        int numberOfMonsters = numberOfRooms / 6;
        for (int i = 0; i < numberOfMonsters; i++) {
            int tempRow = (row - 1);
            int tempCol = (col - 1);
            int randomRow = getRandomNumberInRange(0, tempRow);
            int randomCol = getRandomNumberInRange(0, tempCol);
            if (randomRow == 0 && randomCol == 0) {
                i--;
            } else {
                monsterList.add(new Monster(i, randomRow, randomCol));
            }
        }
        while (player.isAlive(player.playerHealth, input)) {
            Thread.sleep(400);
            System.out.println("");
            System.out.println(
                    "The brave adventurer known as " + player.getName() + " is now at (" + player.getPlayerRow() + ", "
                            + player.getPlayerCol() + "), and they have " + player.getPlayerHealth() + " HP. ");
            System.out.println("");
            int tempMonsters = 0;
            for (Monster monsterName : monsterList) { // this for each lets me check monster's positions in the array
                if (monsterName.inAdjacentRoom(player.getPlayerRow(), player.getPlayerCol())) {
                    tempMonsters++;
                }
            }

            boolean temp = false;
            for (Monster monsterName : monsterList) { // this for each lets me not show the smell-o-vision if we've
                                                      // already encountered a monster that turn
                if ((monsterName.inSameRoom(player.getPlayerCol(), player.getPlayerRow(), player.getPlayerHealth(),
                        player, monsterList))) {
                    temp = true;
                    break;
                }
            }
            // this section lets me only show the smell-o-vision when i want it to be seen
            totalTimesRan++;
            if ((totalTimesRan >= 1) || !((monster.inSameRoom(player.getPlayerCol(), player.getPlayerRow(),
                    player.getPlayerHealth(), player, monsterList)))) {
                System.out.println("You hear " + tempMonsters + " monsters nearby.");
                if (tempMonsters > 0) {
                    System.out.println("You better be careful...");
                }
                System.out.println("");
            }

            Thread.sleep(400);
            ArrayList<Monster> encounteredMonsters = new ArrayList<Monster>();
            for (Monster monsterName : monsterList) {
                if (monsterName.inSameRoom(player.getPlayerCol(), player.getPlayerRow(), player.getPlayerHealth(),
                        player, monsterList)) {
                    encounteredMonsters.add(monsterName); // this is a second array so that i don't encounter the same
                                                          // monsters more than once
                }
            }
            // temp = true;
            for (Monster monsterName : encounteredMonsters) {
                Thread.sleep(400);
                System.out.println("A wild Monster " + (monsterName.getName() + 1) + " appears! It has "
                        + monsterName.getMonsterHealth() + " HP. ");
                Thread.sleep(1000);
                monsterName.fight(player.getPlayerHealth(), player, monsterList, input);
                if (!monsterName.monsterDead(monsterName.getMonsterHealth())) {
                    System.out.println("");
                    System.out.println("You have vanquished the monster! Huzzah! ");
                    System.out.println("");
                    temp = true;
                    // this removes the dead monster
                    for (int i = 0; i <= monsterList.size(); i++) {
                        if ((monsterList.get(i).getName()) == monsterName.getName()) {
                            monsterList.remove(i);
                            break;
                        }
                    }
                }
                room.hasEscaped(row, player, monster, monsterList);
            }
            if (temp == true) {
                // this section lets me only show the smell-o-vision when i want it to be seen
                System.out.println("You hear " + tempMonsters + " monsters nearby.");
                if (tempMonsters > 0) {
                    System.out.println("You better be careful...");
                }
            }
            System.out.println("Which way do you want to go (north, south, east, west)?");
            String directionInput = input.nextLine();
            directionInput = directionInput.replaceAll("\\s", "");
            directionInput = directionInput.toLowerCase();
            player.move(directionInput, room, player, row, monsterList, monster, input);
            Thread.sleep(400);
        }
    }

    public static ArrayList<Monster> monsterList = new ArrayList<Monster>(); // original arraylist that holds their
                                                                             // names and positions and stuff

    static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max > min");
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}