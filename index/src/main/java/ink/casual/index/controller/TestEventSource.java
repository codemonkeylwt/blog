package ink.casual.index.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwt
 * @date 2019/4/8 16:56
 */
public class TestEventSource {

    private List<TestEventListener> listeners;

    public TestEventSource(){
        listeners = new ArrayList<>();
    }

    public void register(TestEventListener testEventListener){
        listeners.add(testEventListener);
    }

    public void newEvent(TestEven even){
        for (TestEventListener listener : listeners) {
            listener.onNewEvent(even);
        }
    }

    public void eventStopped() {
        for (TestEventListener listener : listeners) {
            listener.onEventStopped();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestEven{
        String name;
    }
}
