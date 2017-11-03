package qcryptic.sphin.vo;

/**
 * Created by Kyle on 11/1/2017.
 */
public class HttpResponseVo {

    private int code;
    private String message;

    public HttpResponseVo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
