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
        Scanner sc = new Scanner(System.in); // 创建一个扫描器对象，用来接收用户的输入
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 实例化 Statement 对象
            stmt = conn.createStatement();

            // 定义一个变量，用来存储用户的选择
            int choice = 0;

            // 定义一个循环，让用户可以反复选择操作
            do {
                // 显示菜单，让用户选择操作
                System.out.println("\n欢迎使用GaussDBDemo程序，你可以进行以下操作：");
                System.out.println("1. 用户1点击退出按钮，退出微信，请用SQL语句更新用户的状态。");
                System.out.println("2. 2021年华为正式发布了基于鸿蒙的手机，鸿蒙操作系统名称是Harmony，请在登录设备中增加一项，设设备的编号是6。");
                System.out.println("3. 2021年华为正式发布了openEulerc操作系统，请在登录设备中增加一项。");
                System.out.println("4. 公安局展开新一轮预警，首先需要清除预警表中的所有历史记录，请完成。");
                System.out.println("5. 预警规则1是单笔转账超过400的用户，将该用户的微信UID以及转账的时间存入Warning表。");
                System.out.println("6. 预警规则2是累计转出超过1000的用户以及最后一次转账时间存入预警表。");
                System.out.println("7. 退出程序");
                System.out.println("请输入你的选择（1-7）：");

                // 获取用户的选择
                choice = sc.nextInt();

                // 根据用户的选择，执行相应的操作
                switch (choice) {
                    case 1:
                        // 用户1点击退出按钮，退出微信，请用SQL语句更新用户的状态。
                        updateStatus(stmt);
                        break;
                    case 2:
                        // 2021年华为正式发布了基于鸿蒙的手机，鸿蒙操作系统名称是Harmony，请在登录设备中增加一项，设设备的编号是6。
                        insertHarmony(stmt);
                        break;
                    case 3:
                        // 2021年华为正式发布了openEulerc操作系统，请在登录设备中增加一项。
                        insertOpenEuler(stmt);
                        break;
                    case 4:
                        // 公安局展开新一轮预警，首先需要清除预警表中的所有历史记录，请完成。
                        deleteWarning(stmt);
                        break;
                    case 5:
                        // 预警规则1是单笔转账超过400的用户，将该用户的微信UID以及转账的时间存入Warning表。
                        insertWarning1(stmt);
                        break;
                    case 6:
                        // 预警规则2是累计转出超过1000的用户以及最后一次转账时间存入预警表。
                        insertWarning2(stmt);
                        break;
                    case 7:
                        // 退出程序
                        System.out.println("感谢使用GaussDBDemo程序，再见！");
                        break;
                    default:
                        // 输入错误
                        System.out.println("你的输入有误，请重新输入！");
                        break;
                }
            } while (choice != 7); // 当用户输入7时，退出循环

            // 关闭资源
            stmt.close();
            conn.close();
            sc.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    // 用户1点击退出按钮，退出微信，请用SQL语句更新用户的状态的方法
    public static void updateStatus(Statement stmt) throws SQLException {
        // 执行更新
        String sql = "UPDATE UserInfo SET status = 2 WHERE UID = 1";
        int rows = stmt.executeUpdate(sql);

        // 判断是否更新成功
        if (rows > 0) {
            // 更新成功
            System.out.println("成功更新了用户1的状态！");
        } else {
            // 更新失败
            System.out.println("更新用户1的状态失败！");
        }
    }

    // 2021年华为正式发布了基于鸿蒙的手机，鸿蒙操作系统名称是Harmony，请在登录设备中增加一项，设设备的编号是6的方法
    public static void insertHarmony(Statement stmt) throws SQLException {
        // 执行插入
        String sql = "INSERT INTO Device VALUES (6, 'Harmony')";
        int rows = stmt.executeUpdate(sql);

        // 判断是否插入成功
        if (rows > 0) {
            // 插入成功
            System.out.println("成功插入了一条设备信息！");
        } else {
            // 插入失败
            System.out.println("插入设备信息失败！");
        }
    }

    // 2021年华为正式发布了openEulerc操作系统，请在登录设备中增加一项的方法
    public static void insertOpenEuler(Statement stmt) throws SQLException {
        // 执行插入
        String sql = "INSERT INTO Device(DID, Name) SELECT MAX(DID) + 1, 'openEuler' FROM Device";
        int rows = stmt.executeUpdate(sql);

        // 判断是否插入成功
        if (rows > 0) {
            // 插入成功
            System.out.println("成功插入了一条设备信息！");
        } else {
            // 插入失败
            System.out.println("插入设备信息失败！");
        }
    }

    // 公安局展开新一轮预警，首先需要清除预警表中的所有历史记录，请完成的方法
    public static void deleteWarning(Statement stmt) throws SQLException {
        // 执行删除
        String sql = "DELETE FROM Warning";
        int rows = stmt.executeUpdate(sql);

        // 判断是否删除成功
        if (rows > 0) {
            // 删除成功
            System.out.println("成功删除了所有的预警信息！");
        } else {
            // 删除失败
            System.out.println("删除预警信息失败！");
        }
    }

    // 预警规则1是单笔转账超过400的用户，将该用户的微信UID以及转账的时间存入Warning表的方法
    public static void insertWarning1(Statement stmt) throws SQLException {
        // 执行插入
        String sql = "INSERT INTO Warning SELECT UID1, SendTime FROM Transfer WHERE amount >= 400";
        int rows = stmt.executeUpdate(sql);

        // 判断是否插入成功
        if (rows > 0) {
            // 插入成功
            System.out.println("成功插入了一些预警信息！");
        } else {
            // 插入失败
            System.out.println("插入预警信息失败！");
        }
    }

    // 预警规则2是累计转出超过1000的用户以及最后一次转账时间存入预警表的方法
    public static void insertWarning2(Statement stmt) throws SQLException {
        // 执行插入
        String sql = "INSERT INTO Warning SELECT UID1, max(SendTime) FROM Transfer GROUP BY UID1 HAVING sum(amount) >= 1000";
        int rows = stmt.executeUpdate(sql);

        // 判断是否插入成功
        if (rows > 0) {
            // 插入成功
            System.out.println("成功插入了一些预警信息！");
        } else {
            // 插入失败
            System.out.println("插入预警信息失败！");
        }
    }
}