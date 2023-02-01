/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.state;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author CWH
 */
// A small shipping dock only can manage one ship.
// But the dock can have new ship if the old ship decide to leave.
// Or adding a new ship when the old ship is shipping cargo.
public class ShippingDockManager {

    public static void main(String[] args) {
        Dock tranquilHarbor = new Dock("Tranquil Harbor");
        List<String> shipNames = new ArrayList<>(Arrays.asList("Serenity Voyager", "Tranquility Explorer", "Calm Seas",
                "Peaceful Journey", "Relaxation Voyager", "Serene Voyager", "Gentle Waves", "Ocean Oasis",
                "Paradise Explorer", "Island Retreat", "Coastal Breeze", "Lazy Days", "Sunny Seas", "Paradise Cruiser",
                "Relaxing Oceans", "Ocean Bliss", "Tropical Retreat", "Peaceful Waters", "Relax & Sail",
                "Island Escape", "Beachcomber", "Ocean Harmony", "Sea of Tranquility", "Restful Voyage",
                "Serenity Explorer", "Peaceful Explorer", "Tranquil Voyager", "Calming Seas", "Relaxing Waters",
                "Ocean Sanctuary"));
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        int choiceNum = -1;

        OUTER: while (choiceNum != 0) {
            System.out
                    .print("Enter 1 to display, 2 to add new ship to the dock, 3 to make shipping working 0 to exit: ");
            try {
                choiceNum = Integer.parseInt(scanner.next());
                switch (choiceNum) {
                    case 0:
                        break OUTER;
                    case 1:
                        System.out.println(tranquilHarbor.toString());
                        break;
                    case 2:
                        System.out.println("");
                        int shipNameIndex = rand.nextInt(shipNames.size());
                        // tranquilHarbor.addShip(new Ship(shipNames.get(shipNameIndex)));
                        shipNames.remove(shipNameIndex);
                        System.out.println(tranquilHarbor.toString());
                        break;
                    case 3:
                        System.out.println("");
                        tranquilHarbor.makeShippingWork();
                        System.out.println(tranquilHarbor.toString());
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid choice. Please try again.");
            }
        }

    }

}
