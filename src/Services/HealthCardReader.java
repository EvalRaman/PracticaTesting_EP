package Services;

import Data.HealthCardID;
import Services.Exceptions.HealthCardException;

public interface HealthCardReader {

    HealthCardID getHealthCardID() throws HealthCardException;
}
