/**
 这个类用于测试Stock对象的异常处理。
 */
public class DefensiveProgram{
    //测试stock生成的日期和产品种类的异常处理
    public static void main(String[] args){
        try {
            //过去时间
            //Stock s1 = new Stock(1, 1.0f, 1.0f, "food", 1.0f, "name", "2014-05-01");

            //未来时间
            //Stock s2 = new Stock(1, 1.0f, 1.0f, "food", 1.0f, "name", "2030-04-01");

            //合法时间，非法产品类型
            Stock s3 = new Stock(1, 1.0f, 1.0f, "type", 1.0f, "name", "2018-04-01");

            //合法时间，合法产品类型
            //Stock s4 = new Stock(1, 1.0f, 1.0f, "food", 1.0f, "whatever", "2018-04-01");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
