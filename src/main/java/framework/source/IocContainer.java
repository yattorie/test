package framework.source;

import java.util.HashMap;
import java.util.Map;

public class IocContainer {

    private final Map<Class<?>, Object> beans = new HashMap<>();

    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(beans.get(clazz));
    }

    public void registerBean(Class<?> clazz, Object instance) {
        beans.put(clazz, instance);

        for (Class<?> iface : clazz.getInterfaces()) {
            beans.put(iface, instance);
        }
    }

    public Map<Class<?>, Object> getBeans() {
        return beans;
    }
}
