package xyz.przemyk.fansmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import xyz.przemyk.fansmod.tiles.ConfigurableFanTile;

import javax.annotation.Nullable;

public class ConfigurableFanBlock extends FanBlock {

    /**
     * See {@link xyz.przemyk.fansmod.FansMod#FansMod()}
     */
    public static IntegerProperty LEVEL;

    @Override
    public ConfigurableFanTile createTileEntity(BlockState state, IBlockReader world) {
        return new ConfigurableFanTile();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.with(FACING, getFacingFromEntity(pos, placer)).with(LEVEL, 0), 2);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(LEVEL);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.isCrouching()) {
            worldIn.setBlockState(pos, state.with(LEVEL, 0), 2);
        } else {
            worldIn.setBlockState(pos, state.func_235896_a_(LEVEL), 2);
        }

//        ((ConfigurableFanTile) worldIn.getTileEntity(pos)).update();
        return ActionResultType.SUCCESS;
    }
}
