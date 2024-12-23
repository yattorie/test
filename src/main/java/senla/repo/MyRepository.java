package senla.repo;

import framework.annotation.Component;

@Component
public class MyRepository implements Repository {

    @Override
    public void save() {
        System.out.println("Saving data...");
    }
}