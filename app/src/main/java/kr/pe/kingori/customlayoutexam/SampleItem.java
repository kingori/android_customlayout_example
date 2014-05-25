package kr.pe.kingori.customlayoutexam;

import android.graphics.Color;

public class SampleItem {
    private int index;

    public SampleItem(int index) {
        this.index = index;
    }

    public int getImageBgColor() {
//        return Color.argb(00, 00, 255, (index * 30) % 256);
        return Color.RED;
    }

    public String getName() {
        return "name" + index;
    }

    public String getSubString() {
        return "substadsfkjhasdlfkjhasdlfkjhasldkfjhasldkfjhaslkdjfhalskdjhfr" + index;
    }
}
