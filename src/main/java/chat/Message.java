package chat;

public class Message {
    private String alias;
    private String message;
    private String image; // 이미지 데이터 추가

    // 기본 생성자
    public Message() {
    }

    // Getters 및 Setters
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
