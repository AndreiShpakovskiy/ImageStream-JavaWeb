package com.shpakovskiy.imagestream;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class ImageReceiver {

    @RequestMapping(value = "/photo", method = RequestMethod.POST)
    private void acceptImage(InputStream imageStream) {

        try {
            File newFile = new File("" + System.currentTimeMillis() + ".bmp");
            newFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            fileOutputStream.write(IOUtils.toByteArray(imageStream));
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //try {
            //FrameStore.getInstance().updateCurrentFrame();
            //System.out.println("Smth: " + imageStream.available());
            //System.out.println("Lenght: " + .length);
        //} catch (IOException e) {
            //e.printStackTrace();
        //}

        System.out.println("Receiving new something");
    }
}