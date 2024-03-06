package edu.escuelaing.arem.awsprimerlogservice;

import com.mongodb.client.MongoDatabase;
import edu.escuelaing.arem.awsprimerlogservice.repository.LogDAO;
import edu.escuelaing.arem.awsprimerlogservice.repository.MongoUtil;

import java.util.Date;

import static spark.Spark.*;

public class LogService {
    public static void main( String[] args )
    {
        MongoDatabase database = MongoUtil.getDB();
        LogDAO logDAO = new LogDAO(database);

        port(5001);
        get("/logservice", (req, res) -> {
            Date savingTime = new Date();
            res.type("application/json");
            logDAO.addLog(savingTime, req.queryParams("msg"));
            return logDAO.listLogs();
        });
    }
}
