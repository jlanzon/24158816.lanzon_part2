package app;

import model.store.DataRepository;

public class TestLoad {
    public static void main(String[] args) {
        System.out.println("Starting Data Loading Test...");
        DataRepository repo = new DataRepository();
        repo.loadAll();
        System.out.println("Test Complete.");
    }
}
