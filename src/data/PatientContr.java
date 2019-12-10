package data;

import java.math.BigDecimal;

final public class PatientContr {
    final private BigDecimal adp;

    public PatientContr(BigDecimal aportacionDelPaciente) {
        this.adp = aportacionDelPaciente;
    }

    public BigDecimal getAdp() {
        return adp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientContr patientContr = (PatientContr) o;
        return adp.equals(patientContr.adp);
    }
    @Override
    public int hashCode() {
        return adp.hashCode();
    }

    @Override
    public String toString() {
        return "PatientContr{" + "aportacion del paciente='" + adp + '\'' + '}';
    }
}
