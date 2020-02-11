package io.libsoft.tradeserver.service.pojo;


import io.libsoft.tradeserver.model.entity.Alert;
import lombok.Data;

@Data
public class Message {

    private String text;
    private String chat_id;



    public static Message newMessage(String text) {
        Message message = new Message();
        message.setText(text);
        return message;
    }


    public static Message messageBuilder(Alert alert){
        Message message = new Message();
        if (alert.getShorterBuySetup() < -321){
            message.chat_id = "@tradebuys";
        }
        else {
            message.chat_id = "@tradesells";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Megalodon is giving a ");
        sb.append(alert.getShorterBuySetup() < -321 ? "buy":"sell");
        sb.append(" Signal for " + alert.getAsset()+" at price ").append(alert.getPrice()).append(", with");
        sb.append(alert.getShorterBuySetup() < -321 ? alert.getShorterBuySetup()+" buy setups found, " : alert.getShorterSellSetup()+ " sell setups found with ");
        sb.append(alert.getLongerTimer()+" td9 longer term reversal patterns, " + alert.getShorterTimer() + " td9 shorter term reversal patterns, ");
        sb.append(alert.getLongerSetup()).append(" % on longer term setups, ").append(alert.getFibonacci()).append(" % on fibonacci calculator.");
        message.setText(sb.toString());
        System.out.println(sb);

        return message;
    }
}
