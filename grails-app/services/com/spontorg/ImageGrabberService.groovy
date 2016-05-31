package com.spontorg

import com.github.dragon66.AnimatedGIFWriter
import grails.transaction.Transactional
import reactor.bus.Event

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.InputStream


/**
 * service for handling image writing and assembly
 *
 * MAYBE WE SHOULD USE EVENTS?
 * http://grailsrocks.github.io/grails-platform-core/guide/events.html
 *
 */

@Transactional
class ImageGrabberService {

    public String baseFolder = ""

    def serviceMethod() {
        println("HEY!!! image grab asser   http://wx.wkbn.com/weather/warren-ohio-weather-camera.jpg")
        println("http://ftpcontent.worldnow.com/wfmj/TOWERCAM/niles.jpg")
    }


    //use method name 'mailRegistration' as topic name
    //can also use custom topic name using topic arg: @Listener(topic='test')
    def publishEvent(File[] imageList1){
        println("published event "+ imageList1)

        def key = "appEvents"
        def message = UUID.randomUUID().toString()
        eventBus.notify key, Event.wrap(imageList1)
    }


    def captureImage(String address = "http://wx.wkbn.com/weather/warren-ohio-weather-camera.jpg"){
        String currentDateTime = new Date().getTime().toString()

        def newfile =null
        newfile= new File(baseFolder+"/images/warren-ohio-weather-camera${currentDateTime}.jpg").withOutputStream { out -> out << new URL(address).openStream()}
        println("newfile="+ newfile)
    return newfile
    }
//            public void download(def address) {
//                new File("${address.tokenize('/')[-1]}.png").withOutputStream { out ->
//                    out << new URL(address).openStream()
//                }

//        URL url = new URL("http://upload.wikimedia.org/wikipedia/commons/9/9c/Image-Porkeri_001.jpg");
//        InputStream in = new BufferedInputStream(url.openStream());
//        OutputStream out = new BufferedOutputStream(new FileOutputStream("Image-Porkeri_001.jpg"));
//
//        for ( int i; (i = in.read()) != -1; ) {
//            out.write(i);
//        }
//        in.close();
//        out.close();


    def createAnimatedGif(File[] imageFileList){
        // True for dither. Will use more memory and CPU
        AnimatedGIFWriter writer = new AnimatedGIFWriter(true);
        OutputStream os = new FileOutputStream(baseFolder+"animated.gif");
        // Grab the BufferedImage whatever way you can
        BufferedImage frame;
        // Use -1 for both logical screen width and height to use the first frame dimension
        imageFileList.each{def f ->
            println("name="+f.name)
            frame = ImageIO.read(f)
            println("frame ...:"+ frame.width + " height=" +frame.height)
            writer.prepareForWrite(os, frame.width/2, frame.height/2)
            writer.writeFrame(os, frame, 0.5);
        }

        // Keep adding frame here
        writer.finishWrite(os);
    }
}
