package xyz.przemyk.fansmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class FanBlock extends Block {

    public FanBlock(double fan_speed, double boxLength) {
        super(Properties.create(Material.PISTON)
                .sound(SoundType.WOOD)
                .hardnessAndResistance(2.0f)
        );

        FAN_SPEED = fan_speed;
        BOX_LENGTH = boxLength;
    }

    private final double FAN_SPEED;
    private final double BOX_LENGTH;

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (placer != null) {
            worldIn.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, placer)), 2);
        }
    }

    public static Direction getFacingFromEntity(BlockPos blockPos, LivingEntity entity) {
        return Direction.getFacingFromVector(entity.getPosX() - blockPos.getX(),
                entity.getPosY() - blockPos.getY(), entity.getPosZ() - blockPos.getZ());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FanTile(FAN_SPEED, BOX_LENGTH);
    }
}
