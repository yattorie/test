package senla.service;

import framework.annotation.Autowired;
import framework.annotation.Component;
import senla.repo.MyRepository;

@Component
public class MyService {

    @Autowired
    private MyRepository myRepository;

    public void save() {
        myRepository.save();
    }
}