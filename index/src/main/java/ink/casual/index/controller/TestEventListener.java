package ink.casual.index.controller;

/**
 * @author lwt
 * @date 2019/4/8 16:58
 */
public interface TestEventListener {

    void onNewEvent(TestEventSource.TestEven event);

    void onEventStopped();

}
