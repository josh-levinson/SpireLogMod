package spire_log_mod;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.net.*;
import java.io.*;

import basemod.BaseMod;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostExhaustSubscriber;
import basemod.interfaces.PostInitializeSubscriber;

@SpireInitializer
public class SpireLogMod implements PostExhaustSubscriber,
        PostBattleSubscriber, PostDungeonInitializeSubscriber,
        PostInitializeSubscriber {

    public static final String MODNAME = "Spire Log Mod";
    public static final String AUTHOR = "Josh Levinson";
    public static final String VERSION = "0.1.0";
    public static final String DESCRIPTION = "Spire Log Mod provides additional card information from SpireLogs.com";


    private void getSpireLogs() {
        System.out.println(getUrl("https://spirelogs.com/stats/ironclad/tierlist.php"));
    }

    private String getUrl(String urlString)
    {
        try {
            URL reqUrl = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) reqUrl.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            System.out.println("HTTP Request Status: " + status);
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "This method works.";
    }

    public SpireLogMod() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new SpireLogMod();
    }

    @Override
    public void receivePostExhaust(AbstractCard c) {
    }

    @Override
    public void receivePostBattle(AbstractRoom r) {
        getSpireLogs();
    }

    @Override
    public void receivePostDungeonInitialize() {
    }

    @Override
    public void receivePostInitialize() {
        getSpireLogs();
    }

}