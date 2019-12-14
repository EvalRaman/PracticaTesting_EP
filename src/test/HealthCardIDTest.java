package test;


import data.HealthCardID;
import org.junit.Test;

import static org.junit.Assert.*;

public class HealthCardIDTest {
        @Test
        public void TesttoString(){


            asd
            HealthCardID hc = new HealthCardID("Test_Code");
            String hc2 = "HealthCardID{personal code='Test_Code'}";
            assertEquals(hc2,hc.toString());
        }

        @Test
        public void TestEquals(){
            HealthCardID hc = new HealthCardID("Equals");
            HealthCardID hc2 = new HealthCardID ("Equals");
            assertEquals(hc, hc2);
        }

        @Test
        public void TestHashCode(){
            HealthCardID hc = new HealthCardID ("HashCode");
            HealthCardID hc2 = new HealthCardID ("HashCode");
            assertEquals(hc.hashCode(), hc2.hashCode());
        }

}
