package com.for_comprehension.function.demo;

class Demo {
    public static void main(String[] args) {
        Runnable hello_inner_class = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from another thread");
            }
        };

        //Runnable hello_lambda = () -> System.out.println("Hello from another thread");

        Thread thread = new Thread(hello_inner_class);

        thread.start();
    }
}
