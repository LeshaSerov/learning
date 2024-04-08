package alexey.skoblin.multithreading.base;


import org.junit.Test;

public class BaseThreadMethodsTest {

    @Test
    public void nameThread() {
        BaseThreadMethods.nameThread();
    }

    @Test
    public void currentThread() {
        BaseThreadMethods.currentThread();
    }

    @Test
    public void join() {
        BaseThreadMethods.join();
    }
}