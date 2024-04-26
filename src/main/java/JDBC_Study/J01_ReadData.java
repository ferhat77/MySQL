package JDBC_Study;

import java.sql.*;

public class J01_ReadData {
/*
Bir SORU : JDBC'de execute, execute Query ve execute Update arasındaki fark nedir?
Bir CEVAP  :
--> Execute(): Her türlü SQL Sorgusu için kullanılabilir.
--> ExecuteQuery() : sorguyu seçmek için kullanılabilir.
--> ExecuteUpdate(): tabloyu değiştirmek/güncellemek için kullanılabilir.
 */
    public static void main(String[] args) throws SQLException {

        String url="jdbc:mysql://localhost:3306/jdbc";
        String username="root";
        String password="12345678";
        // Step1
        // Connection con = DriverManager.getConnection(url, user, password);//catıya kurulan anten tv'ye(DB) ya baglandi
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "12345678");//catıya kurulan anten tv'ye(DB) ya baglandi
        // Step2
        Statement statement = con.createStatement();// sql query(sorgu) execute edecek (şimşeklemek için tv kumanda) tanımlandı

        System.out.println("   ***   task01   ***   ");
        // Task01-> talebeler table'daki record'ları listeleyen code create ediniz
        String query = "select * from talebeler";

        // Step3
        ResultSet rs = statement.executeQuery(query);// tanımlana sql query execute edilerek(şimşeklenerek) output rs'ye atandı

        //step4
        while (rs.next()) {//table'den gelen record'ları tekrara alan loop tanımlandı
            System.out.printf("%-6d %-20.20s %-8s %-6d\n", rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getInt(4));
        }



        System.out.println("   ***   task02   ***   ");
        // Task02-> talebeler table'daki notları 90 dan yuksek olan record'ları listeleyen code create ediniz
        ResultSet not90ustu = statement.executeQuery("SELECT * FROM talebeler WHERE yazili_notu>=90");
        while (not90ustu.next()) {//table'den gelen record'ları tekrara alan loop tanımlandı
            System.out.printf("%-6d %-20.20s %-8s %-6d\n", not90ustu.getInt(1), not90ustu.getString(2),
                    not90ustu.getString(3), not90ustu.getInt(4));
        }


        System.out.println("   ***   task03   ***   ");
        // Task03-> talebeler table'daki id değeri 124 olan record'ları listeleyen code create ediniz
        ResultSet id124 = statement.executeQuery("SELECT * FROM talebeler WHERE id=124");
        while (id124.next()) {//table'den gelen record'ları tekrara alan loop tanımlandı
            System.out.printf("%-6d %-20.20s %-8s %-6d\n", id124.getInt(1), id124.getString(2),
                    id124.getString(3), id124.getInt(4));
        }


        System.out.println("   ***   task04   ***   ");
        // Task04-> talebeler table'daki notu 70 ile 90 arasında  olan record'ları listeleyen code create ediniz.
        ResultSet not7090arasi = statement.executeQuery("SELECT * FROM talebeler WHERE yazili_notu between 70 and 90");
        while (not7090arasi.next()) {//table'den gelen record'ları tekrara alan loop tanımlandı
            System.out.printf("%-6d %-20.20s %-8s %-6d\n", not7090arasi.getInt(1), not7090arasi.getString(2),
                    not7090arasi.getString(3), not7090arasi.getInt(4));
        }


        System.out.println("   ***   task05   ***   ");
        // Task05-> talebeler table'daki ismin 3. harfi l   olan record'ları listeleyen code create ediniz.
        ResultSet ucuncuHarfL = statement.executeQuery("SELECT * FROM talebeler WHERE isim like '__L%'");
        while (ucuncuHarfL.next()) {//table'den gelen record'ları tekrara alan loop tanımlandı
            System.out.printf("%-6d %-20.20s %-8s %-6d\n", ucuncuHarfL.getInt(1), ucuncuHarfL.getString(2),
                    ucuncuHarfL.getString(3), ucuncuHarfL.getInt(4));
        }
        con.close();//db baglantısı kesildi
        statement.close();//tv baglantısı kesildi

    }
}
