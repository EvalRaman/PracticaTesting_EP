package Data;

import java.math.BigDecimal;
import Data.Exceptions.NullObjectException;

final public class PatientContr{
    private final BigDecimal personalCont;

    public PatientContr(BigDecimal contribution) throws NullObjectException{
        if(contribution == null) throw new NullObjectException("Contribution is Null.");
        this.personalCont = contribution;
    }

    public BigDecimal getPersonalCont() {
        return personalCont;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientContr patientContr = (PatientContr) o;
        return personalCont.equals(patientContr.personalCont);
    }
    @Override
    public int hashCode() {
        return personalCont.hashCode();
    }

    @Override
    public String toString() {
        return "PatientCont{" + "aportacion del paciente='" + personalCont + '\'' + '}';
    }
}
