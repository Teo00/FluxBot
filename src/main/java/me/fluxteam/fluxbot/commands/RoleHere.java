package me.fluxteam.fluxbot.commands;

import me.fluxteam.fluxbot.utils.ConfigUtilities;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;

import java.awt.*;
import java.io.IOException;

public class RoleHere extends FluxCommand{

    public RoleHere(MessageChannel channel, Message commandMessage, Member member, int permissionID) {
        super(channel, commandMessage, member, permissionID);
    }

    @Override
    void dispatch () {
        MessageEmbed eb = new EmbedBuilder()
                .setTitle(" ")
                .setAuthor("İlgilendiğiniz botun rolünü almak için, ilgili emojiye tıklayın.")
                .addField("TO.d", ":tod:", false)
                .addField("Postcard", "<:postcard:788428969862103042>", false)
                .setColor(Color.PINK)
                .setFooter("FluxTeam * 2020")
                .setThumbnail("https://cdn.discordapp.com/attachments/788854303212175451/789200767864668220/flux.png")
                .build();

        channel.sendMessage(eb).queue(message -> {

            for(Emote e : message.getGuild().getEmotes())
                if(e.getName().equalsIgnoreCase("postcard"))
                    message.addReaction(e).queue();

            try {
                ConfigUtilities.setRoleMessageID(message.getId());
            } catch (IOException e) {
                System.out.println("RoleHere.java > ID: 0");
                e.printStackTrace();
            }
        });

    }
}
