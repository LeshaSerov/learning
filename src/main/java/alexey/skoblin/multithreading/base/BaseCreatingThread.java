package alexey.skoblin.multithreading.base;

public class BaseCreatingThread {

    public static void startOfTheSimplestThread() {
        Thread thread = new Thread();//Создание потока
        thread.start();//Запуск потока
        //После запуска потока, его выполнение продолжится в отдельном потоке, а в основном потоке код продолжит
        // выполняться дальше, не дожидаясь его завершения

        //Так как не указан метод run(), то потоку нечего делать
        //Его можно определить через класс наследования, или через интерфейс Runnable

        //Не существует правил относительно того, какой из двух методов является лучшим. Оба метода работают.
        // Я предпочитаю реализовать Runnable и передать экземпляр реализации экземпляру Thread.
        // Когда Runnable выполняется пулом потоков, экземпляры Runnable легко поставить в очередь до тех пор,
        // пока поток из пула не будет простаивать. Это немного сложнее сделать с подклассами Thread.
        //
        //Иногда вам может потребоваться реализовать Runnable, а также подкласс Thread.
        // Например, если создать подкласс
        // Thread, который может выполнять более одного Runnable. Обычно это происходит при реализации пула потоков.
    }

    //Можно переопределить метод run() через класс наследования
    public class MyThread extends Thread {
        @Override
        public void run() {
            //Определение того, что нужно делать потоку
        }
    }

    //То же самое, но короче, через анонимный класс
    public static void overrideRun() {
        Thread thread = new Thread() {
            public void run() {
                //Определение того, что нужно делать потоку
            }
        };
        thread.start();
    }

    //Можно переопределить метод run() через интерфейс Runnable
    public class MyRunnable implements Runnable {
        @Override
        public void run() {
            //Определение того, что нужно делать потоку
        }
    }

    //То же самое, но короче, через анонимный класс
    public void overrideRunnable() {
        Thread thread = new Thread(new MyRunnable() {
            @Override
            public void run() {
                //Определение того, что нужно делать потоку
            }
        });
        thread.start();
    }

    //То же самое, еще короче, через лямбда-выражение
    public void overrideRunnableLambda() {
        Runnable runnable = () -> {
            //Определение того, что нужно делать потоку
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    //То же самое, еще короче, через лямбда-выражение
    public static void overrideRunLambda() {
        Thread thread = new Thread(() -> {
            //Определение того, что нужно делать потоку
        });
        thread.start();
    }

}
