public class MonthlyReportEntry {
    private String itemName;
    private boolean expense;
    private int quantity;
    private int sumOfOne;

    public MonthlyReportEntry(String itemName, boolean expense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.expense = expense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }



    public String getItemName() {
        return itemName;
    }

    public boolean isExpense() {
        return expense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }
}
