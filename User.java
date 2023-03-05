import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    public HashMap<Integer, String> requestedCriteria;

    public HashMap<Integer, String> getRequestedCriteria() {
        return requestedCriteria;
    }

    public void setRequestedCriteria() {
        HashMap<Integer, String> requestedCriteria = new HashMap<>();

        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Select the criteria that you want to make the request by.");

            for (int i = 1; i < Notebook.getCriteriaMap().size() + 1; i++) {
                String printCrit = Notebook.criteriaMap.get(i);
                System.out.println("Do you to take " + printCrit + " into account in your request?\nY/N: ");
                String n = scan.nextLine();
                while (true) {
                    if (Objects.equals(n, "q") || Objects.equals(n, "Q")) {
                        System.exit(0);
                    } else if (Objects.equals(n, "y") || Objects.equals(n, "Y")) {
                        if (Objects.equals("Price", Notebook.criteriaMap.get(i))) {
                            requestedCriteria.put(0, "Price");
                        } else {
                            requestedCriteria.put(i, Notebook.criteriaMap.get(i));
                        }
                        break;
                    } else if (Objects.equals(n, "n") || Objects.equals(n, "N")) {
                        break;
                    } else {
                        System.out.println("Wrong action! Try again!");
                        System.out.println("Do you to take " + printCrit + " into account in your request?\nY/N: ");
                        n = scan.nextLine();
                    }
                }
            }
        }
        this.requestedCriteria = requestedCriteria;
    }

    public HashMap<String, String> requestedChars;

    public HashMap<String, String> getRequestedChars() {
        return requestedChars;
    }

    public void setRequestedChars() {
        HashMap<String, String> reqChars = new HashMap<>();
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Select the characteristics that you want to filter your request by.");
            for (int critKey : requestedCriteria.keySet()) {
                if (Objects.equals("Price", requestedCriteria.get(critKey))) {
                    System.out.println("What maximum price of a Notebook do you want?");
                    String n = scan.nextLine();
                    while (!Parsers.tryParseInt(n)) {
                        System.out.println("You didn't input a number! Try again!");
                        n = scan.nextLine();
                    }
                    reqChars.put("Price", n);
                } else {
                    System.out.printf("""
                            What %s-s do you want to be used as a filter?
                            Input one number at a time.
                            Type 'f' to stop input the characteristics, 'q' to exit.
                            """, requestedCriteria.get(critKey));
                    for (int charKey = 1; charKey < Notebook.characteristicsMap.size(); charKey++) {
                        if (!Notebook.characteristicsMap.containsKey(critKey + Integer.toString(charKey))) {
                            break;
                        }
                        System.out.printf(Notebook.characteristicsMap.get(critKey + Integer.toString(charKey))
                                + " - %d\n", charKey);
                    }
                    while (true) {
                        String n = scan.nextLine();
                        if (Objects.equals(n, "q") || Objects.equals(n, "Q")) {
                            System.exit(0);
                        } else if (Notebook.characteristicsMap.containsKey(critKey + n)) {
                            reqChars.put(critKey + n, Notebook.characteristicsMap.get((critKey + n)));
                        } else if (Objects.equals(n, "f") || Objects.equals(n, "F")) {
                            break;
                        } else {
                            System.out.println("No such characteristic! Try again!");
                        }
                    }
                }
            }
        }
        this.requestedChars = reqChars;
    }

    public void printCriteria() {
        System.out.println("Your criteria list:");
        for (int key : requestedCriteria.keySet()) {
            System.out.println(requestedCriteria.get(key));
        }
        System.out.println();
    }
}