package de.litexo.bomb.web.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Andreas Hauschild
 */
@ApplicationPath("rest")
public class RestApplication  extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(Ping.class);
        return s;
    }
}
