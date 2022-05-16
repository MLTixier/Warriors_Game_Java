package exceptions;

public class CaseVideException extends Exception{

    public CaseVideException(){
        super();
    }

    public CaseVideException(String messageErreur){
        super(messageErreur);
    }
}
