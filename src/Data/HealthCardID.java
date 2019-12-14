package Data;

import Data.DataExceptions.BadFormatException;
import Data.DataExceptions.NullObjectException;

/**
 * The personal identifying code in the National Health Service.
 */
final public class HealthCardID {
    private final String personalID;

    public HealthCardID(String code) throws NullObjectException, BadFormatException {
        if(code == null) throw new NullObjectException("Code is null.");
        if(!codeIsValid(code)) throw new BadFormatException("Invalid code.");
        this.personalID = code;
    }

    public String getPersonalID() {
        return personalID;
    }

    private boolean codeIsValid(String code){
        if(code.length() != 16) return false;
        char [] array = new char[16];
        code.getChars(0,16, array, 0);
        for(int i = 0; i < 4; i++){
            if (!Character.isUpperCase(array[i])) return false;
        }
        for(int i = 4; i < 16; i++){
            if (!Character.isDigit(array[i])) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCardID hcardID = (HealthCardID) o;
        return personalID.equals(hcardID.personalID);
    }

    @Override
    public int hashCode() {
        return personalID.hashCode();
    }

    @Override
    public String toString() {
        return "HealthCardID{" + "personal code='" + personalID + '\'' + '}';
    }
}