package com.spontorg

import com.github.dragon66.AnimatedGIFWriter
import grails.transaction.Transactional

    import reactor.bus.EventBus
    import reactor.spring.context.annotation.Consumer
    import reactor.spring.context.annotation.Selector

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

@Consumer
    class EventHandlerService {

        static transactional = false
        public String baseFolder = ""
//        EventBus eventBus

        @Selector("appEvents")
        void sendPushMessage(File[] message) {
            println(" \n\n void sendPushMessage(String message) =" +message)
            createAnimatedGif(message)
        }

    def createAnimatedGif(File[] imageFileList){
        println("working createAnimatedGif ")
        // True for dither. Will use more memory and CPU
        baseFolder = "C:\\grdev\\gr-base\\src\\main\\webapp\\images\\" // imageFileList[0].canonicalPath;
        println("working createAnimatedGif ... baseFolder= "+baseFolder)
        AnimatedGIFWriter writer = new AnimatedGIFWriter(false);

        try {

            OutputStream os = new FileOutputStream(baseFolder + "animated.gif");
            // Grab the BufferedImage whatever way you can
            BufferedImage frame;
            // Use -1 for both logical screen width and height to use the first frame dimension
            imageFileList.each { def f ->
                println("name=" + f.name)
                //println(" ready=" + f.absoluteFile)
                frame = ImageIO.read(f)
                println("frame ...:" + frame.width + " height=" + frame.height)
                writer.prepareForWrite(os, frame.width, frame.height)
                println("writeframe")
                writer.writeFrame(os, frame);
            }

            // Keep adding frame here
            writer.finishWrite(os);
        }
        catch(Exception exception){
            println("YOU SUCK" + exception)
            exception.printStackTrace()
        }
    }

    }
