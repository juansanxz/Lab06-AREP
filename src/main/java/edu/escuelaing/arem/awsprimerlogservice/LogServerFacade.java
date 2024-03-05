package edu.escuelaing.arem.awsprimerlogservice;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

/**
 * Hello world!
 *
 */
public class LogServerFacade
{
    private static final String LOG_SERVICE_URL = "http://localhost:5000/logservice";
    public static void main( String[] args )
    {
        staticFileLocation("/public");

        RemoteLogServiceInvoker remoteLogServiceInvoker = new RemoteLogServiceInvoker(LOG_SERVICE_URL);

        get("/logservicefacade", (req, res) -> {
            res.type("application/json");
            return remoteLogServiceInvoker.invoke(args) ;
        });
    }
}
