import me.oragejuice.commandDispatcher.ArgumentNode;
import me.oragejuice.commandDispatcher.CommandDispatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandTester {

    @Test
    @DisplayName("generate command test")
    void generateCommand(){

        Assertions.assertTrue("heLP".matches("(?i)^Help"));

        Assertions.assertTrue("Terminal".matches("(?i)(TestFeature|Watermark|Terminal|)"));


        ArgumentNode secondaryNode = new ArgumentNode("123", new String[]{"123","132","421"}, 1);
        secondaryNode.setExecutor((String[] a) -> System.out.println(a[0].toString()));
        ArgumentNode initialNode = new ArgumentNode("Help", new String[]{"help"}, 0, secondaryNode);

        initialNode.dispatch(new String[]{"Help", "123"});
    }

    @Test
    @DisplayName("suggestions")
    void suggestion(){
        ArgumentNode secondaryNode = new ArgumentNode("(123|132|421|5645H)", new String[]{"123","132","421","5645H"}, 1);
        ArgumentNode initialNode = new ArgumentNode("Help", new String[]{"help", "asdf"}, 0, secondaryNode);

        String[] args = "Help 1".split(" ");
        Assertions.assertArrayEquals(new String[]{"123", "132", "421"}, initialNode.getSuggestions(args));
        System.out.println("TEST 2");
        String[] args2 = "Help ".split(" ");
        Assertions.assertArrayEquals(new String[]{"123","132","421","5645H"}, initialNode.getSuggestions(args2));
    }

    @Test
    @DisplayName("real suggestion example")
    void realSuggestion(){
        ArgumentNode lastnode = new ArgumentNode("123", new String[]{"thing1","thing2","thing3","thing5"}, 2,
                ((String[] s) -> System.out.println("SUCSESS" + s[1]))
                );
        ArgumentNode module = new ArgumentNode("^(Watermark|TesFeature)$", new String[]{"Watermark", "TesFeature"}, 1, lastnode);
        ArgumentNode setting = new ArgumentNode("(?i)setting", new String[]{"setting"}, 0, module);

        String[] args = "setting Watermark thi ".split(" ");
        for (String suggestion : setting.getSuggestions(args)) {
            System.out.println(suggestion);
        }
    }

    @Test
    @DisplayName("Config example")
    void a(){
        CommandDispatcher cd = new CommandDispatcher();

        ArgumentNode foo = new ArgumentNode("(?i)foo", new String[]{"foo"}, 2, ((String[] s) -> {
            System.out.println("FOO!");
        }));

        ArgumentNode bar = new ArgumentNode("(?i)bar", new String[]{"bar"}, 2, ((String[] s) -> {
            System.out.println("BAR!");
        }));

        ArgumentNode save = new ArgumentNode("(?i)save", new String[]{"save"}, 1, foo, bar);

        ArgumentNode load = new ArgumentNode("(?i)load", new String[]{"load"}, 1,
                ((String[] s) -> {
                    System.out.println("LOADED");
                }));

        ArgumentNode config = new ArgumentNode("(?i)^config$", new String[]{"config"}, 0, save, load);

        cd.register(config);

        String command = "config sa";
        for (String s : cd.getSuggestion(command)) {
            System.out.println(s);
        }
        System.out.println("TEST 2");
        String command2 = "conf";
        for (String s : cd.getSuggestion(command2)) {
            System.out.println(s);
        }
        System.out.println("TEST 3");
        String command3 = "config save";
        for (String s : cd.getSuggestion(command3)) {
            System.out.println(s);
        }
    }

}
