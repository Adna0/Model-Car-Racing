// Custom exception class for car explosion
class CarExplodedException extends Exception {
    public CarExplodedException(String message) {
        super(message);
    }
}

// Custom exception class for running out of fuel
class OutOfFuelException extends Exception {
    public OutOfFuelException(String message) {
        super(message);
    }
}

class OutOfIterationsException extends Exception {
    public OutOfIterationsException(String message) {
        super(message);
    }
}
