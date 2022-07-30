package me.oragejuice.oragehack.client.features.commands;

import me.oragejuice.commandDispatcher.ArgumentNode;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.feature.Feature;
import org.lwjgl.input.Keyboard;

public class Bind {


    ArgumentNode bind = new ArgumentNode("(?i)bind", new String[]{"bind"}, 0, generateFeatureArgumentNodes());

    public Bind() {
        Oragehack.INSTANCE.commandDispatcher.register(bind);
    }


    private static ArgumentNode[] generateFeatureArgumentNodes(){
        Feature[] features = Oragehack.INSTANCE.featureManager.getFeatures().stream().toArray(Feature[]::new);
        ArgumentNode[] featureNodes = new ArgumentNode[features.length];

        for (int i = 0; i < featureNodes.length; i++) {
            Feature f = features[i];
            featureNodes[i] = new ArgumentNode(
                    "(?i)" + f.getName(),
                    new String[]{f.getName().replaceAll(" ", "_")},
                    1,
                    generateBindNodes()
            );
        }
        return featureNodes;
    }


    private static ArgumentNode[] generateBindNodes(){
        ArgumentNode[] bindNodes = new ArgumentNode[binds.length];
        for (int i = 0; i < binds.length; i++) {
            ArgumentNode node = new ArgumentNode(
                    "(?i)" + binds[i],
                    new String[]{binds[i]},
                    2,
                    ((String[] s) -> {
                        Feature f = Oragehack.INSTANCE.featureManager.getFeatureByName(s[1]);
                        if (f != null) {
                            f.keybind = Keyboard.getKeyIndex(s[2]);
                            Oragehack.LOGGER.info("set {} to bound to {}", s[1], Keyboard.getKeyName(Keyboard.getKeyIndex(s[2])));
                        } else {
                            Oragehack.LOGGER.info("Feature {} not found", s[1]);
                        }
                    }));
            bindNodes[i] = node;
        }
        return bindNodes;
    }


    static String[] binds = new String[]{
            "ESCAPE",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "0",
            "MINUS",
            "EQUALS",
            "BACK",
            "TAB",
            "Q",
            "W",
            "E",
            "R",
            "T",
            "Y",
            "U",
            "I",
            "O",
            "P",
            "LBRACKET",
            "RBRACKET",
            "RETURN",
            "LCONTROL",
            "A",
            "S",
            "D",
            "F",
            "G",
            "H",
            "J",
            "K",
            "L",
            "SEMICOLON",
            "APOSTROPHE",
            "GRAVE",
            "LSHIFT",
            "BACKSLASH",
            "Z",
            "X",
            "C",
            "V",
            "B",
            "N",
            "M",
            "COMMA",
            "PERIOD",
            "SLASH",
            "RSHIFT",
            "MULTIPLY",
            "LMENU",
            "SPACE",
            "CAPITAL",
            "F1",
            "F2",
            "F3",
            "F4",
            "F5",
            "F6",
            "F7",
            "F8",
            "F9",
            "F10",
            "NUMLOCK",
            "SCROLL",
            "NUMPAD7",
            "NUMPAD8",
            "NUMPAD9",
            "SUBTRACT",
            "NUMPAD4",
            "NUMPAD5",
            "NUMPAD6",
            "ADD",
            "NUMPAD1",
            "NUMPAD2",
            "NUMPAD3",
            "NUMPAD0",
            "DECIMAL",
            "F11",
            "F12",
            "F13",
            "F14",
            "F15",
            "F16",
            "F17",
            "F18",
            "KANA",
            "F19",
            "CONVERT",
            "NOCONVERT",
            "YEN",
            "NUMPADEQUAL",
            "CIRCUMFLEX",
            "AT",
            "COLON",
            "UNDERLINE",
            "KANJI",
            "STOP",
            "AX",
            "UNLABELED",
            "NUMPADENTER",
            "RCONTROL",
            "SECTION",
            "NUMPADCOMMA",
            "DIVIDE",
            "SYSRQ",
            "RMENU",
            "FUNCTION",
            "PAUSE",
            "HOME",
            "UP",
            "PRIOR",
            "LEFT",
            "RIGHT",
            "END",
            "DOWN",
            "NEXT",
            "INSERT",
            "DELETE",
            "CLEAR",
            "LMETA"
    };

}
