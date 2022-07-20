package me.oragejuice.oragehack.client.features.modules.terminal;

import me.oragejuice.oragehack.client.api.IListener;
import me.oragejuice.oragehack.client.api.feature.Feature;
import org.lwjgl.input.Keyboard;

public class Terminal extends Feature {

    TerminalGUI terminalGUI = new TerminalGUI();

    public Terminal() {
        super("Terminal");
        super.keybind = Keyboard.KEY_P;
    }

    @Override
    public void onEnable(){
        mc.displayGuiScreen(terminalGUI);
        this.setEnabled(false);
    }
}
