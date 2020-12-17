package Day16;

import advent.utils.AdventFileUtils;

import java.util.List;

public class Day16Main {

    public static void main(String[] args) {

        List<String> input = getInput();
        input.forEach(System.out::println);

        TicketValidator ticketValidator = new TicketValidator(input);
        int ticketErrorRate = ticketValidator.calculateTicketErrorRate();
        System.out.println("ticketErrorRate = " + ticketErrorRate);

        long departureFieldsOfYourTicket = ticketValidator.multiplyDepartureFieldsOfYourTicket();
        System.out.println("departureFieldsOfYourTicket = " + departureFieldsOfYourTicket);
    }

    protected static List<String> getInput() {
        return AdventFileUtils.readClassInputIntoLines(Day16Main.class);
    }
}
