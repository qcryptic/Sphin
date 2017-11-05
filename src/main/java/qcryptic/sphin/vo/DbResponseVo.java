package qcryptic.sphin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Kyle on 10/7/2017.
 */
@Data
@AllArgsConstructor
public class DbResponseVo {

    private boolean result;
    private String message;

}
