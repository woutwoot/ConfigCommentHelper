import com.woutwoot.cch.CCH;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * @author woutwoot
 *         Created by on 31/05/2015 - 19:31.
 */
public class TestCCH {

    File file = new File("config.yml");

    @Test
    public void testAddComment(){
        CCH cch = new CCH(file);
        cch.readFile();
        assertTrue(cch.addComment("inventory.misc", "Contains all sorts of settings concerning the shop menu (inventory)\nAlso, just for testing, this is a new line."));
        assertTrue(cch.addComment("inventory.misc.disabledIconMaterial", "Change this to change which item will be used to represent a disabled feature."));
        cch.addHeader("This is a test header", "This is the second header line.", "This is the third header line.\nThis is the fourth line.");
        cch.writeFile();
    }

}
