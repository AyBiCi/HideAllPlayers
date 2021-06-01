import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public class HideAllPlayers extends JavaPlugin {

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
