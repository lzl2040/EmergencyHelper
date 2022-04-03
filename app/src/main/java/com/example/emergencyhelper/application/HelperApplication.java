package com.example.emergencyhelper.application;

import android.app.Application;

import com.example.emergencyhelper.R;
import com.example.emergencyhelper.bean.Category;
import com.example.emergencyhelper.bean.Communicate;
import com.example.emergencyhelper.bean.Task;
import com.example.emergencyhelper.bean.User;
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
        save();
    }

    public void save(){
        //存储分类
        List<Category> categories = new ArrayList<>();
        //存储任务
        List<Task> tasks = new ArrayList<>();
        //存储用户
        List<User> users = new ArrayList<>();
        Category category = new Category(1,getString(R.string.home_service));
        Category category1 = new Category(2,getString(R.string.social_service));
        Category category2 = new Category(3,getString(R.string.emergency_service));
        Category category3 = new Category(4,getString(R.string.school_service));
        Category category4 = new Category(5,getString(R.string.old_service));
        Category category5 = new Category(6,getString(R.string.children_service));
        Category category6 = new Category(7,getString(R.string.cooporate_service));
        Category category7 = new Category(8,getString(R.string.disable_service));
        //保存用户
        User user1 = new User("18166158481","小西瓜", R.drawable.a13,"123456");
        User user2 = new User("18166158482","张先生",R.drawable.a12,"123456");
        User user3 = new User("18166158483","lZL2040",R.drawable.a11,"123456");
        User user4 = new User("18166158484","安之若素",R.drawable.a10,"123456");
        User user5 = new User("18166158485","奶油桃子",R.drawable.a9,"123456");
        User user6 = new User("18166158486","青青草原",R.drawable.a8,"123456");
        User user7 = new User("18166158487","墨水轻轻",R.drawable.a7,"123456");
        User user8 = new User("18166158488","石梅",R.drawable.a6,"123456");
        User user9 = new User("18166158489","小兔子",R.drawable.a5,"123456");
        User user10 = new User("18166158480","余生",R.drawable.a4,"123456");
        User user11 = new User("18166158491","飘飘呼",R.drawable.a3,"123456");
        User user12 = new User("18166158492","寒烟若雨",R.drawable.a2,"123456");
        User user13 = new User("18166158493","镜花水月",R.drawable.a14,"123456");
        User user = new User("18274427303","辰天框",R.drawable.a2,"123456");
        //保存任务
        Task task = new Task("需要外出几天，需要照顾一下宠物","2022-04-01 12:30","中信小区",300,user1,category);
        Task task1 = new Task("家里的衣柜比较乱，希望找能收拾屋子的家里的衣柜比较乱，希望找能收拾屋子的","2022-04-02 10:19","泰沙小区",500,user2,category);
        Task task2 = new Task("找一个上门打扫卫生的阿姨","2022-04-01 23:34","大兴小区",200,user2,category);
        category.getTasks().add(task);
        category.getTasks().add(task1);
        category.getTasks().add(task2);

        Task task3 = new Task("项目缺人，想找一个一起一起做的","2022-04-02 06:30","凡汐公司",1000,user3,category6);
        Task task4 = new Task("本人最近事务繁多，需要找一位帮忙完成项目的开发，只需要1-2天时间即可","2022-04-01 08:40","欣鑫公司",1100,user4,category6);
        category6.getTasks().add(task3);
        category6.getTasks().add(task4);

        Task task5 = new Task("需要外出几天，需要照顾一下宠物","2022-04-12 06:00","典鑫小区",200,user5,category2);
        Task task6 = new Task("家里水管破了，急需上门修理水管","2022-04-03 12:00","惠霄小区",80,user6,category2);
        Task task7 = new Task("厕所堵住了，有疏通厕所的上门疏通一下吗","2022-04-01 10:30","大兴小区",100,user7,category2);
        category2.getTasks().add(task5);
        category2.getTasks().add(task6);
        category2.getTasks().add(task7);

        Task task8 = new Task("需要找一个能辅导孩子数学的老师，在暑假期间带着孩子一起学习","2022-04-10 13:50","电子厂旁",100,user8,category5);
        Task task9 = new Task("找一位能够辅导孩子在化学的，马上就要学习化学了，希望能够带着孩子入门化学的学习，最好事化学专业的大学生或者研究生","2022-04-08 11:50","泰沙小区",100,user9,category5);
        Task task10 = new Task("想找一位能带领孩子学习篮球的老师，希望周末有时间教导孩子的篮球","2021-04-01 07:50","大兴小区",120,user10,category5);
        category5.getTasks().add(task8);
        category5.getTasks().add(task9);
        category5.getTasks().add(task10);

        Task task11 = new Task("需要找位人，去看望一下家里的老人","2022-04-04 05:50","兴安小区",300,user11,category4);
        Task task12 = new Task("老人一人在家，家里老人需要去看医院，想找一个能一起去医院看病的","2022-04-03 12:50","沙尾小区",500,user12,category4);
        Task task13 = new Task("老人家里卫生很乱，需要找人打理一下","2022-04-02 15:20","大兴小区",200,user13,category4);
        category4.getTasks().add(task11);
        category4.getTasks().add(task12);
        category4.getTasks().add(task13);

        Task task14 = new Task("有会高数的吗，能帮忙补习一下高数吗","2022-04-01 16:30","湘潭大学",100,user1,category3);
        Task task15 = new Task("有多余的c语言书，有需要的吗","2022-03-31 12:23","湘潭大学",10,user4,category3);
        Task task16 = new Task("能帮忙代取一下琴湖的快递吗","2021-04-01 18:00","湘潭大学",10,user8,category3);
        category3.getTasks().add(task14);
        category3.getTasks().add(task15);
        category3.getTasks().add(task16);

        Task task17 = new Task("项目需要人帮忙辅助做一下，需要精通java","2022-04-01 16:23","凡汐科技",1000,user10,category1);
        Task task18 = new Task("在泰山旅游，想找一个熟悉这里环境的当地人","2022-04-03 19:20","泰山",400,user5,category1);
        category1.getTasks().add(task17);
        category1.getTasks().add(task18);

        Task task19 = new Task("在湘大社区需要找人帮忙拿下快递","2022-04-04 16:23","湘大社区",50,user3,category7);
        Task task20 = new Task("可以帮忙买下菜吗,行动不便不好去买菜","2022-04-02 16:20","联建小吃街",100,user8,category7);
        category7.getTasks().add(task19);
        category7.getTasks().add(task20);
        users.add(user1);users.add(user2);users.add(user3);users.add(user4);
        users.add(user5);users.add(user6);users.add(user7);users.add(user8);
        users.add(user9);users.add(user10);users.add(user11);users.add(user12);
        users.add(user13);

        tasks.add(task);tasks.add(task1);tasks.add(task2);tasks.add(task3);
        tasks.add(task4);tasks.add(task5);tasks.add(task6);tasks.add(task7);
        tasks.add(task8);tasks.add(task9);tasks.add(task10);tasks.add(task11);
        tasks.add(task12);tasks.add(task13);tasks.add(task14);tasks.add(task15);
        tasks.add(task16);tasks.add(task17);tasks.add(task18);tasks.add(task19);
        tasks.add(task20);

        categories.add(category);categories.add(category1);categories.add(category2);
        categories.add(category3);categories.add(category4);categories.add(category5);
        categories.add(category6);categories.add(category7);

        StaticData.setCurUser(user);
        StaticData.setCategories(categories);
        StaticData.setTaskList(tasks);
        StaticData.setUserList(users);
        StaticData.setCommunicates(new ArrayList<Communicate>());
    }
}
