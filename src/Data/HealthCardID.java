package Data;

import Data.Exceptions.*;

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

    private boolean codeIsValid(String code){
        return code.length() == 16 && first4Letters(code) && last12digits(code);
    }

    private boolean first4Letters(String code){
        char [] alphabetic = new char[4];
        code.getChars(0,4, alphabetic, 0);
        for(int i = 0; i < 4; i++){
            if (!Character.isAlphabetic(alphabetic[i])) return false;
        }
        return true;
    }

    private boolean last12digits(String code){
        char [] digit = new char[12];
        code.getChars(4,16, digit, 0);
        for(int i = 0; i < 12; i++){
            if (!Character.isDigit(digit[i])) return false;
        }
        return true;
    }

    public String getPersonalID() {
        return personalID;
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