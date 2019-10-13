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
import ru.morefood.MoreFood;

public class DarkChocolateBar implements Listener {

    MoreFood mf;
    public DarkChocolateBar(MoreFood mf) {
        this.mf = mf;
    }

    @EventHandler
    public void use(PlayerInteractEvent e) {
        Player p = e.getPlayer(); //Находим игрока из события
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) { //Если действие - правый клик
            if(p.getInventory().getItemInMainHand() != null) {
                if (p.getInventory().getItemInMainHand().getType().equals(Material.NETHER_BRICK_ITEM)) { //Если предмет равен незер-кирпичу
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if(item.getItemMeta() != null) {
                        if (item.getItemMeta().hasDisplayName() == true && item.getItemMeta().hasLore() == true) { //Если у предмета есть изменённое имя и лор
                            if (item.getItemMeta().getLore().get(0).equals(ChatColor.DARK_GRAY + mf.getConfig().getString("Chocolates.Dark_Chocolate_Bar_Lore"))) { //Если лор равен "Белый шоколад... Как вкусно!"
                                if (item.getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + mf.getConfig().getString("Chocolates.Dark_Chocolate_Bar"))) { //Если название равно "Молочный шоколадный батончик"
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
        ItemStack item = p.getInventory().getItemInMainHand(); //Предмет в главной руке
        item.setAmount(item.getAmount() - 1); //-1 к кол-ву предметов
        p.getInventory().setItemInMainHand(item); //Установка значения
    }

    private void activate(Player p) {
        p.setFoodLevel(p.getFoodLevel() + 3); //Добавляем 3 единицы сытости игроку
        p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 2)); //Добавляем игроку эффект прыжка 2 на 30 секунл
    }
}
