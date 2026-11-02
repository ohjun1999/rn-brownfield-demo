import { AppRegistry } from 'react-native';
import OrdersApp from './src/OrdersApp';
import { name as appName } from './app.json';

// 네이티브 OrdersReactActivity.getMainComponentName() 과 같은 이름("OrdersApp")으로 등록한다.
AppRegistry.registerComponent(appName, () => OrdersApp);
