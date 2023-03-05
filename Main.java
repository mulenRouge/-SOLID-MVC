import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static HashSet<Notebook> getNotebookSet() {
        HashSet<Notebook> notebooks = new HashSet<>();
        Notebook.setCriteriaMap();
        Notebook.setCharacteristicsMap();
        for (int i = 0; i < 100; i++) {
            String brand = CharSource.brands[(int) Math.floor(Math.random() * CharSource.brands.length)];
            Integer ram = CharSource.rams[(int) Math.floor(Math.random() * CharSource.rams.length)];
            Integer hardDriveCap = CharSource.roms[(int) Math.floor(Math.random() * CharSource.roms.length)];
            String proc = CharSource.procs[(int) Math.floor(Math.random() * CharSource.procs.length)];
            Double screenDiag = CharSource.screenDiags[(int) Math.floor(Math.random() * CharSource.screenDiags.length)];
            String os = CharSource.oss[(int) Math.floor(Math.random() * CharSource.oss.length)];
            String colour = CharSource.colours[(int) Math.floor(Math.random() * CharSource.colours.length)];
            Boolean inStock = CharSource.inStocks[(int) Math.floor(Math.random() * CharSource.inStocks.length)];
            Integer price = (int) (Math.random() * (100_000 + 1));
            Notebook newNotebook = new Notebook(i + 1, brand, ram, hardDriveCap, proc,
                    screenDiag, os, colour, inStock, price);
            notebooks.add(newNotebook);
        }
        return notebooks;
    }

    public static void main(String[] args) {
        Shop myShop = new Shop("New Shop");
        myShop.setNotebooks(getNotebookSet());
        User newUser = new User("Петя");

        boolean end = false;
        while (!end) {

            newUser.setRequestedCriteria();
            newUser.printCriteria();

            newUser.setRequestedChars();
            HashMap<String, String> filterMap = newUser.getRequestedChars();

            myShop.setRequest(filterMap);
            myShop.printRequest();

            myShop.printFilteredNotes();

            System.out.println("Do you want to make another request?\nInput 'N to stop', Enter to continue");
            try (Scanner scan = new Scanner(System.in)) {
                String n = scan.nextLine();
                if (Objects.equals(n, "n") || Objects.equals(n, "N")) {
                    end = true;
                }
            }
        }
    }
}