/*
 * This file is part of Titanium
 * Copyright (C) 2019, Horizon Studio <contact@hrznstudio.com>, All rights reserved.
 *
 * This means no, you cannot steal this code. This is licensed for sole use by Horizon Studio and its subsidiaries, you MUST be granted specific written permission by Horizon Studio to use this code, thinking you have permission IS NOT PERMISSION!
 */

package com.hrznstudio.titanium._test;

import com.hrznstudio.titanium.annotation.Save;
import com.hrznstudio.titanium.api.IItemStackQuery;
import com.hrznstudio.titanium.block.tile.TilePowered;
import com.hrznstudio.titanium.block.tile.inventory.PosInvHandler;
import com.hrznstudio.titanium.block.tile.progress.PosProgressBar;
import com.hrznstudio.titanium.client.gui.addon.EnergyBarGuiAddon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;

public class TileTwentyFourTest extends TilePowered implements ITickable {

    @Save
    private PosProgressBar bar;

    @Save
    private PosInvHandler first;
    @Save
    private PosInvHandler second;
    @Save
    private PosInvHandler third;

    public TileTwentyFourTest() {
        super(BlockTwentyFourTest.TEST);

        this.addInventory(first = new PosInvHandler("test", -120, 20, 1).setTile(this).setInputFilter(IItemStackQuery.ANYTHING.toSlotFilter()));
        this.addInventory(second = new PosInvHandler("test2", 80, 30, 1).setTile(this).setInputFilter(IItemStackQuery.ANYTHING.toSlotFilter()));
        this.addGuiAddonFactory(() -> new EnergyBarGuiAddon(4, 10, getEnergyStorage()));
        this.addProgressBar(bar = new PosProgressBar(20, 20, 500).setCanIncrease(tileEntity -> true).setOnFinishWork(() -> System.out.println("WOWOOW")));
        this.addInventory(third = new PosInvHandler("test3", 180, 30, 1).setTile(this).setInputFilter(IItemStackQuery.ANYTHING.toSlotFilter()));
    }

    @Override
    public boolean onActivated(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        openGui(playerIn);
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        if (!world.isRemote) {
            this.getEnergyStorage().receiveEnergy(10, false);
            markForUpdate();
        } else {

        }
    }
}