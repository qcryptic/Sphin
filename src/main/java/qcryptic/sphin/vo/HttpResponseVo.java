package qcryptic.sphin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Kyle on 11/1/2017.
 */
@Data
@AllArgsConstructor
public class HttpResponseVo {

    private int code;
    private String message;

}
