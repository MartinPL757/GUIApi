package gui.api;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Check {
	public static boolean twoItems(ItemStack one, ItemStack two) {
		if(one == null) {
			if(two == null) return true;
			return false;
		}
		if(!one.getType().equals(two.getType())) return false;
		if(one.hasItemMeta() && two.hasItemMeta()) {
			if(one.getItemMeta() != null && two.getItemMeta() != null) {
				if(one.getItemMeta().getDisplayName().equals(two.getItemMeta().getDisplayName())) {
					if(one.getItemMeta().getLore().equals(two.getItemMeta().getLore())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean twoItemsNoLore(ItemStack one, ItemStack two) {
		if(one == null) {
			if(two == null) return true;
			return false;
		}
		if(!one.getType().equals(two.getType())) return false;
		if(one.hasItemMeta() && two.hasItemMeta()) {
			if(one.getItemMeta() != null && two.getItemMeta() != null) {
				if(one.getItemMeta().getDisplayName().equals(two.getItemMeta().getDisplayName())) {
					return true;					
				}
			}
		}
		return false;
	}
	
	public static ItemStack fastItem(Material m, int size, short bit, String Display, List<String> lore) {
		ItemStack i = new ItemStack(m, size, bit); {
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(Display);
			im.setLore(lore);
			i.setItemMeta(im);
		}
		
		return i;
	}
	public static ItemStack fastItem(Material m, int size, short bit, String Display) {
		ItemStack i = new ItemStack(m, size, bit); {
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(Display);
			i.setItemMeta(im);
		}
		
		return i;
	}
}
