package github.com.emreisler.simplebanking.dto;

public class AmountDto {
    private double amount;

    public AmountDto(double amount) {
        this.amount = amount;
    }

    public AmountDto() {}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

