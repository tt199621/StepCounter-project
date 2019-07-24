package com.today.step.Diy_view;

public class Help_update_view {
    public static com.today.step.Diy_view.Update_view uu;
    public static void setHelp_update_view(Update_view u){
        uu=u;
    }
    public static void doHelp_update_view(int code,int i){
        uu.setview(code,i);
    }
}
