package xyz.przemyk.fansmod.blocks;

import net.minecraft.entity.Entity;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

public class FanTile extends TileEntity implements ITickableTileEntity {

    public FanTile() {
        this(0, 0);
    }

    public FanTile(double fan_speed, double boxLength) {
        super(ModBlocks.FAN_TILE);

        FAN_SPEED = fan_speed;

        eastBox = new AxisAlignedBB(0, 0, 0, boxLength, 1, 1);
        westBox = new AxisAlignedBB(0, 0, 0, -boxLength, 1, 1);
        southBox = new AxisAlignedBB(0, 0, 0, 1, 1, boxLength);
        northBox = new AxisAlignedBB(0, 0, 0, 1, 1, -boxLength);
        upBox = new AxisAlignedBB(0, 0, 0, 1, boxLength, 1);
        downBox = new AxisAlignedBB(0, 0, 0, 1, -boxLength, 1);
    }

    private final double FAN_SPEED;

    private final AxisAlignedBB eastBox;
    private final AxisAlignedBB westBox;
    private final AxisAlignedBB southBox;
    private final AxisAlignedBB northBox;
    private final AxisAlignedBB upBox;
    private final AxisAlignedBB downBox;

    @Override
    public void tick() {
        if (world != null) {
            switch (getBlockState().get(BlockStateProperties.FACING)) {
                case EAST:
                    for (Entity entity : world.getEntitiesWithinAABB(Entity.class, eastBox.offset(pos))) {
                        entity.setMotion(entity.getMotion().add(FAN_SPEED, 0, 0));
                    }
                    break;
                case WEST:
                    for (Entity entity : world.getEntitiesWithinAABB(Entity.class, westBox.offset(pos))) {
                        entity.setMotion(entity.getMotion().add(-FAN_SPEED, 0, 0));
                    }
                    break;
                case SOUTH:
                    for (Entity entity : world.getEntitiesWithinAABB(Entity.class, southBox.offset(pos))) {
                        entity.setMotion(entity.getMotion().add(0, 0, FAN_SPEED));
                    }
                    break;
                case NORTH:
                    for (Entity entity : world.getEntitiesWithinAABB(Entity.class, northBox.offset(pos))) {
                        entity.setMotion(entity.getMotion().add(0, 0, -FAN_SPEED));
                    }
                    break;
                case UP:
                    for (Entity entity : world.getEntitiesWithinAABB(Entity.class, upBox.offset(pos))) {
                        entity.setMotion(entity.getMotion().add(0, FAN_SPEED, 0));
                    }
                    break;
                case DOWN:
                    for (Entity entity : world.getEntitiesWithinAABB(Entity.class, downBox.offset(pos))) {
                        entity.setMotion(entity.getMotion().add(0, -FAN_SPEED, 0));
                    }
                    break;
            }
        }
    }
}
