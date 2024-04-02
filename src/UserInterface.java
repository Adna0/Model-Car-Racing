import CarParts.Motor;
import CarParts.Reductor;
import CarParts.Tank;
import CarParts.Wheels;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    //Main Variables
    private final Scanner scanner;

    //Constructor
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    //Main Methods
    public void greetPlayer() {
        //Greets the player and outputs the basic rules/idea of the game.
        System.out.println(Constants.ANSI_GREEN + "Welcome to Model Car Racing!" + Constants.ANSI_RESET);
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

    public boolean askCustomYesNo(String question, String[] options) {
        //First inputted option is regarded as the "true" answer.
        System.out.println(question);
        String answer = scanner.nextLine().toLowerCase();
        return answer.equals(options[0]);
    }

    public Car changePart(Car car) throws InterruptedException {
        while (true) {
            UserInterface.clearTerminal();
            showSetup(car);

            System.out.println("\nWhat part would you like to change? (input the number or 'done')");
            String partAnswer = scanner.nextLine().toLowerCase();
            if (partAnswer.equals("done")) {
                return car;
            } else if (partAnswer.contains(".")) {
                partAnswer = partAnswer.split("\\.")[0];
            }

            UserInterface.clearTerminal();

            //Check the answer
            if (Objects.equals(partAnswer, "1")) {
                //Change the motor properties
                System.out.println("Inspecting Part: \u001B[38;2;200;0;0mMotor" + Constants.ANSI_RESET);
                System.out.println("1. Power - " + car.getCarPart(Motor.class).getPower() + " W");
                System.out.println("\nInput the number of the attribute you'd wish to change: ");
                String atAnswer = scanner.nextLine().toLowerCase();
                if (atAnswer.contains(".")) {
                    atAnswer = atAnswer.split("\\.")[0];
                }

                //New value
                System.out.println("Input the new value of the attribute:");
                String valAnswer = scanner.nextLine().toLowerCase();
                if (valAnswer.contains(".")) {
                    valAnswer = valAnswer.split("\\.")[0];
                }

                //Change the correct attribute
                Motor newM;
                if (Objects.equals(atAnswer, "1")) {
                    newM = new Motor(car.getCarPart(Motor.class).getMass(), Double.parseDouble(valAnswer));
                } else {
                    newM = new Motor(Double.parseDouble(valAnswer), car.getCarPart(Motor.class).getPower());
                }

                car.setCarPart(newM);
                System.out.println(Constants.ANSI_GREEN + "Success!" + Constants.ANSI_RESET);
                Thread.sleep(1000);
            }

            //Change Wheels
            else if (Objects.equals(partAnswer, "2")) {
                //Change the motor properties
                System.out.println("Inspecting Part: \u001B[38;2;180;0;0mWheels" + Constants.ANSI_RESET);
                System.out.println("1. Diameter - " + car.getCarPart(Wheels.class) + " m");
                System.out.println("\nInput the number of the attribute you'd wish to change: ");
                String atAnswer = scanner.nextLine().toLowerCase();
                if (atAnswer.contains(".")) {
                    atAnswer = atAnswer.split("\\.")[0];
                }

                //New value
                System.out.println("Input the new value of the attribute:");
                String valAnswer = scanner.nextLine().toLowerCase();
                if (valAnswer.contains(".")) {
                    valAnswer = valAnswer.split("\\.")[0];
                }

                //Change the correct attribute
                Wheels newW;
                if (Objects.equals(atAnswer, "1")) {
                    newW = new Wheels(car.getCarPart(Wheels.class).getMass(), Double.parseDouble(valAnswer));
                } else {
                    newW = new Wheels(Double.parseDouble(valAnswer), car.getCarPart(Wheels.class).getDiameter());
                }

                car.setCarPart(newW);
                System.out.println(Constants.ANSI_GREEN + "Success!" + Constants.ANSI_RESET);
                Thread.sleep(1000);
            }
        }
    }

    public void showSetup(Car car) {
        //Get the correct data
        Motor m = car.getCarPart(Motor.class);
        Wheels w = car.getCarPart(Wheels.class);
        Tank t = car.getCarPart(Tank.class);
        Reductor re = car.getCarPart(Reductor.class);

        //Print the information
        System.out.println("\u001B[38;2;200;0;0m1. Motor " + Constants.ANSI_RESET + ">> Power: " + m.getPower() + " W");
        System.out.println("\u001B[38;2;180;0;0m2. Wheels " + Constants.ANSI_RESET + ">> Diameter: " + w.getDiameter() + " m");
        System.out.println("\u001B[38;2;166;0;0m3. Tank " + Constants.ANSI_RESET + ">> Total Capacity: " + t.getMaht() + " l; Pressure Pipe Area: " + t.getSurvetoru() + " m^2");
        System.out.println("\u001B[38;2;140;0;0m4. Reductor " + Constants.ANSI_RESET + ">> Gear Ratio: " + re.getGearRatios());
        System.out.println("\u001B[38;2;128;0;0m5. Engine Pipe " + Constants.ANSI_RESET + ">> WIP");
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}
