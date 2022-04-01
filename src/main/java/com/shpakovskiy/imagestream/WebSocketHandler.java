package com.shpakovskiy.imagestream;

import com.shpakovskiy.imagestream.data.FrameStore;
import com.shpakovskiy.imagestream.data.WeirdLogger;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WebSocketHandler extends AbstractWebSocketHandler {
    private final Set<WebSocketSession> activeSessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        WeirdLogger.info("New websocket connection: " + session);

        activeSessions.add(session);

        new Thread(() -> {
            while (activeSessions.contains(session)) {
                try {
                    session.sendMessage(
                            new BinaryMessage(
                                    FrameStore.getInstance().getCurrentFrame()
                            )
                    );
                } catch (IOException e) {
                    WeirdLogger.error(Arrays.toString(e.getStackTrace()));
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    WeirdLogger.error(Arrays.toString(e.getStackTrace()));
                }
            }
        }).start();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        WeirdLogger.info("Connection closed: " + session + " with status " + session);
        activeSessions.remove(session);
    }
}