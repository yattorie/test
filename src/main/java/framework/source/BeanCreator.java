package framework.source;

import framework.annotation.Autowired;
import framework.exception.DependencyNotFoundException;
import framework.exception.FrameworkException;
import framework.util.ErrorMessages;
import framework.util.Messages;

import java.lang.reflect.Constructor;
import java.util.Optional;

public class BeanCreator {

    private final ApplicationContext applicationContext;

    public BeanCreator(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Object createInstance(Class<?> clazz) throws Exception {
        Optional<Constructor<?>> constructor = findAutowiredConstructor(clazz);
        if (constructor.isPresent()) {
            Object[] dependencies = getConstructorDependencies(constructor.get());
            System.out.println(Messages.BEAN_CREATED.getMessage(clazz.getName(), "constructor with dependencies"));
            return constructor.get().newInstance(dependencies);
        } else {
            System.out.println(Messages.BEAN_CREATED.getMessage(clazz.getName(), "default"));
            Object instance = clazz.getDeclaredConstructor().newInstance();
            applicationContext.processBean(instance); // Process fields after creation
            return instance;
        }
    }

    private Optional<Constructor<?>> findAutowiredConstructor(Class<?> clazz) throws FrameworkException {
        Constructor<?> autowiredConstructor = null;
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Autowired.class)) {
                if (autowiredConstructor != null) {
                    throw new FrameworkException(ErrorMessages.MULTIPLE_AUTOWIRED_CONSTRUCTORS.getDescription(clazz.getName()));
                }
                autowiredConstructor = constructor;
            }
        }
        return Optional.ofNullable(autowiredConstructor);
    }

    private Object[] getConstructorDependencies(Constructor<?> constructor) {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] dependencies = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Object dependency = applicationContext.getBean(parameterTypes[i]);
            if (dependency == null) {
                throw new DependencyNotFoundException(ErrorMessages.DEPENDENCY_NOT_FOUND.getDescription(parameterTypes[i].getName()));
            }
            dependencies[i] = dependency;
        }
        return dependencies;
    }
}
