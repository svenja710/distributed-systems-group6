package Assignment1;

public  class Assignment1{
    public static class SimpleThread extends Thread {
        String m;
        int n;
        public SimpleThread (String message, int number){
            this.m = message;
            this.n = number;
        }

        public void run(){
            System.out.println(n+m);
        }

    }


    public static  void T1(){
     (new SimpleThread(" I'm Thread one!",1)).start();
     (new SimpleThread(" I'm Thread two!",2)).start(); 
    }
    public static  void T2(){
        Counter counter = new Counter();
        SyncCounter syncCounter = new SyncCounter();
          Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter.increment();
            }
            });
          Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                counter.decrement();
            }
            });
               t1.start();
               t2.start();
            
               

        try {
            t1.join(); 
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }  
            System.out.println(counter.value());
    
       
            Thread st1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                syncCounter.increment();
            }
        });

        Thread st2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                syncCounter.decrement();
            }
        });

        st1.start();
        st2.start();
        

        try {
            st1.join();
            st2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(syncCounter.value());
    }
}
