
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

import webservices.AuthenticationEndPoint;
import webservices.UniteEnsWS;
import webservices.ModuleWS;
import filtres.AuthenticationFilter;

@ApplicationPath("/api")
public class Index extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(AuthenticationEndPoint.class);
        classes.add(UniteEnsWS.class);
        classes.add(ModuleWS.class);
        classes.add(AuthenticationFilter.class);

        return classes;
    }
}
