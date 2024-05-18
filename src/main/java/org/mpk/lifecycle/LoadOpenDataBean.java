package org.mpk.lifecycle;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.vertx.VertxContextSupport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import org.hibernate.reactive.mutiny.Mutiny;
import org.mpk.data.LoadDataFromGTFS;
import org.mpk.entity.Route;
import org.mpk.service.RouteService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class LoadOpenDataBean {


//    void loadRoutes(){
//        RouteService service = new RouteService();
//        Log.info("LoadOpenDataBean");
//        Path path = Paths.get("src/test/data/routes.txt");
//        LoadDataFromGTFS processor = new LoadDataFromGTFS();
//        List<Map<String, String>> parsedData = processor.parseTxtFile(path);
//        for (Map<String, String> entry : parsedData) {
//            Log.info(entry);
//        }
//        Route route = new Route("LoadOpenDataBeanTest");
//        Panache.withTransaction(route::persist);
//    }
    @Startup
    void onStart(@Observes StartupEvent event, Mutiny.SessionFactory sf) throws Throwable {
        LoadDataFromGTFS processor = new LoadDataFromGTFS();
        Path path = Paths.get("src/data/routes.txt");
        List<Map<String, String>> parsedData = processor.parseTxtFile(path);
        for (Map<String, String> entry : parsedData) {
            Route route = new Route();
            route.populateFromGTFS(entry);
            sf.withTransaction(session -> session.persist(route)).await().indefinitely();
        }
    }

}
