package com.ch07.repository.shop;


import com.ch07.dto.CustomerDTO;
import com.ch07.dto.ProductAggDTO;
import com.ch07.entity.QParent;
import com.ch07.entity.shop.Customer;
import com.ch07.entity.shop.Product;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ShopQueryDslTest {

    private static final long

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    private QCustomer

    @Test
    void test01(){
       List<Product> products =  jpaQueryFactory
                                    .selectFrom(QProduct)
                                    .fetch();
       System.out.println(products);
    }

    @Test
    void test02(){
        List<Product> products = jpaQueryFactory
                .select(
                        Projections.fields(
                                Product.class,
                                qProduct.productId,
                                qProduct.productName,
                                qProduct.price
                        )
                )
                .from(qProduct)
                .fetch(); // fetch 결과는 List
        System.out.println(products);



    }

    @Test
    void test03(){
       List<Customer> customers1 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.name.eq("김유신")).fetch();.
       List<Customer> customers2 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.name.eq("김유신")).fetch();.
    }

    @Test
    void test04(){
        List<Customer> customers1 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.gt(30)).fetch();
        List<Customer> customers2 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.goe(30)).fetch();
        List<Customer> customers3 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.lt(30)).fetch();
        List<Customer> customers4 = jpaQueryFactory.selectFrom(qCustomer).where(qCustomer.age.loe(30)).fetch();

        System.out.println(customers1);
        System.out.println(customers2);
        System.out.println(customers3);
        System.out.println(customers4);

    }

    @Test
    void test05(){
        List<Customer> customers = jpaQueryFactory
                                    .selectFrom(qCustomer)
                                    .where(qCustomer.addr.in("서울", "김해"))
                                    .fetch();
        System.out.println(customers);
    }

    @Test
    void test06(){
        List<Customer> customers =  jpaQueryFactory
                                        .select(qCustomer)
                                        .where(qCustomer.name.like("%신"))
                                        .fetch();

        System.out.println(customers);
    }

    @Test
    void test07(){
        jpaQueryFactory.selectFrom(qProduct)
                        .where(qProduct.stock.gt(0))
                        .orderBy(qProduct.price.desc())
                        .fetch();
    }

    @Test
    void test08(){
        // 0에서 3까지
        // 게시판 목록 페이징 할 떄

        List<Product> Products =  jpaQueryFactory
                                    .select(qProduct)
                                    .where(qProduct.name.gt(0))
                                    .orderBy()
                                    .offset(0)
                                    .limit(3)
                                    .fetch();

        System.out.println(Products);
    }

    @Test
    void test09(){
       ProductAggDTO = jpaQueryFactory
                .select(Projections.fields(
                        Product.class,
                        qProduct.price.sum().as("priceSum");
                        qProduct.price.sum().as("priceAvg");
                        qProduct.price.sum().as("priceMax");
                        qProduct.price.sum().as("priceMin");
                )
            )
            .from(qProduct)
            .fetchOne();

        System.out.println(ProductAggDTO);
    }

    @Test
    void test10(){
      List<CustomerDTO> customerDTOS  jpaQueryFactory
                .select(
                        Projections.fields(
                            CustomerDTO.class,
                                qOrder.customer.custId,
                                qOrder.customer.name,
                                qOrder.customer.custId.count().as("orderCount")
                        )
                )
                .from(qOrder)
                .where(qOrder.orderStatus.eq(1))
                .groupBy(qOrder.customer.custId)
                .having(qOrder.customer.custId.count().goe(2))
                .fetch();

        System.out.println(customerDTOS);
    }

    @Transactional
    @Test
    void test11(){
      List<Tuple> result = jpaQueryFactory
                                .select(qOrder, qCustomer)
                                .from(qOrder)
                                .join(qCustomer)
                                .on(qOrder.customer.eq(qCustomer))
                                .fetch();

        System.out.println(result);

    }

    @Test
    void test12(){




    }

    @Test
    void test13(){

    }



}
