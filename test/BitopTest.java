/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import riokuva.Bitop;
import static org.junit.Assert.*;

/**
 *
 * @author Arcane
 */
public class BitopTest {
    
    public BitopTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void valkoinenPikseli(){
        assertEquals(16777215, Bitop.makePixel(255, 255, 255));
    }
    
    @Test
    public void mustaPikseli(){
        assertEquals(0, Bitop.makePixel(0, 0, 0));
    }
    
    @Test
    public void eriVariPikseli(){
        assertEquals(15347084, Bitop.makePixel(234, 45, 140));
    }
    
    @Test
    public void eriVariPikseli2(){
        assertEquals(874244, Bitop.makePixel(13, 87, 4));
    }
    
    @Test
    public void punainenOikein(){
        assertEquals(48, Bitop.getR(3207937));
    }
    
    @Test
    public void vihreaOikein(){
        assertEquals(128, Bitop.getG(1540351));
    }
    
    @Test
    public void sininenOikein(){
        assertEquals(243, Bitop.getB(13318899));
    }
}
