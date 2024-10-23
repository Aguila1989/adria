package be.howest.ti.adria.web.exceptions;

import java.util.ArrayList;
import java.util.List;

public class IllegalRequestException extends RuntimeException {
    private final boolean hasErrorArray;
    private final List<String> errorArray;

    public IllegalRequestException(String msg) {
        super(msg);
        this.hasErrorArray = false;
        this.errorArray = new ArrayList<>();
    }

    public IllegalRequestException(List<String> messages) {
        super(String.format("failed with multiple errors %s", messages.toString()));
        this.errorArray = messages;
        this.hasErrorArray = true;
    }

    public boolean hasErrorArray() {
        return hasErrorArray;
    }

    public List<String> getErrorArray() {
        return errorArray;
    }
}
