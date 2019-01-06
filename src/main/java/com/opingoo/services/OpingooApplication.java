package com.opingoo.services;

import com.codahale.metrics.MetricRegistry;
import com.opingoo.utils.AppLogger;
import com.opingoo.utils.BaseUtils;
import de.spinscale.dropwizard.jobs.JobsBundle;
import io.dropwizard.Application;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.JDBCSessionIdManager;
import org.eclipse.jetty.server.session.JDBCSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.glassfish.jersey.filter.LoggingFilter;

import java.util.logging.Logger;

/**
 * @Author SmrutiRanjan(c99.smruti@gmail.com)
 *
 */
public class OpingooApplication extends Application<OpingooApplicationConfiguration> {

    private final MetricRegistry metricRegistry = new MetricRegistry();

    private static AppLogger logger = AppLogger.getLogger();
    private static final String CLASSNAME = "OpingooApplication";

    private static int serverPort = 8080;

    public static void main( String[] args ) throws Exception
    {
        String[] appConfArr = {"server","src/main/resources/config.yaml"};
        System.out.println( "Welcome to Opingoo!" );
        new OpingooApplication().run(appConfArr);
    }



    @Override
    public void initialize(Bootstrap<OpingooApplicationConfiguration>bootstrap){
        bootstrap.addBundle(new SwaggerBundle<OpingooApplicationConfiguration>(){
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(OpingooApplicationConfiguration opingooApplicationConfiguration) {

                return opingooApplicationConfiguration.swaggerBundleConfiguration;
            }
        });
        bootstrap.addBundle(new JobsBundle("com.opingoo.jobs"));
    }

    @Override
    public void run(OpingooApplicationConfiguration configuration, Environment environment) throws Exception{
        logger.logInfo(CLASSNAME,"Registering REST resources");


        if(BaseUtils.isResponseLoggingEnabled()) {
            // Enable the request / response login
            environment.jersey().register(new LoggingFilter(Logger.getLogger("InboundRequestResponse"), true));
            environment.jersey().register(new LoggingFilter(Logger.getLogger("OutboundRequestResponse"), true));
        }

        environment.jersey().register(new UserWebServices());


        JDBCSessionManager bcManager = new JDBCSessionManager();
        ServerFactory  serverFactory = configuration.getServerFactory();
        Server server = new Server(serverPort);
        JDBCSessionIdManager idManager = new JDBCSessionIdManager(server);

        idManager.setDatasource(configuration.getDataSourceFactory().build(metricRegistry, "mysql_session"));

        idManager.setWorkerName("pingo_session_mgr");
        idManager.setScavengeInterval(3600);

        SessionHandler sessionHandler = new SessionHandler();
        sessionHandler.setSessionManager(bcManager);
        String timeout = BaseUtils.getCommonProperty("SESSION_TIMOUT_SEC", "864000");
        int inactiveTimeout = Integer.decode(timeout);
        logger.logError(CLASSNAME, "Session inactiveTimeout is set to " + inactiveTimeout + " seconds");
        sessionHandler.getSessionManager().setSessionIdManager(idManager);

        sessionHandler.getSessionManager().setMaxInactiveInterval(inactiveTimeout);

        environment.servlets().setSessionHandler(sessionHandler);
        int val = sessionHandler.getSessionManager().getMaxInactiveInterval();
        System.out.println(val);

    }

}
