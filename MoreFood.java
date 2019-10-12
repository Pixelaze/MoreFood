package ru.morefood;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import ru.morefood.Chocolates.ChocolateCake;
import ru.morefood.Chocolates.DarkChocolateBar;
import ru.morefood.Chocolates.HotChocolate;
import ru.morefood.Chocolates.MilkChocolateBar;

import java.util.ArrayList;
import java.util.List;

public final class MoreFood extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("MoreFood started! Thank you for downloading that :3"); //Стартовое сообщение

        milkChocolateBarCraft(); //Крафт Молочного шоколадного батончика
        Bukkit.getPluginManager().registerEvents(new MilkChocolateBar(), this); //Регистрируем ивенты
        System.out.println("MoreFood: Milk Chocolate Bar created!");

        darkChocolateBarCraft(); //Крафт Горького шоколадного батончика
        Bukkit.getPluginManager().registerEvents(new DarkChocolateBar(), this); //Регистрируем ивенты
        System.out.println("MoreFood: Dark Chocolate Bar created!");

        hotChocolateCraft(); //Крафт Горячего шоколада
        Bukkit.getPluginManager().registerEvents(new HotChocolate(), this); //Регистрируем ивенты
        System.out.println("MoreFood: Hot Chocolate created!");

        chocolateCakeCraft(); //Крафт Шоколадного торта
        Bukkit.getPluginManager().registerEvents(new ChocolateCake(), this); //Регистрируем ивенты
        System.out.println("MoreFood: Chocolate Cake created!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("MoreFood disabled. Thank you for using this :3"); //Конечное сообщение
    }

    private void milkChocolateBarCraft() {
        ItemStack item = new ItemStack(Material.IRON_INGOT); //Материал - железный слиток
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GRAY + "Молочный шоколадный батончик"); //Имя - Молочный шоколадный батончик

        List<String> lore = new ArrayList<>(); //Лор

        lore.add(ChatColor.GRAY + "Белый шоколад... Как вкусно!");

        meta.setLore(lore); //Установка лора
        item.setItemMeta(meta); //Установка мета-данных

        NamespacedKey key = new NamespacedKey(this, "Milk_Chocolate_Bar");

        ShapedRecipe s = new ShapedRecipe(key, item);
        s.shape(new String[] {"C C", "FMF", "C C"});
        s.setIngredient('C', Material.INK_SACK, 3); //Какао бобы
        s.setIngredient('F', Material.WHEAT); //Пшеница
        s.setIngredient('M', Material.MILK_BUCKET); //Ведро молока
        getServer().addRecipe(s);
    }

    private void darkChocolateBarCraft() {
        ItemStack item = new ItemStack(Material.NETHER_BRICK_ITEM); //Материал - адский кирпич
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.DARK_GRAY + "Горький шоколадный батончик"); //Имя - Горький шоколадный батончик

        List<String> lore = new ArrayList<>(); //Лор

        lore.add(ChatColor.DARK_GRAY + "Тёмный шоколад... сахар где?!");

        meta.setLore(lore); //Установка лора
        item.setItemMeta(meta); //Установка мета-данных

        NamespacedKey key = new NamespacedKey(this, "Dark_Chocolate_Bar");

        ShapedRecipe s = new ShapedRecipe(key, item);
        s.shape(new String[] {"C C", "FCF", "C C"});
        s.setIngredient('C', Material.INK_SACK, 3); //Какао бобы
        s.setIngredient('F', Material.WHEAT); //Пшеница
        getServer().addRecipe(s);
    }

    private void hotChocolateCraft() {
        ItemStack item = new ItemStack(Material.MILK_BUCKET); //Материал - Ведро молока
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GRAY + "Горячий шоколад"); //Имя - Горячий шоколад

        List<String> lore = new ArrayList<>(); //Лор

        lore.add(ChatColor.GRAY + "Только что из печи! Наверное...");

        meta.setLore(lore); //Установка лора
        item.setItemMeta(meta); //Установка мета-данных

        FurnaceRecipe s = new FurnaceRecipe(item, Material.INK_SACK, 3); //Печка переплавляет какао-боб получает горячий шоколад

        getServer().addRecipe(s);
    }

    private void chocolateCakeCraft() {
        ItemStack item = new ItemStack(Material.CAKE); //Материал - адский кирпич
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GRAY + "Шоколадный торт"); //Имя - Горький шоколадный батончик

        List<String> lore = new ArrayList<>(); //Лор

        lore.add(ChatColor.GRAY + "Какой вкусный торт из шоколада!");

        meta.setLore(lore); //Установка лора
        item.setItemMeta(meta); //Установка мета-данных

        NamespacedKey key = new NamespacedKey(this, "Chocolate_Cake");

        ShapedRecipe s = new ShapedRecipe(key, item);
        s.shape(new String[] {"MCM", "WSW", "CFC"});
        s.setIngredient('C', Material.INK_SACK, 3); //Какао бобы
        s.setIngredient('F', Material.WHEAT); //Пшеница
        s.setIngredient('M', Material.MILK_BUCKET); //Ведро молока
        s.setIngredient('W', Material.SUGAR); //Сахар
        s.setIngredient('S', Material.EGG); //Яйцо
        getServer().addRecipe(s);

    }
}
