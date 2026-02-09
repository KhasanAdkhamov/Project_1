import exceptions.YearException;

import java.util.ArrayList;
import java.util.List;

public class YearlyReport {
    private List<YearlyReportEntry> entries;
    private int year;

    public YearlyReport(int year) {
        this.entries = new ArrayList<>();
        this.year = year;
    }

    public void addEntry(YearlyReportEntry entry) {
        if (entry == null) {
            throw new YearException("неверный формат");
        }
        entries.add(entry);
    }

    public List<YearlyReportEntry> getEntries() {
        return entries;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "YearlyReport{" +
                "entries=" + entries +
                ", year=" + year +
                '}';
    }
}
