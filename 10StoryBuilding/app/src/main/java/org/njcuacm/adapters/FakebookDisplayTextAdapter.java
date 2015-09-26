package org.njcuacm.adapters;

/**
 * Created by Saurabh on 9/12/2015.
 */
public class FakebookDisplayTextAdapter {
    public boolean left;
    public String comment;
    public String speaker;
    public int profile_pic_resource;
    public int row_bg_color;

    public FakebookDisplayTextAdapter(boolean left, String comment, String speaker, int profile_pic_resource, int row_bg_color) {
        super();
        this.left = left;
        this.comment = comment;
        this.speaker = speaker;
        this.profile_pic_resource = profile_pic_resource;
        this.row_bg_color = row_bg_color;
    }
}
