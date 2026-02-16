import exceptions.ReportsException;

import java.util.ArrayList;
import java.util.List;

public class ReportProcess {

    private final static String MONTH_NAME = "m.20210";
    private final static String YEAR_NAME = "y.202";
    private List<MonthlyReport> monthlyReports = new ArrayList<>();
    private List<YearlyReport> yearlyReports = new ArrayList<>();
    private FileReader fileReader;

    // todo продумать хранение данных

    public void loadMonthlyReports() {
        fileReader = new FileReader();
        for (int i = 1; i < 4; i++) {
            MonthlyReport monthlyReport = new MonthlyReport(i);
            monthlyReports.add(monthlyReport);
            ArrayList<String> readFileContents = fileReader.readFile(MONTH_NAME + i + ".csv");
            System.out.println(MONTH_NAME + i + ".csv");
            for (int j = 1; j < readFileContents.size(); j++) {
                System.out.println(readFileContents.get(j));
                String[] split = readFileContents.get(j).split(",");
                String itemName = split[0];
                boolean isExpense = Boolean.parseBoolean(split[1]);
                int quantity = Integer.parseInt(split[2]);
                int sumOfOne = Integer.parseInt(split[3]);
                monthlyReport.addEntry(new MonthlyReportEntry(itemName, isExpense, quantity, sumOfOne));
            }
        }
    }

    public void loadYearlyReports() {
        fileReader = new FileReader();
        for (int i = 1; i < 2; i++) {
            YearlyReport yearlyReport = new YearlyReport(i);
            yearlyReports.add(yearlyReport);
            ArrayList<String> readFilesContents = fileReader.readFile(YEAR_NAME + i + ".csv");
            for (int j = 1; j < readFilesContents.size(); j++) {
                System.out.println(readFilesContents.get(j));
                String[] split = readFilesContents.get(j).split(",");
                int month = Integer.parseInt(split[0]);
                int amount = Integer.parseInt(split[1]);
                boolean isExpense = Boolean.parseBoolean(split[2]);
                yearlyReport.addEntry(new YearlyReportEntry(month, amount, isExpense));
            }
        }
    }

    public void reconcileReports() {
        if (monthlyReports == null || monthlyReports.isEmpty() && yearlyReports == null || yearlyReports.isEmpty()) {
            throw new ReportsException("сначала считай файл");
        }
        int totalSumOfExpense = 0;
        int totalSumOfIncome = 0;
        for (MonthlyReport monthlyReport : monthlyReports) {
            System.out.println(monthlyReport.getMonth());
            for (MonthlyReportEntry entry : monthlyReport.getEntries()) {
                if (entry.isExpense()) {
                    int expense = monthlyReport.totalSum(entry);
                    totalSumOfExpense += expense;
                    System.out.println(entry.getItemName() + " " + expense + " расход");
                } else {
                    int income = monthlyReport.totalSum(entry);
                    System.out.println(entry.getItemName() + " " + income + " доход");
                    totalSumOfIncome += income;
                }
            }
            System.out.println(totalSumOfExpense + " общая сумма расходов " + " за " + monthlyReport.getMonth() + " месяц");
            System.out.println(totalSumOfIncome + " общая сумма доходов " + " за " + monthlyReport.getMonth() + " месяц");
            for (YearlyReport yearlyReport : yearlyReports) {
                for (YearlyReportEntry yearlyReportEntry : yearlyReport.getEntries()) {
                    if (yearlyReportEntry.isExpense() && yearlyReportEntry.getMonth() == monthlyReport.getMonth()) {
                        int expenseYm = yearlyReportEntry.getAmount() - totalSumOfExpense;
                        System.out.println(expenseYm + " сверка расхода за " + monthlyReport.getMonth() + " месяц");
                    } else {
                        if (!(yearlyReportEntry.isExpense()) && yearlyReportEntry.getMonth() == monthlyReport.getMonth()) {
                            int incomeYm = yearlyReportEntry.getAmount() - totalSumOfIncome;
                            System.out.println(incomeYm + " сверка дохода за " + monthlyReport.getMonth() + " месяц");
                        }
                    }
                }
            }

        }
    }


    public void printMonthlyReportsInfo() {
        fileReader = new FileReader();
//        monthlyReports = new ArrayList<>();
//        for (int i = 1; i < 4; i++) {
//            MonthlyReport monthlyReport = new MonthlyReport(i);
//            monthlyReports.add(monthlyReport);
//            ArrayList<String> readFileContents = fileReader.readFile(MONTH_NAME + i + ".csv");
//            System.out.println(MONTH_NAME + i + ".csv");
//            for (int j = 1; j < readFileContents.size(); j++) {
//                System.out.println(readFileContents.get(j));
//                String[] split = readFileContents.get(j).split(",");
//                String itemName = split[0];
//                boolean isExpense = Boolean.parseBoolean(split[1]);
//                int quantity = Integer.parseInt(split[2]);
//                int sumOfOne = Integer.parseInt(split[3]);
//                monthlyReport.addEntry(new MonthlyReportEntry(itemName, isExpense, quantity, sumOfOne));
//            }
//        }
    }

    public void printYearlyReportsInfo() {

    }
}

