import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class HideAllPlayers extends JavaPlugin implements Listener {

    private Set<Player> playersThatHideOthers = new HashSet<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("hideplayers"))
            hideAllPlayersFor((Player) sender);
        else
            showAllPlayersFor((Player) sender);
        return false;
    }

    public void hideAllPlayersFor(Player player) {
        playersThatHideOthers.add(player);
        for(Player p : Bukkit.getOnlinePlayers()) {
            player.hidePlayer(this, p);
        }
    }

    public void showAllPlayersFor(Player player) {
        playersThatHideOthers.remove(player);
        for(Player p : Bukkit.getOnlinePlayers()) {
            player.showPlayer(this, p);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player joinedPlayer = event.getPlayer();
        for(Player player : playersThatHideOthers) {
            player.hidePlayer(this, joinedPlayer);
        }
    }

    //For MockBukkit
    public HideAllPlayers()
    {
        super();
    }

    protected HideAllPlayers(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file)
    {
        super(loader, description, dataFolder, file);
    }
}
