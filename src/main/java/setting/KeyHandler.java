package setting;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import panel.GamePanel;
import panel.LevelPanel;

public class KeyHandler implements KeyListener {
    GameFrame gf;
    GamePanel gp;
    LevelPanel lv1p;
    LevelPanel lv2p;
    LevelPanel lv3p;
    CheckState cState;

    int gameState;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public void setKeyReleased() {
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        enterPressed = false;
    }

    public KeyHandler(GameFrame gf) {
        this.gf = gf;
        this.gp = gf.gamePanel;
        this.lv1p = gf.level1Panel;
        this.lv2p = gf.level2Panel;
        this.lv3p = gf.level3Panel;
        this.cState = gf.cState;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        gameState = cState.getGameState();

        if (gameState == cState.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                if (gf.player.inGamePanel) {
                    gp.gameState = cState.pauseState;
                }

                else if (gf.player.inLevel1Panel) {
                    lv1p.gameState = cState.pauseState;
                }

                else if (gf.player.inLevel2Panel) {
                    lv2p.gameState = cState.pauseState;
                }

                else if (gf.player.inLevel3Panel) {
                    lv3p.gameState = cState.pauseState;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }

        else if (gameState == cState.pauseState) {
            if (code == KeyEvent.VK_P) {
                if (gf.player.inGamePanel) {
                    gp.gameState = cState.playState;
                }

                else if (gf.player.inLevel1Panel) {
                    lv1p.gameState = cState.playState;
                }

                else if (gf.player.inLevel2Panel) {
                    lv2p.gameState = cState.playState;
                }

                else if (gf.player.inLevel3Panel) {
                    lv3p.gameState = cState.playState;
                }
            }
        }

        else if (gameState == cState.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                if (gf.player.inGamePanel) {

                    gp.gameState = cState.playState;
                    if (gp.npc[gf.player.npcIndex] != null) {
                        gp.npc[gf.player.npcIndex].direction = gp.npc[gf.player.npcIndex].originalDirection;
                    }

                } else if (gf.player.inLevel1Panel) {
                } else if (gf.player.inLevel2Panel) {
                } else if (gf.player.inLevel3Panel) {
                }
            }
        }

        else if (gameState == cState.openDoorState) {

            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                if (gf.ui.choiceIndex < 1)
                    gf.ui.choiceIndex++;
                else
                    gf.ui.choiceIndex = 0;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                if (gf.ui.choiceIndex > 0)
                    gf.ui.choiceIndex--;
                else
                    gf.ui.choiceIndex = 1;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gf.ui.choiceIndex == 1) {
                    if (gf.player.inGamePanel) {
                        gp.gameState = cState.playState;
                    }

                    else if (gf.player.inLevel1Panel) {
                        lv1p.gameState = cState.playState;
                    }

                    else if (gf.player.inLevel2Panel) {
                        lv2p.gameState = cState.playState;
                    }

                    else if (gf.player.inLevel3Panel) {
                        lv3p.gameState = cState.playState;
                    }
                }
                if (gf.ui.choiceIndex == 0) {
                    if (gf.player.inGamePanel) {
                        openDoor_GP();
                    } else if (gf.player.inLevel1Panel) {
                        openDoor_LVP(lv1p);
                    } else if (gf.player.inLevel2Panel) {
                        openDoor_LVP(lv2p);
                    } else if (gf.player.inLevel3Panel) {
                        openDoor_LVP(lv3p);
                    }
                }
                gf.ui.choiceIndex = 0;
            }
        }

        else if (gameState == cState.offLimitsState) {
            if (code == KeyEvent.VK_ENTER) {
                if (gf.player.inGamePanel) {
                }

                else if (gf.player.inLevel1Panel) {
                    lv1p.gameState = cState.playState;
                }

                else if (gf.player.inLevel2Panel) {
                    lv2p.gameState = cState.playState;
                }

                else if (gf.player.inLevel3Panel) {
                    lv3p.gameState = cState.playState;
                }
            }
        }

    }

    public void openDoor_GP() {
        System.out.println(gf.player.doorIndex);
        gp.obj_Door[gf.player.doorIndex].isOpen = true;
        gp.obj_Door[gf.player.doorIndex].setDoorImage();
        gp.gameState = cState.playState;
    }

    public void openDoor_LVP(LevelPanel lvp) {
        lvp.obj_Door[gf.player.doorIndex].isOpen = true;
        lvp.obj_Door[gf.player.doorIndex].setDoorImage();
        lvp.gameState = cState.playState;
        gf.player.numberOfKeys--;
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }

    }
}
