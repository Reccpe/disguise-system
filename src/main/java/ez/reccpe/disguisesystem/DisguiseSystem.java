package ez.reccpe.disguisesystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public final class DisguiseSystem extends JavaPlugin {

    private List<String> names;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfigToServer();
        getLogger().info(ChatColor.AQUA + "DisguiseSystem " +
                ChatColor.GRAY + "| " +
                ChatColor.WHITE + "Takma isim eklentisi basariyla baslatildi");
        getLogger().info(ChatColor.AQUA + "DisguiseSystem " +
                ChatColor.GRAY + "| " +
                ChatColor.WHITE + "Reccpe tarafindan gelistirildi");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.AQUA + "DisguiseSystem " +
                    ChatColor.GRAY + "| " +
                    ChatColor.WHITE + "Bu komutu sadece oyuncular kullanabilir");
            return true;
        }

        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("disguise")){
            if (!(player.isOp())){
                player.sendMessage(ChatColor.AQUA + "DisguiseSystem " +
                        ChatColor.GRAY + "| " +
                        ChatColor.WHITE + "Bu komutu kullanmak için yetkiniz yetersiz.");
                return true;
            }

            if (args.length != 0){
                player.sendMessage(ChatColor.AQUA + "DisguiseSystem " +
                        ChatColor.GRAY + "| " +
                        ChatColor.WHITE + "Doğru kullanım şekli : " + ChatColor.LIGHT_PURPLE + "/disguise");
                return true;
            }

            changePlayerNickName(player);
            return true;

        }

        return false;

    }

    private void loadConfigToServer(){
        FileConfiguration file = getConfig();
        names = file.getStringList("rastgele_isimler");
    }

    private void changePlayerNickName(Player player){
        Random rand = new Random();
        String randomName = names.get(rand.nextInt(names.size()));
        player.setPlayerListName(randomName);
        player.setDisplayName(randomName);
        player.sendMessage(ChatColor.AQUA + "DisguiseSystem " +
                ChatColor.GRAY + "| " +
                ChatColor.WHITE + "Oyun içi ismin değiştirildi : " +
                ChatColor.GREEN + randomName);
    }

}
