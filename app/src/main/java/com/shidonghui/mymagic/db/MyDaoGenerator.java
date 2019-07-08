package com.shidonghui.mymagic.db;

import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Dao实体类生成器
 *
 * @author gxf
 * @date 2017/11/28
 */

public class MyDaoGenerator {
    private static void addEntity(Schema schema) {
        //指定实体类
        Entity entity = schema.addEntity("TreasureChestModel");
        //添加ID属性
        entity.addStringProperty("liveId").primaryKey();
        entity.addStringProperty("startTime");
        entity.addLongProperty("watchSeconds");
        entity.addIntProperty("box1Status");
        entity.addIntProperty("box2Status");
        entity.addIntProperty("box3Status");
        entity.addIntProperty("box4Status");
        entity.addIntProperty("box5Status");
        entity.addIntProperty("box6Status");
        entity.addIntProperty("favoriteStatus");
        entity.addIntProperty("shareStatus");

    }

    public static void main(String[] args) throws Exception {
        //第一个参数是数据库版本号，第二个参数是所在包名
        Schema schema = new Schema(12, "tempdb");
        addEntity(schema);
        try {
            //第二个参数是要生成实体类的全路径,这里采用相对路径的写法
            //'..'代表工程前一个目录,接着是工程名/app(AndroidStudio生成)
            new de.greenrobot.daogenerator.DaoGenerator().generateAll(schema, "../MyMagic/app/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
