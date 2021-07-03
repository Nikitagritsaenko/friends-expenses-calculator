import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static List<Transaction> calculate(List<Person> people, List<Payment> payments) {
        printSummary(payments);

        Calculator.countPeopleDebts(people, payments);
        people.forEach(person -> System.out.println(person.getName() + "'s debt is: " + (int)person.getDebt()));

        List<Transaction> transactions = Calculator.balanceDebts(people);

        System.out.println();
        System.out.println("Solution: ");
        transactions.forEach(System.out::println);

        return transactions;
    }

    public static void printSummary(List<Payment> payments) {
        double totalCost = payments.stream()
            .map(Payment::getAmount)
            .reduce(Double::sum)
            .orElse(0.0);
        System.out.println("Expenses sum = " + totalCost + " rub");
        System.out.println();
    }

    public static List<Transaction> balanceDebts(List<Person> people) {
        List<Transaction> transactions = new ArrayList<>();

        while (!isCalculated(people)) {
            for (Person person : people) {
                if (person.getDebt() < 0) {
                    double paymentAmount = Math.abs(person.getDebt());
                    people.stream()
                        .filter(p -> p.getDebt() > 0)
                        .findAny()
                        .ifPresent(payer -> {
                            transactions.add(new Transaction(payer, person, paymentAmount));

                            payer.decreaseDebt(paymentAmount);
                            person.increaseDebt(paymentAmount);
                        });
                }
            }
        }

        return transactions;
    }

    public static void countPeopleDebts(List<Person> people, List<Payment> payments) {
        for (Payment payment : payments) {
            double amount = payment.getAmount();
            String payersName = payment.getPayer();
            Person payer = people.stream()
                .filter(person -> person.getName().equals(payersName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wrong person name: " + payersName));

            payer.decreaseDebt(amount);

            List<Person> responsible = payment.getResponsible();
            double amountPerPerson = amount / responsible.size();

            responsible.forEach(
                responsiblePerson -> responsiblePerson.increaseDebt(amountPerPerson)
            );
        }
    }

    private static boolean isCalculated(List<Person> people) {
        for (Person person : people) {
            if ((int)person.getDebt() > 0) {
                return false;
            }
        }
        return true;
    }
}
