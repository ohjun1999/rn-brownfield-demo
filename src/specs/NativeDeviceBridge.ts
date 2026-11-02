import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

/**
 * JS ↔ 네이티브 계약(Contract).
 * 빌드 시 codegen 이 이 파일을 읽어 Kotlin 추상 클래스(NativeDeviceBridgeSpec)를 생성한다.
 * 파일명은 반드시 Native 로 시작해야 codegen 대상이 된다.
 */
export type DeviceInfo = {
  manufacturer: string;
  model: string;
  osVersion: string;
  sdkInt: number;
  appVersion: string;
};

export interface Spec extends TurboModule {
  showToast(message: string, isLong: boolean): void;
  getDeviceInfo(): Promise<DeviceInfo>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('NativeDeviceBridge');
