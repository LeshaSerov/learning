package alexey.skoblin.multithreading.base;

public class BaseThreadMethods {
    public static void runOrStart() {
        //Создание потока
        Thread thread = new Thread();
        //Запуск метода run в текущем потоке
        thread.run(); //После запуска, код будет ожидать завершения работы метода и только потом продолжит выполняться дальше.
        //Запуск потока с выполнением метода run в отдельном потоке
        thread.start(); //После запуска, код не будет ожидать завершения работы потока и продолжит выполняться дальше.
    }

    public static void nameThread() {
        Thread thread = new Thread();
        thread.setName("MyThread");
        System.out.println(thread.getName()); //MyThread

        Thread thread2 = new Thread("MyThread2");
        System.out.println(thread2.getName()); //MyThread2

        Thread thread3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }, "MyThread3");
        thread3.start(); //MyThread3
    }

    public static void currentThread() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()); //main
    }

    //Поток засыпает на заданное время
    public static void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Поток останавливается
    public static class MyRunnable implements Runnable {

        private boolean doStop = false;

        public synchronized void doStop() {
            this.doStop = true;
        }

        private synchronized boolean keepRunning() {
            return !this.doStop; //this.doStop == false
        }

        @Override
        public void run() {
            //Выполнение метода до тех пор, пока не будет вызван метод doStop
            while (keepRunning()) {
                System.out.println("Running");

                try {
                    Thread.sleep(3L * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void stop() {
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(10L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnable.doStop();
    }

    //Join подключиться к какому-нибудь потоку и ждать его окончания
    public static void join(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Running");
                    Thread.sleep(10L * 1000L);
                    System.out.println("Stop");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
            System.out.println("Done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
