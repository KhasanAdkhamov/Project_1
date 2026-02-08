import java.util.ArrayList;
import java.util.List;

public class ReportProcess {

    private final static String MONTH_NAME = "m.20210";
    private final static String YEAR_NAME = "y.202";
    public List<String> monthReportsList;
    private MonthlyReportEntry monthlyReportEntry;
    private MonthlyReport monthlyReport;

    // todo продумать хранение данных

    public void loadMonthlyReports() {
        FileReader fileReader = new FileReader();
        for (int i = 1; i < 4; i++) {
            ArrayList<String> readFilesContents= fileReader.readFilesContents(MONTH_NAME + i + ".csv");
            System.out.println(MONTH_NAME + i + ".csv");
            for (int j = 1; j < readFilesContents.size(); j++) {
                System.out.println(readFilesContents.get(j));
            }
        }
    }

    public void loadYearlyReports() {
        FileReader fileReader = new FileReader();
        for (int i = 1; i < 2; i++) {
            ArrayList<String> readFilesContents = fileReader.readFilesContents(YEAR_NAME + i + ".csv");
            for (int j = 1; j < readFilesContents.size(); j++) {
                System.out.println(readFilesContents.get(j));
            }
        }
    }

    public void reconcileReports() {
        //monthlyReportEntry = new MonthlyReportEntry();
        FileReader fileReader = new FileReader();
        for (int i = 1; i < 4; i++) {
            ArrayList<String> filesContents = fileReader.readFilesContents(MONTH_NAME + i + ".csv");
            for (int j = 1; j < filesContents.size(); j++) {
                monthlyReport = new MonthlyReport(i);
                String[] split = filesContents.get(j).split(",");
                String itemName = split[0];
                boolean isExpense = Boolean.parseBoolean(split[1]);
                int quantity = Integer.parseInt(split[2]);
                int sumOfOne = Integer.parseInt(split[3]);
                monthlyReport.addEntry(new MonthlyReportEntry(itemName, isExpense, quantity, sumOfOne));
                System.out.println(monthlyReport);
            }
        }
    }


    public void printMonthlyReportsInfo() {

    }

    public void printYearlyReportsInfo() {

    }
}
