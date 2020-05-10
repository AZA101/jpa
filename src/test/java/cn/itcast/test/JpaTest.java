package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


public class JpaTest {
    /**
     * 利用jpa测试保存
     * jpa的操作步骤
     *    1加载配置文件创建工厂(实体管理器工厂)对象
     *    2通过实体管理器工厂获取实体管理器
     *    3获取事务对象，开启事务
     *    4完成增删改查操作
     *    5提交事务（回滚事务）
     *    6释放资源
     */
    @Test
    public void testSave(){
        /*1加载配置文件创建工厂(实体管理器工厂)对象  PersistenceProvider*/
       // EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        /*2通过实体管理器工厂获取实体管理器*/
      //  EntityManager entityManager =factory.createEntityManager();
        EntityManager entityManager= JpaUtils.getEntityManager();
        /*3获取事务对象，开启事务
        *    先获取事务对象 */
        EntityTransaction tx =entityManager.getTransaction();
        tx.begin();/*开启事务*/
        /*4完成增删改查操作*/
        Customer customer=new Customer();
        customer.setCustName("李四");
        customer.setCustAddress("云南");
        customer.setCustPhone("123456789");
        customer.setCustIndustry("教育");
        /**
         * EntityManager对象：实体类管理器
         *  beginTransaction : 创建事务对象
         *  getTransaction :获取事务对象
         *   presist : 保存
         *   merge  :更新
         *   remove : 删除
         *   find/getRefrence :根据id查询
         */
        entityManager.persist(customer);//通过实体管理器进行保存操作
        /*5提交事务*/
        /**
         * Transaction 对象 ： 事务
         * begin :开启事务
         * commit :提交事务
         * rollback : 回滚
         */
        tx.commit();
        /*6释放资源*/
        entityManager.close();
       // factory.close();
    }
    @Test
    public void testFind(){
        /*1通过工具类获取EntityManager对象*/
        EntityManager entityManager=JpaUtils.getEntityManager();
        /*2获取事务*/
        EntityTransaction tx=entityManager.getTransaction();
        tx.begin();
        /*3增删改查  根据id查询用户
        * 通过entityManager对象同数据库进行操作
        * find ：根据id查询数据
        *    class :查询数据的结果需要包装的实体类类型的字节码
        *     id : 查询的主键的取值
        *  使用find方法查询：
        *    1查询对象就是当前客户对象本身
        *    2再调用find方法的时候，就会发送sql语句查询数据库
        * */
         Customer customer=entityManager.find(Customer.class,1l);
         Assert.assertNotNull(customer);
        /*4提交事务*/
        tx.commit();
        /*5释放资源*/
        entityManager.close();
    }
    @Test
    public void testReference(){
        /*1通过工具类获取EntityManager对象*/
        EntityManager entityManager=JpaUtils.getEntityManager();
        /*2获取事务*/
        EntityTransaction tx=entityManager.getTransaction();
        tx.begin();
        /*3增删改查  根据id查询用户
         * 通过entityManager对象同数据库进行操作
         * Reference ：根据id查询数据
         *    class :查询数据的结果需要包装的实体类类型的字节码
         *     id : 查询的主键的取值
         *   使用Reference方法查询：
         *     1获取的对象是一个动态代理对象
         *     2调用getReference方法不会立即发送sql语句查询数据库
         *       当调用查询结果对象的时候，才会发送查询的sql语句，什么时候使用，什么时候发送语句
         *     这是一种懒加载模式
         * */
        Customer customer=entityManager.getReference(Customer.class,1l);
        Assert.assertNotNull(customer);
        /*4提交事务*/
        tx.commit();
        /*5释放资源*/
        entityManager.close();
    }


    @Test
    public void testRemove(){
        /*1通过工具类获取EntityManager对象*/
        EntityManager entityManager=JpaUtils.getEntityManager();
        /*2获取事务*/
        EntityTransaction tx=entityManager.getTransaction();
        tx.begin();
        /*3增删改查  删除数据
        *    1先查询出要删除的数据
        *    2再删除数据*/
        Customer customer=entityManager.find(Customer.class,1l);
        entityManager.remove(customer);
        /*4提交事务*/
        tx.commit();
        /*5释放资源*/
        entityManager.close();
    }

    @Test
    public void testUpdate(){
        /*1通过工具类获取EntityManager对象*/
        EntityManager entityManager=JpaUtils.getEntityManager();
        /*2获取事务*/
        EntityTransaction tx=entityManager.getTransaction();
        tx.begin();
        /*3增删改查  更新数据
         *    1先查询出要更新的数据
         *    2再更新数据*/
        Customer customer=entityManager.find(Customer.class,2l);
        customer.setCustPhone("456561325");
        entityManager.merge(customer);
        /*4提交事务*/
        tx.commit();
        /*5释放资源*/
        entityManager.close();
    }


}
