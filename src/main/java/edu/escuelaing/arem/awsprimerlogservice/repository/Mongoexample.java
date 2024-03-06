package edu.escuelaing.arem.awsprimerlogservice.repository;

import com.mongodb.client.MongoDatabase;

import java.util.Date;


public class Mongoexample {


        public static void main(String[] args) {
            MongoDatabase database = MongoUtil.getDB();
            LogDAO logDAO = new LogDAO(database);

            // Create a new user
            logDAO.addLog(new Date(), "hola");

            // List users
            logDAO.listLogs();

            // Update user
            logDAO.updateLog(new Date(), "hola");

                // Delete user
            logDAO.deleteLog("hola");
        }

}
