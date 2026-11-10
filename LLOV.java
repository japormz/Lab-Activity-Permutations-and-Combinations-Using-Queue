package librando;
import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LLOV {
	
    static Scanner input = new Scanner(System.in);

    // Queue implementation
    static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {
		
        int choice;

        do {
            System.out.println("\n===== PERMUTATION AND COMBINATION =====");
            System.out.println("[1] Add Elements to Queue");
            System.out.println("[2] Display Queue");
            System.out.println("[3] Generate Permutations");
            System.out.println("[4] Generate Combinations");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    addElements();
                    break;

                case 2:
                    displayQueue();
                    break;

                case 3:
                    generatePermutations();
                    break;

                case 4:
                    generateCombinations();
                    break;

                case 5:
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);
    }

    // TODO 1: Accept elements and add them to the Queue
    static void addElements() {
        System.out.print("How many elements do you want to add? ");
        int count = input.nextInt();
        input.nextLine();
    	
        for (int i = 0; i < count; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            String element = input.nextLine();
            queue.add(element);
        }
        System.out.println("Elements added successfully");
    }

    // TODO 2: Display all elements in the Queue
    static void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        System.out.println("Queue: " + queue);
    }

    // TODO 3: Generate all possible permutations
    static void generatePermutations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is Empty! Add elements first.");
            return;
        }
        System.out.print("Enter number of elements to arrange: ");
        int r = input.nextInt();
        input.nextLine();
    	
        if (r < 0 || r > queue.size()) {
            System.out.println("Invalid value! Must be between 0 and " + queue.size());
            return;
        }
    	
        List<String> elements = new ArrayList<>(queue);
    	
        Queue<List<Integer>> processingQueue = new LinkedList<>();
        processingQueue.add(new ArrayList<>());
    	
        int totalPermutations = 0;
        System.out.println();
    	
        // FIXED: Removed the stray semicolon here
        while (!processingQueue.isEmpty()) {
            List<Integer> currentPath = processingQueue.poll();
    	
            if (currentPath.size() == r) {
                totalPermutations++;
                for (int index : currentPath) {
                    System.out.print(elements.get(index));
                }
                System.out.println();
                continue;
            }
    	     
            for (int i = 0; i < elements.size(); i++) {
                // FIXED: Check to make sure we don't pick the same index twice in a permutation
                if (!currentPath.contains(i)) {
                    List<Integer> nextpath = new ArrayList<>(currentPath);
                    nextpath.add(i);
                    processingQueue.add(nextpath);
                }
            }
        } // FIXED: Closed the while loop cleanly before printing the summary
        
        System.out.println("\nTotal Permutations: " + totalPermutations);
    }

    // TODO 4: Generate all possible combinations
    static void generateCombinations() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty! Add elements first.");
            return;
        }
        System.out.print("Enter number of elements to select: ");
        int r = input.nextInt();
        input.nextLine();
    	
        if (r < 0 || r > queue.size()) {
            System.out.println("Invalid value! Must be between 0 and " + queue.size());
            return;
        }
        List<String> elements = new ArrayList<>(queue);
        Queue<List<Integer>> processingQueue = new LinkedList<>();
        processingQueue.add(new ArrayList<>());
        
        int totalCombinations = 0;
        System.out.println(); 
        
        while (!processingQueue.isEmpty()) {
            List<Integer> currentPath = processingQueue.poll();
            
            if (currentPath.size() == r) {
                totalCombinations++;
                for (int index : currentPath) {
                    System.out.print(elements.get(index));
                }
                System.out.println();
                continue;
            }
            int lastIndex = currentPath.isEmpty() ? -1 : currentPath.get(currentPath.size() - 1);
            
            for (int i = lastIndex + 1; i < elements.size(); i++) {
                List<Integer> nextPath = new ArrayList<>(currentPath);
                nextPath.add(i);
                processingQueue.add(nextPath);
            }
        }
        System.out.println("\nTotal Combinations: " + totalCombinations);
    }
}
