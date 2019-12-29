package Test;

import Data.PatientContr;
import Data.Exceptions.NullObjectException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class PatientContrTest {
    BigDecimal cont;
    PatientContr pc;

    @BeforeEach
    public void setUp() throws NullObjectException{
        cont = new BigDecimal("253.746481");
        pc = new PatientContr(cont);
    }

    @Test
    public void getPersonalContTest(){
        assertEquals(cont, pc.getPersonalCont());
    }

    @Test
    public void equalsTest() throws NullObjectException{
        assertEquals(new PatientContr(cont), pc);
    }

    @Test
    public void hashCodeTest() throws NullObjectException{
        assertEquals(new PatientContr(cont).hashCode(), pc.hashCode());
    }

    @Test
    public void toStringTest(){
        assertEquals("PatientCont{" + "aportacion del paciente='" + cont.toString() + '\'' + '}', pc.toString());
    }
}
