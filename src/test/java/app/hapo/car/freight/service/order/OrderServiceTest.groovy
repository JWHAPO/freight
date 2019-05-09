package app.hapo.car.freight.service.order

import app.hapo.car.freight.domain.order.Order
import app.hapo.car.freight.domain.order.OrderRepository
import app.hapo.car.freight.domain.order.OrderStatus
import app.hapo.car.freight.domain.order.response.OrderResponse
import app.hapo.car.freight.domain.order.response.OrderResponseRepository
import app.hapo.car.freight.domain.user.User
import app.hapo.car.freight.domain.user.UserRepository
import app.hapo.car.freight.service.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification

import javax.swing.text.html.Option
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
    private OrderResponseRepository orderResponseRepository
    @Autowired
    private OrderRepository orderRepository
    @Autowired
    private OrderService orderService
    @Autowired
    private UserRepository userRepository



    def setup(){
        Order order = new Order("FIGHTING FREIGHT ORDER3", 3L, "ADDRESS 1","ADDRESS 2",135000L,LocalDate.parse("2019-04-01")
                ,LocalTime.parse("11:30:00"), 20000L, "N","FIGHTING",OrderStatus.IN_PROGRESS,"",null
        )
        order.setOrderId(3L)
        OrderResponse orderResponse1 = new OrderResponse(order.getOrderId(), 1L, LocalDate.parse("2019-03-31"), LocalTime.parse("14:00:00")
                ,30000L, 25000L, "To be best","",3L,"Y")

        OrderResponse orderResponse2 = new OrderResponse(order.getOrderId(), 2L, LocalDate.parse("2019-04-01"), LocalTime.parse("11:00:00")
                ,30000L, 25000L, "To be best No2","",0L,"N")

        orderRepository.save(order)
        orderResponseRepository.save(orderResponse1)
        orderResponseRepository.save(orderResponse2)
    }

    def cleanup(){
        orderRepository.deleteAll()
    }

    def "complete"(){

        when:
        Optional<Order> testOrder = orderRepository.findById(3L)
        Optional<User> beforeUser = userRepository.findById(1L)
        Optional<Order> resultOrder = orderService.complete(testOrder.get())
        Optional<User> afterUser = userRepository.findById(1L)
        Optional<OrderResponse> isSelectedOrderResponse = orderResponseRepository.findByOrderIdAndIsSelected(resultOrder.get().orderId, "Y")

        then :
        resultOrder.get().orderResponses.size() == 2

        afterUser.get().experienceValue == beforeUser.get().experienceValue + isSelectedOrderResponse.get().resultPoint*5

    }
}
