package android.unipu.kvizalica;
import android.provider.BaseColumns;

public class KvizContract {
    public KvizContract(){}
    public static class QuestionTable implements BaseColumns {

        public static final String TABLE_NAME = "kviz_pitanja";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1="option1";
        public static final String COLUMN_OPTION2="option2";
        public static final String COLUMN_OPTION3="option3";
        public static final String COLUMN_OPTION4="option4";
        public static final String COLUMN_ANSWER_NUMBER="ANSWER_NUMBER";


    }
}
