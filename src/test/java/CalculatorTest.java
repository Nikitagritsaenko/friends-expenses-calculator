import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    void test1() {
        // GIVEN
        List<Person> people = new ArrayList<>();
        people.add(new Person("Nikita"));
        people.add(new Person("Alexander"));
        people.add(new Person("Sergey"));
        people.add(new Person("Georgiy"));

        List<Payment> payments = new ArrayList<Payment>();
        payments.add(new Payment("Nikita", "Burger King", 600, people));
        payments.add(new Payment("Nikita", "Taxi", 933, people));
        payments.add(new Payment("Sergey", "KillFish", 1942, people));
        payments.add(new Payment("Sergey", "Beer and chips", 600, people));

        // WHEN
        List<Transaction> transactions = Calculator.calculate(people, payments);

        // THEN
        assertTrue(people.stream().allMatch(person -> person.getDebt() == 0.0));
    }

    @Test
    void test2() {
        // GIVEN
        List<Person> people = new ArrayList<>();
        people.add(new Person("Nikita"));
        people.add(new Person("Alexander"));
        people.add(new Person("Georgiy"));
        people.add(new Person("Evgenii"));

        List<Payment> payments = new ArrayList<Payment>();
        payments.add(new Payment("Evgenii", "KillFish", 1800, people));
        payments.add(new Payment("Georgiy", "KillFish", 1000, people));
        payments.add(new Payment("Georgiy", "Beer", 200,
            people.stream()
                .filter(person -> !person.getName().equals("Evgenii"))
                .collect(Collectors.toList()))
        );

        // WHEN
        List<Transaction> transactions = Calculator.calculate(people, payments);

        // THEN
        assertTrue(people.stream().allMatch(person -> (int)person.getDebt() == 0));
    }
}
