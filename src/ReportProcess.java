import java.util.ArrayList;
import java.util.List;

public class ReportProcess {

    public final static String MONTH_NAME = "m.20210";
    public final static String YEAR_NAME = "y.202";
    public List<String> monthReports;

    // todo продумать хранение данных

    public void loadMonthlyReports() {
        FileReader fileReader = new FileReader();
        for (int i = 1; i < 4; i++) {
            ArrayList<String> readFilesContents= fileReader.readFilesContents(MONTH_NAME + i + ".csv");
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

    }

    public void printMonthlyReportsInfo() {

    }

    public void printYearlyReportsInfo() {

    }
}
