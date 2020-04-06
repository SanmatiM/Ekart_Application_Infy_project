// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

const HOSTNAME: string = "localhost";
const PORT_NUMBER: number = 3333;
const APPLICATION_NAME: string = '/EKart_Server';
//myshec259147l
//'+PORT_NUMBER+'
export const environment = {
  production: false,
  sellerAPIUrl: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/SellerAPI',
  updateSellerAPI: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/SellerAPI',
  updateCustomerAPI: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/CustomerAPI',
  customerAPIUrl: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/CustomerAPI',
  customerCartUrl: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/CustomerCartAPI',
  SellerProductManagementAPI: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/SellerProductAPI',
  sellerOrderAPI: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/SellerOrderAPI',
  productAPIUrl: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/SellerProductAPI',
  recommendedproductAPIUrl: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/SellerRecommendationAPI',
  CustomerProductAPI: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/CustomerProductAPI',
  AddProductsAPI: 'http://' + HOSTNAME + ':' + PORT_NUMBER + APPLICATION_NAME + '/SellerRecommendationAPI'
};