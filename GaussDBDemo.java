import java.sql.*;
import java.util.Scanner;

public class GaussDBDemo {
    static final String JDBC_DRIVER = "com.huawei.opengauss.jdbc.Driver";
    static final String DB_URL = "jdbc:opengauss://110.41.120.191:8000/Wechat?ApplicationName=app1";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "abc888888@";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Scanner sc = new Scanner(System.in); // 创建一个扫描器对象，用于接收用户输入
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println(" 实例化 Statement 对象...");
            stmt = conn.createStatement();
            String sql;
            int choice; // 用于存储用户的选择
            do {
                System.out.println("\n欢迎使用GaussDBDemo程序，我们提供以下服务：");
                System.out.println("1. 用户1点击退出按钮，退出微信，请用SQL语句更新用户的状态。");
                System.out.println("2. 2021年华为正式发布了基于鸿蒙的手机，鸿蒙操作系统名称是Harmony，请在登录设备中增加一项，设设备的编号是6。");
                System.out.println("3. 2021年华为正式发布了openEulerc操作系统，请在登录设备中增加一项。");
                System.out.println("4. 公安局展开新一轮预警，首先需要清除预警表中的所有历史记录，请完成。");
                System.out.println("5. 预警规则1是单笔转账超过400的用户，将该用户的微信UID以及转账的时间存入Warning表。");
                System.out.println("6. 预警规则2是累计转出超过1000的用户以及最后一次转账时间存入预警表。");
                System.out.println("0. 退出程序。");
                System.out.println("请输入你想要执行的功能编号：");
                choice = sc.nextInt(); // 接收用户输入的整数
                switch (choice) {
                    case 1:
                        // 功能1：更新用户1的状态为2（离线）
                        sql = "UPDATE \"Schema_ljh_zzh\".UserInfo SET Status = 2 WHERE UID = 1";
                        stmt.executeUpdate(sql); // 执行更新语句
                        System.out.println("用户1的状态已更新为离线。");
                        break;
                    case 2:
                        // 功能2：在登录设备中增加一项，设备编号为6，名称为Harmony
                        sql = "INSERT INTO \"Schema_ljh_zzh\".Device VALUES (6, 'Harmony')";
                        stmt.executeUpdate(sql); // 执行插入语句
                        System.out.println("登录设备中已增加一项，设备编号为6，名称为Harmony。");
                        break;
                    case 3:
                        // 功能3：在登录设备中增加一项，设备编号为最大编号加1，名称为openEuler
                        sql = "INSERT INTO \"Schema_ljh_zzh\".Device(DID, Name) SELECT MAX(DID) + 1, 'openEuler' FROM \"Schema_ljh_zzh\".Device";
                        stmt.executeUpdate(sql); // 执行插入语句
                        System.out.println("登录设备中已增加一项，设备编号为最大编号加1，名称为openEuler。");
                        break;
                    case 4:
                        // 功能4：删除预警表中的所有记录
                        sql = "DELETE FROM \"Schema_ljh_zzh\".Warning";
                        stmt.executeUpdate(sql); // 执行删除语句
                        System.out.println("预警表中的所有记录已删除。");
                        break;
                    case 5:
                        // 功能5：将单笔转账超过400的用户的微信UID以及转账的时间存入Warning表
                        sql = "INSERT INTO \"Schema_ljh_zzh\".Warning SELECT UID1, SendTime FROM \"Schema_ljh_zzh\".Transfer WHERE Amount >= 400";
                        stmt.executeUpdate(sql); // 执行插入语句
                        System.out.println("单笔转账超过400的用户的微信UID以及转账的时间已存入Warning表。");
                        break;
                    case 6:
                        // 功能6：将累计转出超过1000的用户以及最后一次转账时间存入预警表
                        sql = "INSERT INTO \"Schema_ljh_zzh\".Warning SELECT UID1, MAX(SendTime) FROM \"Schema_ljh_zzh\".Transfer GROUP BY UID1 HAVING SUM(Amount) >= 1000";
                        stmt.executeUpdate(sql); // 执行插入语句
                        System.out.println("累计转出超过1000的用户以及最后一次转账时间已存入预警表。");
                        break;
                    case 0:
                        // 退出程序
                        System.out.println("感谢使用，再见！");
                        break;
                    default:
                        // 输入无效的编号
                        System.out.println("请输入有效的编号！");
                        break;
                }
            } while (choice != 0); // 循环输入直到用户输入0

            // 完成后关闭
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // 什么都不做
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            if (sc != null) // 关闭扫描器对象
                sc.close();
        }
        System.out.println("Goodbye!");
    }
}