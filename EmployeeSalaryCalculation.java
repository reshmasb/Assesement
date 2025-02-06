import java.util.Scanner;

class EmployeeSalaryCalculator {
    private static final int REGULAR_HOURS_PER_WEEK = 40;
    private static final double OVERTIME_MULTIPLIER = 1.5;
    private static final double TAX_RATE = 0.10; 

    public static double calculateWeeklySalary(double hourlyRate, double hoursWorked) {
        double regularPay, overtimePay = 0;

        if (hoursWorked > REGULAR_HOURS_PER_WEEK) {
            regularPay = REGULAR_HOURS_PER_WEEK * hourlyRate;
            double overtimeHours = hoursWorked - REGULAR_HOURS_PER_WEEK;
            overtimePay = overtimeHours * hourlyRate * OVERTIME_MULTIPLIER;
        } else {
            regularPay = hoursWorked * hourlyRate;
        }

        return regularPay + overtimePay;
    }

    public static double calculateMonthlySalary(double hourlyRate, double hoursWorkedPerWeek) {
        double weeklySalary = calculateWeeklySalary(hourlyRate, hoursWorkedPerWeek);
        double grossMonthlySalary = weeklySalary * 4; 
        double taxDeduction = grossMonthlySalary * TAX_RATE;
        return grossMonthlySalary - taxDeduction;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter hourly rate: $");
        double hourlyRate = scanner.nextDouble();

        System.out.print("Enter hours worked per week: ");
        double hoursWorkedPerWeek = scanner.nextDouble();

        double netMonthlySalary = calculateMonthlySalary(hourlyRate, hoursWorkedPerWeek);

        System.out.printf("Net Monthly Salary after 10%% tax: $%.2f\n", netMonthlySalary);
        scanner.close();
    }
}
