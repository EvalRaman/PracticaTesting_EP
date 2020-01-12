package Services;

import Data.Exceptions.BadFormatException;
import Data.HealthCardID;
import DispensingTerminal.Exceptions.PatientIDException;
import Services.Exceptions.HealthCardException;

public interface HealthCardReader {

    HealthCardID getHealthCardID() throws HealthCardException, PatientIDException;

}
