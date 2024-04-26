package JDBC_Study;

public class Dersler {//pojo class

    // fieds -> column
   private   int id;
   private String ders_isim;
   private int egitim_suresi;
   // constructor

    public Dersler(int id, String ders_isim, int egitim_suresi) {//zengin full parametirzed telescopic cons.
        this.id = id;
        this.ders_isim = ders_isim;
        this.egitim_suresi = egitim_suresi;
    }

    // getter setter meth.


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDers_isim() {
        return ders_isim;
    }

    public void setDers_isim(String ders_isim) {
        this.ders_isim = ders_isim;
    }

    public int getEgitim_suresi() {
        return egitim_suresi;
    }

    public void setEgitim_suresi(int egitim_suresi) {
        this.egitim_suresi = egitim_suresi;
    }
    // toString meth.

    @Override
    public String toString() {
        return
                "id=" + id +
                ", ders_isim='" + ders_isim + '\'' +
                ", egitim_suresi=" + egitim_suresi;
    }
}
