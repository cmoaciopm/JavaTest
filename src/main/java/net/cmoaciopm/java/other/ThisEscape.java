package net.cmoaciopm.java.other;

// Publication and escape
// Refer <Java Concurrency in Practice> 3.2
public class ThisEscape {

    public static class EventSource {
        public void registerListener(EventListener listener) {

        }
    }

    interface EventListener {
        void onEvent();
    }

    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {
            @Override
            public void onEvent() {
                abc();
            }
        });
    }

    private void abc() {

    }
}
