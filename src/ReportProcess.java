import exceptions.ReportsException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportProcess {

    private List<MonthlyReport> monthlyReports = new ArrayList<>();
    private List<YearlyReport> yearlyReports = new ArrayList<>();
    private FileReader fileReader;
    private File file = new File("./resources/");

    // todo продумать хранение данных

    public void loadMonthlyReports() {
        fileReader = new FileReader();
        File[] files = file.listFiles();
        if (files != null) {
            String month = "m.";
            String format = ".csv";
            String year = "2021";
            for (File file1 : files) {
                String monthName = file1.getName();
                int indexFormat = monthName.indexOf(format);
                int indexYear = monthName.indexOf(year);
                String subString = monthName.substring(indexYear+4, indexFormat);
                if (monthName.contains(month)) {
                    MonthlyReport monthlyReport = new MonthlyReport(Integer.parseInt(subString));
                    monthlyReports.add(monthlyReport);
                    ArrayList<String> readFileContents = fileReader.readFile(monthName);
                    System.out.println(monthName);
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
        }

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

    public void loadYearlyReports() {
        fileReader = new FileReader();
        File[] files = file.listFiles();
        if (files != null) {
            String year = "y.";
            String format = ".csv";
            String y = "20";
            for (File file1 : files) {
                String yearName = file1.getName();
                int indexFormat = yearName.indexOf(format);
                int indexYear = yearName.indexOf(y);
                String substring = yearName.substring(indexYear + 2, indexFormat);
                if (yearName.contains(year)) {
                    YearlyReport yearlyReport = new YearlyReport(Integer.parseInt(substring));
                    yearlyReports.add(yearlyReport);
                    ArrayList<String> readFilesContents = fileReader.readFile(yearName);
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
        }
    }


//    public void loadYearlyReportss() {
//        fileReader = new FileReader();
//        for (int i = 1; i < 2; i++) {
//            YearlyReport yearlyReport = new YearlyReport(i);
//            yearlyReports.add(yearlyReport);
//            ArrayList<String> readFilesContents = fileReader.readFile(YEAR_NAME + i + ".csv");
//            for (int j = 1; j < readFilesContents.size(); j++) {
//                System.out.println(readFilesContents.get(j));
//                String[] split = readFilesContents.get(j).split(",");
//                int month = Integer.parseInt(split[0]);
//                int amount = Integer.parseInt(split[1]);
//                boolean isExpense = Boolean.parseBoolean(split[2]);
//                yearlyReport.addEntry(new YearlyReportEntry(month, amount, isExpense));
//            }
//        }
//    }

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
        for (MonthlyReport monthlyReport : monthlyReports) {
            String bestProductName = null;
            String totalSumName = null;
            int bestProduct = 0;
            int bestProfitAble = 0;
            String worseProductName = null;
            String totalSumExpenseName = null;
            int worseProduct = 0;
            int worseExpense = 0;
            System.out.println(monthlyReport.getMonth() + " месяц");
            for (MonthlyReportEntry monthlyReportEntry : monthlyReport.getEntries()) {
                if (!monthlyReportEntry.isExpense()) {
                    monthlyReportEntry.getSumOfOne();
                    int totalSumEntry = monthlyReport.totalSum(monthlyReportEntry);
                    if (monthlyReportEntry.getSumOfOne() > bestProduct) {
                        bestProduct = monthlyReportEntry.getSumOfOne();
                        bestProductName = monthlyReportEntry.getItemName();
                    }
                    if (totalSumEntry > bestProfitAble) {
                        bestProfitAble = totalSumEntry;
                        totalSumName = monthlyReportEntry.getItemName();
                    }
                } else {
                    monthlyReportEntry.getSumOfOne();
                    int totalSumEntryExpense = monthlyReport.totalSum(monthlyReportEntry);
                    if (monthlyReportEntry.getSumOfOne() > worseProduct) {
                        worseProduct = monthlyReportEntry.getSumOfOne();
                        worseProductName = monthlyReportEntry.getItemName();
                    }
                    if (totalSumEntryExpense > worseExpense) {
                        worseExpense = totalSumEntryExpense;
                        totalSumExpenseName = monthlyReportEntry.getItemName();
                    }
                }
            }
            System.out.println("самый прибыльный товар " + bestProduct + " " + bestProductName);
            System.out.println("cамый прибыльный товар по общей сумме " + bestProfitAble + " " + totalSumName);
            System.out.println("самая большая трата " + worseProduct + " " + worseProductName);
            System.out.println("самая большая трата по общей сумме " + worseExpense + " " + totalSumExpenseName);
        }
    }

    public void printYearlyReportsInfo() {
        for (YearlyReport yearlyReport : yearlyReports) {
            int totalIncome = 0;
            int totalExpense = 0;
            System.out.println(yearlyReport.getYear());
            for (YearlyReportEntry yearlyReportEntry : yearlyReport.getEntries()) {
                if (!yearlyReportEntry.isExpense()) {
                    System.out.println(yearlyReportEntry.getAmount() + " прибыль за " + yearlyReportEntry.getMonth() + " месяц");
                    totalIncome += yearlyReportEntry.getAmount();
                } else {
                    totalExpense += yearlyReportEntry.getAmount();
                }
            }
            int averageIncome = totalIncome / monthlyReports.size();
            int averageExpense = totalExpense / monthlyReports.size();
            System.out.println(averageIncome + " средний доход за все имеющиеся операции в году.");
            System.out.println(averageExpense + " средний расход за все имеющиеся операции в году");
        }
    }
}

