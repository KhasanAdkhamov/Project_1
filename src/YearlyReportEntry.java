public class YearlyReportEntry {
    private int month;
    private int amount;
    private boolean isExpense;

    public YearlyReportEntry(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
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
