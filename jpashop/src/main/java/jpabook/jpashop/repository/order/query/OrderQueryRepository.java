package jpabook.jpashop.repository.order.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQueryDtos(){

        List<OrderQueryDto> result = finOrders();
        result.forEach(o->{
            List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
        });
        return finOrders();
    }

    private List<OrderItemQueryDto> findOrderItems(Long orderId) {

        return em.createQuery(
                "select  from OrderItem oi" +
                        " join oi.item i" +
                        " where oi.order.id = :orderId",OrderItemQueryDto.class
        ).setParameter("orderId",orderId)
                .getResultList();

    }

    private List<OrderQueryDto> finOrders() {
        return em.createQuery("select new jpabook.jpashop.repository.order.query.OrderQueryDto(o.id,m.name,o.orderDate,o.status,d.address) " +
                " from Order o " +
                " join o.member m" +
                " join o.delivery d",OrderQueryDto.class)
        .getResultList();
    }

}
