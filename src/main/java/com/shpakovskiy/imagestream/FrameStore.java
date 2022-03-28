package com.shpakovskiy.imagestream;

public class FrameStore {
    private static final FrameStore instance = new FrameStore();
    private static byte[] currentFrame = null;

    private FrameStore() {
    }

    public static FrameStore getInstance() {
        return instance;
    }

    public byte[] getCurrentFrame() {
        return currentFrame;
    }

    public void updateCurrentFrame(byte[] currentFrame) {
        FrameStore.currentFrame = currentFrame;
    }
}