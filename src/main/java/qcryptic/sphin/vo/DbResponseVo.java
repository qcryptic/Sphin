package qcryptic.sphin.vo;

/**
 * Created by Kyle on 10/7/2017.
 */
public class DbResponseVo {

    private boolean result;
    private String message;

    public DbResponseVo(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
