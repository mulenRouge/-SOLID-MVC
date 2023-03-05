import java.util.*;

public class Shop {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private HashSet<Notebook> notebooks;

    public HashSet<Notebook> getNotebooks() {
        return notebooks;
    }

    public void setNotebooks(HashSet<Notebook> notebooks) {
        this.notebooks = notebooks;
    }

    public void printNotebooks() {
        for (Notebook notebook : this.notebooks) {
            System.out.println(notebook.GetCharacteristics());
        }
    }

    public Shop(String name) {
        this.name = name;
    }

    private ShopRequest request;

    public void setRequest(HashMap<String, String> filter) {
        ShopRequest newRequest = new ShopRequest();
        newRequest.setUserRequest(filter);

        if (newRequest.getBrands().size() == 0) {
            newRequest.setBrands();
        }
        if (newRequest.getRams().size() == 0) {
            newRequest.setRams();
        }
        if (newRequest.getRoms().size() == 0) {
            newRequest.setRoms();
        }
        if (newRequest.getScreens().size() == 0) {
            newRequest.setScreens();
        }
        if (newRequest.getProcs().size() == 0) {
            newRequest.setProcs();
        }
        if (newRequest.getOss().size() == 0) {
            newRequest.setOss();
        }
        if (newRequest.getColours().size() == 0) {
            newRequest.setColours();
        }
        if (newRequest.getInStocks().size() == 0) {
            newRequest.setInStocks();
        }
        this.request = newRequest;
    }

    public void printRequest() {
        System.out.println();

        StringBuilder outStr = new StringBuilder();
        outStr.append("Brands:\n");
        if (request.getBrands().size() == CharSource.brands.length) {
            outStr.append("\t").append("Any").append("\n");
        } else {
            for (String brand : request.getBrands()) {
                outStr.append("\t").append(brand).append("\n");
            }
        }

        outStr.append("Rams, Gb:\n");
        if (request.getRams().size() == CharSource.rams.length) {
            outStr.append("\t").append("Any").append("\n");
        } else {
            for (int ram : request.getRams()) {
                outStr.append("\t").append(ram).append("\n");
            }
        }

        outStr.append("Roms, Gb:\n");
        if (request.getRoms().size() == CharSource.roms.length) {
            outStr.append("\t").append("Any").append("\n");
        } else {
            for (int rom : request.getRoms()) {
                outStr.append("\t").append(rom).append("\n");
            }
        }

        outStr.append("Processors:\n");
        if (request.getProcs().size() == CharSource.procs.length) {
            outStr.append("\t").append("Any").append("\n");
        } else {
            for (String proc : request.getProcs()) {
                outStr.append("\t").append(proc).append("\n");
            }
        }

        outStr.append("Screen sizes:\n");
        if (request.getScreens().size() == CharSource.screenDiags.length) {
            outStr.append("\t").append("Any").append("\n");
        } else {
            for (Double screen : request.getScreens()) {
                outStr.append("\t").append(screen).append("\"").append("\n");
            }
        }

        outStr.append("OS:\n");
        if (request.getOss().size() == CharSource.oss.length) {
            outStr.append("\t").append("Any").append("\n");
        } else {
            for (String os : request.getOss()) {
                outStr.append("\t").append(os).append("\n");
            }
        }

        outStr.append("Colours:\n");
        if (request.getColours().size() == CharSource.colours.length) {
            outStr.append("\t").append("Any").append("\n");
        } else {
            for (String colour : request.getColours()) {
                outStr.append("\t").append(colour).append("\n");
            }
        }

        outStr.append("In Stock:\n");
        if (request.getInStocks().size() == CharSource.inStocks.length) {
            outStr.append("\t").append("Any").append("\n");
        } else {
            for (Boolean colour : request.getInStocks()) {
                if (colour) {
                    outStr.append("\t").append("Yes").append("\n");
                } else {
                    outStr.append("\t").append("No").append("\n");
                }
            }
        }

        outStr.append("Max. price: ");
        if (request.getMaxPrice() == 100_000_000) {
            outStr.append("Any").append("\n");
        } else {
            outStr.append(request.getMaxPrice()).append(" RUB").append("\n");
        }

        System.out.println(outStr);
    }

    public HashSet<Notebook> getFilteredNotes() {
        HashSet<Notebook> filteredNotes = new HashSet<>();
        for (String brand : request.getBrands()) {
            for (int ram : request.getRams()) {
                for (int rom : request.getRoms()) {
                    for (String proc : request.getProcs()) {
                        for (double screen : request.getScreens()) {
                            for (String os : request.getOss()) {
                                for (String colour : request.getColours()) {
                                    for (boolean inStock : request.getInStocks()) {
                                        for (Notebook note : notebooks) {
                                            if (note.getBrand().equals(brand) && note.getRam() == ram
                                                    && note.getRom() == rom
                                                    && note.getProc().equals(proc) && note.getScreenDiag() == screen
                                                    && note.getOs().equals(os) && note.getColour().equals(colour)
                                                    && note.getInStock() == inStock &&
                                                    note.getPrice() < request.getMaxPrice()) {
                                                filteredNotes.add(note);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return filteredNotes;
    }

    public void printFilteredNotes() {
        System.out.println();
        if (getFilteredNotes().size() != 0) {
            System.out.println("List of notebooks that fit the filter: ");
            for (Notebook note : getFilteredNotes()) {
                System.out.println(note.GetCharacteristics());
            }
        } else {
            System.out.println("There are no notebooks that fit your criteria");
        }

    }
}