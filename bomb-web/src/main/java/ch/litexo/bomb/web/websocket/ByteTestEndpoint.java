package ch.litexo.bomb.web.websocket;

import ch.litexo.de.bomb.graphics.BaseObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Andreas Hauschild
 */
@ServerEndpoint("/byteTestEndpoint")
public class ByteTestEndpoint {
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private Runnable runnable;

    public static boolean clientReady = true;

    @OnOpen
    public void open(Session session) {

        runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    BaseObject circle = new BaseObject(50, 50);
                    ObjectMapper mapper = new ObjectMapper();
                    List<Path> images = Files.list(Paths.get("C:\\temp\\_01-Temp-06.02.2018\\_cam_test"))
                            .filter(path -> {
                                if (path.toFile().isFile()) {
                                    return path.toFile().getName().matches("(?ims).*[.]jpg");
                                }
                                return false;
                            }).collect(Collectors.toList());
                    images.sort((o1, o2) -> {
                       int val1 = Integer.parseInt(o1.toFile().getName().replaceAll("[^0-9]",""));
                       int val2 = Integer.parseInt(o2.toFile().getName().replaceAll("[^0-9]",""));
                       return Integer.compare(val1,val2);
                    });

                    List<ImageObject> data = new ArrayList<>();

                    for (Path image : images.subList(0, 1000)) {
                        data.add(new ImageObject(
                                "jpg",
                                Files.readAllBytes(image),
                                image.toFile().getName()
                        ));
                    }
                    while (session.isOpen()) {
                        for (ImageObject img : data) {
                            if(clientReady){
                                System.out.println("Sending "+ img.getTimeStamp());
                                session.getBasicRemote().sendBinary(ByteBuffer.wrap(img.getBytes()));
                                clientReady =false;
                            }
                            Thread.sleep(30);
                        }

                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        };

        executor.execute(runnable);
        System.out.println("Websocket opened");
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Websocket close");
        clientReady = true;

    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("Websocket Error");
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        if(message.equalsIgnoreCase("ready")){
            clientReady=true;
        }
    }
}
