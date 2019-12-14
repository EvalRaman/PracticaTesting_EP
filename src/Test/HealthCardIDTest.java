package Test;


import Data.HealthCardID;
import Data.DataExceptions.BadFormatException;
import Data.DataExceptions.NullObjectException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.Assert.*;

public class HealthCardIDTest {
        HealthCardID hc;

        @BeforeEach
        public void setUp() throws NullObjectException, BadFormatException {
            hc = new HealthCardID("ABCD123456789012");
        }

        @Test
        public void toStringTest(){
            assertEquals("HealthCardID{" + "personal code='" + "ABCD123456789012" + '\'' + '}', hc.toString());
        }

        @Test
        public void equalsTest() throws NullObjectException, BadFormatException{
            assertEquals(new HealthCardID("ABCD123456789012"), hc);
        }

        @Test
        public void hashCodeTest() throws NullObjectException, BadFormatException{
            assertEquals(new HealthCardID("ABCD123456789012").hashCode(), hc.hashCode());
        }


}
