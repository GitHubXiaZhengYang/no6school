package com.straight_ocean.coin;

import net.minecraft.nbt.NbtCompound;

// 1. 定义货币数据组件
public class Coin {
    private long balance;

    public Coin(long initialBalance) {
        this.balance = initialBalance;
    }

    public long getBalance() {
        return balance;
    }

    public void add(long amount) {
        balance = Math.max(0, balance + amount);
    }

    public boolean trySpend(long amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // NBT序列化方法
    public static Coin fromNbt(NbtCompound tag) {
        return new Coin(tag.getLong("balance"));
    }

    public void writeNbt(NbtCompound tag) {
        tag.putLong("balance", balance);
    }
}
