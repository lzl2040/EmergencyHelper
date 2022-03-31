package com.example.emergencyhelper.application;

import android.app.Application;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.entity.TaskEntity;
import com.example.emergencyhelper.entity.User;
import com.example.emergencyhelper.util.StaticData;

import java.util.ArrayList;
import java.util.List;

/**
 * author ： yxm521
 * time    ： 2022/3/29
 */
public class HelperApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        saveUsers();
        saveTasks();
    }

    public void saveUsers(){
        List<User> users = new ArrayList<>();
        User user1 = new User("小西瓜", R.drawable.a13);
        User user2 = new User("张先生",R.drawable.a12);
        User user3 = new User("lZL2040",R.drawable.a11);
        User user4 = new User("安之若素",R.drawable.a10);
        User user5 = new User("奶油桃子",R.drawable.a9);
        User user6 = new User("青青草原",R.drawable.a8);
        User user7 = new User("墨水轻轻",R.drawable.a7);
        User user8 = new User("石梅",R.drawable.a6);
        User user9 = new User("小兔子",R.drawable.a5);
        User user10 = new User("余生",R.drawable.a4);
        User user11 = new User("飘飘呼",R.drawable.a3);
        User user12 = new User("寒烟若雨",R.drawable.a2);
        User user13 = new User("镜花水月",R.drawable.a14);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);
        users.add(user12);
        users.add(user13);
        StaticData.setUserList(users);
    }
    
    public void saveTasks(){
        List<TaskEntity> tasks = new ArrayList<>();
        TaskEntity data1 = new TaskEntity();
        data1.setDesc("需要外出几天，需要照顾一下宠物");
        data1.setName("安之若素");
        data1.setDeadline("2021-04-01 12:30");
        data1.setSite("中信小区");
        data1.setReward("300");
        data1.setHeader(R.drawable.a10);
        tasks.add(data1);

        TaskEntity data2 = new TaskEntity();
        data2.setDesc("家里的衣柜比较乱，希望找能收拾屋子的家里的衣柜比较乱，希望找能收拾屋子的");
        data2.setName("奶油桃子");
        data2.setDeadline("2022-04-02 10:19");
        data2.setSite("泰沙小区");
        data2.setReward("500");
        data2.setHeader(R.drawable.a2);
        tasks.add(data2);

        TaskEntity data3 = new TaskEntity();
        data3.setDesc("找一个上门打扫卫生的阿姨");
        data3.setName("青草香氕");
        data3.setDeadline("2022-04-01 23:34");
        data3.setSite("大兴小区");
        data3.setReward("200");
        data3.setHeader(R.drawable.a13);
        tasks.add(data3);

        TaskEntity data4 = new TaskEntity();
        data4.setDesc("项目缺人，想找一个一起一起做的");
        data4.setName("墨玲珑");
        data4.setDeadline("2022-04-02 06:30");
        data4.setSite("凡汐公司");
        data4.setReward("1000");
        data4.setHeader(R.drawable.a8);
        tasks.add(data4);

        TaskEntity data5 = new TaskEntity();
        data5.setDesc("本人最近事务繁多，需要找一位帮忙完成项目的开发，只需要1-2天时间即可");
        data5.setName("十梦九他");
        data5.setDeadline("2022-04-01 08:40");
        data5.setSite("欣鑫公司");
        data5.setReward("1100");
        data5.setHeader(R.drawable.a15);
        tasks.add(data5);

        TaskEntity data6 = new TaskEntity();
        data6.setDesc("需要外出几天，需要照顾一下宠物");
        data6.setName("垂耳兔");
        data6.setDeadline("2022-04-12 06:00");
        data6.setSite("典鑫小区");
        data6.setReward("200");
        data6.setHeader(R.drawable.a16);
        tasks.add(data6);

        TaskEntity data7 = new TaskEntity();
        data7.setDesc("家里水管破了，急需上门修理水管");
        data7.setName("余默");
        data7.setDeadline("2022-04-03 12:00");
        data7.setSite("惠霄小区");
        data7.setReward("80");
        data7.setHeader(R.drawable.a2);
        tasks.add(data7);

        TaskEntity data8 = new TaskEntity();
        data8.setDesc("厕所堵住了，有疏通厕所的上门疏通一下吗");
        data8.setName("飘逸的云");
        data8.setDeadline("2022-04-01 10:30");
        data8.setSite("大兴小区");
        data8.setReward("100");
        data8.setHeader(R.drawable.a13);
        tasks.add(data8);

        TaskEntity data9 = new TaskEntity();
        data9.setDesc("需要找一个能辅导孩子数学的老师，在暑假期间带着孩子一起学习");
        data9.setName("烟寒若雨");
        data9.setDeadline("2022-04-10 13:50");
        data9.setSite("电子厂旁");
        data9.setReward("100");
        data9.setHeader(R.drawable.a10);
        tasks.add(data9);

        TaskEntity data10 = new TaskEntity();
        data10.setDesc("找一位能够辅导孩子在化学的，马上就要学习化学了，希望能够带着孩子入门化学的学习，最好事化学专业的大学生或者研究生");
        data10.setName("镜湖月");
        data10.setDeadline("2022-04-08 11:50");
        data10.setSite("泰沙小区");
        data10.setReward("100");
        data10.setHeader(R.drawable.a2);
        tasks.add(data10);

        TaskEntity data11 = new TaskEntity();
        data11.setDesc("想找一位能带领孩子学习篮球的老师，希望周末有时间教导孩子的篮球");
        data11.setName("青草香氕");
        data11.setDeadline("2021-04-01 07:50");
        data11.setSite("大兴小区");
        data11.setReward("120");
        data11.setHeader(R.drawable.a13);
        tasks.add(data11);
        StaticData.setTasks(tasks);
    }
}
