import lombok.Getter;

import java.util.List;

@Getter
public class Payment {

    private final String payer;
    private final String reason;
    private final double amount;

    @Getter
    private final List<Person> responsible;

    public Payment(String payer, String reason, double amount, List<Person> responsible) {
        this.payer = payer;
        this.reason = reason;
        this.amount = amount;
        this.responsible = responsible;
    }
}
