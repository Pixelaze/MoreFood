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

public class ChocolateCake implements Listener {

    MoreFood mf;
    public ChocolateCake(MoreFood mf) {
        this.mf = mf;
    }

    @EventHandler
    public void use(PlayerInteractEvent e) {
        Player p = e.getPlayer(); //Находим игрока из события
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) { //Если действие - правый клик
            if(p.getInventory().getItemInMainHand() != null) {
                if (p.getInventory().getItemInMainHand().getType().equals(Material.CAKE)) { //Если предмет равен торту
                    ItemStack item = p.getInventory().getItemInMainHand();
                    if(item.getItemMeta() != null) {
                        if (item.getItemMeta().hasDisplayName() == true && item.getItemMeta().hasLore() == true) { //Если у предмета есть изменённое имя и лор
                            if (item.getItemMeta().getLore().get(0).equals(ChatColor.GRAY + mf.getConfig().getString("Chocolates.Chocolate_Cake_Lore"))) { //Если лор равен "Какой вкусный торт из шоколада!"
                                if (item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + mf.getConfig().getString("Chocolates.Chocolate_Cake"))) { //Если название равно "Шоколадный торт"
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
        p.setFoodLevel(20); //Ставим максимальный уровень сытости(ну торт же)
        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 2)); //Добавляем игроку эффект регенерации 2 на 30 секунл
    }
}
