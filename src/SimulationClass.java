public class SimulationClass {
    public static void main(String[] args) throws CarExplodedException, OutOfFuelException, OutOfIterationsException, InterruptedException {
        if (args.length > 0) {
            // Run simulation with arguments
            Main.simulateUsingArgs(args);
        } else {
            // No command-line arguments provided, run simulation without arguments
            try {
                Main.simulate();
            } catch (OutOfIterationsException | CarExplodedException | OutOfFuelException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
