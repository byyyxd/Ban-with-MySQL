package me.repize2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import me.repize2.cmd.BanCMD;
import me.repize2.cmd.MuteCMD;
import me.repize2.cmd.UnbanCMD;
import me.repize2.cmd.UnmuteCMD;
import me.repize2.event.PlayerListener;
import me.repize2.mysql.MySQLMain;

public class Main extends JavaPlugin {
	
	public static Main instance;
	public static Main getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"[REPIZE] Banimentos");
		saveDefaultConfig();
		MySQLMain.open();
		
		getCommand("ban").setExecutor(new BanCMD());
		getCommand("mute").setExecutor(new MuteCMD());
		getCommand("unban").setExecutor(new UnbanCMD());
		getCommand("unmute").setExecutor(new UnmuteCMD());
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED+"[REPIZE] Banimentos");
	}
	
}
