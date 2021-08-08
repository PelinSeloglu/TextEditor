package nesneproje;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TypoCheckerTest {
    
    public TypoCheckerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    //İşaretsil metodunun testi
    @Test
    public void testIsaretSil() {
        System.out.println("isaretleriSil");
        String content = "But,";
        TypoChecker instance = new TypoChecker();
        String expResult = "But";
        String result = instance.isaretSil(content);
        assertEquals(expResult, result);
        
    }

    //sözlük kontrolünün testi
    @Test
    public void testSozluk_kontrol() {
        System.out.println("sozlukKontrol");
        String content = "whimpers";
        TypoChecker instance = new TypoChecker();
        Boolean expResult = true;
        Boolean result = instance.sozluk_kontrol(content);
        assertEquals(expResult, result);
    }

    //kelime düzeltmenin testi
    @Test
    public void testKelimeleriDuzelt() {
        System.out.println("kelimeleriDuzelt");
        String content = "But I must epxlain to you how all this mistaken idea of denuoncing pleasure and praising pian was born and I will give you a compelte account of the ssytem, and expound the actual taechings of the great explorer of the truth";
        TypoChecker instance = new TypoChecker();
        String expResult = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system and expound the actual teachings of the great explorer of the truth";
        String result = instance.kelimeleriDuzelt(content);
        assertEquals(expResult, result);
    }
    
}
