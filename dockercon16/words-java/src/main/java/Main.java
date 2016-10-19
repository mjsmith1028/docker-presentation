import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import net.codestory.http.WebServer;
import net.codestory.http.filters.log.LogRequestFilter;
import org.jongo.Jongo;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Random;

@SuppressWarnings("ALL")
public class Main {
    public static void main(String[] args) throws UnknownHostException {
        Supplier<WordResponse> verb = Suppliers.memoize(() -> randomWord("verbs"));
        Supplier<WordResponse> adjective = Suppliers.memoize(() -> randomWord("adjectives"));
        Supplier<WordResponse> noun = Suppliers.memoize(() -> randomWord("nouns"));

        new WebServer().configure(routes -> routes
                .filter(LogRequestFilter.class)
                .filter(AddHostName.class)
                .get("/verb", () -> {
                    return verb.get();
                })
                .get("/adjective", () -> {
                    return adjective.get();
                })
                .get("/noun", () -> {
                    return noun.get();
                })
        ).start();
    }

    private static WordResponse randomWord(String set) {
        Words words = new Words(createJongo(), set);

        switch (set) {
            case "nouns":
                words.addIfEmpty("developer", "application", "sandwich", "git branch", "president", "halloween", "tester", "elephant", "coffee", "laptop", "turtle", "whale");
                break;
            case "verbs":
                words.addIfEmpty("will drink", "ignores", "smashes", "fixes", "breaks", "walks towards", "loves", "helps", "pushes", "debugs", "merges", "dances with", "votes for", "eats with");
                break;
            case "adjectives":
                words.addIfEmpty("the exquisite", "a pink", "the rotten", "a broken", "the angry",  "the impressive", "an awesome", "the hairy", "a crazy", "the spooky");
                break;
        }

        Random random = new Random();
        List<String> all = words.all();
        String word = all.get(random.nextInt(all.size()));

        return new WordResponse(word, null);
    }

    private static Jongo createJongo() {
        DB db = new MongoClient("mongo:27017", new MongoClientOptions.Builder()
                .serverSelectionTimeout(2000)
                .build()).getDB("lab-docker");

        return new Jongo(db);
    }
}
