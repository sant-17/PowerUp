package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.NewOrderStatusNotValidException;
import com.pragma.powerup.domain.exception.UserCantOrderException;
import com.pragma.powerup.domain.exception.UserFromDifferentRestaurantException;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantEmpModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.spi.ICheckUserCanOrderPort;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.domain.spi.IRestaurantEmpPersistencePort;
import com.pragma.powerup.domain.spi.IUserContextPort;
import com.pragma.powerup.domain.spi.IUserIdPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderUseCaseTest {

    @InjectMocks OrderUseCase underTest;
    @Mock IOrderPersistencePort orderPersistencePort;
    @Mock IRestaurantEmpPersistencePort employeePersistencePort;
    @Mock ICheckUserCanOrderPort checkUserCanOrderPort;
    @Mock IUserContextPort userContextPort;
    @Mock IUserIdPort userIdPort;

    @Test
    void saveOrder() {
        //given
        String expectedUsernameContext = "usuario@email.com";
        Long expectedUserId = 1L;
        OrderModel orderModel = OrderUseCaseDataTest.getOrder();

        //when
        when(userContextPort.getUserContext()).thenReturn(expectedUsernameContext);
        when(userIdPort.getUserIdByEmail(expectedUsernameContext)).thenReturn(expectedUserId);
        when(checkUserCanOrderPort.canUserMakeNewOrder(expectedUsernameContext)).thenReturn(true);

        underTest.saveOrder(orderModel);

        //then
        verify(orderPersistencePort).saveOrder(orderModel);
    }

    @Test
    void dontSaveOrderDisplayException(){
        //given
        String expectedUsernameContext = "usuario@email.com";
        Long expectedUserId = 1L;
        OrderModel orderModel = OrderUseCaseDataTest.getOrder();

        //when
        when(userContextPort.getUserContext()).thenReturn(expectedUsernameContext);
        when(userIdPort.getUserIdByEmail(expectedUsernameContext)).thenReturn(expectedUserId);
        when(checkUserCanOrderPort.canUserMakeNewOrder(expectedUsernameContext)).thenReturn(false);

        //then
        assertThatThrownBy(() -> underTest.saveOrder(orderModel))
                .isInstanceOf(UserCantOrderException.class);
    }

    @Test
    public void testGetOrdersByStatus() {
        // Paso 2: Definir valores para los parámetros pageNumber, pageSize y status
        Integer pageNumber = 1;
        Integer pageSize = 10;
        String status = "PENDIENTE";

        // Paso 3: Crear lista de órdenes simuladas
        List<OrderModel> simulatedOrders = new ArrayList<>();
        OrderModel order1 = new OrderModel();
        order1.setId(1L);
        order1.setStatus("PENDIENTE");
        simulatedOrders.add(order1);
        OrderModel order2 = new OrderModel();
        order2.setId(2L);
        order2.setStatus("PENDIENTE");
        simulatedOrders.add(order2);

        // Paso 4: Definir comportamiento esperado del componente de persistencia de órdenes
        when(orderPersistencePort.getOrdersByStatus(pageNumber, pageSize, status)).thenReturn(simulatedOrders);

        // Paso 5: Llamar al método getOrdersByStatus y almacenar resultado en variable
        List<OrderModel> result = underTest.getOrdersByStatus(pageNumber, pageSize, status);

        // Paso 6: Verificar que el resultado es la lista simulada que esperabas obtener
        assertEquals(simulatedOrders, result);
    }

    @Test
    void setChef(){
        //given
        Long id = 1L;
        String expectedUsernameContext = "usuario@email.com";
        Long expectedUserId = 1L;
        OrderModel orderModel = OrderUseCaseDataTest.getOrder();
        RestaurantEmpModel restaurantEmpModel = new RestaurantEmpModel(1L, 1L);

        //when
        when(userContextPort.getUserContext())
                .thenReturn(expectedUsernameContext);

        when(userIdPort.getUserIdByEmail(expectedUsernameContext))
                .thenReturn(expectedUserId);

        when(employeePersistencePort.getEmployeeById(expectedUserId))
                .thenReturn(restaurantEmpModel);

        when(orderPersistencePort.getOrderById(id))
                .thenReturn(orderModel);

        underTest.setChef(id);

        verify(orderPersistencePort).setChef(id);

        //then
        assertThatThrownBy(() -> underTest.saveOrder(orderModel))
                .isInstanceOf(UserCantOrderException.class);
    }

    @Test
    public void testSetChefWithOrderFromDifferentRestaurant() {
        String employeeEmail = "employee@example.com";
        Long employeeId = 1L;
        Long orderRestaurantId = 2L;

        when(userContextPort.getUserContext()).thenReturn(employeeEmail);
        when(userIdPort.getUserIdByEmail(anyString())).thenReturn(employeeId);

        RestaurantEmpModel restaurantEmpModel = new RestaurantEmpModel(employeeId, orderRestaurantId);

        when(employeePersistencePort.getEmployeeById(employeeId)).thenReturn(restaurantEmpModel);

        OrderModel orderModel = new OrderModel();
        orderModel.setId(1L);
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(orderRestaurantId + 1);
        orderModel.setRestaurant(restaurantModel);
        orderModel.setStatus("APROBADO");

        when(orderPersistencePort.getOrderById(1L)).thenReturn(orderModel);

        assertThatThrownBy(() -> underTest.setChef(1L))
                .isInstanceOf(UserFromDifferentRestaurantException.class);
    }

    @Test
    public void testSetChefWithOrderNotInPendingStatus() {
        String employeeEmail = "employee@example.com";
        Long employeeId = 1L;
        Long orderRestaurantId = 2L;

        when(userContextPort.getUserContext()).thenReturn(employeeEmail);
        when(userIdPort.getUserIdByEmail(anyString())).thenReturn(employeeId);

        RestaurantEmpModel restaurantEmpModel = new RestaurantEmpModel(employeeId, orderRestaurantId);


        when(employeePersistencePort.getEmployeeById(employeeId)).thenReturn(restaurantEmpModel);

        OrderModel orderModel = new OrderModel();
        orderModel.setId(1L);
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setId(orderRestaurantId);
        orderModel.setRestaurant(restaurantModel);
        orderModel.setStatus("APROBADO");

        when(orderPersistencePort.getOrderById(1L)).thenReturn(orderModel);

        assertThatThrownBy(() -> underTest.setChef(1L))
                .isInstanceOf(NewOrderStatusNotValidException.class);
    }

    @Test
    void setOrderStatusReadyThrowNewOrderStatusNotValidException(){
        Long id = 1L;
        String expectedUsernameContext = "usuario@email.com";
        Long expectedUserId = 1L;
        OrderModel orderModel = OrderUseCaseDataTest.getOrder();
        RestaurantEmpModel restaurantEmpModel = new RestaurantEmpModel(1L, 1L);

        //when
        when(userContextPort.getUserContext())
                .thenReturn(expectedUsernameContext);

        when(userIdPort.getUserIdByEmail(expectedUsernameContext))
                .thenReturn(expectedUserId);

        when(employeePersistencePort.getEmployeeById(expectedUserId))
                .thenReturn(restaurantEmpModel);

        when(orderPersistencePort.getOrderById(id))
                .thenReturn(orderModel);

        //then
        assertThatThrownBy(() -> underTest.setOrderStatusReady(id))
                .isInstanceOf(NewOrderStatusNotValidException.class);
    }
}