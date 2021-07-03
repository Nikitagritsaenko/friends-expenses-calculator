import lombok.Getter;
import lombok.Setter;

@Getter
public class Person {

    private final String name;

    @Setter
    private double debt;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void increaseDebt(double amount) {
        debt += amount;
    }

    public void decreaseDebt(double amount) {
        debt -= amount;
    }
}