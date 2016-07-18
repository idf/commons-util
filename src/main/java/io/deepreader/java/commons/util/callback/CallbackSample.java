package io.deepreader.java.commons.util.callback;

/**
 * Created by Daniel on 17/07/2016.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * In computer programming, a callback is a reference to executable code, or a piece of executable code,
 * that is passed as an argument to other code. This allows a lower-level software layer to call a subroutine (or
 * function) defined in a higher-level layer.
 */
public class CallbackSample {
    interface Callback {
        void call();
    }

    public static class MyForm implements Callback {
        public MyForm(){
            MyClass myClass = new MyClass();
            myClass.addCallback(this);
        }

        public void call(){
            System.out.println("Called me!");
        }
    }

    public static class MyClass{
        private List<Callback> listeners = new ArrayList<>();

        public void addCallback(Callback listener) {
            listeners.add(listener);
        }

        void run(){
            listeners.forEach(CallbackSample.Callback::call);
        }
    }
}
