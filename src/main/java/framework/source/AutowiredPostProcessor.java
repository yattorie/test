package framework.source;

import framework.annotation.Autowired;
import framework.annotation.Component;
import framework.exception.DependencyNotFoundException;
import framework.util.ErrorMessages;
import framework.util.Messages;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AutowiredPostProcessor implements PostProcessor {

    private final ApplicationContext applicationContext;

    public AutowiredPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void process(Object bean, IocContainer iocContainer) throws Exception {
        fieldProcessing(bean, iocContainer);
        methodProcessing(bean, iocContainer);
    }

    private void fieldProcessing(Object bean, IocContainer iocContainer) throws IllegalAccessException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object dependency = findDependency(field.getType(), iocContainer);
                if (dependency == null) {
                    throw new DependencyNotFoundException(ErrorMessages.DEPENDENCY_NOT_FOUND.getDescription(field.getType().getName()));
                }
                field.setAccessible(true);
                field.set(bean, dependency);
                System.out.println(Messages.DEPENDENCY_INJECTED.getMessage(field.getType().getName(), bean.getClass().getName()));
            }
        }
    }

    private void methodProcessing(Object bean, IocContainer iocContainer) throws Exception {
        Method[] methods = bean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Autowired.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Object[] dependencies = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    dependencies[i] = findDependency(parameterTypes[i], iocContainer);
                    if (dependencies[i] == null) {
                        throw new DependencyNotFoundException(ErrorMessages.DEPENDENCY_NOT_FOUND.getDescription(parameterTypes[i].getName()));
                    }
                }
                method.setAccessible(true);
                method.invoke(bean, dependencies);
                System.out.println(Messages.DEPENDENCY_INJECTED.getMessage(method.getName(), bean.getClass().getName()));
            }
        }
    }

    private Object findDependency(Class<?> type, IocContainer iocContainer) {
        Object dependency = iocContainer.getBean(type);
        if (dependency == null && type.isInterface()) {
            for (Object bean : iocContainer.getBeans().values()) {
                if (type.isAssignableFrom(bean.getClass())) {
                    return bean;
                }
            }
        }
        if (dependency == null && type.isAnnotationPresent(Component.class)) {
            try {
                dependency = applicationContext.getBean(type);
            } catch (Exception e) {
                throw new DependencyNotFoundException(ErrorMessages.DEPENDENCY_NOT_FOUND.getDescription(type.getName()));
            }
        }
        return dependency;
    }
}
