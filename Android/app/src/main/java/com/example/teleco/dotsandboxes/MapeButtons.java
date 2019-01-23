package com.example.teleco.dotsandboxes;

public class MapeButtons {

    public String getIdButton(String move) {

        String buttonId = "";

        switch (move) {

            case "H (0,0)\t":
                buttonId = "button1";
                break;
            case "H (0,1)\t":
                buttonId = "button2";
                break;
            case "H (0,2)\t":
                buttonId = "button3";
                break;
            case "V (0,0)\t":
                buttonId = "button4";
                break;
            case "V (0,1)\t":
                buttonId = "button5";
                break;
            case "V (0,2)\t":
                buttonId = "button6";
                break;
            case "V (0,3)\t":
                buttonId = "button7";
                break;
            case "H (1,0)\t":
                buttonId = "button8";
                break;
            case "H (1,1)\t":
                buttonId = "button9";
                break;
            case "H (1,2)\t":
                buttonId = "button10";
                break;
            case "V (1,0)\t":
                buttonId = "button11";
                break;
            case "V (1,1)\t":
                buttonId = "button12";
                break;
            case "V (1,2)\t":
                buttonId = "button13";
                break;
            case "V (1,3)\t":
                buttonId = "button14";
                break;
            case "H (2,0)\t":
                buttonId = "button15";
                break;
            case "H (2,1)\t":
                buttonId = "button16";
                break;
            case "H (2,2)\t":
                buttonId = "button17";
                break;
            case "V (2,0)\t":
                buttonId = "button18";
                break;
            case "V (2,1)\t":
                buttonId = "button19";
                break;
            case "V (2,2)\t":
                buttonId = "button20";
                break;
            case "V (2,3)\t":
                buttonId = "button21";
                break;
            case "H (3,0)\t":
                buttonId = "button22";
                break;
            case "H (3,1)\t":
                buttonId = "button23";
                break;
            case "H (3,2)\t":
                buttonId = "button24";
                break;
            default: break;
        }
        return buttonId;
    }
}
