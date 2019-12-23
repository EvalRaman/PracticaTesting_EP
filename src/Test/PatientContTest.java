package Test;

import Data.PatientCont;
import Data.Exceptions.NullObjectException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PatientContTest {
    BigDecimal cont;
    PatientCont pc;

    @BeforeEach
    public void setUp() throws NullObjectException{
        cont = new BigDecimal(253.746481);
        pc = new PatientCont(cont);
    }

    @Test
    public void getPersonalContTest(){
        assertEquals(cont, pc.getPersonalCont());
    }

    @Test
    public void equalsTest() throws NullObjectException{
        assertEquals(new PatientCont(cont), pc);
    }

    @Test
    public void hashCodeTest() throws NullObjectException{
        assertEquals(new PatientCont(cont).hashCode(), pc.hashCode());
    }

    @Test
    public void toStringTest(){
        assertEquals("PatientCont{" + "aportacion del paciente='" + cont.toString() + '\'' + '}', pc.toString());
    }
}
