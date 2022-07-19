import me.oragejuice.oragehack.Oragehack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommandTest {

    Oragehack oragehack = new Oragehack();

    @Test
    @DisplayName("command dispatched")
    void a(){
        Oragehack.INSTANCE.init();
        Oragehack.INSTANCE.commandDispatcher.dispatch("config save");
    }

    @Test
    @DisplayName("command sugges")
    void b(){
        Oragehack.INSTANCE.init();
        Oragehack.INSTANCE.commandDispatcher.dispatch("config save");
    }
}
