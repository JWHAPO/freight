package app.hapo.car.freight.service.order

import app.hapo.car.freight.domain.order.Order
import app.hapo.car.freight.domain.order.OrderRepository
import app.hapo.car.freight.domain.order.OrderStatus
import app.hapo.car.freight.domain.order.response.OrderResponse
import app.hapo.car.freight.domain.order.response.OrderResponseRepository
import app.hapo.car.freight.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime

import static org.mockito.BDDMockito.given

/*
 * Created by hapo
 * Date : 19. 3. 31 오전 1:00
 * Description : 
 */

@SpringBootTest
class OrderServiceTest extends Specification {
    @Autowired
    private OrderService orderService

    @MockBean(name = "orderResponseRepository")
    private OrderResponseRepository orderResponseRepository

    @MockBean(name = "orderRepository")
    private OrderRepository orderRepository
    @Autowired
    private UserService userService


    def "complete"(){
        given:

        Order order = new Order("FIGHTING FREIGHT ORDER3", 3L, "ADDRESS 1","ADDRESS 2",135000L,LocalDate.parse("2019-04-01")
                                ,LocalTime.parse("11:30:00"), 20000L, "N","FIGHTING",OrderStatus.IN_PROGRESS,"",null
        )

        OrderResponse orderResponse1 = new OrderResponse(order.getOrderId(), 1L, LocalDate.parse("2019-03-31"), LocalTime.parse("14:00:00")
                ,30000L, 25000L, "To be best","",3L,"Y")

        OrderResponse orderResponse2 = new OrderResponse(order.getOrderId(), 2L, LocalDate.parse("2019-04-01"), LocalTime.parse("11:00:00")
                                                        ,30000L, 25000L, "To be best No2","",0L,"N")

        orderResponse1.setOrderResponseId(98L)
        orderResponse2.setOrderResponseId(99L)

        List<OrderResponse> orderResponses = new ArrayList<>()
        orderResponses.add(orderResponse1)
        orderResponses.add(orderResponse2)

        order.setOrderResponses(orderResponses)

        given(orderRepository.save(order)).willReturn(order)
        given(orderResponseRepository.save(orderResponse1)).willReturn(orderResponse1)
        given(orderResponseRepository.save(orderResponse2)).willReturn(orderResponse2)

        when:
        Optional<Order> resultOrder = orderService.save(order)

        then :
        resultOrder.get() == order
    }
}
