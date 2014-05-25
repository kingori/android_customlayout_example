package kr.pe.kingori.customlayoutexam;

import android.graphics.Color;

public class SampleItem {
    private int index;

    public SampleItem(int index) {
        this.index = index;
    }

    public int getImageBgColor() {
        return Color.rgb(00, (index * 20) % 256, (index * 15) % 256);
    }

    public String getName() {
        return "name" + index;
    }

    public String getSubString() {
        return "substadsfkjhasdlfkjhasdlfkjhasldkfjhasldkfjhaslkdjfhalskdjhfr" + index;
    }
}
