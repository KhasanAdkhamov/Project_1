public class MonthlyReportEntry {
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private int sumOfOne;

    public MonthlyReportEntry(String itemName, boolean expense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = expense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }



    public String getItemName() {
        return itemName;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }
}
