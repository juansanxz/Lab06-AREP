package edu.escuelaing.arem.awsprimerlogservice;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class LogServerFacade
{
    private static final String[] LOG_SERVICE_URL = {"http://logservice1:46001/logservice", "http://logservice2:46002/logservice", "http://logservice3:46003/logservice"};
    public static void main( String[] args )
    {
        port(getPort());

        staticFileLocation("/public");

        RemoteLogServiceInvoker remoteLogServiceInvoker = new RemoteLogServiceInvoker(LOG_SERVICE_URL);

        get("/logservicefacade", (req, res) -> {
            res.type("application/json");
            return remoteLogServiceInvoker.invoke(req.queryParams("msg")) ;
        });


    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
