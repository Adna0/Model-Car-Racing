import java.util.Arrays;

public class Track {
    //Main Variables
    private char[][] layout;
    private int carPosX;
    private int carPosY;

    //Constructor
    public Track(char[][] layout) {
        this.layout = layout;
    }

    //Main Methods
    public void displayTrack(Car car) {
        //Displays the track with a given car object
        for (char[] row : layout) {
            for (char cell : row) {
                if (cell == 'N') {
                    System.out.print(Constants.ANSI_GREEN + cell + Constants.ANSI_RESET);
                } else if (cell == 'T') {
                    System.out.print(Constants.ANSI_YELLOW + cell + Constants.ANSI_RESET);
                } else {
                    System.out.print(Constants.ANSI_RED + cell + Constants.ANSI_RESET);
                }
            }
            System.out.println();
        }
    }

    public void moveCarAlongTrack(Car car) {
        // Find the next valid position for the car (The algorithm assumes we cannot move diagonally)
        double currentX = car.getCarPosX();
        double currentY = car.getCarPosY();

        double nextX = currentX;
        double nextY = currentY;

        char currentCell = layout[(int) currentY][(int) currentX];
        if (currentCell == 'N') {
            //No track, car stays in place
            return;
        }

        layout[(int) currentY][(int) currentX] = 'T';

        // Determine the direction of movement based on the track layout
        if (currentX < layout[0].length - 1 && layout[(int) currentY][(int) (currentX + 1)] != 'N') {
            nextX++; // Move right
        } else if (currentY < layout.length - 1 && layout[(int) (currentY + 1)][(int) currentX] != 'N') {
            nextY++; // Move down
        } else if (currentX > 0 && layout[(int) currentY][(int) (currentX - 1)] != 'N') {
            nextX--; // Move left
        } else if (currentY > 0 && layout[(int) (currentY - 1)][(int) currentX] != 'N') {
            nextY--; // Move up
        }

        // Check the distance between the current position and the next position
        double distance = Math.sqrt(Math.pow(nextX - currentX, 2) + Math.pow(nextY - currentY, 2));

        // Calculate the number of spaces the car needs to move based on the distance traveled
        int numSpacesToMove = (int) (distance / 5);
        UserInterface.displayMessage("Distance: " + distance);
        UserInterface.displayMessage("Spaces to move: " + numSpacesToMove);

        // Adjust the next position based on the number of spaces to move
        if (currentX < layout[0].length - 1 && layout[(int) currentY][(int) (currentX + numSpacesToMove)] != 'N') {
            nextX += numSpacesToMove; // Move right
        } else if (currentY < layout.length - 1 && layout[(int) (currentY + numSpacesToMove)][(int) currentX] != 'N') {
            nextY += numSpacesToMove; // Move down
        } else if (currentX > 0 && layout[(int) currentY][(int) (currentX - numSpacesToMove)] != 'N') {
            nextX -= numSpacesToMove; // Move left
        } else if (currentY > 0 && layout[(int) (currentY - numSpacesToMove)][(int) currentX] != 'N') {
            nextY -= numSpacesToMove; // Move up
        }

        // Update the car's position
        car.setCarPosX(nextX);
        car.setCarPosY(nextY);

        layout[(int) nextY][(int) nextX] = car.getStyle();
    }

    public void displayTrackPeriodically(Track track, Car car, int maxIterations) throws InterruptedException {
        for (int i = 0; i < maxIterations; i++) {
            for (int j = 0; j < 10; j++) { // Adjust the number of lines as needed
                System.out.println();
            }

            //Deal with the car logistics
            track.moveCarAlongTrack(car);
            track.displayTrack(car);

            //Deal with UI messages
            UserInterface.displayMessage(car.toString());

            //Nightnight time
            Thread.sleep(Constants.REFRESH_RATE_MS);
        }
    }
}

class TrackGenerator {
    //Useful for generating tracks of different sizes and shapes

    //Main Methods
    public static char[][] generateRectangularTrack(int height) {
        int width = 2 * height;
        char[][] track = new char[height][width];

        for (int i = 0; i < height; i++) {
            Arrays.fill(track[i], 'N');
        }

        for (int i = 1; i < height - 1; i++) {
            // Top and bottom borders
            if (i == 1 || i == height - 2) {
                for (int j = 1; j < width - 1; j++) {
                    track[i][j] = 'T';
                }
            }
            // Left and right borders
            else {
                track[i][1] = 'T';
                track[i][width - 2] = 'T';
            }
        }
        return track;
    }
}