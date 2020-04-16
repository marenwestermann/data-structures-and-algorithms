/* Problem: 
Comparators are used to compare two objects. In this challenge, you'll create 
a comparator and use it to sort an array. The Player class is provided in the 
editor below. It has two fields:
1. name: a string
2. score: an integer

Given an array of n Player objects, write a comparator that sorts them in order 
of decreasing score. If 2 or more players have the same score, sort those players 
alphabetically ascending by name. */

import java.util.*;

class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

class Checker implements Comparator<Player> {
    // complete this method
    public int compare(Player a, Player b) {
        // override compare method of Comparator for descending order of scores
        if (a.score < b.score) return +1;
        if (a.score > b.score) return -1;
        // use Comparable interface for sorting names
        return a.name.compareTo(b.name);
    }
}


public class SortingComparator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); // number of players

        Player[] player = new Player[n];
        Checker checker = new Checker();
        
        for(int i = 0; i < n; i++){ // read in player names and scores
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for(int i = 0; i < player.length; i++){
            System.out.printf("%s %s\n", player[i].name, player[i].score);
        }
    }
}
