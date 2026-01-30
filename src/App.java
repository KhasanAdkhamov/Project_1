import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportProcess reportProcess = new ReportProcess();

        while (true){
            printMenu();
            System.out.println("введи число от 0 до 5");
            int command = scanner.nextInt();
            switch (command) {
                case 0 -> {
                    System.out.println("выход");
                    scanner.close();
                    return;
                }
                case 1 -> {

                    reportProcess.loadMonthlyReports();
                }
                case 2 -> {

                    reportProcess.loadYearlyReports();
                }
                case 3 -> {
                    reportProcess.reconcileReports();
                }
                case 4 -> {
                    reportProcess.printMonthlyReportsInfo();
                }
                case 5 -> {
                    reportProcess.printYearlyReportsInfo();
                }
                default -> System.out.println("некорректная команда");
            }
        }
    }

    private static void printMenu() {
        System.out.println("===== приложение бухгалтерии ====");
        System.out.println("1. считать все месячные отчеты");
        System.out.println("2. считать все годовые отчеты");
        System.out.println("3. сверить отчеты");
        System.out.println("4. вывести всю информацию о месячных отчетах");
        System.out.println("5. вывести всю информацию о годовых отчетах");
        System.out.println("0. выход");
        System.out.println("===========================");
    }
}
