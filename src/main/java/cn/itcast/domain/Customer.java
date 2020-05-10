package cn.itcast.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 配置客户的实体类
 * 配置映射关系
 * 1实体类和表的映射关系
 *   @Entity 声明此类是一个实体类
 *   @Table 配置实体类和表的映射关系
 *     name ：配置数据库表的名称
 * 2实体类中属性和表字段的映射关系
 *
 * @author admin
 */
@Data
@Entity
@Table(name = "cst_customer")
public class Customer {
    /**
     *  @Id 声明主键的配置
     *  @GeneratedValue 配置主键的生成策略
     *     GenerationType.IDENTITY 自增(只有底层数据库支持自增才能使用)
     *     GenerationType.SEQUENCE 序列 (使用oracle数据库时候就采用序列，oracle数据库不支持自增长)
     *     GenerationType.TABLE  jpa提供的一种机制，通过一张数据库表的形式帮助完成主键自增
     *     GenerationType.AUTO   由程序自动帮助选择主键生成策略
     *  @Column 配置属性和字段的映射关系
     *     name ：代表数据库字段名
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private long custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_source")
    private String custSource;
    @Column(name = "cust_industry")
    private String custIndustry;
    @Column(name = "cust_level")
    private String custLevel;
    @Column(name = "cust_address")
    private String custAddress;
    @Column(name = "cust_phone")
    private String custPhone;
}
