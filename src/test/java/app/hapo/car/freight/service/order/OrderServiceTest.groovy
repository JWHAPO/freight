package app.hapo.car.freight.service.order

import app.hapo.car.freight.domain.order.Order
import app.hapo.car.freight.domain.order.OrderRepository
import app.hapo.car.freight.domain.order.OrderStatus
import app.hapo.car.freight.domain.order.response.OrderResponse
import app.hapo.car.freight.domain.order.response.OrderResponseRepository
import app.hapo.car.freight.domain.user.User
import app.hapo.car.freight.domain.user.UserRepository
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
    @MockBean(name = "orderRepository")
    private OrderRepository orderRepository
    @MockBean(name = "orderService")
    private OrderService orderService

    @MockBean(name = "orderResponseRepository")
    private OrderResponseRepository orderResponseRepository
    @MockBean(name = "userRepository")
    private UserRepository userRepository


    def "complete"(){
        given:
        OrderResponse orderResponse1 = new OrderResponse()
        orderResponse1.orderResponseId = 3L
        orderResponse1.orderId = 3L
        orderResponse1.userId = 1L
        orderResponse1.pickupDate = LocalDate.parse("2019-03-31")
        orderResponse1.pickupTime = LocalTime.parse("14:00:00")
        orderResponse1.suggestedPrice = 30000L
        orderResponse1.currentAvgPrice = 25000L
        orderResponse1.sellerMessage = "To be Best"
        orderResponse1.resultPoint = 3L
        orderResponse1.isSelected = "Y"


        OrderResponse orderResponse2 = new OrderResponse()
        orderResponse2.orderResponseId = 4L
        orderResponse2.orderId = 3L
        orderResponse2.userId = 2L
        orderResponse2.pickupDate = LocalDate.parse("2019-04-01")
        orderResponse2.pickupTime = LocalTime.parse("14:30:00")
        orderResponse2.suggestedPrice = 20000L
        orderResponse2.currentAvgPrice = 25000L
        orderResponse2.sellerMessage = "To be Best No 2"
        orderResponse2.resultPoint = 0L
        orderResponse2.isSelected = "N"

        List<OrderResponse> orderResponses = new ArrayList<>()
        orderResponses.add(orderResponse1)
        orderResponses.add(orderResponse2)


        Order order = new Order()
        order.orderId = 3L
        order.description = "FIGHTING FREIGHT ORDER3"
        order.carId = 3L
        order.departureAddress = "ADDRESS 1"
        order.arrivalAddress = "ADDRESS 2"
        order.distance = 135000L
        order.hopeDate = LocalDate.parse("2019-04-01")
        order.hopeTime = LocalTime.parse("11:30:00")
        order.hope_price = 20000L
        order.isMixed = "N"
        order.remark = "FIGHTING"
        order.status = OrderStatus.IN_PROGRESS
        order.cancelRemark = ""
        order.orderResponses = orderResponses

        given(orderRepository.save(order)).willReturn(order)

        when:
        orderService.save(order)
        Optional<OrderResponse> orderResponse = orderResponseRepository.findByOrderIdAndIsSelected(order.getOrderId(), "Y")
        Optional<User> beforeUser = userRepository.findById(orderResponse.get().getUserId())

        Optional<Order> resultOrder = orderService.complete(order)
        Optional<User> afterUser = userRepository.findById(orderResponse.get().getUserId())

        then :
        Long afterVal = orderResponse1.resultPoint*5L
        beforeUser.get().experienceValue + afterVal == afterUser.get().experienceValue


    }
}
