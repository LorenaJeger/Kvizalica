package android.unipu.kvizalica;

public class ModelClass {

    String Question;
    String odgA;
    String odgB;
    String odgC;
    String odgD;
    String tocan;
    String slika;

    //kreiranje konstruktora
    public ModelClass(String question, String odgA, String odgB, String odgC, String odgD, String tocan, String slika) {
        Question = question;
        this.odgA = odgA;
        this.odgB = odgB;
        this.odgC = odgC;
        this.odgD = odgD;
        this.tocan = tocan;
        this.slika = slika;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOdgA() {
        return odgA;
    }

    public void setOdgA(String odgA) {
        this.odgA = odgA;
    }

    public String getOdgB() {
        return odgB;
    }

    public void setOdgB(String odgB) {
        this.odgB = odgB;
    }

    public String getOdgC() {
        return odgC;
    }

    public void setOdgC(String odgC) {
        this.odgC = odgC;
    }

    public String getOdgD() {
        return odgD;
    }

    public void setOdgD(String odgD) {
        this.odgD = odgD;
    }

    public String getTocan() {
        return tocan;
    }

    public void setTocan(String tocan) {
        this.tocan = tocan;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
}
