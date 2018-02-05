package net.cmoaciopm.java.other;

// Safe construction practices
// Refer <Java Concurrency in Practice> 3.2.1
public class SafeListener {

    public static class EventSource {
        public void registerListener(EventListener listener) {

        }
    }

    interface EventListener {
        void onEvent();
    }

    private final EventListener listener;
    private SafeListener() {
        listener = new EventListener() {
            @Override
            public void onEvent() {
                abc();
            }
        };
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }

    private void abc() {

    }

}
