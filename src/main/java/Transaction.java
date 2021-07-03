import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {

    private final Person from;
    private final Person to;
    private final double amount;

    @Override
    public String toString() {
        return "Transaction{" +
                "from=" + from +
                ", to=" + to +
                ", amount=" + (int)amount +
                " rub" +
                '}';
    }
}
