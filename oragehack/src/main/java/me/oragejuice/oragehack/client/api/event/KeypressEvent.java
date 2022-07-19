package me.oragejuice.oragehack.client.api.event;

public class KeypressEvent {

    public KeypressEvent(int keycode) {
        this.keycode = keycode;
    }

    public final int keycode;
}
