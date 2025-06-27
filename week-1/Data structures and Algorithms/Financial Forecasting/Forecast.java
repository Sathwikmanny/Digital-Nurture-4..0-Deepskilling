public class Forecast {

    public static double forecastValue(double currentValue, double growthRate, int years) {
        if (years == 0) return currentValue;
        return forecastValue(currentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static double forecastValueIterative(double currentValue, double growthRate, int years) {
        for (int i = 0; i < years; i++) {
            currentValue *= (1 + growthRate);
        }
        return currentValue;
    }

    public static void main(String[] args) {
        double currentValue = 1000.0;
        double growthRate = 0.05; // 5% annual growth
        int years = 10;

        double resultRecursive = forecastValue(currentValue, growthRate, years);
        double resultIterative = forecastValueIterative(currentValue, growthRate, years);

        System.out.printf("Recursive Forecast for %d years: %.2f%n", years, resultRecursive);
        System.out.printf("Iterative Forecast for %d years: %.2f%n", years, resultIterative);
    }
}