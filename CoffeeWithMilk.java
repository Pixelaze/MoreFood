package ru.morefood.Coffee;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.morefood.MoreFood;

public class CoffeeWithMilk implements Listener {

    MoreFood mf;
    public CoffeeWithMilk(MoreFood mf) {
        this.mf = mf;
    }

    @EventHandler
    public void use(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(p.getInventory().getItemInMainHand() != null) {
                if(p.getInventory().getItemInMainHand().getType().equals(Material.MILK_BUCKET)) {
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if(item.getItemMeta() != null) {
                        if(item.getItemMeta().hasLore() == true && item.getItemMeta().hasDisplayName() == true) {
                            if(item.getItemMeta().getLore().get(0).equals(ChatColor.GRAY + mf.getConfig().getString("Coffee.Coffee_With_Milk_Lore"))) {
                                if(item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + mf.getConfig().getString("Coffee.Coffee_With_Milk"))) {
                                    e.setCancelled(true);
                                    removeItem(p);
                                    activate(p);
                                } else return;
                            } else return;
                        } else return;
                    } else return;
                } else return;
            } else return;
        } else return;
    }

    private void removeItem(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();
        item.setAmount(item.getAmount() - 1);
    }

    private void activate(Player p) {
        p.setFoodLevel(p.getFoodLevel() + 4); //Добавляем 4 единицы сытости игроку

        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType()); //Удаляем эффекты зелий
        }

        p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 1)); //Добавляем эффект ночного зрения
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2)); //Добавляем эффект скорости
    }
}
