package Test.Data;


import Data.Exceptions.BadFormatException;
import Data.Exceptions.NullObjectException;
import Data.HealthCardID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class HealthCardIDTest {
        HealthCardID hc;

        @BeforeEach
        public void setUp() throws NullObjectException, BadFormatException {
            hc = new HealthCardID("ABCD123456789012");
        }

        @Test
        public void getPersonalIDTest(){
            assertEquals("ABCD123456789012", hc.getPersonalID());
        }

        @Test
        public void equalsTest() throws NullObjectException, BadFormatException{
            assertEquals(new HealthCardID("ABCD123456789012"), hc);
        }

        @Test
        public void hashCodeTest() throws NullObjectException, BadFormatException{
            assertEquals(new HealthCardID("ABCD123456789012").hashCode(), hc.hashCode());
        }

        @Test
        public void toStringTest(){
            assertEquals("HealthCardID{" + "personal code='" + "ABCD123456789012" + '\'' + '}', hc.toString());
        }


}
