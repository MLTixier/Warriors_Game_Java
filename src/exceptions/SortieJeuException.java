package exceptions;

public class SortieJeuException extends Exception {

    public SortieJeuException(){
        super();
    }

    public SortieJeuException(String messageErreur){
        super(messageErreur);
    }

}
