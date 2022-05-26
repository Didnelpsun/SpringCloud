// Code.java
package org.didnelpsun.util;

// 状态码
public enum Code {
    SUCCESS(200),
    NO_CONTENT(204),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    NO_RESPONSE(444),
    SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    private final int id;

    Code(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
