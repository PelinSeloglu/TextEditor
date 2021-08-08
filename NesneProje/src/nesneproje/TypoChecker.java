package nesneproje;

import java.util.*;
import java.io.*;

public class TypoChecker {

    public TypoChecker() {
    }

    
    
    public String isaretSil(String kelime) {
        //Metinlerde kelimelerin sonlarında yer alan işaretlerin silinmesi.
        String sonString = null;
        if (kelime.contains(",") || kelime.contains(".") || kelime.contains(";") || kelime.contains(":") || kelime.contains("?") || kelime.contains("!")) {
            sonString = kelime.substring(0, kelime.length() - 1);
        }
        
        if(kelime.contains("-")){
            kelime=kelime.replaceAll("-", " ");
        }
        
        if (sonString != null) {
            return sonString;
        } else {
            return kelime;
        }
        
        
    }

    public boolean sozluk_kontrol(String sozluk) {
        //Words dosyası içerisinden verilerin alınması.

        Scanner okuma = null;
        try {
            okuma = new Scanner(new FileInputStream("words.txt"));
        } catch (FileNotFoundException e) {
            e.getMessage();
            System.exit(0);
        }

        while (okuma.hasNext()) {
            //Words dosyasından alınan veriler girdi dosyalarındaki verilerle karşılaştırılması.

            String metin_okunan = okuma.nextLine();

            if (metin_okunan.equals(sozluk)) {
                return true;
            }
        }

        return false;
    }

    private String hataDuzelt(String icerik) {
        //Hata kontrolu yapan metot
        String icerik2 = icerik.toLowerCase();
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new FileInputStream("words.txt"));
        } catch (FileNotFoundException e) {
            e.getMessage();
            System.exit(0);
        }

        if (icerik2.contains("1") || icerik2.contains("2") || icerik2.contains("3") || icerik2.contains("4") || icerik2.contains("5") || 
                icerik2.contains("6") || icerik2.contains("7") || icerik2.contains("8") || icerik2.contains("9") || icerik2.contains("0")) {
            //Eğer kelimede rakamlar varsa doğru kabul edilir
            return icerik;
        }

        int dogru = 0;
        ArrayList<Character> harf = new ArrayList<>();
        ArrayList<String> harf2 = new ArrayList<>();

        for (int k = 0; k <= icerik2.length() - 1; k++) {//Alınan kelimeler char olarak bir harf listesine atılır.
            harf.add(icerik2.charAt(k));
        }
        for (int k = 0; k < harf.size(); k++) {//Harf listesindeki elemanlar yeni bir listede toString metodu ile yazılır.
            harf2.add(harf.get(k).toString());
        }
        while (fileIn.hasNext()) {//Sözlük içerisindeki kelimeler ile yazılan kelimenin karşılaştırılması
            //Kelime doğruysa hata düzeltemeye girmemesini sağlar.
            String sozluk_kelime = fileIn.nextLine();
            if (sozluk_kelime.equals(icerik2)) {
                //Eğer metinde alınan kelime sözlükte de varsa doğru kabul edilir.
                return icerik;
            }
        }

        if (dogru == 0) {
            //İki harfin yer değiştirmesinden kaynaklı bir hata olup olmadığını kontrol eder ve düzeltir.
            for (int i = 0; i <= icerik2.length() - 1; i++) {
                String originalContent = icerik2;
                char[] c = originalContent.toCharArray();
                char temp = c[i];
                if (icerik2.length() != i + 1) {
                    c[i] = c[i + 1];
                    c[i + 1] = temp;
                }
                String swappedContent = new String(c);
                if (sozluk_kontrol(swappedContent)) {
                    //Eğer hata düzeltilirse diğer hata kontrollerine girmemesini sağlar.   
                    return swappedContent;
                }
            }
        }

        return icerik;
    }
    //yanlış yazımların doğruları ile değiştirilmesi
    public String kelimeleriDuzelt(String content) {
        String kelimeler = content;
        ArrayList<String> duzeltilmisKelimeler = new ArrayList<>();
        kelimeler = kelimeler.replaceAll("\n", " ");
        String[] tekKelime = kelimeler.split(" ");

        for (int i = 0; i < tekKelime.length; i++) {
            String isaretiSilinmisKelime = this.hataDuzelt(this.isaretSil(tekKelime[i]));
            String duzeltilmisKelime = isaretiSilinmisKelime;
            duzeltilmisKelimeler.add(duzeltilmisKelime);
        }

        return String.join(" ", duzeltilmisKelimeler);
    }
}
