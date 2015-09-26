package org.njcuacm.adapters;

/**
 * Created by Saurabh on 9/13/2015.
 */
public class SelectiveDisplayTextAdapter {
    public boolean left;
    public String comment;
    public String speaker;
    public int background_res_color;

    public SelectiveDisplayTextAdapter(boolean left, String comment, String speaker, int background_res_color) {
        super();
        this.left = left;
        this.comment = comment;
        this.speaker = speaker;
        this.background_res_color = background_res_color;
    }
}
