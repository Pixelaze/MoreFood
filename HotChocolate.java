package ru.morefood.Chocolates;

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

public class HotChocolate implements Listener {

    @EventHandler
    public void use(PlayerInteractEvent e) {
        Player p = e.getPlayer(); //Находим игрока из события
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) { //Если действие - правый клик
            if (p.getInventory().getItemInMainHand().getType().equals(Material.MILK_BUCKET)) { //Если предмет равен ведру молока
                ItemStack item = p.getInventory().getItemInMainHand();
                if (item.getItemMeta().hasDisplayName() == true && item.getItemMeta().hasLore() == true) { //Если у предмета есть изменённое имя и лор
                    if (item.getItemMeta().getLore().get(0).equals(ChatColor.GRAY + "Только что из печи! Наверное...")) { //Если лор равен "Только что из печи! Наверное..."
                        if (item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Горячий шоколад")) { //Если название равно "Горячий шоколад"
                            e.setCancelled(true);
                            removeItem(p);
                            activate(p);
                        } else return;
                    } else return;
                } else return;
            } else return;
        } else return;
    }

    private void removeItem(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand(); //Предмет в главной руке
        item.setAmount(item.getAmount() - 1); //-1 к кол-ву предметов
        p.getInventory().setItemInMainHand(item); //Установка значения
    }

    private void activate(Player p) {
        p.setFoodLevel(p.getFoodLevel() + 1); //Добавляем 3 единицы сытости игроку

        for (PotionEffect effect : p.getActivePotionEffects()) {
            p.removePotionEffect(effect.getType()); //Удаляем эффекты зелий
        }
    }

}
