package ch.litexo.bomb.web.websocket;

import ch.litexo.de.bomb.graphics.BaseObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Andreas Hauschild
 */
@ServerEndpoint("/game")
public class GameSocket {
    private ExecutorService executor = Executors.newFixedThreadPool(10);
    private Runnable runnable;


    @OnOpen
    public void open(Session session) {

        runnable = new Runnable() {

            @Override
            public void run() {
                BaseObject circle = new BaseObject(50,50);
                ObjectMapper mapper = new ObjectMapper();
                while (true) {
                    try {
                        circle.setX(circle.getX()+10);
                        session.getBasicRemote().sendText(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(circle));
                        Thread.sleep(20);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        };

        executor.execute(runnable);
        System.out.println("Websocket opened");
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Websocket close");

    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("Websocket Error");
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        System.out.println("Message message");
    }
}
