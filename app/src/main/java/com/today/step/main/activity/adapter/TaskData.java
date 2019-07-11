package com.today.step.main.activity.adapter;

public class TaskData {
    String task_id;//任务id
    String task_name;//任务名称
    String buy_task;//买任务所需要的果数
    String over_vitality;//完成任务奖励的活跃度
    String over_award;//完成任务奖励的人生果数
    String task_step;//完成任务所需要的步数
//    String task_time;//任务时间
    int task_state;//任务状态

    public TaskData(String task_id, String task_name, String buy_task, String over_vitality, String over_award, String task_step, int task_state) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.buy_task = buy_task;
        this.over_vitality = over_vitality;
        this.over_award = over_award;
        this.task_step = task_step;
        this.task_state = task_state;
    }

    public TaskData(String task_id, String task_name, String buy_task, String over_vitality, String over_award, String task_step) {
        this.task_id = task_id;
        this.task_name = task_name;
        this.buy_task = buy_task;
        this.over_vitality = over_vitality;
        this.over_award = over_award;
        this.task_step = task_step;
//        this.task_state = task_state;
    }

    public String getTask_id() {
        return task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public String getBuy_task() {
        return buy_task;
    }

    public String getOver_vitality() {
        return over_vitality;
    }

    public String getOver_award() {
        return over_award;
    }

    public String getTask_step() {
        return task_step;
    }

    public int getTask_state() {
        return task_state;
    }

    public void setTask_state(int task_state) {
        this.task_state = task_state;
    }
    //    public String getTask_state() {
//        return task_state;
//    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setBuy_task(String buy_task) {
        this.buy_task = buy_task;
    }

    public void setOver_vitality(String over_vitality) {
        this.over_vitality = over_vitality;
    }

    public void setOver_award(String over_award) {
        this.over_award = over_award;
    }

    public void setTask_step(String task_step) {
        this.task_step = task_step;
    }

//    public void setTask_state(String task_state) {
//        this.task_state = task_state;
//    }
}
