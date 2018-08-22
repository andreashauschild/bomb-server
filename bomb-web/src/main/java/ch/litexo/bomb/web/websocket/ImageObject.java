package ch.litexo.bomb.web.websocket;

public class ImageObject {
    private  byte[] bytes;
    private String type;
    private String base64;
    private String timeStamp;
    private String id;

    public ImageObject(String type, String base64, String timeStamp) {
        this.type = type;
        this.base64 = "data:image/"+type+";base64, "+base64;
        this.timeStamp = timeStamp;
    }

    public ImageObject(String type, byte[] bytes, String timeStamp) {
        this.type = type;
        this.bytes = bytes;
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
