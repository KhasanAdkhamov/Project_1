public class YearlyReportEntry {
    private int month;
    private boolean isExpense;
    private int amount;

    public YearlyReportEntry(int month, boolean isExpense, int amount) {
        this.month = month;
        this.isExpense = isExpense;
        this.amount = amount;
    }

    public int getMonth() {
        return month;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public int getAmount() {
        return amount;
    }
}
