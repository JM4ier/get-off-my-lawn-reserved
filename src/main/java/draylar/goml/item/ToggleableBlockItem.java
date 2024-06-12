package draylar.goml.item;

import eu.pb4.polymer.core.api.block.PolymerHeadBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.BooleanSupplier;

public class ToggleableBlockItem extends TooltippedBlockItem {
    private final BooleanSupplier isEnabled;

    public <T extends Block & PolymerHeadBlock> ToggleableBlockItem(T block, Settings settings, int lines, BooleanSupplier isEnabled) {
        super(block, settings, lines);
        this.isEnabled = isEnabled;
    }

    @Override
    protected boolean canPlace(ItemPlacementContext context, BlockState state) {
        return isEnabled.getAsBoolean()
                ? super.canPlace(context, state)
                : false;
    }

    public boolean isEnabled() {
        return this.isEnabled.getAsBoolean();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (isEnabled.getAsBoolean()) {
            super.appendTooltip(stack, context, tooltip, type);
        } else {
            tooltip.add(Text.translatable(String.format("text.goml.disabled_augment")).formatted(Formatting.RED, Formatting.BOLD));
        }
    }
}
