import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.entity.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HideAllPlayersTest {
    private ServerMock server;
    private HideAllPlayers plugin;
    private Player player1;
    private Player player2;
    @Before
    public void setUp()
    {
        server = MockBukkit.mock();
        plugin = (HideAllPlayers) MockBukkit.load(HideAllPlayers.class);
        player1 = server.addPlayer();
        player2 = server.addPlayer();
    }

    @After
    public void tearDown()
    {
        MockBukkit.unmock();
    }

    @Test
    public void testCommands() {
        player1.performCommand("hideplayers");
        assertFalse(player1.canSee(player2));
        player1.performCommand("showplayers");
        assertTrue(player1.canSee(player2));
    }

    @Test
    public void onNewPlayerEnter(){
        player1.performCommand("hideplayers");
        player2 = server.addPlayer();
        assertFalse(player1.canSee(player2));
    }

    @Test
    public void onPlayerExitWithHidingPlayersOn(){

    }
}
