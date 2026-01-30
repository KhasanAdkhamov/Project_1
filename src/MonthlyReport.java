import exceptions.MonthException;

import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    private List<MonthlyReportEntry> entries;
    private int month;

    public MonthlyReport(int month) {
        this.entries = new ArrayList<>();
        this.month = month;
    }

    public void addEntry(MonthlyReportEntry entry) {
        if (entry == null)  {
            throw new MonthException("неверный формат");
        }
        entries.add(entry);
    }


}
