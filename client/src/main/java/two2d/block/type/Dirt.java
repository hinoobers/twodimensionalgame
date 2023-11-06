package two2d.block.type;

import two2d.block.Block;
import two2d.block.BlockType;

import java.awt.*;

public class Dirt extends Block {

    public Color color = new Color(150, 75, 0);

    public Dirt() {
        this.type = BlockType.DIRT;
    }

    @Override
    public void onDraw(Graphics graphics) {
        graphics.setColor(color);
        super.onDraw(graphics);
        graphics.setColor(color.darker());
        graphics.drawRect(this.x, this.y, 20, 20);
    }
}
