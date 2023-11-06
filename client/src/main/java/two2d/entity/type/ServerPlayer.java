package two2d.entity.type;

import two2d.entity.Player;

import java.awt.*;

// Others, for us
public class ServerPlayer extends Player {

    public String displayName;

    @Override
    public void onDraw(Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.PLAIN, 25));
        graphics.drawString(displayName, posX, posY - 15);
        graphics.setColor(Color.RED);
        graphics.fillRect(this.posX, this.posY, width, height);
    }
}
