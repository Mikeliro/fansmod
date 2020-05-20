package xyz.przemyk.fansmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RedstoneFanBlock extends FanBlock {

    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public RedstoneFanBlock(double fan_speed, double boxLength) {
        super(fan_speed, boxLength);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new RedstoneFanTile(FAN_SPEED, BOX_LENGTH);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.with(FACING, getFacingFromEntity(pos, placer)).with(BlockStateProperties.POWERED, worldIn.isBlockPowered(pos)), 2);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isRemote) {
            if (state.get(BlockStateProperties.POWERED) != worldIn.isBlockPowered(pos)) {
                worldIn.setBlockState(pos, state.with(BlockStateProperties.POWERED, worldIn.isBlockPowered(pos)), 2);
            }
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(POWERED);
    }
}