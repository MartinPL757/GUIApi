package gui.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public static GPlayers gp = new GPlayers();
	
	public static GPlayers getGP() {
		return gp;
	}
	
	@Override
	public void onDisable() {
		System.out.println("Wylaczanie..");
	}

	@Override
	public void onEnable() {
		System.out.println("Uruchamianie..");
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if(gp.have(p)) {
			gp.close(p);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(gp.have(p)) {
			if(e.getCurrentItem() == null) return;
			if(e.getClickedInventory().equals(gp.getGui(p).getInv())) {
				onGuiItemClickEvent ev = new onGuiItemClickEvent(p, gp.getGui(p), e.getCurrentItem(), e.getSlot());
				Bukkit.getPluginManager().callEvent(ev);
				if(ev.isCancelled()) e.setCancelled(true);
				
			}
		}
	}
}
