# Tasks

total time: 

## /products

time：

- post()

  1. return 201

     - return 201 when create product — 5

     - return uri when create product — 5

     - save and find product in product repository  — 15

       ((Optional)ProductRepository.createProduct,

       ProductDaor.save,

       ProductDao.findById)

     - return 201 when create product with specified parameter —3

  2. return 400

     - return 400 when name, description or price is null — 15 

       (Exception handling)

     ​

- get()

  1. return 200

     - return 200 when get products — 3 

     - get products in product repository — 5 

       (ProductRepository.getAllProducts,

       ProductDao.list)

     - return detail when get products — 5

       (toJason, toRefJason)

       ​

## /products/{productId}

time: 

- get()

  1. return 200

     - return 200 when get product — 3

     - find product in product repository  — 5

       ((Optional)ProductRepository.findProductById)

     - return detail when get product — 3

  2. return 404

     - return 404 when no product exists — 3

  ​

## /users

time:

- post()

  1. return 201

     - return 201 when create user — 5

     - return uri when create user — 5

     - save and find user in user repository  — 10

       ((Optional)UserRepository.createUser,

       UserDao.save,

       UserDao.findById)

     - return 201 when create user with specified parameter — 3

  2. return 400

     - return 400 when name is null — 3

       ​

## /users/{userId}

time: 

- get()

  1. return 200

     - return 200 when find user by id — 5

     - find user by id in user repository — 5

       ((Optional)UserRepository.findUserById)

     - return details when find user by id —5

  2. return 404

     - return 404 when user not exists — 3

## /users/{id}/orders

time: 

- post()

  1. return 201

     - return 201 when create order — 7

     - return uri when create order — 10

     - save and find order in user — 30

       ((Optional)User.createOrder,

       OrderDao.save,

       OrderDao.findById)

     - return 201 when create oder with specified parameter — 3

  2. return 400

     - return 400 when name, address, phone or order_items is null — 5

     ​

- get()

  1. return 200

     - return 200 when get orders — 3

     - get orders in user repository in user — 10

       (User.getAllOrders,

       OrderDao.list)

     - return detail when get orders — 5

     ​

## /users/{id}/orders/{orderId}

time:

- get()

  1. return 200

     - return 200 when find order by id— 3

     - find order by order_id in user  — 5

       ((Optional)User.findOrderById)

     - return detail when get order — 7

  2. return 404 when no order exists — 3

     ​

## /users/{id}/orders/{orderId}/payments

time:

- post()

  1. return 201

     - return 201 when create payment — 5

     - return uri when create payment — 7

     - save payment in order — 10

       ((Optional)Order.createPayment,

       PaymentDao.save,

       PaymentDao.findById)

     - return 201 when create payment with specified parameter — 3

  2. return 400

     - return 400 when pay_type or amount is null — 5

     ​

- get()

  1. return 200

     - return 200 when get payment — 3

     - find payment in order — 5

       ((Optional)Order.getPayment)

     - return details when get payment — 5

  2. return 404 when no payment exists — 3

