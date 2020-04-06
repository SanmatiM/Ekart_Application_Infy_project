import { Product } from './product';
import { PaymentThrough } from './payment-through';
import { OrderStatus } from './order-status';

export class Orders {
    customerEmailId: string;
    orderNumber: number;
    orderId: number;
    dateOfOrder: Date;
    product: Product;
    quantity: number;
    totalPrice: number;
    orderStatus: OrderStatus;
    addressId: number;
    paymentThrough: PaymentThrough;
    dataOfDelivery: Date
}