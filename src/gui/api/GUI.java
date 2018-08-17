package gui.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class GUI {
	private Inventory inv;
	private String title;
	private int size, ran, own;
	private InventoryType type;
	public int getRan() {
		return this.ran;
	}


	public String getTitle() {
		return this.title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Inventory getInv() {
		return this.inv;
	}


	public int getSize() {
		return this.size;
	}

	
	
	public void setTo(Player p) {
		if(!Main.getGP().have(p)) {
			Main.getGP().open(p, this);
		} else {
			Main.getGP().changeGui(p, this);			
		}
	}
	
	public GUI(String title, int size, int ownId) {
		this.inv = Bukkit.createInventory(null, size, title);
		this.title = title;
		this.size = size;
		this.own = ownId;
		generateId();
	}
	
	public GUI(String title, InventoryType type, int ownId) {
		this.inv = Bukkit.createInventory(null, type, title);
		this.title = title;
		this.own = ownId;
		this.type = type;
		generateId();
	}	
	public int getOwn() {
		return own;
	}


	public void setOwn(int own) {
		this.own = own;
	}


	public InventoryType getType() {
		return type;
	}


	public void setHead(String nick, int index) {		
		this.inv.setItem(index, createHead(nick));
	}
	
	public void setHead(String nick, int index, List<String> lore) {		
		this.inv.setItem(index, createHead(nick, lore));
	}
	
	public void setItem(int index, Material m, short bit) {	
		ItemStack i = new ItemStack(m, 1, bit); {
			ItemMeta im = i.getItemMeta();
			im.setDisplayName("");
			i.setItemMeta(im);
		}
		this.inv.setItem(index, i);
	}
	
	public void setItem(int index, Material m, short bit, String title) {
		ItemStack i = new ItemStack(m, 1, bit); {
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(title);
			i.setItemMeta(im);
		}
		this.inv.setItem(index, i);
	}
	
	public void setItem(int index, Material m, short bit, String title, List<String> lore) {
		ItemStack i = new ItemStack(m, 1, bit); {
			ItemMeta im = i.getItemMeta();
			im.setDisplayName(title);
			im.setLore(lore);
			i.setItemMeta(im);
		}
		this.inv.setItem(index, i);
	}

	
	
	
	private ItemStack createHead(String nick) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()); {
			SkullMeta sm = (SkullMeta) head.getItemMeta();
			sm.setDisplayName("§a" + nick);
			sm.setOwner(nick);
			head.setItemMeta(sm);
		}
		
		return head;
	}
	private ItemStack createHead(String nick, List<String> lore) {
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal()); {
			SkullMeta sm = (SkullMeta) head.getItemMeta();
			sm.setDisplayName("§a" + nick);
			sm.setOwner(nick);
			sm.setLore(lore);
			head.setItemMeta(sm);
		}
		
		return head;
	}
	
	private void generateId() {
		Random r = new Random();
		this.ran = r.nextInt(2000)+1000;
		
	}
	
	
	public boolean equals(GUI g) {
		if(this.ran == g.getRan()) return true;
		return false;
	}
}
