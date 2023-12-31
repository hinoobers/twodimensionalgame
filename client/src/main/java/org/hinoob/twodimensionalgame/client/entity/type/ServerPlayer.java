package org.hinoob.twodimensionalgame.client.entity.type;

import org.hinoob.twodimensionalgame.client.entity.Player;

import java.awt.*;

// Others, for us
public class ServerPlayer extends Player {

    public String displayName;

    @Override
    public void onDraw(Graphics graphics) {
        graphics.setFont(new Font("Arial", Font.PLAIN, 25));
        if(displayName == null) {
            displayName = "NULL";
        }
        graphics.drawString(displayName, posX, posY - 15);
        graphics.setColor(Color.RED);
        graphics.fillRect(this.posX, this.posY, width, height);
    }
}
