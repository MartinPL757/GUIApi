package gui.api;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import gui.api.GUI;

public class GPlayers {
	private Map<String, GUI> lp = new HashMap<String, GUI>();	
	
	public boolean have(Player p) {
		if(this.lp.containsKey(p.getName())) {
			return true;
		}
		return false;
	}
	
	public void open(Player p, GUI gui) {
		if(!have(p)) {
			this.lp.put(p.getName(), gui);
			onGuiOpenEvent ev = new onGuiOpenEvent(p, gui, gui.getInv());
			Bukkit.getPluginManager().callEvent(ev);
			if(ev.isCancelled()) return;
			p.openInventory(gui.getInv());
		}
	}
	
	public void close(Player p) {
		if(have(p)) {
			onGuiClose ev = new onGuiClose(p, getGui(p));
			Bukkit.getPluginManager().callEvent(ev);
			this.lp.remove(p.getName());
			
		}
	}
	
	public GUI getGui(Player p) {
		if(have(p)) {
			return this.lp.get(p.getName());
		}
		return null;
	}
	
	public void changeGui(Player p, GUI gui) {
		if(have(p)) {	
			onGuiChangeEvent e = new onGuiChangeEvent(p, getGui(p), gui);
			Bukkit.getPluginManager().callEvent(e);
			if(e.isCancelled()) return;			
			remove(p);
		}
		p.openInventory(gui.getInv());
		add(p, gui);
		
		
	}
	
	private void remove(Player p) {
		this.lp.remove(p.getName());
	}
	
	private void add(Player p, GUI gui) {
		if(!have(p)) {
			this.lp.put(p.getName(), gui);
		}
	}
}
