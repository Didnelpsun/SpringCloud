// Result.java
package org.didnelpsun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.didnelpsun.util.Code;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<data> {
    private Code code;
    private String message;
    private data data;

    public Result(Code code, String message){
        this.code = code;
        this.message = message;
    }
}
