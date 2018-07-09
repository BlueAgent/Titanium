/*
 * This file is part of Titanium
 * Copyright (C) 2018, Horizon Studio <contact@hrznstudio.com>, All rights reserved.
 */
package com.hrznstudio.titanium._test;

import com.hrznstudio.titanium.api.IItemStackQuery;
import com.hrznstudio.titanium.block.tile.TileBase;
import com.hrznstudio.titanium.inventory.PosInvHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;

public class TileTest extends TileBase {

    public TileTest() {
        this.addInventory(new PosInvHandler("test", 20, 20, 3).setTile(this).setInputFilter((stack, integer) -> IItemStackQuery.ANYTHING.test(stack)));
        this.addInventory(new PosInvHandler("test2", 80, 30, 6).setTile(this).setRange(3, 2).setInputFilter((stack, integer) -> stack.isItemEqual(new ItemStack(Blocks.COBBLESTONE))));
    }

    @Override
    public boolean onActivated(EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            openGui(playerIn);
        }
        return true;
    }
}