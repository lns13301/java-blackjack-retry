package domain.card;

public enum Type {
    SPADE("♠"),
    DIAMOND("♦"),
    HEART("♥"),
    CLUB("♣");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
