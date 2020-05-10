package cn.itcast.test;

import cn.itcast.utils.JpaUtils;
import org.hibernate.Transaction;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jpql查询
 */
public class JpqlTest {
    /**
     * 查询全部
     * jpql: from Customer /cn.itcast.domain.Customer
     * sql：select * from cst_customer
     */

    @Test
    public void testFindAll() {
       /*1获取EntityManager对象*/
        EntityManager entityManager= JpaUtils.getEntityManager();
        /*2获取事务*/
        EntityTransaction tx =entityManager.getTransaction();
        tx.begin();
        /*3增删改查 查询全部
        * 创建Query查询，query对象才是jpql的对象
        * */
        String jpql=" from cn.itcast.domain.Customer";
        Query query =entityManager.createQuery(jpql);
        /*发送查询并封装结果集*/
        List list =query.getResultList();
        for (Object obj:list) {
            System.out.println(obj);
        }
        /*4提交事务*/
        tx.commit();
        /*5释放资源*/
        entityManager.close();

    }

}
