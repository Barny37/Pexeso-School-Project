package sk.tuke.kpi.kp.pexeso;

import java.io.Serializable;

public class Card implements Cards, Serializable {
    private String image;
   private String defaultImage;
    private CardState cardState;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private int specialNumber;

    public Card(int specialNumber, String image){
        defaultImage = "neutral";
        this.image = image;
        this.specialNumber = specialNumber;
        cardState = CardState.CLOSED;

    }

    public String getImage() {
        return image;
    }

    public String getDefaultImage() {
        return defaultImage;
    }

    @Override
    public void setImage(String image) {
        this.image=image;
    }

    @Override
    public void setDefaultImage() {
        defaultImage = "neutral";
    }

    @Override
    public void imageChange() {
        if (cardState == CardState.OPENED || cardState == CardState.FOUNDED){
            if (specialNumber < 8) {
                System.out.print(ANSI_BLUE+"0"+(specialNumber)+ANSI_RESET);
            }else {
                System.out.print(ANSI_BLUE+(specialNumber)+ANSI_RESET);
            }
        }else{
            System.out.print(("??"));
        }
    }

    public void changeTurnedState(){
        if (cardState ==CardState.CLOSED){
            cardState =CardState.OPENED;
        }else if (cardState == CardState.OPENED){
            cardState =CardState.CLOSED;
        }
    }

    public void changeFoundState(){
        if (cardState == CardState.OPENED){
            cardState = CardState.FOUNDED;
        }
    }

    public int getSpecialNumber() {
        return specialNumber;
    }

    public void setSpecialNumber(int specialNumber) {
        this.specialNumber = specialNumber;
    }

    public CardState getCardState() {
        return cardState;
    }

    public void setCardState(CardState state) {
        this.cardState = state;
    }

}
