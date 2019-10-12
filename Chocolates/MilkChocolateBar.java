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

public class MilkChocolateBar implements Listener {

    @EventHandler
    public void use(PlayerInteractEvent e) {
        Player p = e.getPlayer(); //Находим игрока из события
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) { //Если действие - правый клик
            if (p.getInventory().getItemInMainHand().getType().equals(Material.IRON_INGOT)) { //Если предмет равен железному слитку
                ItemStack item = p.getInventory().getItemInMainHand();
                if (item.getItemMeta().hasDisplayName() == true && item.getItemMeta().hasLore() == true) { //Если у предмета есть изменённое имя и лор
                    if (item.getItemMeta().getLore().get(0).equals(ChatColor.GRAY + "Белый шоколад... Как вкусно!")) { //Если лор равен "Белый шоколад... Как вкусно!"
                        if (item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Молочный шоколадный батончик")) { //Если название равно "Молочный шоколадный батончик"
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
        p.setFoodLevel(p.getFoodLevel() + 3); //Добавляем 3 единицы сытости игроку
        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 2)); //Добавляем игроку эффект скорости 2 на 30 секунл
    }
}
