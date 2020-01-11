package Test.Data;

import Data.Exceptions.NullObjectException;
import Data.PatientContr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

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
