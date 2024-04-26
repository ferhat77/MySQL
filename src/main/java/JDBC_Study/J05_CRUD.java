package JDBC_Study;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class J05_CRUD {
    /*
    Cok miktarda kayit eklemek icin PreparedStatement metodu kullanilabilir.
    PreparedStatement hem hizli hem de daha guvenli (SQL injection saldirilari icin) bir yontemdir.
    ResultSet tum sonucları bellekte tutuyor.. banka hesapları vs hassas datalar için  ama guvenli degil..
    bu yuzden database in guvenligi acisindan PreparedStatement tercih edilir..
    Bunun icin; Data insert'e uygun bir POJO(Plain Old Java Object) class'i obj ile datalar db'ye insert edilir.

    PreparedStatement : Statement interface'ini extend eder.
    Statement ile olusturdugumuz sorgu icin Db'de bellekte sorgunun bir örnegi olusturulur.
    Sorgu her calistirildiginda yeni bir örnegi olusturulur.
    PreparedStatement : Birden fazla kez calistirilabilen parametrelendirilmis SQL sorgularini temsil eder.
    PreparedStatement ile sorgu olusturudumuzda, bu sorgunun örnegi DB'de bellekte tutulur,
    sorgu her çalıştırıldığında aynı önceki örneği kullanılır.

    Bir SORU :writing_hand:
-->Java JDBC de Statement ve PreparedStatement kullanımları  arasında ne fark var?
Bir CEVAP  :clipboard:
--> Statement: Statik SQL sorgularını çalıştırmak için kullanılır. Dinamik parametre geçişi yoktur.
 Bir sorgu çok defa çalıştırılmayacaksa kullanılması mantıklıdır.
--> PreparedStatement: Dinamik SQL sorgularını çalıştırmak için kullanılır.
Yani sql sorgularımıza parametre geçişi yapabiliriz. Bir sorgu çok defa çalıştırılcaksa kullanılması mantıklıdır.
Çünkü Statement nesnesi ile sorgu her çalıştırıldığında derlenirken PreparedStatement nesnesi sorguyu tek bir kez derler.
 */


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345678"); //database baglantisi icin
        Statement statement = connection.createStatement();//sql sorgu(query) calistirmak (execute etmek) icin
        // Task- > dersler adında bir table create edip (id int, ders_isim varchar(15), egitim_suresi int)
        String queryCreate = "create table dersler (id int, ders_isim varchar(15), egitim_suresi int)";
         statement.execute(queryCreate);//table create edildi
         System.out.println("dersler table create edildi");

        List<Dersler> dersList = new ArrayList<>();
        dersList.add(new Dersler(101, "java", 250));
        dersList.add(new Dersler(102, "selenium", 100));
        dersList.add(new Dersler(103, "jdbc", 6));
        dersList.add(new Dersler(104, "sql", 24));
        dersList.add(new Dersler(105, "api", 36));
        dersList.add(new Dersler(106, "SDLC", 21));

        System.out.println("dersList = " + dersList);

        PreparedStatement table = connection.prepareStatement("insert into dersler values(?,?,?)");
        for (Dersler avuc : dersList) {
            table.setInt(1, avuc.getId());
            table.setString(2, avuc.getDers_isim());
            table.setInt(3, avuc.getEgitim_suresi());
            table.addBatch();// table statement  girilen obj value'lari mavi zemin(query group) coklu seçim yapıldı
        }
     table.executeBatch();// table statement(kumanda) obj value'leri mavi zemin(çoklu seçim)  şimşeklendi (group execute)
//        System.out.println("Agam bu output aldıysan code cincix :) DEWAMKEEE...");
}
}
