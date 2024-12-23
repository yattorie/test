package framework.source;

import framework.annotation.Component;
import framework.util.Messages;

public class ComponentPostProcessor implements PostProcessor {

    @Override
    public void process(Object bean, IocContainer iocContainer) throws Exception {
        Class<?> clazz = bean.getClass();

        if (clazz.isAnnotationPresent(Component.class)) {
            System.out.println(Messages.PROCESSING_COMPONENT.getMessage() + clazz.getName());
        }
    }
}
