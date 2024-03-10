import java.util.Scanner;

public class UserInterface {
    //Main Variables
    private Scanner scanner;

    //Constructor
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    //Main Methods
    public void greetPlayer() {
        //Greets the player and outputs the basic rules/idea of the game.
        System.out.println("Welcome to Model Car Racing!");
        System.out.println("Basic rules blah blah...");
    }

    public boolean askYesNoQuestion(String question) {
        System.out.println(question + " (y/n)");
        String answer = scanner.nextLine().toLowerCase();
        return answer.equals("y");
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}
