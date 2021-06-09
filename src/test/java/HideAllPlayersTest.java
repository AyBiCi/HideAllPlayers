import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HideAllPlayersTest {
    private ServerMock server;
    private PlayerMock player1;
    private PlayerMock player2;

    @Before
    public void setUp()
    {
        server = MockBukkit.mock();
        MockBukkit.load(HideAllPlayers.class);
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
        player1.performCommand("hideplayers");
        server.removePlayer(player1);
        server.addPlayer(player1);
        assertTrue(player1.canSee(player2));
        player2 = server.addPlayer();
        assertTrue(player1.canSee(player2));
    }
}
