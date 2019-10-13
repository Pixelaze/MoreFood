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

public class EnergyDrink implements Listener {

    MoreFood mf;
    public EnergyDrink(MoreFood mf) {
        this.mf = mf;
    }

    @EventHandler
    public void use(PlayerInteractEvent e) {
        Player p = e.getPlayer(); //Находим игрока из события
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) { //Если действие - правый клик
            if(p.getInventory().getItemInMainHand() != null) {
                if (p.getInventory().getItemInMainHand().getType().equals(Material.POTION)) { //Если предмет равен ведру молока
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if(item.getItemMeta() != null) {
                        if (item.getItemMeta().hasDisplayName() == true && item.getItemMeta().hasLore() == true) { //Если у предмета есть изменённое имя и лор
                            if (item.getItemMeta().getLore().get(0).equals(ChatColor.GRAY + mf.getConfig().getString("Coffee.Energy_Drink_Lore"))) { //Если лор равен "Только что из печи! Наверное..."
                                if (item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + mf.getConfig().getString("Coffee.Energy_Drink"))) { //Если название равно "Горячий шоколад"
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
        p.getInventory().setItemInMainHand(item);
    }

    private void activate(Player p) {
        p.setFoodLevel(p.getFoodLevel() + 2); //Добавляем сытость
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1200, 3)); //Добавляем эффект ночного зрения
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 3)); //Добавляем эффект скоромти
    }
}
