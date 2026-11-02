import React from 'react';
import { FlatList, StyleSheet, Text, View } from 'react-native';
import type { Order, OrderStatus, OrdersAppProps } from './types';

const STATUS_LABEL: Record<OrderStatus, string> = {
  PENDING: '결제 완료',
  SHIPPING: '배송 중',
  DELIVERED: '배송 완료',
};

const won = (n: number) => `${n.toLocaleString('ko-KR')}원`;

// 네이티브가 넘긴 initialProps 가 그대로 루트 컴포넌트의 props 로 들어온다.
export default function OrdersApp({ userName = '고객', orders = [] }: OrdersAppProps) {
  const total = orders.reduce((sum, o) => sum + o.price * o.quantity, 0);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>React Native 주문 상세</Text>
      <Text style={styles.subtitle}>
        {userName}님의 주문 {orders.length}건 · 합계 {won(total)}
      </Text>

      <FlatList
        data={orders}
        keyExtractor={item => item.id}
        renderItem={({ item }) => <OrderItem order={item} />}
        ItemSeparatorComponent={() => <View style={styles.separator} />}
        ListEmptyComponent={<Text>네이티브에서 전달된 주문이 없습니다.</Text>}
        style={styles.list}
      />
    </View>
  );
}

function OrderItem({ order }: { order: Order }) {
  return (
    <View style={styles.item}>
      <View style={styles.itemHeader}>
        <Text style={styles.itemName}>{order.productName}</Text>
        <Text style={styles.badge}>{STATUS_LABEL[order.status]}</Text>
      </View>
      <Text style={styles.itemMeta}>
        {order.id} · {won(order.price)} × {order.quantity} = {won(order.price * order.quantity)}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, padding: 16, paddingTop: 48, backgroundColor: '#F5F6FA' },
  title: { fontSize: 22, fontWeight: '700', color: '#1A1A2E' },
  subtitle: { marginTop: 4, marginBottom: 12, color: '#555' },
  list: { flex: 1 },
  separator: { height: 8 },
  item: { backgroundColor: 'white', borderRadius: 12, padding: 12 },
  itemHeader: { flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center' },
  itemName: { fontSize: 16, fontWeight: '600' },
  itemMeta: { marginTop: 4, color: '#666', fontSize: 13 },
  badge: {
    fontSize: 12,
    color: '#3D5AFE',
    backgroundColor: '#E8EBFF',
    paddingHorizontal: 8,
    paddingVertical: 2,
    borderRadius: 8,
    overflow: 'hidden',
  },
});
