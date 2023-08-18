package Test;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testng.AssertJUnit.assertFalse;

import java.util.List;
import Command.Command;



public class CommandTest {


    public void testExecute() {

        Command command = new Command();
        List<String> result = command.execute("ls");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
