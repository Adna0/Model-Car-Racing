public class Constants {
    //Physics Constants
    public static final double GRAVITATIONAL_CONSTANT = 9.818;

    // ANSI color codes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m"; // Light green
    public static final String ANSI_YELLOW = "\u001B[33m"; // Yellow
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_MAGENTA = "\u001B[35m";
    public static final String ANSI_ORANGE = "\u001B[38;2;255;165;0m"; //Orange?


    //Other
    public static int FPS = 10;
    public static int REFRESH_RATE_MS = 1000;

    public static final int TRACK_RADIUS = 10;
    public static final double TRACK_CIRCUMFERENCE = 2*TRACK_RADIUS*Math.PI;
}
