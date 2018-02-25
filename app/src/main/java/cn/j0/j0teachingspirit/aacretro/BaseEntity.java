package cn.j0.j0teachingspirit.aacretro;

/**
 * Created by aa on 2018/2/25.
 */

public class BaseEntity<T> {
    private String message;
    private int status;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
