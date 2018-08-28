package gui.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public static GPlayers gp = new GPlayers();
	
	public static GPlayers getGP() {
		return gp;
	}
	
	@Override
	public void onDisable() {
		System.out.println("[GUIApi] Is disabled!");
	}

	@Override
	public void onEnable() {
		System.out.println("[GUIApi] Is enabled!");
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
			ItemStack current;
			current = e.getCurrentItem() == null ? new ItemStack(Material.AIR) : e.getCurrentItem();
			if(e.getClickedInventory().equals(gp.getGui(p).getInv())) {
				onGuiItemClickEvent ev = new onGuiItemClickEvent(p, gp.getGui(p), current, e.getSlot(), e.getCursor());
				Bukkit.getPluginManager().callEvent(ev);
				ItemStack  was = new ItemStack(Material.AIR);
				if(e.getAction().equals(InventoryAction.HOTBAR_SWAP) || e.getAction().equals(InventoryAction.HOTBAR_MOVE_AND_READD)) {
					was = e.getWhoClicked().getInventory().getItem(e.getHotbarButton());
				}
								
				if(ev.isCancelled()) {
					if(e.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
						Bukkit.broadcastMessage("Przesunieto! " + e.getCurrentItem());
						ItemStack moved = e.getCurrentItem();
						e.getWhoClicked().getInventory().remove(moved);
					}
					e.setCancelled(true);
					if(e.getAction().equals(InventoryAction.HOTBAR_SWAP) || e.getAction().equals(InventoryAction.HOTBAR_MOVE_AND_READD)) {
						e.getWhoClicked().getInventory().setItem(e.getHotbarButton(), was);
					}
					
				}
				
			} else if(e.getClickedInventory().equals(e.getWhoClicked().getInventory())) {
				if(e.getCurrentItem() != null) {
					onGuiShiftClickFromBottomInventoryEvent ev = new onGuiShiftClickFromBottomInventoryEvent(p, gp.getGui(p), current, e.getSlot(), e.getCursor());
					Bukkit.getPluginManager().callEvent(ev);
					if(ev.isCancelled()) e.setCancelled(true);
				}
			}
		}
	}
}
