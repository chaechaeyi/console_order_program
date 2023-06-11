package kr.co._29cm.homework;

import kr.co._29cm.homework.constant.Operation;
import kr.co._29cm.homework.constant.ProgramStringMassage;
import kr.co._29cm.homework.domain.Item;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.dto.OrderItemDto;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.service.ItemService;
import kr.co._29cm.homework.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@Profile("!test")
@RequiredArgsConstructor
public class OrderProgramApplication implements CommandLineRunner {
    Scanner scanner = new Scanner(System.in);
    private final ItemService itemService;
    private final OrderService orderService;

    @Override
    public void run(String... args) {
        boolean run = true;
        do {
            List<OrderItemDto> orderItems = new ArrayList<>();

            System.out.print(ProgramStringMassage.ORDER_INIT.getMsg());
            String inputOperation = scanner.nextLine().trim();

            // 올바른 명령어가 아니라면
            if (!Operation.isExistOperation(inputOperation)) {
                System.out.println(ProgramStringMassage.IS_WRONG_OPERATION.getMsg());
            }

            // 종료 명령어 실행
            if (Operation.isQuitOperation(inputOperation)) {
                run = false;
            }

            // 주문 명령어 실행
            if (Operation.isOrderOperation(inputOperation)) {
                List<Item> items = itemService.getItemAll();
                System.out.println(ProgramStringMassage.ITEM_LIST_TITLE.getMsg());
                String itemFormat = "%s     %s     %s     %s";
                items.forEach((item) -> {
                    System.out.println(String.format(itemFormat, item.getId(), item.getName(), item.getPrice(),item.getStockQuantity()));
                });

                boolean doOrder = true;
                while (doOrder) {
                    System.out.print(ProgramStringMassage.INPUT_ITEM_ID.getMsg());
                    String inputItemId = scanner.nextLine();
                    String inputItemIdTrim = inputItemId.trim();

                    // 주문 요청
                    if (inputItemId.equals(" ")) {
                        try {
                            if(orderItems.isEmpty()){
                                System.out.println(ProgramStringMassage.IS_NOT_EXIST_ORDER_ITEM.getMsg());
                                continue;
                            }
                            Long orderId = orderService.createOrder(orderItems);
                            Order orderInfo = orderService.getOrder(orderId);
                            this.printOrderInfo(orderInfo);
                        } catch (SoldOutException soldOutException) {
                            System.out.println(soldOutException.getMessage());
                        } catch (Exception e) {
                            System.out.println(ProgramStringMassage.ORDER_FAIL);
                        }
                        orderItems = null;
                        doOrder = false;
                        continue;
                    }

                    // 상품 id 입력 처리
                    if (!this.checkInputItemIdAndQuantityFormat(inputItemIdTrim)) continue;
                    try {
                        boolean isExistItem = itemService.isExistItem(Long.valueOf(inputItemIdTrim));
                        if (!isExistItem) {
                            System.out.println(ProgramStringMassage.IS_NOT_EXIST_ITEM.getMsg());
                            continue;
                        }
                    } catch (Exception e) {
                        System.out.println(ProgramStringMassage.IS_NOT_EXIST_ITEM.getMsg());
                        continue;
                    }

                    // 상품 수량 입력 처리
                    System.out.print(ProgramStringMassage.INPUT_ITEM_QUANTITY.getMsg());
                    String inputOrderQuantity = scanner.nextLine().trim();
                    if (!this.checkInputItemIdAndQuantityFormat(inputOrderQuantity)) continue;
                    try {
                        orderItems.add(OrderItemDto.of(Long.valueOf(inputItemIdTrim), Integer.parseInt(inputOrderQuantity)));
                        continue;
                    } catch (Exception e) {
                        System.out.println(ProgramStringMassage.IS_WRONG_INPUT.getMsg());
                        continue;
                    }
                }
            }
        } while (run);
        System.out.println(ProgramStringMassage.ORDER_COMPLITED);
    }

    /**
     * 주문 내역 print
     * @param orderInfo
     */
    private void printOrderInfo(Order orderInfo){
        String priceFormat = "%s : %s%s";
        String orderItemFormat = "%s - %s";

        System.out.println("----------------------------------------");
        System.out.println(ProgramStringMassage.ORDER_INFO_TITLE.getMsg());
        orderInfo.getOrderItems().forEach((orderItem) -> {
            System.out.println(String.format(orderItemFormat, orderItem.getItem().getName(), orderItem.getOrderQuantity())) ;
        });
        System.out.println("----------------------------------------");
        System.out.println(String.format(priceFormat, ProgramStringMassage.ORDER_PRICE.getMsg(), orderInfo.getTotalPrice(), ProgramStringMassage.KOREA_MONEY_UNIT.getMsg()));
        if(orderInfo.getDeliveryPrice() > 0) {
            System.out.println(String.format(priceFormat, ProgramStringMassage.DELIVERY_PRICE.getMsg(), orderInfo.getDeliveryPrice(), ProgramStringMassage.KOREA_MONEY_UNIT.getMsg()));
        }
        System.out.println("----------------------------------------");
        System.out.println(String.format(priceFormat, ProgramStringMassage.PAY_PRICE.getMsg(), orderInfo.getPayPrice(), ProgramStringMassage.KOREA_MONEY_UNIT.getMsg()));
        System.out.println("----------------------------------------");
    }

    /**
     * 상품 id, 수량 입력 값 포맷 체크
     * @param inputString
     * @return
     */
    private boolean checkInputItemIdAndQuantityFormat(String inputString){
        if(inputString.isEmpty() || !inputString.matches("^[1-9][0-9]*$")) {
            System.out.println(ProgramStringMassage.IS_WRONG_INPUT.getMsg());
            return false;
        }
        return true;
    }

}
