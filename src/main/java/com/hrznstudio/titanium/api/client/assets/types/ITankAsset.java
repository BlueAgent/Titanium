/*
 * This file is part of Titanium
 * Copyright (C) 2019, Horizon Studio <contact@hrznstudio.com>, All rights reserved.
 *
 * This means no, you cannot steal this code. This is licensed for sole use by Horizon Studio and its subsidiaries, you MUST be granted specific written permission by Horizon Studio to use this code, thinking you have permission IS NOT PERMISSION!
 */

package com.hrznstudio.titanium.api.client.assets.types;

import com.hrznstudio.titanium.api.client.IAsset;
import net.minecraft.util.EnumFacing;

public interface ITankAsset extends IAsset {

    int getFluidRenderPadding(EnumFacing facing);

}