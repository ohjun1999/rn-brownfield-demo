import React, { useState } from 'react';
import {
  FlatList,
  Pressable,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import DeviceBridge, { type DeviceInfo } from './specs/NativeDeviceBridge';
import type { Order, OrderStatus, OrdersAppProps } from './types';

const STATUS_LABEL: Record<OrderStatus, string> = {
  PENDING: '결제 완료',
  SHIPPING: '배송 중',
  DELIVERED: '배송 완료',
};

const won = (n: number) => `${n.toLocaleString('ko-KR')}원`;

// 네이티브가 넘긴 initialProps 가 그대로 루트 컴포넌트의 props 로 들어온다.
export default function OrdersApp({ userName = '고객', orders = [] }: OrdersAppProps) {
  const [deviceInfo, setDeviceInfo] = useState<DeviceInfo | null>(null);
  const total = orders.reduce((sum, o) => sum + o.price * o.quantity, 0);

  const onToast = () => {
    DeviceBridge.showToast(`${userName}님, 총 ${won(total)} 주문을 확인했어요`, false);
  };

  const onDeviceInfo = async () => {
    try {
      setDeviceInfo(await DeviceBridge.getDeviceInfo());
    } catch (e) {
      DeviceBridge.showToast(`기기 정보 조회 실패: ${String(e)}`, true);
    }
  };

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

      {deviceInfo && (
        <View style={styles.infoBox}>
          <Text style={styles.infoTitle}>기기 정보 (네이티브 모듈)</Text>
          <Text>
            {deviceInfo.manufacturer} {deviceInfo.model}
          </Text>
          <Text>
            Android {deviceInfo.osVersion} (API {deviceInfo.sdkInt}) · 앱 v{deviceInfo.appVersion}
          </Text>
        </View>
      )}

      <View style={styles.buttons}>
        <Button label="네이티브 토스트" onPress={onToast} />
        <Button label="기기 정보 가져오기" onPress={onDeviceInfo} />
      </View>
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

function Button({ label, onPress }: { label: string; onPress: () => void }) {
  return (
    <Pressable
      onPress={onPress}
      style={({ pressed }) => [styles.button, pressed && styles.buttonPressed]}>
      <Text style={styles.buttonText}>{label}</Text>
    </Pressable>
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
  infoBox: { backgroundColor: '#E0F7F3', borderRadius: 12, padding: 12, marginTop: 12, gap: 2 },
  infoTitle: { fontWeight: '700', marginBottom: 4 },
  // edgeToEdgeEnabled=true 라 시스템 내비게이션 바 영역만큼 여백을 둔다.
  // 실서비스라면 react-native-safe-area-context 의 insets 를 쓰는 것이 정확하다.
  buttons: { flexDirection: 'row', gap: 8, marginTop: 12, marginBottom: 56 },
  button: { flex: 1, backgroundColor: '#3D5AFE', borderRadius: 12, paddingVertical: 14, alignItems: 'center' },
  buttonPressed: { opacity: 0.7 },
  buttonText: { color: 'white', fontWeight: '600' },
});
