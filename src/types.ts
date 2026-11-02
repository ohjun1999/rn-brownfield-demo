// 네이티브 OrderPropsMapper 가 만드는 Bundle 구조와 1:1 로 대응한다.
export type OrderStatus = 'PENDING' | 'SHIPPING' | 'DELIVERED';

export type Order = {
  id: string;
  productName: string;
  quantity: number;
  price: number;
  status: OrderStatus;
};

export type OrdersAppProps = {
  userName?: string;
  orders?: Order[];
};
