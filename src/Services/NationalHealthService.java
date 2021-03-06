package Services;

import Data.*;
import DispensingTerminal.Exceptions.ProductNotFoundException;
import Pharmacy.*;
import Services.Exceptions.*;

import java.util.List;


public interface NationalHealthService {

    Dispensing getePrescription(HealthCardID hcID)
            throws HealthCardException, NotValidePrescriptionException,
            ConnectException;

    PatientContr getPatientContr(HealthCardID hcID) throws ConnectException;

    ProductSpecification getProductSpecific(ProductID pID)
            throws ProductIDException, ConnectException, ProductNotFoundException;

    List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp)
            throws ConnectException;

}
