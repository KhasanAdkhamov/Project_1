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
}
