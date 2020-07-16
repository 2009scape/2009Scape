package plugin.trivia;

import core.game.system.SystemLogger;
import core.tools.RandomFunction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TriviaQuestions {
    private static final String qfile = System.getProperty("user.dir") + "/src/main/java/Server/plugin/trivia/questions.txt";
    private static List<TriviaQuestion> questions = new ArrayList<>();

    public static TriviaQuestion getQuestion(){
        int index = RandomFunction.random(questions.size());
        return questions.get(index);
    }

    public static void loadQuestions(){
        String s;
        int count = 0;
        try {
            InputStream inp = new FileInputStream(qfile);
            BufferedReader rd = new BufferedReader(new InputStreamReader(inp));
            while((s = rd.readLine()) != null) {
                String[] args = s.split("::");
                questions.add(new TriviaQuestion(args[0], args[1]));
                count++;
            }
            SystemLogger.log("Successfully loaded " + count + " questions from trivia file " + qfile);
        } catch (Exception e){
            SystemLogger.log("Couldn't find trivia questions file: " + qfile);
        }
    }
}
