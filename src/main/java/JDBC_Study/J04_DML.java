package JDBC_Study;

import jdk.dynalink.linker.LinkerServices;

import java.sql.*;

public class J04_DML {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345678"); //database baglantisi icin
        Statement statement = connection.createStatement();//sql sorgu(query) calistirmak (execute etmek) icin


        System.out.println("   ***   task01   ***   ");
        // Task01-> Calisanlar tablosuna yeni bir kayit ((1005, 'Yıldız Hanım' , 33000)  ekleyelip kaydi teyit icin sorgulayınız.
       // System.out.println("statement.executeUpdate(\"insert into calisanlar values(1005, 'Yıldız Hanım' , 33000)\") = " + statement.executeUpdate("insert into calisanlar values(1005, 'Yıldız Hanım' , 33000)"));
        ResultSet rs=statement.executeQuery("select*from calisanlar;");
        while (rs.next()) {//table'den gelen record'ları tekrara alan loop tanımlandı
            System.out.println(rs.getInt(1)+"\t\t"+ rs.getString(2)+"\t\t"+
                    rs.getString(3));
        }
        // table son hali intelij de gorelim...


        System.out.println("   ***   task02   ***   ");
        // Task02-> Calisanlar tablosuna birden fazla yeni kayıt ekleyelim.

        // 1.YONTEM
        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin yavas yapilmasina yol acar.
        // 10000 tane veri kaydi yapildigi dusunuldugunde  bu kotu bir yaklasimdir.
        String[] insertQuery = {
                "insert into calisanlar values(1006, 'Can Bey' , 88000)",
                "insert into calisanlar values(1007, 'Sebnem Hanım' , 109000)",
                "insert into calisanlar values(1008, 'Sennur Hanım' , 101000)",
                "insert into calisanlar values(1009, 'Merve Hanım' , 133000)",
                "insert into calisanlar values(1010, 'Recep Bey' , 55000)"
        };
//        int insertSatirSayisi = 0;
//         for (String  avuc:insertQuery ) {//query depolanan arr takrara alındı
//            insertSatirSayisi+= statement.executeUpdate(avuc);// tekrardan gelen her bir arr elemanı olan query wexecute edild(şimşeklendi)
//         }
//        while (rs.next()) {//table'den gelen record'ları tekrara alan loop tanımlandı
//            System.out.println(rs.getInt(1)+"\t\t"+ rs.getString(2)+"\t\t"+
//                    rs.getString(3));
//        }
        // 2.YONTEM --> addBatch ve excuteBatch() metotlari ile
        // addBatch() -> metodu ile SQL ifadeleri gruplandirilabilir ve exucuteBatch()  metodu ile veritabanina bir kere gonderilebilir.
        // ***!!!!**** excuteBatch() metodu bir int [] dizi dondurur ve  Bu dizi her bir ifade sonucunda etkilenen satir sayisini return eder.
        String[] insertQuery1 = {
                "insert into calisanlar values(1011, 'Abdullah Bey' , 76000)",
                "insert into calisanlar values(1012, 'Sema Hanım' , 45000)",
                "insert into calisanlar values(1013, 'Ayse Hanım' , 105000)",
                "insert into calisanlar values(1014, 'Melek Hanım' , 82000)",
                "insert into calisanlar values(1015, 'Ferhat Bey' , 70000)"
        };
//         for (String avuc : insertQuery1) {
//             statement.addBatch(avuc);// sql->mavi  çoklu komut secim:terardan gelen query ler grup oluruldu
//         }
//         statement.executeBatch();// executeBatch() ->execute edilen gropg query den etkilene satırların sayısını verir

        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t\t" + rs.getInt(3));
        }
        connection.close();
        statement.close();
    }
}
