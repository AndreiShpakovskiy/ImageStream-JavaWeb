package com.shpakovskiy.imagestream;

import com.shpakovskiy.imagestream.data.FrameStore;
import com.shpakovskiy.imagestream.data.WeirdLogger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ImageReceiver {

    private long lastPhotoReceivingTime = 0;
    private long fps = 0;

    @RequestMapping(value = "/photo", method = RequestMethod.POST)
    public ResponseEntity<String> acceptImage(@RequestBody byte[] imageBytes, HttpServletRequest request) {
        FrameStore.getInstance().updateCurrentFrame(imageBytes);

        WeirdLogger.info("New image ["
                + imageBytes.length + " bytes, "
                + imageBytes.length / 1024 + " kilobytes]" +
                " from " + request.getRemoteHost());

        long currentTime = System.currentTimeMillis();

        fps++;
        if (currentTime - lastPhotoReceivingTime > 1000) {
            lastPhotoReceivingTime = currentTime;
            WeirdLogger.info("FPS = " + fps);
            fps = 0;
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/log", method = RequestMethod.POST)
    private void acceptLog(@RequestBody String logMessage) {
        WeirdLogger.info(logMessage);
    }
}