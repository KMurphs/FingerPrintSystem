/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import fingerprint.system.ParserConcatenator;
import javafx.collections.ObservableList;
import org.junit.Assert;

/**
 *
 * @author stephane.kibonge
 */
public class ConcatenatorParserTest {
    
    public ConcatenatorParserTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void ContenatorTest() {
        assertEquals("", ParserConcatenator.Concatenator(new String[]{}));
        assertEquals("1::2", ParserConcatenator.Concatenator(new String[]{ "1","2"}));
        
        assertEquals("1", ParserConcatenator.Concatenator(new String[]{"1"}));
        assertEquals("1", ParserConcatenator.Concatenator(new String[]{ "1"}, ":::"));
        
        assertEquals("", ParserConcatenator.Concatenator(new String[]{}, ":::"));
        assertEquals("1:::2", ParserConcatenator.Concatenator(new String[]{ "1","2"}, ":::"));
    }
    
    
    @Test
    public void ParserTest() {
        Assert.assertArrayEquals(new String[]{}, ParserConcatenator.Parser(null));
        
        Assert.assertArrayEquals(new String[]{}, ParserConcatenator.Parser(""));
        Assert.assertArrayEquals(new String[]{"1","2","3"}, ParserConcatenator.Parser("1::2::3"));
        
        Assert.assertArrayEquals(new String[]{}, ParserConcatenator.Parser("", ":::"));
        Assert.assertArrayEquals(new String[]{"1","2","3"}, ParserConcatenator.Parser("1:::2:::3", ":::"));
        
        Assert.assertArrayEquals(new String[]{}, ParserConcatenator.Parser("", ";"));
        Assert.assertArrayEquals(new String[]{"1","2","3"}, ParserConcatenator.Parser("1;2;3", ";"));
    }
}
