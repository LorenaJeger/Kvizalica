package android.unipu.kvizalica;

import java.util.ArrayList;
import java.util.List;

public class QuestionsBlank {

    private static List<QuestionList> voce() {
        final List<QuestionList> questionLists = new ArrayList<>();

        final QuestionList question1 = new QuestionList("Koje voce je na slici", "banana", "jabuka", "kivi", "jagoda", "jagoda", "");
        questionLists.add(question1);
        return questionLists;

    }

    private static List<QuestionList> povrce(){
        final List<QuestionList> questionLists = new ArrayList<>();

        final QuestionList question1 = new QuestionList("Koje povrce je na slici", "banana", "mrkva", "kivi", "jagoda", "mrkva", "");
        questionLists.add(question1);
        return questionLists;

    }


    public static List<QuestionList> getQuestions(String selectedTopicName) {
        switch (selectedTopicName) {
            case "voce":
                return voce();
            case "povrce":
                return povrce();

        }}


}