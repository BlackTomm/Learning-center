package chapter2;

import org.springframework.dao.DuplicateKeyException;

public class CustomDuplicatedKeyException extends DuplicateKeyException {
    public CustomDuplicatedKeyException(String msg){
        super(msg);
    }
    public CustomDuplicatedKeyException(String msg, Throwable cause){
        super(msg, cause);
    }
}
