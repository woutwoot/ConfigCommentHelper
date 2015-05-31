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
        assertTrue(cch.addComment("inventory.misc", "Contains all sorts of settings concerning the shop menu (inventory)"));
        assertTrue(cch.addComment("inventory.misc.disabledIconMaterial", "Change this to change which item will be used to represent a disabled feature."));
        cch.writeFile();
    }

}
