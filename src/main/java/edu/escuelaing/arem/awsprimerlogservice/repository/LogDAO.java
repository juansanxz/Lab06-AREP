package edu.escuelaing.arem.awsprimerlogservice.repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class LogDAO {

    private final MongoCollection<Document> logsCollection;

    public LogDAO(MongoDatabase database) {
        this.logsCollection = database.getCollection("logs");
    }

    public void addLog(Date date, String msg) {
        Document newLog = new Document("log", date)
                .append("message", msg);
        logsCollection.insertOne(newLog);
    }

    public String listLogs() {
        List<String> jsonLogs = new ArrayList<>();
        FindIterable<Document> logs = logsCollection.find()
                .sort(Sorts.descending("log"))
                .limit(10);

        for (Document log : logs) {
            jsonLogs.add(log.toJson());
            System.out.println(log.toJson());
        }
        return "[" + String.join(",", jsonLogs) + "]";
    }

    public void updateLog(Date date, String msg) {
        logsCollection.updateOne(eq("message", msg), set("log", date));
    }

    public void deleteLog(String msg) {
        logsCollection.deleteOne(eq("message", msg));
    }
}
