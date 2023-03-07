package com.example.cqsexample;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AssignPickingTest {


    @Test
    void assignPicking() {
        final LocalDateTime now = LocalDateTime.now();
        LPN lpn = new LPN(1L, "A0001", 1, now, now.plusDays(2), 1L);
        LPN lpn2 = new LPN(2L, "A0001", 2, now, now.plusDays(1), 2L);
        LPN lpn3= new LPN(3L, "A0001", 1, now, now.plusDays(3), 3L);
        LPN lpn4 = new LPN(4L, "A0001", 2, now, now.plusDays(1), 1L);

        final List<LPN> lpns = List.of(lpn, lpn2);

        final List<LPN> filteredLpns = lpns.stream()
                .filter(l -> l.productSerialNumber.equals("A0001"))
                .filter(l -> l.stockQuantity > 0)
                .filter(l -> l.expiredAt.isAfter(now))
                .collect(Collectors.toList());

        final ShippingItem shippingItem = new ShippingItem(1L, "A0001", 5);
        // 만료기한이 가장 짧은것중 보관된지 가장 오래된LPN을 로케이션이 가까운 순서로 집품을 등록하고 LPN의 재고를 조정한다.



    }

    private class ShippingItem {
        private Long id;
        private String productSerialNumber;
        private int orderQuantity;
        List<Picking> pickings;

        public ShippingItem(final long id, final String productSerialNumber, final int orderQuantity) {
            this.id = id;
            this.productSerialNumber = productSerialNumber;
            this.orderQuantity = orderQuantity;
        }
    }

    private class LPN {
        private Long id;
        private String productSerialNumber;
        private int stockQuantity;
        private LocalDateTime receivedAt;
        private LocalDateTime expiredAt;
        private long locationId;

        public LPN(final long id, final String productSerialNumber, final int stockQuantity, final LocalDateTime receivedAt, final LocalDateTime expiredAt, final long locationId) {
            this.id = id;
            this.productSerialNumber = productSerialNumber;
            this.stockQuantity = stockQuantity;
            this.receivedAt = receivedAt;
            this.expiredAt = expiredAt;
            this.locationId = locationId;
        }
    }

    private class Picking {
        private Long id;
        private Long lpnId;
        private String productSerialNumber;
        private int allocatedQuantity;
    }
}
