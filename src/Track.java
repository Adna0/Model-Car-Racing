import java.awt.*;
import java.util.Arrays;

public class Track {
    //Constructor
    public Track() {
    }

    //Main Methods
    public void displayTrack(char[][] layout) {
        //Displays the track
        StringBuilder sb = new StringBuilder();
        UserInterface.clearTerminal();
        for (char[] row : layout) {
            for (char cell : row) {
                if (cell == 'N') {
                    sb.append(Constants.ANSI_GREEN).append(cell).append(Constants.ANSI_RESET);
                    sb.append(Constants.ANSI_GREEN).append(cell).append(Constants.ANSI_RESET);
                } else if (cell == 'T') {
                    sb.append(Constants.ANSI_YELLOW).append(cell).append(Constants.ANSI_RESET);
                    sb.append(Constants.ANSI_YELLOW).append(cell).append(Constants.ANSI_RESET);
                } else {
                    sb.append(Constants.ANSI_RED).append(cell).append(Constants.ANSI_RESET);
                    sb.append(Constants.ANSI_RED).append(cell).append(Constants.ANSI_RESET);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public char[][] moveCarAlongTrack(Car car, char[][] layout) {
        double currentCarPos = car.getCarPos();

        double fullLaps = currentCarPos / Constants.TRACK_CIRCUMFERENCE;
        double remainderLap = currentCarPos % (Constants.TRACK_CIRCUMFERENCE);
        double remainderAngle = (remainderLap * Math.PI* 2) / (Constants.TRACK_CIRCUMFERENCE);

        double carDisplayX = Constants.TRACK_RADIUS * Math.cos(Math.PI - remainderAngle) + 10;
        double carDisplayY = Constants.TRACK_RADIUS * Math.cos(remainderAngle - Math.PI/2) + 20;

        layout[(int) carDisplayX][(int) carDisplayY] = car.getStyle();
        return layout;
    }

    public void displayTrackPeriodically(Track track, Car car, int maxIterations) throws InterruptedException {
        for (int i = 0; i < maxIterations; i++) {

            char[][] trackLayout = TrackGenerator.generateCircularTrack(20);

            //Deal with the car logistics
            car.updateVelocity();
            char[][] layout = track.moveCarAlongTrack(car, trackLayout);
            track.displayTrack(layout);

            //Deal with UI messages
            UserInterface.displayMessage(car.toString());

            //Nightnight time
            long sleepTime = Constants.REFRESH_RATE_MS / Constants.FPS;
            Thread.sleep(sleepTime);
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

    public static char[][] generateCircularTrack(int height) {
        int width = 2 * height;
        char[][] track = new char[height][width];

        for (int i = 0; i < height; i++) {
            Arrays.fill(track[i], 'N');
        }

        double radius = Constants.TRACK_RADIUS;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Point origin = new Point(height/2, width/2);
                double dist = Math.sqrt(Math.pow(origin.x - i, 2) + Math.pow(origin.y - j, 2));

                if (dist < radius && dist > radius*0.7) {
                    track[i][j] = 'T';
                }
            }
        }
        return track;
    }
}