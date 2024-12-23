package framework.source;

import framework.annotation.Component;
import framework.exception.FrameworkException;
import framework.util.ClassScanner;
import framework.util.ErrorMessages;
import framework.util.Messages;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationContext {

    private final IocContainer iocContainer;
    private final BeanCreator beanCreator;
    private final Set<PostProcessor> postProcessors = new HashSet<>();

    public ApplicationContext(String packageName) {
        this.iocContainer = new IocContainer();
        this.beanCreator = new BeanCreator(this);

        registerPostProcessor(new AutowiredPostProcessor(this));
        registerPostProcessor(new ComponentPostProcessor());

        try {
            scanAndRegister(packageName);
        } catch (Exception e) {
            throw new FrameworkException(ErrorMessages.FAILED_INIT.getDescription());
        }

        processBeans();
    }

    private void scanAndRegister(String packageName) throws Exception {
        List<Class<?>> classes = ClassScanner.scanPackage(packageName);

        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class)) {
                Object instance = beanCreator.createInstance(clazz);
                iocContainer.registerBean(clazz, instance);
                System.out.println(Messages.REGISTERED_CLASS.getMessage() + clazz.getName());
            }
        }
    }

    private void registerPostProcessor(PostProcessor postProcessor) {
        postProcessors.add(postProcessor);
    }

    private void processBeans() {
        for (Object bean : iocContainer.getBeans().values()) {
            processBean(bean);
        }
    }

    public void processBean(Object bean) {
        for (PostProcessor postProcessor : postProcessors) {
            try {
                postProcessor.process(bean, iocContainer);
            } catch (Exception e) {
                throw new FrameworkException(ErrorMessages.FAILED_TO_PROCESS_BEAN.getDescription() + bean.getClass().getName());
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        T bean = iocContainer.getBean(clazz);
        if (bean == null) {
            try {
                bean = (T) beanCreator.createInstance(clazz);
                iocContainer.registerBean(clazz, bean);
                System.out.println(Messages.CREATED_AND_REG_CLASS.getMessage() + clazz.getName());
            } catch (Exception e) {
                throw new FrameworkException(ErrorMessages.FAILED_TO_CREATE_BEAN.getDescription() + clazz.getName());
            }
        }
        return bean;
    }
}