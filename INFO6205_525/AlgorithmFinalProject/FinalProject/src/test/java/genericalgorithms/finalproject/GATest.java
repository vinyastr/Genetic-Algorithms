package genericalgorithms.finalproject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vinya
 */
public class GATest {
    
    public GATest() {
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
    @Test
    public void distanceFnTest()
    {
     TraversalPath tp = new TraversalPath();
     City c1 = new City(10.0,20.0,"test1",1);
     City c2 = new City(20.0,30.0,"test2",2);
     City c3 = new City(40.0,20.0,"test3",3);
     City c4 = new City(50.0,30.0,"test4",4);
     City c5 = new City(20.0,65.0,"test5",5);
     City c6 = new City(10.0,70.0,"test6",6);

     
     DecimalFormat df = new DecimalFormat("0.00");
     assertEquals(df.format(tp.cityDistance(c1,c2)),String.valueOf(14.14));
     assertEquals(df.format(tp.cityDistance(c3,c4)),String.valueOf(14.14));
     assertEquals(df.format(tp.cityDistance(c5,c6)),String.valueOf(11.18));
     assertEquals(df.format(tp.cityDistance(c5,c3)),String.valueOf(49.24));
    }
    
    @Test
    public void fitnessFnTest(){
    TraversalPath tp = new TraversalPath(1);
     City c1 = new City(0.6,0.7,"test1",1);
     City c2 = new City(0.9,0.8,"test2",2);
     City c3 = new City(10.0,20.0,"test3",3);
     City c4 = new City(30.0,30.0,"test4",4);
     City c5 = new City(20.0,65.0,"test5",5);
     City c6 = new City(10.0,70.0,"test6",6);
     List<City> cityList= new ArrayList<City>();
     cityList.add(c1);
     cityList.add(c2);
     cityList.add(c3);
 tp.setTraversalPath(cityList);
    
TraversalPath tp2 = new TraversalPath(2);
     List<City> cityList2 = new ArrayList<City>();
     cityList2.add(c3);
     cityList2.add(c4);
     cityList2.add(c5);
     cityList2.add(c6);
     tp2.setTraversalPath(cityList2);
     
     TraversalPath tp3 = new TraversalPath(3);
     List<City> cityList3 = new ArrayList<City>();
    
     cityList3.add(c3);
     cityList3.add(c2);
     cityList3.add(c6);
     tp3.setTraversalPath(cityList3);
     
     DecimalFormat df = new DecimalFormat("0.00");
     assertEquals(df.format(tp.calculateFitnessScore()),String.valueOf(0.02));
     assertEquals(df.format(tp2.calculateFitnessScore()),String.valueOf(0.01));
     assertEquals(df.format(tp3.calculateFitnessScore()),String.valueOf(0.01));
    }    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
