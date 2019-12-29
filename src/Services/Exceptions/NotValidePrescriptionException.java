package Services.Exceptions;

public class NotValidePrescriptionException extends Exception {
    public NotValidePrescriptionException (String msg) {
        super(msg);
    }
}
