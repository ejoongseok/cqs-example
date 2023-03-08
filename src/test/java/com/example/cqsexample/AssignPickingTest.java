package com.example.cqsexample;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AssignPickingTest {


    @Test
    void assignPicking() {
        final ShippingItem shippingItem = new ShippingItem("쌀1kg", 5);

        final LocalDateTime now = LocalDateTime.now();
        final LPN lpn4 = new LPN(4L, "쌀1kg", 2, now.minusDays(2), now.plusDays(1));
        final LPN lpn2 = new LPN(2L, "쌀1kg", 2, now.minusDays(1), now.plusDays(1));
        final LPN lpn = new LPN(1L, "쌀1kg", 1, now.minusDays(1), now.plusDays(2));
        final LPN lpn3 = new LPN(3L, "쌀1kg", 1, now.minusDays(1), now.plusDays(3));

        final List<LPN> lpns = List.of(lpn, lpn2, lpn3, lpn4);

        // 만료기한이 가장 짧게 남은것중 보관된지 가장 오래된 LPN을 순서로 해당출고에 집품을 배정하면서 LPN의 재고를 조정한다.
        // 만료기한이 짧은 순서 4, 2, 1, 3
        // 보관된지 가장 오래된 순서 4, 2, 1, 3
        // 4,2,1의 재고를 차감해야함.
        final List<LPN> filteredLpns = lpns.stream()
                .filter(l -> l.getProductSerialNumber().equals(shippingItem.getProductSerialNumber()))
                .filter(l -> 0 < l.getStockQuantity())
                .filter(l -> l.getExpiredAt().isAfter(now))
                .collect(Collectors.toList());

        final int availableQuantity = filteredLpns.stream().mapToInt(l -> l.getStockQuantity()).sum();

        if (availableQuantity < shippingItem.getOrderQuantity()) {
            throw new RuntimeException("재고가 부족합니다.");
        }

        final List<LPN> sortedLpns = filteredLpns.stream()
                .sorted(Comparator.comparing(LPN::getExpiredAt)
                        .thenComparing(LPN::getReceivedAt))
                .collect(Collectors.toList());

        final List<Picking> pickings = getPickings(shippingItem, sortedLpns);

        System.out.println("pickings = " + pickings);
        System.out.println("sotredLpns = " + sortedLpns);

        final List<Picking> pickings2 = getPickings(shippingItem, sortedLpns);
        System.out.println("pickings2 = " + pickings2);
        System.out.println("sotredLpns = " + sortedLpns);


    }

    private List<Picking> getPickings(final ShippingItem shippingItem, final List<LPN> sortedLpns) {
        int remainQuantity = shippingItem.getOrderQuantity(); // 집품을 배정해야할 남은수량
        final LPN firstLPN = sortedLpns.get(0);
        if (firstLPN.getStockQuantity() >= remainQuantity) {
            firstLPN.decreaseQuantity(remainQuantity);
            shippingItem.assignPicking(new Picking(firstLPN.getId(), remainQuantity));
        } else {
            for (final LPN lpn1 : sortedLpns) {
                final int quantityToAllocate = Math.min(lpn1.getStockQuantity(), remainQuantity);
                lpn1.decreaseQuantity(quantityToAllocate);
                remainQuantity -= quantityToAllocate;
                shippingItem.assignPicking(new Picking(lpn1.getId(), quantityToAllocate));
                if (0 == remainQuantity) {
                    break;
                }
            }
        }
        return shippingItem.getPickings();
    }


}
