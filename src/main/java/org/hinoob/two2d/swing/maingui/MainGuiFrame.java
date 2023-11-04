package org.hinoob.two2d.swing.maingui;

import org.hinoob.loom.ByteWriter;
import org.hinoob.two2d.TwodimensionalGame;
import org.hinoob.two2d.packet.outgoing.LoginAttempt;

import javax.swing.*;
import java.awt.*;

public class MainGuiFrame extends JPanel {

    public MainGuiFrame() {
        setPreferredSize(new Dimension(600, 75));
        setSize(new Dimension(600, 75));

        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(200, 35));
        username.setSize(new Dimension(200, 35));
        username.setText("Username");
        JTextField password = new JTextField();
        password.setPreferredSize(new Dimension(200, 35));
        password.setSize(new Dimension(200, 35));
        password.setText("Password");

        JLabel authenticating = new JLabel("Your credentials will be passed to the official server of twodimensionalgame");
        authenticating.setSize(new Dimension(600, 35));

        JButton login = new JButton("Login");
        login.addActionListener(e -> {
            TwodimensionalGame.getInstance().getPacketWriter().sendPacket(new LoginAttempt(username.getText(), password.getText()));
        });

        add(authenticating);
        add(username);
        add(password);
        add(login);
    }
}
