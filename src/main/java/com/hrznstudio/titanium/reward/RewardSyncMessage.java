package com.hrznstudio.titanium.reward;

import com.hrznstudio.titanium.network.Message;
import com.hrznstudio.titanium.reward.storage.ClientRewardStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.network.NetworkEvent;

public class RewardSyncMessage extends Message {

    private CompoundNBT compoundNBT;

    public RewardSyncMessage(CompoundNBT compoundNBT) {
        this.compoundNBT = compoundNBT;
    }

    public RewardSyncMessage() {

    }

    @Override
    protected void handleMessage(NetworkEvent.Context context) {
        Minecraft.getInstance().enqueue(() -> {
            ClientRewardStorage.REWARD_STORAGE.deserializeNBT(compoundNBT);
        });
    }
}
