package edu.escuelaing.arem.awsprimerlogservice;

import com.mongodb.client.MongoDatabase;
import edu.escuelaing.arem.awsprimerlogservice.repository.LogDAO;
import edu.escuelaing.arem.awsprimerlogservice.repository.MongoUtil;

import java.util.Date;

import static spark.Spark.*;

public class LogService {
    public static void main( String[] args )
    {
        port(getPort());
        MongoDatabase database = MongoUtil.getDB();
        LogDAO logDAO = new LogDAO(database);

        get("/logservice", (req, res) -> {
            Date savingTime = new Date();
            res.type("application/json");
            logDAO.addLog(savingTime, req.queryParams("msg"));
            return logDAO.listLogs();
        });


    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}