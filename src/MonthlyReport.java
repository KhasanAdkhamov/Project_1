import exceptions.ReportsException;

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
            throw new ReportsException("неверный формат");
        }
        entries.add(entry);
    }

    public List<MonthlyReportEntry> getEntries() {
        return entries;
    }

    public int totalSum(MonthlyReportEntry monthlyReportEntry) {
        return monthlyReportEntry.getSumOfOne() * monthlyReportEntry.getQuantity();
    }

    public int getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return "MonthlyReport{" +
                "entries=" + entries +
                ", month=" + month +
                '}';
    }
}
