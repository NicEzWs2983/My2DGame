package setting;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class GetText {

        GameFrame gf;

        public Font defaultFont, maruMonica, unifont;

        public final int English = 1;
        public final int Chinese = 2;
        public int language;

        public String[][] titleName = { {},
                        { "The Door of Destiny" },
                        { "命運之門" }
        };
        public String[][] pause = { {},
                        { "pause" },
                        { "暫停" }
        };
        public String[][] askOpenDoor = { {},
                        { "Make sure you really want to go in." },
                        { "確定你想進去嗎" }
        };
        public String[][] yes = { {},
                        { "yes!" },
                        { "是!" }
        };
        public String[][] no = { {},
                        { "no!" },
                        { "不!" }
        };
        public String[][] notOpenDoor = { {},
                        { "You are not allowed to return there.", "You don't have enough keys to open this door." },
                        { "你已經不能回去了", "你沒有鑰匙開這扇門" }
        };
        public String[][] level = { {},
                        { "level " },
                        { "關卡 " }
        };

        public int completedLevel = 0;
        public String[][] gameOver = { {},
                        { "Game Over", "You've completed " + completedLevel + " levels" },
                        { "你輸了", "你通過了" + completedLevel + "關" }
        };

        public String[][] dialogues = { {},
                        { "Hello! Are you a noob?", "I'm just a giant, nothing more." },
                        { "哈嘍! 你是新來的嗎", "我只是個沒用的巨人" }
        };

        public String[][] key = { {},
                        { "KEY + 1" },
                        { "鑰匙 + 1" }
        };

        public String[][] selectLanguage = { {},
                        { "Language" },
                        { "語言" }
        };

        public String[][] option = {
                        {},
                        { "Option" },
                        { "設定" }
        };

        public GetText(GameFrame gf) {
                this.gf = gf;

                InputStream is;
                try {
                        is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
                        maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);

                        is = getClass().getResourceAsStream("/font/unifont_jp-16.0.02.otf");
                        unifont = Font.createFont(Font.TRUETYPE_FONT, is);

                } catch (FontFormatException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }

                setLanguage(English);
        }

        public void setCompletedLevel(int language) {
                completedLevel = gf.player.level - 1;
                gameOver[1][1] = "You've completed " + completedLevel + " levels";
                gameOver[2][1] = "你通過了" + completedLevel + "關";
                gameOver[0] = gameOver[language];
        }

        public void setLanguage(int language) {
                this.language = language;
                if (language == English) {
                        defaultFont = maruMonica;
                } else if (language == Chinese) {
                        defaultFont = unifont;
                }

                titleName[0] = titleName[language];

                pause[0] = pause[language];

                askOpenDoor[0] = askOpenDoor[language];

                yes[0] = yes[language];

                no[0] = no[language];

                notOpenDoor[0] = notOpenDoor[language];

                level[0] = level[language];

                dialogues[0] = dialogues[language];

                key[0] = key[language];

                selectLanguage[0] = selectLanguage[language];

                option[0] = option[language];
        }
}
