import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // number of participants

        List<Person> participants = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            String name = br.readLine();
            Person person = new Person(name);
            participants.add(person);
        }

        int M = Integer.parseInt(br.readLine()); // number of payments

        List<Payment> payments = new ArrayList<>();
        for (int i = 0; i < M; ++i) {
            String transaction = br.readLine();

            String[] splitted = transaction.split(" ");

            String payer = splitted[0];
            String reason = splitted[1];
            double amount = Double.parseDouble(splitted[2]);

            boolean isExclude = splitted.length > 3 && splitted[3].equals("exclude");
            List<Person> responsibleForPayment = new ArrayList<>(participants);

            if (splitted.length > 3) {
                int startPos = isExclude ? 4 : 3; // TODO: refactor
                if (!isExclude) { // if include some person, then construct list from scratch
                    responsibleForPayment.clear();
                }
                for (int j = startPos; j < splitted.length; ++j) {
                    String payersName = splitted[j];
                    Person participantOfPayment = participants.stream()
                        .filter(person -> person.getName().equals(payersName))
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Wrong person name: " + payersName));
                    if (isExclude) {
                        responsibleForPayment.remove(participantOfPayment);
                    } else {
                        responsibleForPayment.add(participantOfPayment);
                    }
                }
            }

            payments.add(new Payment(payer, reason, amount, responsibleForPayment));
        }

        List<Transaction> transactions = Calculator.calculate(participants, payments);
    }
}
