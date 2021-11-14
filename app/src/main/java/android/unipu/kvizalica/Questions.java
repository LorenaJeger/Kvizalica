package android.unipu.kvizalica;

public class Questions {

    private String pitanje;
    private String odgovor1;
    private String odgovor2;
    private String odgovor3;
    private String odgovor4;

    private int answerNumber;

    public Questions(){}

    public Questions(String pitanje, String odgovor1, String odgovor2, String odgovor3, String odgovor4, int answerNumber) {
        this.pitanje = pitanje;
        this.odgovor1 = odgovor1;
        this.odgovor2 = odgovor2;
        this.odgovor3 = odgovor3;
        this.odgovor4 = odgovor4;
        this.answerNumber = answerNumber;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getOdgovor1() {
        return odgovor1;
    }

    public void setOdgovor1(String odgovor1) {
        this.odgovor1 = odgovor1;
    }

    public String getOdgovor2() {
        return odgovor2;
    }

    public void setOdgovor2(String odgovor2) {
        this.odgovor2 = odgovor2;
    }

    public String getOdgovor3() {
        return odgovor3;
    }

    public void setOdgovor3(String odgovor3) {
        this.odgovor3 = odgovor3;
    }

    public String getOdgovor4() {
        return odgovor4;
    }

    public void setOdgovor4(String odgovor4) {
        this.odgovor4 = odgovor4;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }
}
