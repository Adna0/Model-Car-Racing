import java.util.Scanner;

public class UserInterface {
    //Main Variables
    public Scanner scanner;

    //Constructor
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    //Main Methods
    public void greetPlayer() {
        //Greets the player and outputs the basic rules/idea of the game.
        System.out.println(Constants.ANSI_GREEN + "Welcome to Model Car Racing!" + Constants.ANSI_RESET);
        System.out.println("Basic rules blah blah...");
        if (System.console() == null) {
            System.out.println(Constants.ANSI_RED + "NB! " + Constants.ANSI_RESET + "You are currently not using a command-line interface to run this program. Please do so, for better enjoyment and a smoother experience!");
        }
    }

    public static void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String returnNumber(int i) {
        StringBuilder sb = new StringBuilder();
        if (i == 3) {
            sb.append(Constants.ANSI_GREEN + "________  " + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_GREEN + "\\_____  \\ " + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_GREEN + "  _(__  < " + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_GREEN + " /       \\" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_GREEN + "/______  /" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_GREEN + "       \\/ " + Constants.ANSI_RESET);
        } else if (i == 2) {
            sb.append(Constants.ANSI_YELLOW + "________" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_YELLOW + "\\_____  \\" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_YELLOW + " /  ____/" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_YELLOW + "/       \\" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_YELLOW + "\\_______ \\" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_YELLOW + "        \\/" + Constants.ANSI_RESET);
        } else if (i == 1) {
            sb.append(Constants.ANSI_RED + " ____ " + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_RED + "/_   |" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_RED + " |   |" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_RED + " |   |" + "\n" + Constants.ANSI_RESET);
            sb.append(Constants.ANSI_RED + " |___|" + "\n" + Constants.ANSI_RESET);
            sb.append(" ");
        } else if (i == 0) {
            //Returns "GO!"
            sb.append("   ________ ________   ._." + "\n");
            sb.append(" /  _____/ \\_____  \\  | |" + "\n");
            sb.append("/   \\  ___  /   |   \\ | |" + "\n");
            sb.append("\\    \\_\\  \\/    |    \\ \\|" + "\n");
            sb.append(" \\______  /\\_______  / __" + "\n");
            sb.append("        \\/         \\/  \\/");
        }

        return String.valueOf(sb);
    }

    public void displayCountdown() throws InterruptedException {
        for (int i = 3; i >= 0; i--) {
            UserInterface.clearTerminal();
            System.out.println(returnNumber(i));
            Thread.sleep(1000);
        }
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
