package com.hrznstudio.titanium.reward.storage;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.UUID;

public class ClientRewardStorage implements INBTSerializable<CompoundNBT> {

    public static final ClientRewardStorage REWARD_STORAGE = new ClientRewardStorage();

    private HashMap<UUID, EnabledRewards> rewards;

    public ClientRewardStorage() {
        this.rewards = new HashMap<>();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return null;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        CompoundNBT compoundNBT = nbt.getCompound(RewardWorldStorage.NAME);
        rewards.clear();
        compoundNBT.keySet().forEach(s -> {
            EnabledRewards rewards = new EnabledRewards();
            rewards.deserializeNBT(compoundNBT.getCompound(s));
            this.rewards.put(UUID.fromString(s), rewards);
        });
    }

    public HashMap<UUID, EnabledRewards> getRewards() {
        return rewards;
    }
}
