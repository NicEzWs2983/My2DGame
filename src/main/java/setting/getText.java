package setting;

public class GetText {

        GameFrame gf;

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
                setLanguage(English);
        }

        public void setCompletedLevel(int language) {
                completedLevel = gf.player.level;
                gameOver[1][2] = "You've completed " + completedLevel + " levels";
                gameOver[2][2] = "你通過了" + completedLevel + "關";
                gameOver[0] = gameOver[language];
        }

        public void setLanguage(int language) {
                this.language = language;

                titleName[0] = titleName[language];

                pause[0] = pause[language];

                askOpenDoor[0] = askOpenDoor[language];

                yes[0] = yes[language];

                no[0] = no[language];

                notOpenDoor[0] = notOpenDoor[language];

                dialogues[0] = dialogues[language];

                key[0] = key[language];

                selectLanguage[0] = selectLanguage[language];

                option[0] = option[language];
        }
}
