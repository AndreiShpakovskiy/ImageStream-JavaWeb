package com.shpakovskiy.imagestream;

import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebSocketHandler extends AbstractWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New websocket connection");

        new Thread(() -> {
            while (true) {
                try {
                    //session.sendMessage(new BinaryMessage());
                    session.sendMessage(
                            new BinaryMessage(
                                    FrameStore.getInstance().getCurrentFrame()
                                    //Files.readAllBytes(Paths.get("TestFrames" + File.separator + "1.jpg"))
                            )
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        new Thread(() -> {
            while (true) {
                for (int i = 0; i < 299; i++) {
                    try {
                        String frameName = "000000";
                        String frameNumber = String.valueOf(i);
                        frameName = frameName.substring(0, 6 - frameNumber.length()) + frameNumber;
                        session.sendMessage(new BinaryMessage(Files.readAllBytes(Paths.get("TestFrames" + File.separator + "Frame_" + frameName + ".jpeg"))));
                        Thread.sleep(1000 / 25);
                        System.out.println("Send #" + i);
                    } catch (IOException | InterruptedException e) {
                        try {
                            session.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }).start();
    }
     */
}