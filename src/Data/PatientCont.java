package Data;

import java.math.BigDecimal;
import Data.Exceptions.NullObjectException;

final public class PatientCont {
    private final BigDecimal personalCont;

    public PatientCont(BigDecimal contribution) throws NullObjectException{
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
        PatientCont patientContr = (PatientCont) o;
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
