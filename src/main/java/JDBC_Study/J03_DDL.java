package JDBC_Study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class J03_DDL {
    /*
                   A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet)
                      dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC’de 2 alternatif bulunmaktadir.
                         1) execute() metodu
                         2) excuteUpdate() metodu.

                   B)   - execute() metodu her tur SQL ifadesiyle kullanibilen genel bir komuttur.
                        - execute(), Boolean bir deger dondurur.
                        - DDL islemlerin false dondururken, DML islemlerinde true deger dondurur.(ResultSet olusturur)
                        - Ozellikle hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi
                          durumlarda tercih edilmektedir.

                   C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
                      -  bu islemlerde islemden etkilenen satir sayisini return eder.
                      - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 return eder.

                    execute() her turlu SQL kjomutuyla kullanilir .......  DDL ---> False    DML----> True
                    executeUpdate()  DDL ----> 0           DML ----->etkilenen satir sayisini verir
               */
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345678"); //database baglantisi icin
        Statement statement = connection.createStatement();//sql sorgu(query) calistirmak (execute etmek) icin statement tanımlandı

        System.out.println("   ***   task01   ***   ");
        // Task01-> markalar adında bir tablo oluşturunuz. marka_id int, marka_isim VARCHAR(15), calisan_sayisi int
        String query = "CREATE TABLE markalar (marka_id int, marka_isim VARCHAR(15), calisan_sayisi int )";
        // 1.yontem : execute () methodu ile
       // boolean s1 = statement.execute(query);
      // System.out.println("markalar tablosu create edildi -> " + statement.execute(query));// false
      //  System.out.println("markalar tablosu create edildi -> " + s1);// false
        // execute();-> Boolean DDL:false DML:True bir deger return eder.
        //   DDL islemlerin false dondururken(ResultSet olusturmadigi icin),  DML islemlerinde true deger dondurur.(ResultSet olusturur)

        //2.yontem : executeUpdate() methodu ile
       //  int s2= statement.executeUpdate(query);
       // System.out.println("markalar tablosu create edildi -> " + statement.execute(query));// 0

        System.out.println("   ***   task02   ***   ");
      // Task02->  markalar tablosunu siliniz
       // System.out.println("markalar tablosu silindi -> " + statement.executeUpdate("drop table markalar"));// 0
          System.out.println("   ***   task03   ***   ");
        // Task03-> markalar tablosuna yeni bir sutun {sube_sayisi int} ekleyiniz.
           String alterQuery = "ALTER table markalar ADD sube_sayisi int";
          // statement.execute(alterQuery);
          // System.out.println("markalar tablosuna  sube_sayisi int eklendi -> " + statement.execute("alter table  markalar add sube_sayisi int"));

        System.out.println("   ***   task04   ***   ");
       // Task04-> markalar tablosuna yeni bir sutun {sube_sayisi int} ekleyiniz, ancak bu sutunun yeri marka_id den sonra olsun
        // System.out.println("markalar tablosuna  sube_id int eklendi -> " + statement.execute("alter table  markalar add sube_id int after marka_id"));

        System.out.println("   ***   task5   ***   ");
        // Task05-> markalar tablosunun adini  brands olarak degistiriniz
       //  System.out.println("markalar tablosunun adini  brands olarak degistirildi -> "+statement.execute("alter table markalar rename to brands"));
        System.out.println("   ***   task06   ***   ");
         // Task06-> markalar tablosunda marka_isim sutununu isim olarak degistiriniz
       // System.out.println(" marka_isim sutununu isim olarak degistirildi -> "+statement.execute("alter table brands  rename  column marka_isim to isim"));

        System.out.println("   ***   task07   ***   ");
       // Task07-> markalar tablosunda isim sutununun data type ini char(20) olarak degistiriniz
        System.out.println("isim sutununun data type ini char(20) olarak degistirildi ->" + statement.execute("alter table brands modify isim char(20)"));

        connection.close();
        statement.close();
    }
    }
