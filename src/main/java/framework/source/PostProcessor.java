package framework.source;

public interface PostProcessor {
    void process(Object bean, IocContainer iocContainer) throws Exception;
}
