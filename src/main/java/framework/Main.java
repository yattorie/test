package framework;

import framework.source.ApplicationContext;
import senla.service.MyService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext("senla");
        MyService myService = context.getBean(MyService.class);
        myService.save();
    }
}
