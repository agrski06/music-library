package agrski.musiclib.exceptions;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {
    private final String message;

    public CustomRuntimeException(String message) {
        this.message = message;
    }
}
